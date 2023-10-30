package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Inventory;
import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing and managing store entities in the database.
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    /**
     * Find a store by its name.
     *
     * @param name The name of the store to search for.
     * @return An Optional containing the store if found, or an empty Optional if not found.
     */
    Optional<Store> findByName(String name);

    /**
     * Search for stores based on various criteria, including name, address, store type, and opening date.
     *
     * @param name         The name of the store (can be a partial match).
     * @param address      The address of the store (can be a partial match).
     * @param type    The type of the store.
     * @param openingDate  The opening date of the store.
     * @return A list of stores that match the specified criteria.
     */
    @Query("SELECT s FROM Store s " +
            "WHERE LOWER(s.name) LIKE %:name% " +
            "OR LOWER(s.address) LIKE %:address% " +
            "OR s.type = :type " +
            "OR s.openingDate = :openingDate")
    List<Store> searchStoresByCriteria(
            String name,
            String address,
            StoreType type,
            LocalDate openingDate
    );

    @Query("SELECT s FROM Store s " +
            "JOIN s.users u " +
            "WHERE (LOWER(s.name) LIKE %:name% " +
            "OR LOWER(s.address) LIKE %:address% " +
            "OR s.type = :type " +
            "OR s.openingDate = :openingDate) " +
            "AND u.id = :userId")
    List<Store> searchStoresByCriteriaForUser(
            @Param("name") String name,
            @Param("address") String address,
            @Param("type") StoreType type,
            @Param("openingDate") LocalDate openingDate,
            @Param("userId") Long userId
    );

    @Query("SELECT s.inventories FROM Store s WHERE s.id = :storeId")
    List<Inventory> findInventoriesByStoreId(@Param("storeId") Long storeId);
}
