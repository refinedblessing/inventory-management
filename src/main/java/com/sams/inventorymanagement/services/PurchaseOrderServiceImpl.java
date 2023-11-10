package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.*;
import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.exceptions.InvalidStatusTransitionException;
import com.sams.inventorymanagement.repositories.InventoryRepository;
import com.sams.inventorymanagement.repositories.ItemRepository;
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
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(UUID id) {
        return purchaseOrderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Purchase Order not found"));
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public void deletePurchaseOrder(UUID id) {
        purchaseOrderRepository.deleteById(id);
    }

//    TODO update purchase order items: items can be updated after approval or delivery but it can't be re-delivered
//    purposely excluding updating purchase order items, to avoid complexity
//    purchase order items will be updated through the purchase order items route for now
    @Override
    public PurchaseOrder updatePurchaseOrder(UUID id, PurchaseOrder updatedPurchaseOrder) {
        PurchaseOrder purchaseOrder = getPurchaseOrderById(id);

//        Only make changes to undelivered orders
        OrderStatus currentStatus = purchaseOrder.getStatus();
        if (currentStatus.equals(OrderStatus.DELIVERED) || currentStatus.equals(OrderStatus.CANCELED)) {
            throw new InvalidStatusTransitionException("Already Canceled/Delivered, can't be changed");
        }

//        set store
        if (!currentStatus.equals(OrderStatus.APPROVED)) {
            purchaseOrder.setStore(updatedPurchaseOrder.getStore());
        }

//        set status
        OrderStatus newStatus = updatedPurchaseOrder.getStatus();

        if (!currentStatus.equals(newStatus)) {
            if (isValidTransition(currentStatus, newStatus)) {
                if (newStatus.equals(OrderStatus.DELIVERED)) {
                    fulfillPurchaseOrder(purchaseOrder);
                }
                purchaseOrder.setStatus(newStatus);
            } else {
                throw new InvalidStatusTransitionException("The requested status transition is not allowed.");
            }
        }
        return purchaseOrderRepository.save(purchaseOrder);
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
            case APPROVED -> newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.CANCELED;
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
            Item item = itemRepository.findById(orderItem.getItem().getId()).orElse(null);
            if (item == null) throw new EntityNotFoundException("Item not in stock");

            int orderedQuantity = orderItem.getQuantity();
            int availableQuantity = item.getQuantity();

            // Ensure that the available quantity is not less than the ordered quantity
            if (availableQuantity >= orderedQuantity) {
                item.setQuantity(availableQuantity - orderedQuantity);
                itemRepository.save(item);

//                update store inventory
                Store store = purchaseOrder.getStore();
                Inventory inventory = inventoryRepository.findByStoreAndItem(store, item).orElse(null);

                if (inventory == null) {
                    Inventory newInventory = new Inventory();
                    newInventory.setStore(store);
                    newInventory.setItem(item);
                    newInventory.setQuantity(orderedQuantity);
                    inventoryRepository.save(newInventory);
                } else {
                    inventory.setQuantity(inventory.getQuantity() + orderedQuantity);
                    inventoryRepository.save(inventory);
                }
            } else {
                throw new InvalidStatusTransitionException("Item in stock is insufficient for fulfillment");
            }
        }
    }
}
