package com.build.todo.Controllers;

import com.build.todo.Dtos.ApiResponse;
import com.build.todo.Models.Task;
import com.build.todo.Services.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TodoListApiController {

    @Autowired
    TodoListService todoListService;

    @GetMapping("/task")
    public ApiResponse<List<Task>> getAllTasks(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Request Processed Successfully", todoListService.getAllTask());
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<ApiResponse<Task>> getTask(@PathVariable Long id){
        return ResponseEntity.ok(todoListService.getTask(id));
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<ApiResponse<Task>> deleteTask(@PathVariable Long id){
        return ResponseEntity.ok(todoListService.deleteTask(id));
    }

    @PostMapping("/task")
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody Task task){
        return ResponseEntity.status(HttpStatus.CREATED).body(todoListService.createTask(task));
    }

    @PutMapping("/task")
    public ResponseEntity<ApiResponse<Task>> updateTask(@RequestBody Task task){
        return ResponseEntity.status(HttpStatus.OK).body(todoListService.updateTask(task));
    }



}
