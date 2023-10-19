package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import com.sams.inventorymanagement.services.PurchaseOrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing purchase order items.
 */
@RestController
@RequestMapping("/api/purchase-order-items")
public class PurchaseOrderItemController {

    /**
     * Service for handling purchase order item-related operations.
     */
    private final PurchaseOrderItemService purchaseOrderItemService;

    /**
     * Constructs a new PurchaseOrderItemController with the provided service.
     *
     * @param purchaseOrderItemService The service for handling purchase order item operations.
     */
    @Autowired
    public PurchaseOrderItemController(PurchaseOrderItemService purchaseOrderItemService) {
        this.purchaseOrderItemService = purchaseOrderItemService;
    }

    /**
     * Create a new purchase order item.
     *
     * @param purchaseOrderItem The purchase order item to create.
     * @return The created purchase order item.
     */
    @PostMapping
    public ResponseEntity<PurchaseOrderItem> createPurchaseOrderItem(@Valid @RequestBody PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrderItem createdPurchaseOrderItem = purchaseOrderItemService.createPurchaseOrderItem(purchaseOrderItem);
        return ResponseEntity.ok(createdPurchaseOrderItem);
    }

    /**
     * Retrieve a purchase order item by its unique identifier.
     *
     * @param id The ID of the purchase order item to retrieve.
     * @return The purchase order item with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderItem> getPurchaseOrderItemById(@PathVariable Long id) {
        PurchaseOrderItem purchaseOrderItem = purchaseOrderItemService.getPurchaseOrderItemById(id);
        return ResponseEntity.ok(purchaseOrderItem);
    }

    /**
     * Get a list of all purchase order items.
     *
     * @return A list of all purchase order items.
     */
    @GetMapping
    public ResponseEntity<List<PurchaseOrderItem>> getAllPurchaseOrderItems() {
        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderItemService.getAllPurchaseOrderItems();
        return ResponseEntity.ok(purchaseOrderItems);
    }

    /**
     * Update an item by its ID.
     *
     * @param id The ID of the purchase order item to update.
     * @param updatedPurchaseOrderItem The updated item.
     * @return The updated purchase order item.
     */
    @PutMapping("/{id}")
    public PurchaseOrderItem updatePurchaseOrderItem(@PathVariable Long id, @Valid @RequestBody PurchaseOrderItem updatedPurchaseOrderItem) {
        return purchaseOrderItemService.updatePurchaseOrderItem(id, updatedPurchaseOrderItem);
    }
}
