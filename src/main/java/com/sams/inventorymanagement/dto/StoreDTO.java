package com.sams.inventorymanagement.dto;

import lombok.Getter;

@Getter
public class StoreDTO {
    // Getters and setters (if needed)
    private Long id;
    private String name;

    // Constructor
    public StoreDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
