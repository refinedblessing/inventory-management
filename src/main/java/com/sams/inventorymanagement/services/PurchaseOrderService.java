package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.enums.OrderStatus;

import java.util.List;

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
    PurchaseOrder getPurchaseOrderById(Long id);

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
    void deletePurchaseOrder(Long id);

    /**
     * Update the status of a purchase order.
     *
     * @param purchaseOrder The purchase order to be updated.
     * @param newStatus     The new status to set for the purchase order.
     * @return The updated purchase order with the new status.
     */
    PurchaseOrder updateOrderStatus(PurchaseOrder purchaseOrder, OrderStatus newStatus);
}
