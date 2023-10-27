package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.PurchaseOrderMaxDTO;
import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.services.PurchaseOrderItemServiceImpl;
import com.sams.inventorymanagement.services.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Controller for managing purchase orders.
 */
@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    /**
     * Service for handling purchase order-related operations.
     */
    private final PurchaseOrderService purchaseOrderService;

    @Autowired
    private PurchaseOrderItemServiceImpl purchaseOrderItemService;


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
    public PurchaseOrderMaxDTO createPurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder createdPurchaseOrder = purchaseOrderService.createPurchaseOrder(purchaseOrder);
        return PurchaseOrderMaxDTO.fromPurchaseOrder(createdPurchaseOrder);
    }

    /**
     * Retrieve a purchase order by its unique identifier.
     *
     * @param id The ID of the purchase order to retrieve.
     * @return The purchase order with the specified ID.
     */
    @GetMapping("/{id}")
    public PurchaseOrderMaxDTO getPurchaseOrderById(@PathVariable UUID id) {
        PurchaseOrder purchaseOrder = purchaseOrderService.getPurchaseOrderById(id);
        return PurchaseOrderMaxDTO.fromPurchaseOrder(purchaseOrder);
    }

    /**
     * Get a list of all purchase orders.
     *
     * @return A list of all purchase orders.
     */
    @GetMapping
    public List<PurchaseOrderMaxDTO> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();

        return purchaseOrders.stream()
                .map(PurchaseOrderMaxDTO::fromPurchaseOrder)
                .collect(Collectors.toList());
    }

    /**
     * Update the status of a purchase order with the specified ID.
     *
     * @param id        The ID of the purchase order to update.
     * @param purchaseOrder The purchase order to be updated.
     * @return ResponseEntity with a PurchaseOrder object and HTTP status indicating the result or error response.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseOrderMaxDTO> updatePurchaseOrder(@PathVariable UUID id, @Valid @RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(id, purchaseOrder);


        if (updatedPurchaseOrder != null) {
            return new ResponseEntity<>(PurchaseOrderMaxDTO.fromPurchaseOrder(updatedPurchaseOrder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PurchaseOrderMaxDTO> updatePurchaseOrderStatus(@PathVariable UUID id, OrderStatus status) {
        PurchaseOrder updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrderStatus(id, status);

        if (updatedPurchaseOrder != null) {
            return new ResponseEntity<>(PurchaseOrderMaxDTO.fromPurchaseOrder(updatedPurchaseOrder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete an item by its ID.
     *
     * @param id The ID of the item to delete.
     */
    @DeleteMapping("/{id}")
    public void deletePurchaseOrder(@PathVariable UUID id) {
        purchaseOrderService.deletePurchaseOrder(id);
    }
}
