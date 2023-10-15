package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Custom query methods for searching items
    List<Item> findByNameContainingIgnoreCaseOrShortDescriptionContainingIgnoreCaseOrLongDescriptionContainingIgnoreCaseOrPriceBetweenOrQuantityBetweenOrCategoryNameContainingIgnoreCase(
            String name,
            String shortDescription, String longDescription,
            Double minPrice, Double maxPrice,
            Integer minQuantity, Integer maxQuantity,
            String categoryName
    );
}