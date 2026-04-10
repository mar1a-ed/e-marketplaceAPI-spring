package com.mar1a_ed.e_marketplace.dto.clients;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @NotNull
    @Size(min = 11, max = 11, message = "Only digits")
    @CPF
    private String cpf;

    @NotNull
    @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "The format of 'cep' must be '00000-000'")
    private String cep;

    @NotBlank
    private String city;

    @NotBlank
    @Pattern(regexp = "[A-Z]{2}", message = "The format of 'state' must be 'XX'.")
    private String state;

    @NotBlank
    private String address;

    @NotBlank
    private Integer number;

}
