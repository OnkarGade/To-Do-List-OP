package com.build.todo.Exceptions;

import com.build.todo.Dtos.ApiResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(ResourceNotFoundException exc){
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),exc.getMessage(), null, false )
                ,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // 1. Collect all individual error messages into a List<String>
        List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                // Adding only the error message text
                errorMessages.add(error.getDefaultMessage()));

        // 2. Create a Map with the single, static key ("messages") pointing to the list of errors
        Map<String, List<String>> errorDataPayload = new HashMap<>();
        errorDataPayload.put("messages", errorMessages); // <-- FIXED KEY HERE

        // 3. IMPORTANT: Ensure your ApiResponse constructor sets isSuccess to FALSE for 400 status.
        ApiResponse<Object> errorBody = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(), // 400 Bad Request
                errorMessages.get(0), // Updated main message
                null, // Pass the map with the static key,
                false
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
            System.out.println(rootCause);
            if (rootCause.contains("email")) {
                userMessage = "A user with this email address already exists. Please use a different one.";
            } else if (rootCause.contains("UK6k4ftquj3orb1mqaw40e0cn2o")) {
                userMessage = "A user with this contact number already exists. Please use a different one.";
            } else {
                userMessage = "The email or contact no are already present used once.";
            }
        } else {
            userMessage = "Database constraint violation occurred.";
        }

        // 2. USE THE ERROR CONSTRUCTOR / Ensure isSuccess is FALSE
        ApiResponse<Object> errorBody = new ApiResponse<>(
                HttpStatus.CONFLICT.value(), // 409 CONFLICT
                userMessage,
                null,
                false
                // The specific, user-friendly message
                // Note: If you use the constructor with only status and message,
                // your DTO must be coded to set isSuccess=false and data=null.
        );

        // 3. Return the ResponseEntity with the 409 status
        return new ResponseEntity<>(errorBody, HttpStatus.CONFLICT);
    }

}
