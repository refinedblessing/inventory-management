package com.sams.inventorymanagement.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ExceptionDetails {
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;

    public ExceptionDetails(LocalDateTime timestamp, String message, List<String> details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
