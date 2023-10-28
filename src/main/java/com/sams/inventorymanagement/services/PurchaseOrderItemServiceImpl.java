package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.dto.PurchaseOrderDTO;
import com.sams.inventorymanagement.dto.PurchaseOrderItemDTO;
import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.exceptions.ValidationException;
import com.sams.inventorymanagement.repositories.PurchaseOrderItemRepository;
import com.sams.inventorymanagement.repositories.PurchaseOrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class PurchaseOrderItemServiceImpl implements PurchaseOrderItemService {
    @Autowired
    private PurchaseOrderItemRepository purchaseOrderItemRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public PurchaseOrderItem createPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        resetQuantityToMaxAvailableQuantity(purchaseOrderItem);

        PurchaseOrder purchaseOrder = purchaseOrderRepository.getReferenceById(purchaseOrderItem.getPurchaseOrder().getId());
        purchaseOrderItem.setPurchaseOrder(purchaseOrder);
        PurchaseOrderItem updatedPurchaseOrderItem = purchaseOrderItemRepository.save(purchaseOrderItem);

        updatePurchaseOrderTotalValues(purchaseOrderItem);
        purchaseOrderRepository.save(purchaseOrder);

        return updatedPurchaseOrderItem;
    }

    @Override
    public void deletePurchaseOrderItem(Long id) {
        PurchaseOrderItem purchaseOrderItem = purchaseOrderItemRepository.getReferenceById(id);
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getReferenceById(purchaseOrderItem.getPurchaseOrder().getId());
        OrderStatus orderStatus = purchaseOrder.getStatus();
        if (!orderStatus.equals(OrderStatus.PENDING)) {
            throw new ValidationException("Items can only be updated in a Pending Order!");
        }

        int quantity = purchaseOrderItem.getQuantity();
        Double price = purchaseOrderItem.getItem().getPrice();

        // Update the PurchaseOrder fields
        purchaseOrder.setLastUpdated(LocalDate.now());
        purchaseOrder.setTotalQuantity(purchaseOrder.getTotalQuantity() - quantity);
        purchaseOrder.setTotalPrice(purchaseOrder.getTotalPrice() - (quantity * price));

        purchaseOrderItemRepository.deleteById(id);
        purchaseOrderRepository.save(purchaseOrder);
    }
    @Override
    public PurchaseOrderItem updatePurchaseOrderItem(Long id, PurchaseOrderItem updatedPurchaseOrderItem) {
        if (purchaseOrderItemRepository.existsById(id)) {
            updatedPurchaseOrderItem.setId(id); // Ensure the ID is set
            return createPurchaseOrderItem(updatedPurchaseOrderItem);
        } else {
            return null; // Return null if the item with the specified ID does not exist
        }
    }

    public void updatePurchaseOrderTotalValues(PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrder purchaseOrder = purchaseOrderItem.getPurchaseOrder();
        OrderStatus orderStatus = purchaseOrder.getStatus();
        if (!orderStatus.equals(OrderStatus.PENDING)) {
            throw new ValidationException("Items can only be updated in a Pending Order!");
        }
        // Calculate totals for the associated PurchaseOrder
        int newTotalQuantity = 0;
        double newTotalPrice = 0.0;

        for (PurchaseOrderItem item : purchaseOrder.getPurchaseOrderItems()) {
            newTotalQuantity += item.getQuantity();
            newTotalPrice += item.getItem().getPrice() * item.getQuantity();
        }

        // Update the PurchaseOrder fields
        purchaseOrder.setLastUpdated(LocalDate.now());
        purchaseOrder.setTotalQuantity(newTotalQuantity);
        purchaseOrder.setTotalPrice(newTotalPrice);
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

//        TODO throw error here, instead of resetting the quantity

        // Ensure that the quantity does not exceed the available quantity.
        purchaseOrderItem.setQuantity(Math.min(quantity, availableQuantity));
    }

    @Override
    public List<PurchaseOrderItem> getPurchaseOrderItemsByPurchaseOrderId(UUID purchaseOrderId) {
        return purchaseOrderItemRepository.findByPurchaseOrderId(purchaseOrderId);
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

    public PurchaseOrderItem mapToEntity(PurchaseOrderItemDTO purchaseOrderItemDTO) {
        PurchaseOrderItem purchaseOrderItem = new PurchaseOrderItem();
        purchaseOrderItem.setId(purchaseOrderItemDTO.getId());
        purchaseOrderItem.setItem(purchaseOrderItemDTO.getItem());
        purchaseOrderItem.setQuantity(purchaseOrderItemDTO.getQuantity());

        PurchaseOrder purchaseOrder = purchaseOrderRepository.getReferenceById(purchaseOrderItemDTO.getPurchaseOrder().getId());
        purchaseOrderItem.setPurchaseOrder(purchaseOrder);

        return purchaseOrderItem;
    }

    public PurchaseOrderItemDTO mapToDTO(PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO(purchaseOrderItem.getPurchaseOrder().getId());
        return new PurchaseOrderItemDTO(purchaseOrderItem.getId(), purchaseOrderItem.getItem(), purchaseOrderItem.getQuantity(), purchaseOrderDTO);
    }
}
