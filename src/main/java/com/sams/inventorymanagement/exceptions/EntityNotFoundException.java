package com.sams.inventorymanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an entity is not found in the system. This typically occurs
 * when attempting to retrieve or manipulate an entity that does not exist.
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new EntityNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}
