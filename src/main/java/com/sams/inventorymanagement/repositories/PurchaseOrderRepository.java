package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing and managing purchase order entities in the database.
 */
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    // You can add custom query methods here if needed
}
