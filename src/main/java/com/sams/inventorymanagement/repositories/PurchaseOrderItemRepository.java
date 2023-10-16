package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {
    @Query("SELECT pi FROM PurchaseOrderItem pi " +
            "WHERE pi.purchaseOrder.id = :purchaseOrderId " +
            "GROUP BY pi.item.category.name " +
            "ORDER BY pi.item.category.name")
    List<PurchaseOrderItem> findByPurchaseOrderIdGroupByCategoryName(@Param("purchaseOrderId") UUID purchaseOrderId);

    List<PurchaseOrderItem> findByPurchaseOrderId(UUID purchaseOrderId);
}

