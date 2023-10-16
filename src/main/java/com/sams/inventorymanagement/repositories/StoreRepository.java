package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Store;
import com.sams.inventorymanagement.enums.StoreType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Optional<Store> findByName(String name);

    @Query("SELECT s FROM Store s " +
            "WHERE LOWER(s.name) LIKE %:name% " +
            "OR LOWER(s.address) LIKE %:address% " +
            "OR s.type = :storeType " +
            "OR s.openingDate = :openingDate")
    List<Store> searchStoresByCriteria(
            String name,
            String address,
            StoreType storeType,
            LocalDate openingDate
    );
}
