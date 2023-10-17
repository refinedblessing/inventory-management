package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.exceptions.EntityNotFoundException;
import com.sams.inventorymanagement.exceptions.InvalidStatusTransitionException;
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
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public PurchaseOrderServiceImpl(
            PurchaseOrderRepository purchaseOrderRepository,
            ItemRepository itemRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.itemRepository = itemRepository;
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
    public PurchaseOrder updatePurchaseOrder(UUID id, PurchaseOrder updatedPurchaseOrder) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Purchase Order not found"));

        updatedPurchaseOrder.setId(id);
        OrderStatus newStatus = updatedPurchaseOrder.getStatus();
        OrderStatus currentStatus = purchaseOrder.getStatus();

//      save update if there is no status change
        if (currentStatus == newStatus) {
            return purchaseOrderRepository.save(updatedPurchaseOrder);
        }
//      Confirm that the status change is a valid transition
        if (isValidTransition(currentStatus, newStatus)) {
//            If an order is approved, fulfill it right away
            if (newStatus == OrderStatus.APPROVED) {
                return fulfillPurchaseOrder(purchaseOrder);
            }
//            if it's not approved, simply update the order
            purchaseOrder.setStatus(newStatus);
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new InvalidStatusTransitionException("The requested status transition is not allowed.");
        }
    }

    /**
     * Check if a status transition is valid based on the current and new status.
     *
     * @param currentStatus The current status of the purchase order.
     * @param newStatus     The new status to transition to.
     * @return True if the transition is valid, false otherwise.
     */
    public boolean isValidTransition(OrderStatus currentStatus, OrderStatus newStatus) {
        return switch (currentStatus) {
            case PENDING ->
                    newStatus == OrderStatus.APPROVED || newStatus == OrderStatus.CANCELED;
            case CANCELED -> newStatus == OrderStatus.APPROVED;
            default -> false;
        };
    }

    @Override
    public PurchaseOrder fulfillPurchaseOrder(PurchaseOrder purchaseOrder) {
        if (isValidPurchaseOrder(purchaseOrder)) {
            // Reduce item quantities and update item inventory
            for (PurchaseOrderItem orderItem : purchaseOrder.getPurchaseOrderItems()) {
                Item item = orderItem.getItem();
                int orderedQuantity = orderItem.getQuantity();
                int availableQuantity = item.getQuantity();

                // Ensure that the available quantity is not less than the ordered quantity
                if (availableQuantity >= orderedQuantity) {
                    item.setQuantity(availableQuantity - orderedQuantity);
                    itemRepository.save(item);
                } else {
                    throw new InvalidStatusTransitionException("Item quantity is insufficient for fulfillment");
                }
            }
            purchaseOrder.setStatus(OrderStatus.DELIVERED);
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new InvalidStatusTransitionException("Purchase order cannot be fulfilled");
        }
    }

    /**
     * Validates whether a purchase order is in the correct state to be fulfilled.
     *
     * @param purchaseOrder The purchase order to be validated.
     * @return {@code true} if the purchase order is in the 'APPROVED' state and can be fulfilled, {@code false} otherwise.
     */
    private boolean isValidPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrder.getStatus() == OrderStatus.APPROVED;
    }
}
