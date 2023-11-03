package com.sams.inventorymanagement.dto;

import com.sams.inventorymanagement.entities.Item;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class HomePageStatsDTO {
    private long totalStores;
    private long totalInventories;
    private long totalPurchaseOrders;
    private long totalItems;
    private long pendingPurchaseOrders;
    private long deliveredPurchaseOrders;
    private long inventoriesAtThreshold;
    private Item mostPopularItemInPurchaseOrders;
    private Item mostPopularItemInInventory;
}
