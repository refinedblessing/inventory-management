package com.sams.inventorymanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This exception is thrown when an invalid status transition is attempted in the application.
 * It is annotated with @ResponseStatus(CONFLICT) to indicate an HTTP 409 (Conflict) status code.
 */
@ResponseStatus(code = HttpStatus.CONFLICT)
public class InvalidStatusTransitionException extends RuntimeException {

    /**
     * Constructs an instance of InvalidStatusTransitionException with the provided error message.
     *
     * @param message The error message describing the invalid status transition.
     */
    public InvalidStatusTransitionException(String message) {
        super(message);
    }
}
