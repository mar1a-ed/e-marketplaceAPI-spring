package com.mar1a_ed.e_marketplace.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientCreateDto {

    @NotBlank
    @Size(min = 5, max = 200, message = "Enter a valid name")
    private String name;

    @CPF
    private String cpf;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÿ0-9\\\\s.,-]+$")
    private String address;
}
