package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.exceptions.InvalidStatusTransitionException;
import com.sams.inventorymanagement.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing purchase orders.
 */
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder) {
        return purchaseOrderRepository.save(purchaseOrder);
    }

    @Override
    public PurchaseOrder getPurchaseOrderById(Long id) {
        return purchaseOrderRepository.findById(id).orElse(null);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public void deletePurchaseOrder(Long id) {
        purchaseOrderRepository.deleteById(id);
    }

    @Override
    public PurchaseOrder updateOrderStatus(PurchaseOrder purchaseOrder, OrderStatus newStatus) {
        // Check if the transition is valid based on the current and new status.
        OrderStatus currentStatus = purchaseOrder.getStatus();

        if (isValidTransition(currentStatus, newStatus)) {
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
                    newStatus == OrderStatus.APPROVED || newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.CANCELED;
            case APPROVED -> newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.CANCELED;
            case CANCELED -> newStatus == OrderStatus.DELIVERED || newStatus == OrderStatus.APPROVED;
            default -> false;
        };
    }
}
