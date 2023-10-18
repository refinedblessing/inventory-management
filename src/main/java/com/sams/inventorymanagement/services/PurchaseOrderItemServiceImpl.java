package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import com.sams.inventorymanagement.repositories.PurchaseOrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {
    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Override
    public PurchaseOrderItem createPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        resetQuantityToMaxAvailableQuantity(purchaseOrderItem);
        return purchaseOrderItemRepository.save(purchaseOrderItem);
    }

    /**
     * Resets the quantity of a PurchaseOrderItem to the maximum available quantity.
     * This method ensures that the quantity of a PurchaseOrderItem is set to the maximum
     * available quantity, preventing over-ordering when creating a purchase order.
     *
     * @param purchaseOrderItem The PurchaseOrderItem to reset the quantity for.
     */
    private void resetQuantityToMaxAvailableQuantity(PurchaseOrderItem purchaseOrderItem) {
        int availableQuantity = purchaseOrderItem.getItem().getQuantity();
        int quantity = purchaseOrderItem.getQuantity();

        // Ensure that the quantity does not exceed the available quantity.
        purchaseOrderItem.setQuantity(Math.min(quantity, availableQuantity));
    }

    @Override
    public List<PurchaseOrderItem> getPurchaseOrderItemsByPurchaseOrderId(UUID purchaseOrderId) {
        return purchaseOrderItemRepository.findByPurchaseOrderId(purchaseOrderId);
    }

    @Override
    public List<PurchaseOrderItem> getPurchaseOrderItemsByPurchaseOrderIdGroupByCategoryName(UUID purchaseOrderId) {
        return purchaseOrderItemRepository.findByPurchaseOrderIdGroupByCategoryName(purchaseOrderId);
    }

    @Override
    public PurchaseOrderItem getPurchaseOrderItemById(Long id) {
        return purchaseOrderItemRepository.findById(id)
                .orElse(null); // Return null if not found
    }

    @Override
    public List<PurchaseOrderItem> getAllPurchaseOrderItems() {
        return purchaseOrderItemRepository.findAll();
    }

    @Override
    public PurchaseOrderItem updatePurchaseOrderItem(Long id, PurchaseOrderItem updatedPurchaseOrderItem) {
        if (purchaseOrderItemRepository.existsById(id)) {
            updatedPurchaseOrderItem.setId(id); // Ensure the ID is set
            resetQuantityToMaxAvailableQuantity(updatedPurchaseOrderItem);
            return purchaseOrderItemRepository.save(updatedPurchaseOrderItem);
        } else {
            return null; // Return null if the item with the specified ID does not exist
        }
    }
}