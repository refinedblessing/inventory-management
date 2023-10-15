package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.repositories.CategoryRepository;
import com.sams.inventorymanagement.repositories.ItemRepository;
import com.sams.inventorymanagement.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCleanupService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    public void cleanupAllData() {
        // Delete all items, categories, and suppliers
        itemRepository.deleteAll();
        categoryRepository.deleteAll();
        supplierRepository.deleteAll();
    }

    public void cleanupItems() {
        // Delete all items
        itemRepository.deleteAll();
    }

    public void cleanupCategories() {
        // Delete all categories
        categoryRepository.deleteAll();
    }

    public void cleanupSuppliers() {
        // Delete all suppliers
        supplierRepository.deleteAll();
    }
}

