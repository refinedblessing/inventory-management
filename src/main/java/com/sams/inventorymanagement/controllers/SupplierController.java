package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.Supplier;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.services.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller for managing suppliers.
 */
@RestController
@RequestMapping("/suppliers")
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
    public ResponseEntity<Supplier> createSupplier(@Valid @RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.createSupplier(supplier);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedSupplier.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Update an existing supplier.
     *
     * @param id       The ID of the supplier to update.
     * @param supplier The updated supplier information.
     * @return The updated supplier, or a not found response if not found.
     */
    @PutMapping("/{id}")
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
    public List<Supplier> searchSuppliers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String address) {
        return supplierService.searchSuppliers(name, email, phone, categoryName, address);
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
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSuppliers();

        if (suppliers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Return 204 if no suppliers found
        } else {
            return new ResponseEntity<>(suppliers, HttpStatus.OK); // Return the list of suppliers with 200 OK status
        }
    }
}
