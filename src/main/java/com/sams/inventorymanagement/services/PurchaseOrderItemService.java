package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing purchase order items.
 */
public interface PurchaseOrderItemService {
    /**
     * Create a new purchase order item.
     *
     * @param purchaseOrderItem The purchase order item to be created.
     * @return The created purchase order item.
     */
    PurchaseOrderItem createPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem);

    /**
     * Get all purchase order items for a given purchase order ID.
     *
     * @param purchaseOrderId The ID of the purchase order.
     * @return A list of purchase order items associated with the given purchase order.
     */
    List<PurchaseOrderItem> getPurchaseOrderItemsByPurchaseOrderId(UUID purchaseOrderId);

    /**
     * Get a specific purchase order item by its ID.
     *
     * @param id The ID of the purchase order item.
     * @return The purchase order item with the specified ID.
     */
    PurchaseOrderItem getPurchaseOrderItemById(Long id);

    /**
     * Get all purchase order items.
     *
     * @return A list of all purchase order items.
     */
    List<PurchaseOrderItem> getAllPurchaseOrderItems();

    void deletePurchaseOrderItem(Long id);

    /**
     * Updates an existing purchase order item with new data.
     *
     * @param id The ID of the purchase order item.
     * @return The updated purchase order item or null if specified purchase order item is non-existent.
     */
    PurchaseOrderItem updatePurchaseOrderItem(Long id, PurchaseOrderItem updatedPurchaseOrderItem);
}
