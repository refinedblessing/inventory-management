package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrCategoriesNameContainingIgnoreCaseOrAddressContainingIgnoreCase (
            String name,
            String email,
            String phone,
            String categoryName,
            String address
    );
}