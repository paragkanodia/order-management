package com.intuit.order.mappers;

import com.intuit.appUtility.dto.request.CreateOrderRequestDTO;
import com.intuit.appUtility.dto.response.OrderResponseDTO;
import com.intuit.appUtility.enums.OrderStatus;
import com.intuit.appUtility.exception.MapstructMappingException;
import com.intuit.commons.restClients.productService.response.PriceQuoteResponseDTO;
import com.intuit.order.repository.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", unexpectedValueMappingException = MapstructMappingException.class)
public interface OrderMapper {

    @Mapping(source = "createOrderRequestDTO.productCode", target = "productCode")
    Order toOrderModel(CreateOrderRequestDTO createOrderRequestDTO, PriceQuoteResponseDTO priceQuoteResponseDTO, OrderStatus status);

    @Mapping(source = "order.id", target = "orderId")
    OrderResponseDTO toOrderResponseDTOFromOrder(Order order);
}
