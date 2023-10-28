package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.PurchaseOrder;

import java.util.List;
import java.util.UUID;

/**
 * Service interface for managing purchase orders.
 */
public interface PurchaseOrderService {
    /**
     * Create a new purchase order.
     *
     * @param purchaseOrder The purchase order to be created.
     * @return The created purchase order.
     */
    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);

    /**
     * Get a specific purchase order by its ID.
     *
     * @param id The ID of the purchase order.
     * @return The purchase order with the specified ID.
     */
    PurchaseOrder getPurchaseOrderById(UUID id);

    /**
     * Get a list of all purchase orders.
     *
     * @return A list of all purchase orders in the system.
     */
    List<PurchaseOrder> getAllPurchaseOrders();

    /**
     * Delete a purchase order by its ID.
     *
     * @param id The ID of the purchase order to be deleted.
     */
    void deletePurchaseOrder(UUID id);

    /**
     * Update a purchase order.
     *
     * @param id The ID of the purchase order to be updated.
     * @param updatedPurchaseOrder The updated purchase order.
     * @return The updated purchase order.
     */
    PurchaseOrder updatePurchaseOrder(UUID id, PurchaseOrder updatedPurchaseOrder);
}
