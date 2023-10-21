package com.sams.inventorymanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LogInRequest {
    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
