package com.mar1a_ed.e_marketplace.dto.order;

import com.mar1a_ed.e_marketplace.dto.orderItem.OrderItemResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponseDto {

    private Long clientId;

    private String status;

    private List<OrderItemResponseDto> itemsOrder;

    private BigDecimal totalPrice;
}
