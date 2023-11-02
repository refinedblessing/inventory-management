package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.PurchaseOrderItemDTO;
import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import com.sams.inventorymanagement.services.PurchaseOrderItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for managing purchase order items.
 */
@RestController
@RequestMapping("/api/purchase-orders-items")
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
    public PurchaseOrderItemDTO createPurchaseOrderItem(@Valid @RequestBody PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrderItem createdPurchaseOrderItem = purchaseOrderItemService.createPurchaseOrderItem(purchaseOrderItem);
        return purchaseOrderItemService.mapToDTO(createdPurchaseOrderItem);
    }

    /**
     * Retrieve a purchase order item by its unique identifier.
     *
     * @param id The ID of the purchase order item to retrieve.
     * @return The purchase order item with the specified ID.
     */
    @GetMapping("/{id}")
    public PurchaseOrderItemDTO getPurchaseOrderItemById(@PathVariable Long id) {
        PurchaseOrderItem purchaseOrderItem = purchaseOrderItemService.getPurchaseOrderItemById(id);
        return purchaseOrderItemService.mapToDTO(purchaseOrderItem);
    }

    /**
     * Get a list of all purchase order items.
     *
     * @return A list of all purchase order items.
     */
    @GetMapping
    public ResponseEntity<List<PurchaseOrderItemDTO>> getAllPurchaseOrderItems() {
        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderItemService.getAllPurchaseOrderItems();
        List<PurchaseOrderItemDTO> purchaseOrderItemsDTOS = purchaseOrderItems.stream()
                .map(purchaseOrderItemService::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(purchaseOrderItemsDTOS);
    }

    /**
     * Update an item by its ID.
     *
     * @param id The ID of the purchase order item to update.
     * @param updatedPurchaseOrderItem The updated item.
     * @return The updated purchase order item.
     */
    @PutMapping("/{id}")
    public PurchaseOrderItemDTO updatePurchaseOrderItem(@PathVariable Long id, @Valid @RequestBody PurchaseOrderItem updatedPurchaseOrderItem) {
        return purchaseOrderItemService.mapToDTO(purchaseOrderItemService.updatePurchaseOrderItem(id, updatedPurchaseOrderItem));
    }

    /**
     * Delete an item by its ID.
     *
     * @param id The ID of the item to delete.
     */
    @DeleteMapping("/{id}")
    public void deletePurchaseOrderItem(@PathVariable Long id) {
        purchaseOrderItemService.deletePurchaseOrderItem(id);
    }
}
