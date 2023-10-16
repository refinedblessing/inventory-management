package com.sams.inventorymanagement.services;

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
        return purchaseOrderItemRepository.save(purchaseOrderItem);
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
}
