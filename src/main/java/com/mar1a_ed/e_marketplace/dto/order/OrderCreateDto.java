package com.mar1a_ed.e_marketplace.dto.order;

import com.mar1a_ed.e_marketplace.dto.orderItem.OrderItemCreateDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderCreateDto {

    @NotNull
    private Long clientId;

    @NotBlank
    private List<OrderItemCreateDto> itemsOrder;
}
