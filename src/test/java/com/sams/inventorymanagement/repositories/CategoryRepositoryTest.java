package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.entities.Supplier;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private Supplier sampleSupplier;

    @BeforeEach
    public void setup() {
        // Create and save a sample supplier before each test
        sampleSupplier = new Supplier();
        sampleSupplier.setName("Sample Supplier");
        sampleSupplier.setPhone("1234567890");
        sampleSupplier = supplierRepository.save(sampleSupplier);
    }

    @Test
    public void saveValidCategory() {
        Category category = new Category();
        category.setName("Valid Category");
        category.setSupplier(sampleSupplier); // Set the associated supplier

        Category savedCategory = categoryRepository.save(category);

        assertNotNull(savedCategory.getId());
        assertEquals("Valid Category", savedCategory.getName());
        assertEquals(sampleSupplier, savedCategory.getSupplier());
    }

    @Test
    public void saveCategoryWithInvalidName() {
        Category category = new Category();
        category.setName(""); // Invalid name
        category.setSupplier(sampleSupplier); // Set the associated supplier

        assertThrows(ConstraintViolationException.class, () -> categoryRepository.saveAndFlush(category));
    }

    @Test
    void testFindCategoryByName_Success() {
        // Create and save a sample category associated with the sample supplier
        Category sampleCategory = new Category();
        sampleCategory.setName("Sample Category");
        sampleCategory.setSupplier(sampleSupplier);
        sampleCategory = categoryRepository.save(sampleCategory);

        // Search for the category by name
        Optional<Category> foundCategory = categoryRepository.findByName("Sample Category");

        assertThat(foundCategory).isPresent().contains(sampleCategory);
    }

    @Test
    void testFindCategoryByName_Failure() {
        // Search for a non-existent category by name
        Optional<Category> foundCategory = categoryRepository.findByName("NonExistent");

        assertThat(foundCategory).isEmpty();
    }
}
