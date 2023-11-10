package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.InventoryDTO;
import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing stores.
 */
@RestController
@RequestMapping("/api/stores")
public class StoreController extends BaseController {

    /**
     * Service for handling store-related operations.
     */
    @Autowired
    private StoreService storeService;


    /**
     * Search for stores based on various criteria.
     *
     * @param name         The name of the store to search for.
     * @param address      The address of the store to search for.
     * @param type    The type of store to search for.
     * @param openingDate  The opening date of the store to search for.
     * @return A list of stores matching the specified criteria.
     */
    @GetMapping("/search")
    public List<Store> searchStores(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) StoreType type,
            @RequestParam(required = false) LocalDate openingDate
    ) {
        boolean hasSearchCriteria = name != null || address != null || type != null || openingDate != null;

        if (hasSearchCriteria) {
            return storeService.searchStoresByCriteria(name, address, type, openingDate);
        } else {
            return storeService.getAllStores();
        }
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

//    @GetMapping
//    public List<Store> getAllUserStores() {
//        return storeService.getAllStores();
//    }

    /**
     * Retrieve a store by its unique identifier.
     *
     * @param id The ID of the store to retrieve.
     * @return The store with the specified ID.
     */
    @GetMapping("/{id}")
    public Store getStoreById(@PathVariable Long id) {
        Store store = storeService.getStoreById(id);

        if (store == null)
            throw new EntityNotFoundException("id: " + id);

        return store;
    }

    @GetMapping("/{storeId}/inventories")
    public List<InventoryDTO> getInventoriesByStoreId(@PathVariable Long storeId) {
        List<Inventory> inventories = storeService.getInventoriesByStoreId(storeId);

        return inventories.stream().map(InventoryDTO::fromInventory).collect(Collectors.toList());
    }

    /**
     * Create a new store.
     *
     * @param store The store to create.
     * @return The created store.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Store> createStore(@Valid @RequestBody Store store) {
        return new ResponseEntity<>(storeService.createStore(store), HttpStatus.OK);
    }

    /**
     * Update an existing store.
     *
     * @param id           The ID of the store to update.
     * @param updatedStore The updated store information.
     * @return The updated store.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Store> updateStore(@PathVariable Long id, @Valid @RequestBody Store updatedStore) {
        return new ResponseEntity<>(storeService.updateStore(id, updatedStore), HttpStatus.OK);
    }

    /**
     * Delete a store by its unique identifier.
     *
     * @param id The ID of the store to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
        return null;
    }
}
