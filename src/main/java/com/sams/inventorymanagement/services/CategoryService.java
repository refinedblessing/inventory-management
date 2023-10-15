package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(int id);

    Category createCategory(Category category);

    Category updateCategory(int id, Category category);

    void deleteCategory(int id);
}
