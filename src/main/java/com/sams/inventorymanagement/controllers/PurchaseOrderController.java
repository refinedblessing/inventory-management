package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing purchase orders.
 */
@RestController
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {

    /**
     * Service for handling purchase order-related operations.
     */
    private final PurchaseOrderService purchaseOrderService;

    /**
     * Constructs a new PurchaseOrderController with the provided service.
     *
     * @param purchaseOrderService The service for handling purchase order operations.
     */
    @Autowired
    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    /**
     * Create a new purchase order.
     *
     * @param purchaseOrder The purchase order to create.
     * @return The created purchase order.
     */
    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrder);
        return ResponseEntity.ok(createdPurchaseOrder);
    }

    /**
     * Retrieve a purchase order by its unique identifier.
     *
     * @param id The ID of the purchase order to retrieve.
     * @return The purchase order with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrderById(@PathVariable Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        return ResponseEntity.ok(purchaseOrder);
    }

    /**
     * Get a list of all purchase orders.
     *
     * @return A list of all purchase orders.
     */
    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        return ResponseEntity.ok(purchaseOrders);
    }
}
