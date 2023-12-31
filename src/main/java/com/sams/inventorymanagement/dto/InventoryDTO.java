package com.sams.inventorymanagement.dto;

import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.entities.Item;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class InventoryDTO {
    private Long id;
    private Item item;
    private StoreDTO store;
    private Integer quantity;
    private Integer threshold;
    private LocalDate lastUpdated;

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setStore(StoreDTO store) {
        this.store = store;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public InventoryDTO(Long id, Item item, StoreDTO store, Integer quantity, Integer threshold, LocalDate lastUpdated) {
        this.id = id;
        this.item = item;
        this.store = store;
        this.quantity = quantity;
        this.threshold = threshold;
        this.lastUpdated = lastUpdated;
    }

    public static InventoryDTO fromInventory(Inventory inventory) {
        return new InventoryDTO(
                inventory.getId(),
                inventory.getItem(),
                StoreDTO.fromStore(inventory.getStore()),
                inventory.getQuantity(),
                inventory.getThreshold(),
                inventory.getLastUpdated());
    }
}
