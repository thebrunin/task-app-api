package com.task.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(
        @Email(message = "User e-mail should be valid")
        @NotBlank(message = "User e-mail cannot be blank")
        String email,

        @NotBlank(message = "User password cannot be blank")
        @Size(min = 6, message = "User password must be at least 6 characters long")
        String password,

        @NotBlank(message = "User name cannot be blank")
        String name
) {
}
