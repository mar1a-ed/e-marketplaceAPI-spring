package com.mar1a_ed.e_marketplace.dto.orderItem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemResponseDto {

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private BigDecimal subtotal;
}
