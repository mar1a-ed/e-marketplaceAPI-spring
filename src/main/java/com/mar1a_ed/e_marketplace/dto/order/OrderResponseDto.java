package com.mar1a_ed.e_marketplace.dto.order;

import com.mar1a_ed.e_marketplace.dto.orderItem.OrderItemCreateDto;
import com.mar1a_ed.e_marketplace.dto.orderItem.OrderItemResponseDto;
import com.mar1a_ed.e_marketplace.model.enums.Status;
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

    private BigDecimal totalPrice;

    private List<OrderItemResponseDto> itemsOrder;
}
