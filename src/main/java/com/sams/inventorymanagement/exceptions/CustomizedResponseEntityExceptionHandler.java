package com.sams.inventorymanagement.exceptions;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
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
     * Handle exceptions of type Exception.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     * @throws Exception An exception.
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(errorDetails, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle exceptions of type ValidationException.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ErrorDetails> handleDuplicateCategoryException(ValidationException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    /**
     * Handle exceptions of type EntityDuplicateException.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(EntityDuplicateException.class)
    public final ResponseEntity<ErrorDetails> handleDuplicateCategoryException(EntityDuplicateException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

    /**
     * Handle exceptions of type EntityNotFoundException.
     * @param ex The exception.
     * @param request The web request.
     * @return ResponseEntity containing error details.
     * @throws Exception An exception.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleItemNotFoundException(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
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
}
