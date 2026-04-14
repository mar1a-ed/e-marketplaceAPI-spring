package com.mar1a_ed.e_marketplace.dto.orderItem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemCreateDto {

    @NotNull
    private Long productId;

    @NotNull
    @Size(min = 1, message = "Select quantity.")
    private Integer quantity;
}
