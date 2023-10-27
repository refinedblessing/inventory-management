package com.sams.inventorymanagement.dto;

import com.sams.inventorymanagement.entities.Item;
import com.sams.inventorymanagement.entities.PurchaseOrderItem;
import lombok.Getter;

@Getter
public class PurchaseOrderItemDTO {
    private Long id;
    private Item item;
    private Integer quantity;
    private PurchaseOrderDTO purchaseOrder;

    public PurchaseOrderItemDTO(Long id, Item item, Integer quantity, PurchaseOrderDTO purchaseOrder) {
        this.id = id;
        this.item = item;
        this.quantity = quantity;
        this.purchaseOrder = purchaseOrder;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPurchaseOrder(PurchaseOrderDTO purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public static PurchaseOrderItemDTO fromPurchaseOrderItem(PurchaseOrderItem purchaseOrderItem) {
        PurchaseOrderDTO purchaseOrderDTO = new PurchaseOrderDTO(purchaseOrderItem.getPurchaseOrder().getId());
        return new PurchaseOrderItemDTO(purchaseOrderItem.getId(), purchaseOrderItem.getItem(), purchaseOrderItem.getQuantity(), purchaseOrderDTO);
    }
}
