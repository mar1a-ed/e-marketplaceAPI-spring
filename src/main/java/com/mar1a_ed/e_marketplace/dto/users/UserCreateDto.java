package com.mar1a_ed.e_marketplace.dto.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDto {

    @NotBlank
    @Email(message = "Enter a valid e-mail format.")
    @Size(min = 13, max = 100)
    private String username;

    @NotBlank
    @Size(min = 8, max = 20, message = "The password must contain at least 8 characters.")
    private String password;
}

