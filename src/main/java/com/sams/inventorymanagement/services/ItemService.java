package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.entities.Supplier;

import java.util.List;

/**
 * Service interface for managing items available for store stocking.
 */
public interface ItemService {
    /**
     * Searches for items based on specified criteria.
     *
     * @param name          The name of the item (partial match).
     * @param description   The description of the item (partial match).
     * @param minPrice      The minimum price of the item.
     * @param maxPrice      The maximum price of the item.
     * @param minQuantity   The minimum quantity of the item in stock.
     * @param maxQuantity   The maximum quantity of the item in stock.
     * @param categoryName  The name of the category to which the item belongs (partial match).
     * @return A list of items that match the search criteria.
     */
    List<Item> searchItemsWithCriteria(String name, String description, Double minPrice, Double maxPrice, Integer minQuantity, Integer maxQuantity, String categoryName);

    /**
     * Retrieves an item by its unique identifier.
     *
     * @param id The ID of the item to retrieve.
     * @return The item if found, or null if it doesn't exist.
     */
    Item getItemById(Long id);

    /**
     * Retrieves an item by its name.
     *
     * @param name The name of the item to retrieve.
     * @return The item if found, or null if it doesn't exist.
     */
    Item getItemByName(String name);

    /**
     * Retrieves the supplier of an item by its unique identifier.
     *
     * @param id The ID of the item for which to retrieve the supplier.
     * @return The supplier of the item if found, or null if the item doesn't exist or has no supplier.
     */
    Supplier getItemSupplier(Long id);

    /**
     * Creates a new item for stocking in stores.
     *
     * @param item The item to create.
     * @return The created item.
     */
    Item createItem(Item item);

    /**
     * Deletes an item by its unique identifier.
     *
     * @param id The ID of the item to delete.
     */
    void deleteItem(Long id);

    /**
     * Updates an existing item with new data.
     *
     * @param id The ID of the item to update.
     * @param updatedItem The updated item data.
     * @return The updated item or null if the specified item does not exist.
     */
    Item updateItem(Long id, Item updatedItem);

    /**
     * Retrieves a list of all available items for store stocking.
     *
     * @return A list of all available items.
     */
    List<Item> getAllItems();
}
