package com.sams.inventorymanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInRequest {
    @NotBlank(message = "Username is required")
    @Email(message = "Invalid email format")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
