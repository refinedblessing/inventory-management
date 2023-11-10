package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.InventoryDTO;
import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing inventory-related operations.
 */
@RestController
@RequestMapping("/api/inventories")
public class InventoryController extends BaseController {

    /**
     * Service for handling inventory-related operations.
     */
    @Autowired
    private InventoryService inventoryService;

    /**
     * Retrieve inventory by its unique identifier.
     *
     * @param id The ID of the inventory to retrieve.
     * @return The inventory with the specified ID.
     * @throws EntityNotFoundException If the inventory with the given ID does not exist.
     */
    @GetMapping("/{id}")
    public InventoryDTO getInventoryById(@PathVariable Long id) {
        return InventoryDTO.fromInventory(inventoryService.getInventoryById(id));
    }

    /**
     * Get a list of all inventories.
     *
     * @return A list of all inventories.
     */
    @GetMapping
    public List<InventoryDTO> getAllInventories() {
        List<Inventory> inventories = inventoryService.getAllInventories();
        return inventories.stream().map(InventoryDTO::fromInventory).collect(Collectors.toList());
    }

    /**
     * Update an existing inventory.
     *
     * @param id             The ID of the inventory to update.
     * @param updatedInventory The updated inventory data.
     * @return The updated inventory.
     */
    @PutMapping("/{id}")
    public InventoryDTO updateInventory(@PathVariable Long id, @Valid @RequestBody Inventory updatedInventory) {
        Inventory inventory = inventoryService.getInventoryById(id);

        if (isNotStoreStaff(inventory.getStore())) {
            throw new AccessDeniedException("Inventory's Store not available to this user");
        }

        return InventoryDTO.fromInventory(inventoryService.updateInventory(id, updatedInventory));
    }

    /**
     * Delete an inventory by its ID.
     *
     * @param id The ID of the inventory to delete.
     *
     */
    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);

        if (isNotStoreStaff(inventory.getStore())) {
            throw new AccessDeniedException("Inventory's Store not available to this user");
        }

        inventoryService.deleteInventory(id);
    }

    /**
     * Get a list of all inventories at their threshold levels.
     *
     * @return A list of inventories that are at or below their defined threshold levels.
     */
    @GetMapping("/threshold")
    public List<InventoryDTO> getAllInventoriesAtThreshold() {
        List<Inventory> inventories = inventoryService.findInventoriesAtThreshold();
        return inventories.stream().map(InventoryDTO::fromInventory).collect(Collectors.toList());
    }
}
