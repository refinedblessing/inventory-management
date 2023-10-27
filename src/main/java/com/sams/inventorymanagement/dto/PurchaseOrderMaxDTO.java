package com.sams.inventorymanagement.dto;

import com.sams.inventorymanagement.entities.PurchaseOrder;
import com.sams.inventorymanagement.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
public class PurchaseOrderMaxDTO {
    private UUID id;
    private StoreDTO store;
    private List<PurchaseOrderItemDTO> purchaseOrderItems;
    private Integer totalQuantity;
    private Double totalPrice;
    private OrderStatus status;
    private LocalDate lastUpdated;


    // Static factory method to create a PurchaseOrderDTO from a PurchaseOrder
    public static PurchaseOrderMaxDTO fromPurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderMaxDTO dto = new PurchaseOrderMaxDTO();
        dto.setId(purchaseOrder.getId());
        StoreDTO storeDTO = new StoreDTO(purchaseOrder.getStore().getId(), purchaseOrder.getStore().getName());
        dto.setStore(storeDTO);
        dto.setTotalQuantity(purchaseOrder.getTotalQuantity());
        dto.setTotalPrice(purchaseOrder.getTotalPrice());
        dto.setStatus(purchaseOrder.getStatus());
        dto.setLastUpdated(purchaseOrder.getLastUpdated());

        // Convert PurchaseOrderItems to PurchaseOrderItemDTOs
        List<PurchaseOrderItemDTO> itemDTOs = purchaseOrder.getPurchaseOrderItems().stream()
                .map(PurchaseOrderItemDTO::fromPurchaseOrderItem)
                .collect(Collectors.toList());

        dto.setPurchaseOrderItems(itemDTOs);

        return dto;
    }
}
