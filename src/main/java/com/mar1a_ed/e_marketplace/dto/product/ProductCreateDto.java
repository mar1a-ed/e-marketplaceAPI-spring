package com.mar1a_ed.e_marketplace.dto.product;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.mar1a_ed.e_marketplace.model.enums.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductCreateDto {

    @NotBlank
    @Size(min = 6, max = 6)
    private String code;

    @NotBlank
    @Size(min = 5, max = 200)
    private String name;

    @NotBlank
    @Size(min = 4, max = 50)
    private Category category = Category.UNKNOWN;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @DecimalMin(value = "0.00", message = "Negative values are not allowed.")
    @DecimalMax(value = "9999999999.99")
    private BigDecimal currentPrice;

    @NotNull
    private Integer stockQuantity;
}
