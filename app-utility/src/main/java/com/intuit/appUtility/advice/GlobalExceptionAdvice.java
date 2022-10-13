package com.intuit.appUtility.advice;

import com.intuit.commons.dto.ErrorResponseDTO;
import com.intuit.commons.exception.*;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(getErrorResponseDTO(HttpStatus.BAD_REQUEST,
                new Exception(ex.getMessage()), null, ExceptionCodes.V101, null), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        (error) -> {
                            String fieldName;
                            try {
                                fieldName = ((FieldError) error).getField();
                            } catch (Exception e) {
                                fieldName = error.getCode();
                            }
                            String errorMessage = error.getDefaultMessage();
                            errors.put(fieldName, errorMessage);
                        });

        String errorString = CollectionUtils.isEmpty(errors) ? "Validation error occurred" : errors.toString();
        return new ResponseEntity<>(getErrorResponseDTO(HttpStatus.BAD_REQUEST,
                new Exception(errorString), null, ExceptionCodes.V101, errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolationException(
            ConstraintViolationException exception, WebRequest request) {
        return getErrorResponseResponseEntity(HttpStatus.BAD_REQUEST, exception, null, ExceptionCodes.V102, null);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ErrorResponseDTO> handleAccessDeniedException(
            AccessDeniedException exception, WebRequest request) {
        return getErrorResponseResponseEntity(HttpStatus.UNAUTHORIZED, exception, null, exception.getExceptionCode(), null);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ErrorResponseDTO> handleValidationException(ServiceException exception, WebRequest request) {
        return getErrorResponseResponseEntity(HttpStatus.BAD_REQUEST, exception, null, exception.getExceptionCode(), null);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest request) {
        return getErrorResponseResponseEntity(HttpStatus.BAD_REQUEST, (Exception) exception.getCause().getCause(), null, ExceptionCodes.V101, null);
    }

    @ExceptionHandler({CallNotPermittedException.class})
    public ResponseEntity<ErrorResponseDTO> handleCallNotPermittedException(CallNotPermittedException callNotPermittedException) {
        return getErrorResponseResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, callNotPermittedException, null, ExceptionCodes.R101,
                Collections.singletonMap("CIRCUIT_BREAKER_NAME", callNotPermittedException.getCausingCircuitBreakerName()));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponseDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return getErrorResponseResponseEntity(HttpStatus.BAD_REQUEST,
                new RuntimeException(String.format("Invalid input parameter %s", exception.getValue())), null, ExceptionCodes.U101, null);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDTO> handleUnknownException(
            Exception exception, WebRequest request) {
        return getErrorResponseResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception, null, ExceptionCodes.U100, null);
    }

    @ExceptionHandler({RemoteServiceException.class})
    public ResponseEntity<ErrorResponseDTO> handleRemoteServiceException(RemoteServiceException exception, WebRequest request) {
        return getErrorResponseResponseEntity(HttpStatus.EXPECTATION_FAILED, exception,
                exception.getApplication(), exception.getExceptionCode(), null);
    }

    @ExceptionHandler({ServiceException.class})
    public ResponseEntity<ErrorResponseDTO> handleServiceException(ServiceException exception, WebRequest request) {
        return getErrorResponseResponseEntity(HttpStatus.EXPECTATION_FAILED, exception, null, exception.getExceptionCode(), null);
    }

    private ResponseEntity<ErrorResponseDTO> getErrorResponseResponseEntity(HttpStatus errorCode, Exception exception,
                                                                            String source, ExceptionCode exceptionCode, Map<String, Object> details) {
        return new ResponseEntity<>(getErrorResponseDTO(errorCode, exception, source, exceptionCode, details), errorCode);
    }

    private ErrorResponseDTO getErrorResponseDTO(HttpStatus errorCode, Exception exception,
                                                 String source,
                                                 ExceptionCode exceptionCode, Map<String, Object> details) {
        String errorId = UUID.randomUUID().toString();
        ErrorResponseDTO errorResponseDTO = ErrorResponseDTO.builder()
                .exceptionId(errorId)
                .exception(exception.getMessage())
                .exceptionCode(exceptionCode.getCode())
                .details(details)
                .source(source)
                .application("orderManagementApp")
                .build();
        errorResponseDTO.setStatusCode(errorCode.value());
        errorResponseDTO.setStatusMessage("Error Occurred");
        return errorResponseDTO;
    }
}
