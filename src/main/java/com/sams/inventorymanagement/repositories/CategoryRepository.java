package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for accessing and managing categories in the database.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Find a category by its name.
     *
     * @param name The name of the category to find.
     * @return An optional containing the category if found, or empty if not found.
     */
    Optional<Category> findByName(String name);
}
