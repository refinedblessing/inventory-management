package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Supplier;
import com.sams.inventorymanagement.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        // Implement create logic here

        // You can save the supplier using the repository
        Supplier createdSupplier = supplierRepository.save(supplier);

        // Return the created supplier
        return createdSupplier;
    }

    @Override
    public Supplier updateSupplier(Long id, Supplier updatedSupplier) {
        // Add logic to validate and update the item
        if (supplierRepository.existsById(id)) {
            updatedSupplier.setId(id); // Ensure the ID is set
            return supplierRepository.save(updatedSupplier);
        } else {
            return null; // Return null if the item with the specified ID does not exist
        }
    }

    @Override
    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> searchSuppliers(String name, String email, String phone, String categoryName, String address) {
        return supplierRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrCategoriesNameContainingIgnoreCaseOrAddressContainingIgnoreCase(
                name, email, phone, categoryName, address
        );
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        // Retrieve all suppliers from the repository
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }
}
