package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.exceptions.EntityDuplicateException;
import com.sams.inventorymanagement.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new EntityDuplicateException("Category with name '" + category.getName() + "' already exists.");
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null;
    }

    @Override
    public Category getCategoryByName(String name) {
        Optional<Category> category = categoryRepository.findByName(name);
        return category.orElse(null);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
