package com.mar1a_ed.e_marketplace.dto.clients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientResponseDto {

    private Long id;

    private String name;

    private String cpf;
}
