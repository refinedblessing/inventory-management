package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Inventory;

import java.util.List;

public interface InventoryService {
    Inventory createInventory(Inventory inventory);

    void deleteInventory(Long id);

    List<Inventory> getAllInventories();

    Inventory updateInventory(Long id, Inventory updatedInventory);

    List<Inventory> getAllInventoriesAtThreshold();

    Inventory getInventoryById(Long id);
}
