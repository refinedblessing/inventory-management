package com.sams.inventorymanagement.dto;

import lombok.Data;

@Data
public class LogInRequest {
    private String username;
    private String password;
}
