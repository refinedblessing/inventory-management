package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.dto.HomePageStatsDTO;
import com.sams.inventorymanagement.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/home")
    public HomePageStatsDTO getHomePageStats() {
        return statsService.getHomePageStats();
    }

//    @GetMapping("/store/{storeId}")
//    public ResponseEntity<StorePageStatsDTO> getStoreStats(@PathVariable Long storeId) {
//
//        return ResponseEntity.ok();
//    }
}
