package com.build.todo.Exceptions;

import com.build.todo.Dtos.ApiResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(ResourceNotFoundException exc){
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),exc.getMessage() )
                ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Collect all field errors into a map or list
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponse<Object> errorBody = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), // 400 Bad Request
                "Validation failed.",
                errors // Pass the map of errors as the data payload
        );

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        String userMessage;

        // Extract the root cause message
        String rootCause = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        // 1. IMPROVED LOGIC FOR EXTRACTING FIELD
        if (rootCause.contains("Duplicate entry")) {
            if (rootCause.contains("email")) {
                userMessage = "A user with this email address already exists. Please use a different one.";
            } else if (rootCause.contains("contactNo")) {
                userMessage = "A user with this contact number already exists. Please use a different one.";
            } else {
                userMessage = "The Email Or Contact No Are Already Present Used Once.";
            }
        } else {
            userMessage = "Database constraint violation occurred.";
        }

        // 2. USE THE ERROR CONSTRUCTOR / Ensure isSuccess is FALSE
        ApiResponse<Object> errorBody = new ApiResponse<>(
                HttpStatus.CONFLICT.value(), // 409 CONFLICT
                userMessage // The specific, user-friendly message
                // Note: If you use the constructor with only status and message,
                // your DTO must be coded to set isSuccess=false and data=null.
        );

        // 3. Return the ResponseEntity with the 409 status
        return new ResponseEntity<>(errorBody, HttpStatus.CONFLICT);
    }

}
