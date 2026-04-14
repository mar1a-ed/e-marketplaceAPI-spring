package com.mar1a_ed.e_marketplace.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDto {

    private Long id;

    private String code;

    private String name;

    private String category;

    private BigDecimal currentPrice;

    private Integer stockQuantity;
}
