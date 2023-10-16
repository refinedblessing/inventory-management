package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl {
    @Autowired
    private InventoryRepository inventoryRepository;
    
    //@Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    //@Override
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    //@Override
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    //@Override
    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        if (inventoryRepository.existsById(id)) {
            updatedInventory.setId(id); // Ensure the ID is set
            return inventoryRepository.save(updatedInventory);
        } else {
            return null; // Return null if the item with the specified ID does not exist
        }
    }

    //@Override
    public List<Inventory> findInventoriesAtThreshold() {
        return inventoryRepository.findAllWhereQuantityLessThanOrEqualThreshold();
    }

    //@Override
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }
}
