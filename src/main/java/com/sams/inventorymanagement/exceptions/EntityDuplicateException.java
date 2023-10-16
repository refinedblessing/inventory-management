package com.sams.inventorymanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when an attempt to create or modify an entity results in a duplicate entity.
 * This typically occurs when creating an entity with a unique constraint that already exists.
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class EntityDuplicateException extends RuntimeException {

    /**
     * Constructs a new EntityDuplicateException with the specified detail message.
     *
     * @param message The detail message explaining the exception.
     */
    public EntityDuplicateException(String message) {
        super(message);
    }
}
