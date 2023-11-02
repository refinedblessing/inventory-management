package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.InventoryDTO;
import com.sams.inventorymanagement.entities.AppUser;
import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.StoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
        AppUser user = getCurrentUser();
        boolean hasSearchCriteria = name != null || address != null || type != null || openingDate != null;

        if (hasSearchCriteria) {
            //        TODO remove after fully implementing add store to user on frontend
            if (isAdmin()) {
                return storeService.searchStoresByCriteria(name, address, type, openingDate);
            }

            return storeService.searchStoresByCriteriaForUser(name,address, type, openingDate, user.getId());
        } else {
            if (isAdmin()) {
                return storeService.getAllStores();
            }

            return new ArrayList<>(user.getStores());
        }
    }

    @GetMapping
    public List<Store> getAllStores() {
        AppUser user = getCurrentUser();

//        TODO remove after fully implementing add store to user on frontend
        if (isAdmin()) {
            return storeService.getAllStores();
        }

        return new ArrayList<>(user.getStores());
    }

//    TODO update other methods to only send store info associated with current User
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
    @Secured("ROLE_ADMIN")
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
    @Secured("ROLE_ADMIN")
    @PutMapping("/{id}")
    public Store updateStore(@PathVariable Long id, @Valid @RequestBody Store updatedStore) {
        return storeService.updateStore(id, updatedStore);
    }

    /**
     * Delete a store by its unique identifier.
     *
     * @param id The ID of the store to delete.
     */
    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public void deleteStore(@PathVariable Long id) {
        storeService.deleteStore(id);
    }
}
