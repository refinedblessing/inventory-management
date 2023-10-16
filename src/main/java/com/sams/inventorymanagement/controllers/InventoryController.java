package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.InventoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller for managing inventory-related operations.
 */
@RestController
@RequestMapping("/inventories")
public class InventoryController {

    /**
     * Service for handling inventory-related operations.
     */
    @Autowired
    private InventoryServiceImpl inventoryService;

    /**
     * Retrieve inventory by its unique identifier.
     *
     * @param id The ID of the inventory to retrieve.
     * @return The inventory with the specified ID.
     * @throws EntityNotFoundException If the inventory with the given ID does not exist.
     */
    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);

        if(inventory == null)
            throw new EntityNotFoundException("id: " + id);

        return inventory;
    }

    /**
     * Get a list of all inventories.
     *
     * @return A list of all inventories.
     */
    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    /**
     * Create a new inventory.
     *
     * @param inventory The inventory to create.
     * @return The created inventory.
     */
    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.createInventory(inventory);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedInventory.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Update an existing inventory.
     *
     * @param id             The ID of the inventory to update.
     * @param updatedInventory The updated inventory data.
     * @return The updated inventory.
     */
    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @Valid @RequestBody Inventory updatedInventory) {
        return inventoryService.updateInventory(id, updatedInventory);
    }

    /**
     * Delete an inventory by its ID.
     *
     * @param id The ID of the inventory to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }

    /**
     * Get a list of all inventories at their threshold levels.
     *
     * @return A list of inventories that are at or below their defined threshold levels.
     */
    @GetMapping("/threshold")
    public List<Inventory> getAllInventoriesAtThreshold() {
        return inventoryService.findInventoriesAtThreshold();
    }
}
