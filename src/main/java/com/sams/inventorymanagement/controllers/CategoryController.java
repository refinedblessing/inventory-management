package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    private final CategoryService categoryService;

    /**
     * Constructs a new CategoryController.
     *
     * @param categoryService The category service to be injected.
     */
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

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
    public ResponseEntity<Category> createCategory(@Valid @RequestBody Category category) {
        Category savedCategory = categoryService.createCategory(category);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getId())
                .toUri();
        System.out.println(location);

        return ResponseEntity.created(location).build();
    }

    /**
     * Update a category by its ID.
     *
     * @param id       The ID of the category to update.
     * @param category The updated category.
     * @return The updated category or returns a not found response if not updated.
     */
    @PutMapping("/{id}")
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
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
