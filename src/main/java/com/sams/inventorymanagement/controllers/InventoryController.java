package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.InventoryService;
import com.sams.inventorymanagement.services.InventoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {
    @Autowired
    private InventoryServiceImpl inventoryService;

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable Long id) {
        Inventory inventory = inventoryService.getInventoryById(id);

        if(inventory == null)
            throw new EntityNotFoundException("id: " + id);

        return inventory;
    }

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryService.getAllInventories();
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@Valid @RequestBody Inventory inventory) {
        Inventory savedInventory = inventoryService.createInventory(inventory);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedInventory.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable Long id, @Valid @RequestBody Inventory updatedInventory) {
        return inventoryService.updateInventory(id, updatedInventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
    }
    
    @GetMapping("/threshold")
    public List<Inventory> getAllInventoriesAtThreshold() {
        return inventoryService.findInventoriesAtThreshold();
    }
}
