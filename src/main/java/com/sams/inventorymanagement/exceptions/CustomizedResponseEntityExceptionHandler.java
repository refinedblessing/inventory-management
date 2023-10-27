package com.sams.inventorymanagement.exceptions;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Customized Response Entity Exception Handler for handling exceptions in the application.
 */
@ControllerAdvice
@Log4j2
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    /**
     * Handle exceptions of type ValidationException.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<Object> handleDuplicateCategoryException(ValidationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionDetails error = new ExceptionDetails(LocalDateTime.now(), "Duplicate Entry", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<Object> handleConstraintViolationException(ValidationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionDetails error = new ExceptionDetails(LocalDateTime.now(), "Violates Data Integrity", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle exceptions of type EntityDuplicateException.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(EntityDuplicateException.class)
    public final ResponseEntity<Object> handleDuplicateCategoryException(EntityDuplicateException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionDetails error = new ExceptionDetails(LocalDateTime.now(), "Duplicate Entry", details);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Handle exceptions of type EntityNotFoundException.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     * @throws Exception An exception.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<Object> handleItemNotFoundException(Exception ex, WebRequest request) throws Exception {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionDetails error = new ExceptionDetails(LocalDateTime.now(), "Entity Not Found", details);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }

        ExceptionDetails error = new ExceptionDetails(LocalDateTime.now(), "Validation Failed.", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle exceptions of type Exception.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     * @throws Exception An exception.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        List<String> details = new ArrayList<>();
        details.add(ex.getMessage());

        ExceptionDetails error = new ExceptionDetails(LocalDateTime.now(), "An Error occurred", details);

        if (ex instanceof ConstraintViolationException) {
            error.setMessage("Constraint violation error occurred.");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof DataIntegrityViolationException) {
            error.setMessage("Action violates Data Integrity");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        } else if (ex instanceof BadCredentialsException) {
            error.setMessage("Authentication Credentials rejected");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
