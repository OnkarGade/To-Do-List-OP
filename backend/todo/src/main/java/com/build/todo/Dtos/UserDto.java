package com.build.todo.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    private boolean available;

    // Must not be null or empty, size must be between 2 and 50 characters
    @NotBlank(message = "First name is required.")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters.")
    private String firstName;

    @NotBlank(message = "Last name is required.")
    private String lastName;

    // Must be a valid email format
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be a valid format.")
    private String email;

    // Must be present and meet minimum security requirements
    @NotBlank(message = "Password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    private String password;

    // Optional: Can use @Pattern for specific contact number format/length
    @Size(max = 15, message = "Contact number cannot exceed 15 digits.")
    private String contactNo;

    // Assuming 'task' is not directly a user property but was added for a DTO test.
    // If it's required for this DTO, apply constraints.
    @NotNull(message = "Task description cannot be null.")
    private String task;
}