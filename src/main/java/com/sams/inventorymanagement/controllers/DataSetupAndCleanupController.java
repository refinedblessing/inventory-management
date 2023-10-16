package com.sams.inventorymanagement.controllers;

import com.sams.inventorymanagement.services.DataCleanupService;
import com.sams.inventorymanagement.services.DataSetupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for setting up and cleaning up data.
 */
@RestController
public class DataSetupAndCleanupController {

    /**
     * Service for setting up data.
     */
    private final DataSetupService dataSetupService;

    /**
     * Service for cleaning up data.
     */
    private final DataCleanupService dataCleanupService;

    /**
     * Constructs a new DataSetupAndCleanupController.
     *
     * @param dataSetupService   The service for setting up data.
     * @param dataCleanupService The service for cleaning up data.
     */
    public DataSetupAndCleanupController(DataSetupService dataSetupService, DataCleanupService dataCleanupService) {
        this.dataSetupService = dataSetupService;
        this.dataCleanupService = dataCleanupService;
    }

    /**
     * Endpoint to set up data.
     *
     * @return A message indicating that data setup is completed.
     */
    @GetMapping("/setup")
    public String setupData() {
        dataSetupService.setupData();
        return "Data setup completed.";
    }

    /**
     * Endpoint to clean up data.
     *
     * @return A message indicating that data cleanup is completed.
     */
    @GetMapping("/cleanup")
    public String cleanupData() {
        dataCleanupService.cleanupAllData();
        return "Data cleanup completed.";
    }
}
