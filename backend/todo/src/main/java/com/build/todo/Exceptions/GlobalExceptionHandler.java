package com.build.todo.Exceptions;

import com.build.todo.Dtos.ApiResponse;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(ResourceNotFoundException exc){
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NOT_FOUND.value(),exc.getMessage() )
                ,HttpStatus.NOT_FOUND);
    }

}
