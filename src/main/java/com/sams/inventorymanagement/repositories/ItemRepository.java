package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing and managing item entities in the database.
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * Custom query method for searching items with various criteria.
     *
     * @param name           The name of the item to search for.
     * @param shortDescription The short description of the item to search for.
     * @param longDescription The long description of the item to search for.
     * @param minPrice       The minimum price of the item.
     * @param maxPrice       The maximum price of the item.
     * @param minQuantity    The minimum quantity of the item.
     * @param maxQuantity    The maximum quantity of the item.
     * @param categoryName   The name of the category associated with the item.
     * @return A list of items that match the specified criteria.
     */
    List<Item> findByNameContainingIgnoreCaseOrShortDescriptionContainingIgnoreCaseOrLongDescriptionContainingIgnoreCaseOrPriceBetweenOrQuantityBetweenOrCategoryNameContainingIgnoreCase(
            String name,
            String shortDescription, String longDescription,
            Double minPrice, Double maxPrice,
            Integer minQuantity, Integer maxQuantity,
            String categoryName
    );

    /**
     * Find an item by its name.
     *
     * @param name The name of the item to search for.
     * @return An optional containing the item if found, or an empty optional.
     */
    Optional<Item> findByName(String name);
}
