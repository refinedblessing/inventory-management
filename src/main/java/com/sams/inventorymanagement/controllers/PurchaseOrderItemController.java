package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import com.sams.inventorymanagement.services.PurchaseOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase-order-items")
public class PurchaseOrderItemController {

    private final PurchaseOrderItemService purchaseOrderItemService;

    @Autowired
    public PurchaseOrderItemController(PurchaseOrderItemService purchaseOrderItemService) {
        this.purchaseOrderItemService = purchaseOrderItemService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderItem> createPurchaseOrderItem(@RequestBody PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrderItem createdPurchaseOrderItem = purchaseOrderItemService.createPurchaseOrderItem(purchaseOrderItem);
        return ResponseEntity.ok(createdPurchaseOrderItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderItem> getPurchaseOrderItemById(@PathVariable Long id) {
        PurchaseOrderItem purchaseOrderItem = purchaseOrderItemService.getPurchaseOrderItemById(id);
        return ResponseEntity.ok(purchaseOrderItem);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderItem>> getAllPurchaseOrderItems() {
        List<PurchaseOrderItem> purchaseOrderItems = purchaseOrderItemService.getAllPurchaseOrderItems();
        return ResponseEntity.ok(purchaseOrderItems);
    }
}
