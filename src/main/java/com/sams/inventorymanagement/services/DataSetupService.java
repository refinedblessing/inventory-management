package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.entities.Supplier;
import com.sams.inventorymanagement.repositories.CategoryRepository;
import com.sams.inventorymanagement.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSetupService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void setupData() {
        List<Supplier> suppliers = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        // Create and save 5 suppliers
        for (int i = 1; i <= 5; i++) {
            Supplier supplier = new Supplier();
            supplier.setName("Supplier " + i);
            supplier.setAddress("Address " + i);
            supplier.setEmail("supplier" + i + "@example.com");
            supplier.setPhone("+1234567890" + i);
            suppliers.add(supplier);
        }
        supplierRepository.saveAll(suppliers);

        // Create and save 10 categories
        for (int i = 1; i <= 10; i++) {
            Category category = new Category();
            category.setName("Category " + i);
            categories.add(category);
        }

        // Assign each supplier to 2 categories
        int supplierIndex = 0;
        for (Category category : categories) {
            category.setSupplier(suppliers.get(supplierIndex));
            categoryRepository.save(category);
            supplierIndex = (supplierIndex + 1) % suppliers.size();
        }

        categoryRepository.saveAll(categories);
    }
}

