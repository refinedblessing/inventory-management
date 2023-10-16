package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Category;

import java.util.List;

/**
 * Service interface for managing product categories.
 */
public interface CategoryService {

    /**
     * Get a list of all available categories.
     *
     * @return A list of all categories.
     */
    List<Category> getAllCategories();

    /**
     * Get a category by its unique identifier.
     *
     * @param id The unique identifier of the category.
     * @return The category if found, or null if not found.
     */
    Category getCategoryById(Long id);

    /**
     * Get a category by its name.
     *
     * @param name The name of the category.
     * @return The category if found, or null if not found.
     */
    Category getCategoryByName(String name);

    /**
     * Create a new category.
     *
     * @param category The category to create.
     * @return The created category.
     */
    Category createCategory(Category category);

    /**
     * Update an existing category.
     *
     * @param id       The unique identifier of the category to update.
     * @param category The updated category information.
     * @return The updated category if the category with the specified ID exists, or null if not found.
     */
    Category updateCategory(Long id, Category category);

    /**
     * Delete a category by its unique identifier.
     *
     * @param id The unique identifier of the category to delete.
     */
    void deleteCategory(Long id);
}
