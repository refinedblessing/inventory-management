package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.PurchaseOrderItem;

import java.util.List;
import java.util.UUID;

public interface PurchaseOrderItemService {
    PurchaseOrderItem createPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem);

    List<PurchaseOrderItem> getPurchaseOrderItemsByPurchaseOrderId(UUID purchaseOrderId);

    List<PurchaseOrderItem> getPurchaseOrderItemsByPurchaseOrderIdGroupByCategoryName(UUID purchaseOrderId);

    PurchaseOrderItem getPurchaseOrderItemById(Long id);

    List<PurchaseOrderItem> getAllPurchaseOrderItems();
}

