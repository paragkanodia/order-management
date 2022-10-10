package com.intuit.commons.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public abstract class BaseRequestDTO {

    private String requestId;

    public BaseRequestDTO(String requestId) {
        super();
        this.requestId = requestId;
    }
}
