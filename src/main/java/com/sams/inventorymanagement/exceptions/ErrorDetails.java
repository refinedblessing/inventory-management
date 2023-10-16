package com.sams.inventorymanagement.exceptions;

import java.time.LocalDateTime;

/**
 * Represents details of an error that occurred in the application, including a timestamp,
 * an error message, and additional details.
 */
public class ErrorDetails {

    /**
     * The timestamp when the error occurred.
     */
    private final LocalDateTime timestamp;

    /**
     * The error message describing the nature of the error.
     */
    private final String message;

    /**
     * Additional details or context related to the error.
     */
    private final String details;

    /**
     * Constructs an instance of ErrorDetails with the provided timestamp, error message, and details.
     *
     * @param timestamp The timestamp when the error occurred.
     * @param message   The error message describing the nature of the error.
     * @param details   Additional details or context related to the error.
     */
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
