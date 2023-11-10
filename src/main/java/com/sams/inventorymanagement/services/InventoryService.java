package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.repositories.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service implementation for managing inventories of items in stores.
 */
@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Creates a new inventory record for an item in a store.
     *
     * @param inventory The inventory to create.
     * @return The created inventory.
     */
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    /**
     * Deletes an inventory record by its unique identifier.
     *
     * @param id The ID of the inventory to delete.
     */
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    /**
     * Retrieves a list of all inventory records.
     *
     * @return A list of all inventory records.
     */
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    /**
     * Updates an existing inventory record with new data
     * we can only reduce quantity and update threshold
     * @param id             The ID of the inventory to update.
     * @param updatedInventory The updated inventory data.
     * @return The updated inventory or null if the specified inventory does not exist.
     */
    public Inventory updateInventory(Long id, Inventory updatedInventory) {
        Inventory inventory = inventoryRepository.findById(id).orElse(null);
        if (inventory == null) return null;

        inventory.setThreshold(updatedInventory.getThreshold());

//        Allow for only quantity reduction
        Integer oldQuantity = inventory.getQuantity();
        Integer newQuantity = updatedInventory.getQuantity();
        if (oldQuantity > newQuantity) {
            inventory.setQuantity(newQuantity);
        }

        inventory.setLastUpdated(LocalDate.now());

        return inventoryRepository.save(inventory);
    }

    /**
     * Retrieves a list of inventory records where the quantity is less than or equal to the threshold.
     *
     * @return A list of inventory records that have quantity less than or equal to the threshold.
     */
    public List<Inventory> findInventoriesAtThreshold() {
        return inventoryRepository.findAllWhereQuantityLessThanOrEqualThreshold();
    }

    /**
     * Retrieves an inventory record by its unique identifier.
     *
     * @param id The ID of the inventory to retrieve.
     * @return The inventory record if found, or null if it doesn't exist.
     */
    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
    }

    public List<Inventory> findInventoryByStore(Store store) {
        return inventoryRepository.findByStore(store);
    }

    public List<Inventory> findInventoryByItem(Item item) {
        return inventoryRepository.findByItem(item);
    }


    public Inventory findInventoryByStoreAndItem(Store store, Item item) {
        return inventoryRepository.findByStoreAndItem(store, item).orElse(null);
    }
}
