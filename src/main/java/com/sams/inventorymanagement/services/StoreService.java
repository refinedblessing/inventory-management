package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing stores.
 */
public interface StoreService {
    //        TODO update all methods after fully implementing add store to user on frontend, so that only stores associated with the current user is returned
    /**
     * Search for stores based on various criteria.
     *
     * @param name        The name to search for (partial match).
     * @param address     The address to search for (partial match).
     * @param type   The type of store to filter by.
     * @param openingDate The opening date to filter by.
     * @return A list of stores that match the specified criteria.
     */
    List<Store> searchStoresByCriteria(
            String name,
            String address,
            StoreType type,
            LocalDate openingDate
    );

    List<Store> searchStoresByCriteriaForUser(String name, String address, StoreType type, LocalDate openingDate, Long userId);

    /**
     * Get a specific store by its ID.
     *
     * @param id The ID of the store.
     * @return The store with the specified ID.
     */
    Store getStoreById(Long id);

    /**
     * Get a store by its name.
     *
     * @param name The name of the store to retrieve.
     * @return The store with the specified name.
     */
    Store getStoreByName(String name);

    /**
     * Create a new store.
     *
     * @param store The store to be created.
     * @return The created store.
     */
    Store createStore(Store store);

    /**
     * Delete a store by its ID.
     *
     * @param id The ID of the store to be deleted.
     */
    void deleteStore(Long id);

    /**
     * Update an existing store by its ID.
     *
     * @param id           The ID of the store to be updated.
     * @param updatedStore The updated store data.
     * @return The updated store.
     */
    Store updateStore(Long id, Store updatedStore);

    /**
     * Get a list of all stores in the system.
     *
     * @return A list of all stores.
     */
    List<Store> getAllStores();

    List<Inventory> getInventoriesByStoreId(Long storeId);
}
