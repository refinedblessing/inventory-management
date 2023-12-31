package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Supplier;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SupplierRepositoryTest {

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    public void saveValidSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("Valid Supplier");
        supplier.setPhone("1234567890");

        Supplier savedSupplier = supplierRepository.save(supplier);

        assertNotNull(savedSupplier.getId());
        assertEquals("Valid Supplier", savedSupplier.getName());
    }

    @Test
    public void saveSupplierWithInvalidEmail() {
        Supplier supplier = new Supplier();
        supplier.setName("Invalid Email Supplier");
        supplier.setPhone("1234567890");
        supplier.setEmail("invalid-email");

        assertThrows(ConstraintViolationException.class, () -> supplierRepository.saveAndFlush(supplier));
    }

    @Test
    public void saveSupplierWithInvalidPhone() {
        Supplier supplier = new Supplier();
        supplier.setName("Invalid Phone Supplier");
        supplier.setPhone("invalid-phone");

        assertThrows(ConstraintViolationException.class, () -> supplierRepository.saveAndFlush(supplier));
    }

    @Test
    void testSearchWithCriteria_Success() {
        // Create and save a sample supplier
        Supplier sampleSupplier = new Supplier();
        sampleSupplier.setName("Sample Supplier");
        sampleSupplier.setEmail("sample@email.com");
        sampleSupplier.setPhone("1234567890");
        sampleSupplier.setAddress("Sample Address");
        sampleSupplier = supplierRepository.save(sampleSupplier);

        // Search for the supplier by various criteria
        List<Supplier> foundSuppliers = supplierRepository
                .searchWithCriteria("Sample", "email", "123", null);

        assertThat(foundSuppliers).containsExactly(sampleSupplier);
    }

    @Test
    void testSearchWithCriteria_Failure() {
        // Search for a non-existent supplier
        List<Supplier> foundSuppliers = supplierRepository
                .searchWithCriteria("NonExistent", null, null, null);

        assertThat(foundSuppliers).isEmpty();
    }

    @Test
    void testFindByName_Success() {
        // Create and save a sample supplier
        Supplier sampleSupplier = new Supplier();
        sampleSupplier.setName("Sample Supplier");
        sampleSupplier.setPhone("1234567890");
        sampleSupplier = supplierRepository.save(sampleSupplier);

        // Search for the supplier by name
        Optional<Supplier> foundSupplier = supplierRepository.findByName("Sample Supplier");

        assertThat(foundSupplier).isPresent().contains(sampleSupplier);
    }

    @Test
    void testFindByName_Failure() {
        // Search for a non-existent supplier by name
        Optional<Supplier> foundSupplier = supplierRepository.findByName("NonExistent");

        assertThat(foundSupplier).isEmpty();
    }
}
