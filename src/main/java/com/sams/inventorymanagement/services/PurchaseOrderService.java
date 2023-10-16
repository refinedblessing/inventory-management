package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.entities.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder createPurchaseOrder(PurchaseOrder purchaseOrder);

    PurchaseOrder getPurchaseOrderById(Long id);

    List<PurchaseOrder> getAllPurchaseOrders();

    void deletePurchaseOrder(Long id);
}
