package com.build.todo.Exceptions;

import com.build.todo.Dtos.ApiResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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

}
