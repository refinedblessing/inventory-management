package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT i FROM Item i WHERE " +
            "(:name IS NULL OR LOWER(i.name) LIKE %:name%) AND " +
            "(:shortDescription IS NULL OR LOWER(i.shortDescription) LIKE %:shortDescription%) AND " +
            "(:longDescription IS NULL OR LOWER(i.longDescription) LIKE %:longDescription%) AND " +
            "(:minPrice IS NULL OR i.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR i.price <= :maxPrice) AND " +
            "(:minQuantity IS NULL OR i.quantity >= :minQuantity) AND " +
            "(:maxQuantity IS NULL OR i.quantity <= :maxQuantity) AND " +
            "(:categoryName IS NULL OR LOWER(i.category.name) LIKE %:categoryName%)")
    List<Item> searchItemsWithCriteria(
            @Param("name") String name,
            @Param("shortDescription") String shortDescription,
            @Param("longDescription") String longDescription,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minQuantity") Integer minQuantity,
            @Param("maxQuantity") Integer maxQuantity,
            @Param("categoryName") String categoryName
    );

    /**
     * Find an item by its name.
     *
     * @param name The name of the item to search for.
     * @return An optional containing the item if found, or an empty optional.
     */
    Optional<Item> findByName(String name);
}
