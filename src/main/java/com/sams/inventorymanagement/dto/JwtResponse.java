package com.sams.inventorymanagement.dto;

import com.sams.inventorymanagement.entities.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private AppUser user;
}
