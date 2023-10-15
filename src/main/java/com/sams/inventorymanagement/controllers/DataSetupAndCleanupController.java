package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.services.DataCleanupService;
import com.sams.inventorymanagement.services.DataSetupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataSetupAndCleanupController {

    private final DataSetupService dataSetupService;
    private final DataCleanupService dataCleanupService;

    public DataSetupAndCleanupController(DataSetupService dataSetupService, DataCleanupService dataCleanupService) {
        this.dataSetupService = dataSetupService;
        this.dataCleanupService = dataCleanupService;
    }

    @GetMapping("/setup")
    public String setupData() {
        dataSetupService.setupData();
        return "Data setup completed.";
    }

    @GetMapping("/cleanup")
    public String cleanupData() {
        dataCleanupService.cleanupAllData();
        return "Data cleanup completed.";
    }
}
