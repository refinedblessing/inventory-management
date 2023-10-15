package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Supplier;

import java.util.List;

public interface SupplierService {
    Supplier createSupplier(Supplier supplier);

    Supplier updateSupplier(Long id, Supplier supplier);

    void deleteSupplier(Long id);

    List<Supplier> searchSuppliers(String name, String email, String phone, String categoryName, String address);

    Supplier getSupplierById(Long id);

    List<Supplier> getAllSuppliers();
}