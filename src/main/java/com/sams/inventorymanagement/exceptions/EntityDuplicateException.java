package com.sams.inventorymanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EntityDuplicateException extends RuntimeException {
    public EntityDuplicateException(String message) {
        super(message);
    }

}
