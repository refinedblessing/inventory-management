package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Supplier;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing suppliers.
 */
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    private final SupplierService supplierService;

    /**
     * Constructs a new SupplierController with the specified SupplierService.
     *
     * @param supplierService The service for handling supplier-related operations.
     */
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /**
     * Create a new supplier.
     *
     * @param supplier The supplier to create.
     * @return The created supplier.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Supplier createSupplier(@Valid @RequestBody Supplier supplier) {
        return supplierService.createSupplier(supplier);
    }

    /**
     * Update an existing supplier.
     *
     * @param id       The ID of the supplier to update.
     * @param supplier The updated supplier information.
     * @return The updated supplier, or a not found response if not found.
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @Valid @RequestBody Supplier supplier) {
        Supplier updatedSupplier = supplierService.updateSupplier(id, supplier);
        if (updatedSupplier != null) {
            return ResponseEntity.ok(updatedSupplier);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a supplier by its unique identifier.
     *
     * @param id The ID of the supplier to delete.
     * @return A no-content response if deleted successfully.
     */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        supplierService.deleteSupplier(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Search for suppliers based on various criteria.
     *
     * @param name         The name of the supplier to search for.
     * @param email        The email of the supplier to search for.
     * @param phone        The phone of the supplier to search for.
     * @param categoryName The name of the category associated with the supplier.
     * @param address      The address of the supplier to search for.
     * @return A list of suppliers matching the specified criteria.
     */
    @GetMapping("/search")
    public List<Supplier> searchSuppliers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address) {
        return supplierService.searchSuppliers(name, email, phone, address);
    }

    /**
     * Retrieve a supplier by its unique identifier.
     *
     * @param id The ID of the supplier to retrieve.
     * @return The supplier with the specified ID, or a not found response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
        Supplier supplier = supplierService.getSupplierById(id);

        if (supplier == null)
            throw new EntityNotFoundException("id: " + id);

        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    /**
     * Retrieve a list of all suppliers.
     *
     * @return A list of all suppliers, or a no-content response if none found.
     */
    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }
}
