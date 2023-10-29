package com.sams.inventorymanagement.dto;

import com.sams.inventorymanagement.entities.Store;
import lombok.Getter;

@Getter
public class StoreDTO {
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

    public static StoreDTO fromStore(Store store) {
        return new StoreDTO(store.getId(), store.getName());
    }
}
