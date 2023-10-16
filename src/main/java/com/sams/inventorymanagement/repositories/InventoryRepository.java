package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT i FROM Inventory i WHERE i.quantity <= i.threshold")
    List<Inventory> findAllWhereQuantityLessThanOrEqualThreshold();
}
