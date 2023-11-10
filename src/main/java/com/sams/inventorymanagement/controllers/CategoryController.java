package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing categories.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    /**
     * Service for handling category-related operations.
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * Get a list of all categories.
     *
     * @return List of all categories.
     */
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    /**
     * Get a category by its ID.
     *
     * @param id The ID of the category to retrieve.
     * @return The retrieved category or throws an EntityNotFoundException if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);

        if (category == null)
            throw new EntityNotFoundException("id: " + id);

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    /**
     * Create a new category.
     *
     * @param category The category to create.
     * @return The created category.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@Valid @RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    /**
     * Update a category by its ID.
     *
     * @param id       The ID of the category to update.
     * @param category The updated category.
     * @return The updated category or returns a not found response if not updated.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(id, category);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a category by its ID.
     *
     * @param id The ID of the category to delete.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
