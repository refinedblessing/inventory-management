package com.sams.inventorymanagement.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class StorePageStatsDTO {
    private long totalItemsInInventory;
    private double totalStoreWorth;
    private long pendingPurchaseOrderCount;
    private long inventoriesAtThresholdCount;
}
