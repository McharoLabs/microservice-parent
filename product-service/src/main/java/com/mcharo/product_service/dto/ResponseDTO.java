package com.mcharo.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class ResponseDTO {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
