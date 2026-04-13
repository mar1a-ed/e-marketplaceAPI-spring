package com.mar1a_ed.e_marketplace.dto.user;

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
    @Email(message = "Insert a valid format of email.")
    private String username;

    @NotBlank
    @Size(min = 8, max = 20, message = "The password must be at least 8 characters")
    private String password;
}
