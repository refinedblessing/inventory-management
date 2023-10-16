package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing and managing supplier entities in the database.
 */
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    /**
     * Search for suppliers based on various criteria, including name, email, phone, category name, and address.
     *
     * @param name         The name of the supplier (can be a partial match).
     * @param email        The email of the supplier (can be a partial match).
     * @param phone        The phone number of the supplier (can be a partial match).
     * @param categoryName The name of the category supplied by the supplier (can be a partial match).
     * @param address      The address of the supplier (can be a partial match).
     * @return A list of suppliers that match the specified criteria.
     */
    List<Supplier> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCaseOrPhoneContainingIgnoreCaseOrCategoriesNameContainingIgnoreCaseOrAddressContainingIgnoreCase(
            String name,
            String email,
            String phone,
            String categoryName,
            String address
    );

    /**
     * Find a supplier by its name.
     *
     * @param name The name of the supplier to search for.
     * @return An Optional containing the supplier if found, or an empty Optional if not found.
     */
    Optional<Supplier> findByName(String name);
}
