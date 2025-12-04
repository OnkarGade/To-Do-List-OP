package com.build.todo.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
    private T data;
    private Boolean isSuccess;

    // Constructor for Success Responses
    public ApiResponse(Integer status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
        this.isSuccess = true;
    }

    // Constructor for failed Response
    public ApiResponse(Integer status, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.isSuccess = false;
        this.data = null;
    }
}