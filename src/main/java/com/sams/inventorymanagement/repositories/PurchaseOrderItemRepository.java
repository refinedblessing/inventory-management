package com.sams.inventorymanagement.repositories;

import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository interface for accessing and managing purchase order item entities in the database.
 */
@Repository
public interface PurchaseOrderItemRepository extends JpaRepository<PurchaseOrderItem, Long> {

    /**
     * Custom query method to retrieve purchase order items by purchase order ID and group them by category name.
     *
     * @param purchaseOrderId The unique ID of the purchase order to search for.
     * @return A list of purchase order items grouped by category name.
     */
    @Query("SELECT pi FROM PurchaseOrderItem pi " +
            "WHERE pi.purchaseOrder.id = :purchaseOrderId " +
            "GROUP BY pi.item.category.name " +
            "ORDER BY pi.item.category.name")
    List<PurchaseOrderItem> findByPurchaseOrderIdGroupByCategoryName(@Param("purchaseOrderId") UUID purchaseOrderId);

    /**
     * Retrieve a list of purchase order items by purchase order ID.
     *
     * @param purchaseOrderId The unique ID of the purchase order to search for.
     * @return A list of purchase order items associated with the specified purchase order.
     */
    List<PurchaseOrderItem> findByPurchaseOrderId(UUID purchaseOrderId);
}
