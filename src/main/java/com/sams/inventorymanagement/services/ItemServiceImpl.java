package com.sams.inventorymanagement.services;
import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.exceptions.EntityDuplicateException;
import com.sams.inventorymanagement.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /** Search name, short/long Description using the name field */
    @Override
    public List<Item> searchItemsByCriteria(String name, String description, Double minPrice, Double maxPrice, Integer minQuantity, Integer maxQuantity, String categoryName) {
        return itemRepository.findByNameContainingIgnoreCaseOrShortDescriptionContainingIgnoreCaseOrLongDescriptionContainingIgnoreCaseOrPriceBetweenOrQuantityBetweenOrCategoryNameContainingIgnoreCase(
                name, description, description, minPrice, maxPrice, minQuantity, maxQuantity, categoryName
        );
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item getItemByName(String name) {
        return itemRepository.findByName(name).orElse(null);
    }

    @Override
    public Item createItem(Item item) {
        if (itemRepository.findByName(item.getName()).isPresent()) {
            throw new EntityDuplicateException("Item with name '" + item.getName() + "' already exists.");
        }

        return itemRepository.save(item);
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Item updateItem(Long id, Item updatedItem) {
        if (itemRepository.existsById(id)) {
            updatedItem.setId(id); // Ensure the ID is set
            return itemRepository.save(updatedItem);
        } else {
            return null; // Return null if the item with the specified ID does not exist
        }
    }

    @Override
    public List<Item> getAllItems() {
        // Retrieve all items from the repository
        return itemRepository.findAll();
    }
}

