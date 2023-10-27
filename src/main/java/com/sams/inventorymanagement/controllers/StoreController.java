package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller for managing stores.
 */
@RestController
@RequestMapping("/api/stores")
public class StoreController {

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
     * @param storeType    The type of store to search for.
     * @param openingDate  The opening date of the store to search for.
     * @return A list of stores matching the specified criteria.
     */
    @GetMapping("/search")
    public List<Store> searchStores(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) StoreType storeType,
            @RequestParam(required = false) LocalDate openingDate
    ) {
        return storeService.searchStoresByCriteria(name, address, storeType, openingDate);
    }

    @GetMapping
    public List<Store> getAllStores() {
        return storeService.getAllStores();
    }

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

    /**
     * Create a new store.
     *
     * @param store The store to create.
     * @return The created store.
     */
    @PostMapping
    public Store createStore(@Valid @RequestBody Store store) {
        return storeService.createStore(store);
    }

    /**
     * Update an existing store.
     *
     * @param id           The ID of the store to update.
     * @param updatedStore The updated store information.
     * @return The updated store.
     */
    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @Valid @RequestBody Store updatedStore) {
        return storeService.updateStore(id, updatedStore);
    }

    /**
     * Delete a store by its unique identifier.
     *
     * @param id The ID of the store to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
