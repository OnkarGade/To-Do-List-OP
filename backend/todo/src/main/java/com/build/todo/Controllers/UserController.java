package com.build.todo.Controllers;

import com.build.todo.Dtos.ApiResponse;
import com.build.todo.Models.User;
import com.build.todo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ApiResponse<List<User>> getAllUsers(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Request Has Been Processed Successfully", userService.getUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user){

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/user")
    public ResponseEntity<ApiResponse<User>> updateUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(user));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }

}
