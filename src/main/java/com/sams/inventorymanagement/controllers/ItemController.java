package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller for managing items.
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {

    /**
     * Service for handling item-related operations.
     */
    @Autowired
    private ItemService itemService;

    /**
     * Get a list of items based on search criteria.
     *
     * @param name         The name to search for.
     * @param description  The description to search for.
     * @param minPrice     The minimum price.
     * @param maxPrice     The maximum price.
     * @param minQuantity  The minimum quantity.
     * @param maxQuantity  The maximum quantity.
     * @param categoryName The name of the category to search for.
     * @return List of items that match the search criteria.
     */
    @GetMapping
    public List<Item> searchItems(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Integer minQuantity,
            @RequestParam(required = false) Integer maxQuantity,
            @RequestParam(required = false) String categoryName
    ) {
        return itemService.searchItemsByCriteria(name, description, minPrice, maxPrice, minQuantity, maxQuantity, categoryName);
    }

    /**
     * Get an item by its ID.
     *
     * @param id The ID of the item to retrieve.
     * @return The retrieved item or throws an EntityNotFoundException if not found.
     */
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);

        if (item == null)
            throw new EntityNotFoundException("id: " + id);

        return item;
    }

    /**
     * Create a new item.
     *
     * @param item The item to create.
     * @return The created item.
     */
    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody Item item) {
        Item savedItem = itemService.createItem(item);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Update an item by its ID.
     *
     * @param id         The ID of the item to update.
     * @param updatedItem The updated item.
     * @return The updated item.
     */
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @Valid @RequestBody Item updatedItem) {
        return itemService.updateItem(id, updatedItem);
    }

    /**
     * Delete an item by its ID.
     *
     * @param id The ID of the item to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
    }
}
