package com.sams.inventorymanagement.services;
import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.entities.Supplier;

import java.util.List;

public interface ItemService {
    List<Item> searchItemsByCriteria(
            String name,
            String description,
            Double minPrice, Double maxPrice,
            Integer minQuantity, Integer maxQuantity,
            String categoryName
    );

    Item getItemById(Long id);

    Item getItemByName(String name);

    Supplier getItemSupplier(Long id);

    Item createItem(Item item);

    void deleteItem(Long id);

    Item updateItem(Long id, Item updatedItem);

    List<Item> getAllItems();
}


