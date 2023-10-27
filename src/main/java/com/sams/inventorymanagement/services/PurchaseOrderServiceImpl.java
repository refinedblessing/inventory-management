package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.*;
import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.exceptions.InvalidStatusTransitionException;
import com.sams.inventorymanagement.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service implementation for managing purchase orders.
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private InventoryServiceImpl inventoryService;
    @Autowired
    private ItemService itemService;


    @Autowired
    public PurchaseOrderServiceImpl(
            PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(UUID id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public void deletePurchaseOrder(UUID id) {
        purchaseOrderRepository.deleteById(id);
    }

    @Override
    public PurchaseOrder updatePurchaseOrderStatus(UUID id, OrderStatus newStatus) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase Order not found"));

        OrderStatus currentStatus = purchaseOrder.getStatus();

        if (currentStatus == newStatus) {
            return purchaseOrder;
        }

        if (isValidTransition(currentStatus, newStatus)) {
            if (newStatus == OrderStatus.DELIVERED) {
                fulfillPurchaseOrder(purchaseOrder);
            }

            purchaseOrder.setStatus(newStatus);
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new InvalidStatusTransitionException("The requested status transition is not allowed.");
        }
    }

    @Override
    public PurchaseOrder updatePurchaseOrder(UUID id, PurchaseOrder updatedPurchaseOrder) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase Order not found"));

        purchaseOrder.setStore(updatedPurchaseOrder.getStore());
        return purchaseOrderRepository.save(updatedPurchaseOrder);
    }

    /**
     * Check if a status transition is valid based on the current and new status.
     *
     * @param currentStatus The current status of the purchase order.
     * @param newStatus     The new status to transition to.
     * @return True if the transition is valid, false otherwise.
     */
    private boolean isValidTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        return switch (currentStatus) {
            case PENDING ->
                    newStatus == OrderStatus.APPROVED || newStatus == OrderStatus.CANCELED;
            case CANCELED -> newStatus == OrderStatus.APPROVED;
            case APPROVED -> newStatus == OrderStatus.DELIVERED;
            default -> false;
        };
    }
    /**
     * Fulfill a purchase order and update item quantities.
     *
     * @param purchaseOrder The purchase order to fulfill.
     */
    private void fulfillPurchaseOrder(PurchaseOrder purchaseOrder) {
        // Reduce item quantities and update item inventory
        for (PurchaseOrderItem orderItem : purchaseOrder.getPurchaseOrderItems()) {
            Item item = orderItem.getItem();
            int orderedQuantity = orderItem.getQuantity();
            int availableQuantity = item.getQuantity();

            // Ensure that the available quantity is not less than the ordered quantity
            if (availableQuantity >= orderedQuantity) {
                item.setQuantity(availableQuantity - orderedQuantity);
                itemService.createItem(item);

//                update store inventory
                Store store = purchaseOrder.getStore();
                Inventory inventory = inventoryService.findInventoryByStoreAndItem(store, item);

                if (inventory == null) {
                    Inventory newInventory = new Inventory();
                    newInventory.setStore(store);
                    newInventory.setItem(item);
                    newInventory.setQuantity(orderedQuantity);
                    inventoryService.createInventory(newInventory);
                } else {
                    inventory.setQuantity(inventory.getQuantity() + orderedQuantity);
                    inventoryService.updateInventory(inventory.getId(), inventory);
                }
            } else {
                throw new InvalidStatusTransitionException("Item quantity is insufficient for fulfillment");
            }
        }
    }
}
