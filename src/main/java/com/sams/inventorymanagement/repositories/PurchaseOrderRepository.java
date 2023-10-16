package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {
    // You can add custom query methods here if needed
}
