package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Category;
import com.sams.inventorymanagement.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrCategoriesNameContainingIgnoreCaseOrAddressContainingIgnoreCase (
            String name,
            String email,
            String phone,
            String categoryName,
            String address
    );

    Optional<Supplier> findByName(String name);
}