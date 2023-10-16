package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing and managing inventory items in the database.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Find all inventory items where the quantity is less than or equal to the threshold.
     *
     * @return A list of inventory items that meet the criteria.
     */
    @Query("SELECT i FROM Inventory i WHERE i.quantity <= i.threshold")
    List<Inventory> findAllWhereQuantityLessThanOrEqualThreshold();
}
