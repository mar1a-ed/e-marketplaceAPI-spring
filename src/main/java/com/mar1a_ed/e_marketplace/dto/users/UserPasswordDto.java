package com.mar1a_ed.e_marketplace.dto.users;

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
public class UserPasswordDto {

    @NotNull
    @Size(min = 8, max = 20, message = "The password must contain at least 8 characters.")
    private String currentPassword;

    @NotNull
    @Size(min = 8, max = 20, message = "The password must contain at least 8 characters.")
    private String newPassword;

    @NotNull
    @Size(min = 8, max = 20, message = "The password must contain at least 8 characters.")
    private String confirmPassword;
}
