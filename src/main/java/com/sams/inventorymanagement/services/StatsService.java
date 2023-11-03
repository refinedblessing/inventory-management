package com.sams.inventorymanagement.services;

import com.sams.inventorymanagement.dto.HomePageStatsDTO;
import com.sams.inventorymanagement.dto.StorePageStatsDTO;
import com.sams.inventorymanagement.enums.OrderStatus;
import com.sams.inventorymanagement.repositories.InventoryRepository;
import com.sams.inventorymanagement.repositories.ItemRepository;
import com.sams.inventorymanagement.repositories.PurchaseOrderRepository;
import com.sams.inventorymanagement.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private ItemRepository itemRepository;

    public HomePageStatsDTO getHomePageStats() {
        HomePageStatsDTO stats = new HomePageStatsDTO();
        stats.setTotalStores(storeRepository.count());
        stats.setTotalInventories(inventoryRepository.count());
        stats.setTotalPurchaseOrders(purchaseOrderRepository.count());
        stats.setTotalItems(itemRepository.count());
        stats.setPendingPurchaseOrders(purchaseOrderRepository.countByStatus(OrderStatus.PENDING));
        stats.setDeliveredPurchaseOrders(purchaseOrderRepository.countByStatus(OrderStatus.DELIVERED));
        stats.setInventoriesAtThreshold(inventoryRepository.countByQuantityAtThreshold());
        stats.setMostPopularItemInPurchaseOrders(itemRepository.findMostPopularItemInPurchaseOrders());
        stats.setMostPopularItemInInventory(itemRepository.findMostPopularItemInInventory());
        return stats;
    }


    public StorePageStatsDTO getStorePageStats(Long storeId) {
        StorePageStatsDTO statsDTO = new StorePageStatsDTO();

        // Total items in store inventory
        statsDTO.setTotalItemsInInventory(inventoryRepository.countTotalItemsInStoreInventory(storeId));

        // Total store worth
        statsDTO.setTotalStoreWorth(inventoryRepository.calculateTotalStoreWorth(storeId));

        // All pending purchase orders count
        statsDTO.setPendingPurchaseOrderCount(purchaseOrderRepository.countByStoreIdAndStatus(storeId, OrderStatus.PENDING));

        // All inventories at threshold count
        statsDTO.setInventoriesAtThresholdCount(inventoryRepository.countByStoreIdAndQuantityAtThreshold(storeId));

        return statsDTO;
    }
}

