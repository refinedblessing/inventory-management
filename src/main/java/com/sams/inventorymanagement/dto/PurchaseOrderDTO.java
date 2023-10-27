package com.sams.inventorymanagement.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PurchaseOrderDTO {
    private UUID id;

    public PurchaseOrderDTO(UUID id) {
        this.id = id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
