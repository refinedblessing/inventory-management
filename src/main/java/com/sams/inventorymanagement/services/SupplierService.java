package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Supplier;

import java.util.List;

/**
 * Service interface for managing suppliers.
 */
public interface SupplierService {
    /**
     * Create a new supplier.
     *
     * @param supplier The supplier to be created.
     * @return The created supplier.
     */
    Supplier createSupplier(Supplier supplier);

    /**
     * Update an existing supplier by its ID.
     *
     * @param id       The ID of the supplier to be updated.
     * @param supplier The updated supplier data.
     * @return The updated supplier.
     */
    Supplier updateSupplier(Long id, Supplier supplier);

    /**
     * Delete a supplier by its ID.
     *
     * @param id The ID of the supplier to be deleted.
     */
    void deleteSupplier(Long id);

    /**
     * Search for suppliers based on various criteria.
     *
     * @param name        The name of the supplier (partial match).
     * @param email       The email address of the supplier (partial match).
     * @param phone       The phone number of the supplier (partial match).
     * @param categoryName The name of the supplier's categories (partial match).
     * @param address     The address of the supplier (partial match).
     * @return A list of suppliers that match the specified criteria.
     */
    List<Supplier> searchSuppliers(String name, String email, String phone, String categoryName, String address);

    /**
     * Get a specific supplier by its ID.
     *
     * @param id The ID of the supplier.
     * @return The supplier with the specified ID.
     */
    Supplier getSupplierById(Long id);

    /**
     * Get a supplier by its name.
     *
     * @param name The name of the supplier to retrieve.
     * @return The supplier with the specified name.
     */
    Supplier getSupplierByName(String name);

    /**
     * Get a list of all suppliers in the system.
     *
     * @return A list of all suppliers.
     */
    List<Supplier> getAllSuppliers();
}
