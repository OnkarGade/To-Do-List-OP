package com.build.todo.Services;

import com.build.todo.Dtos.ApiResponse;
import com.build.todo.Exceptions.ResourceNotFoundException;
import com.build.todo.Models.Task;
import com.build.todo.Repositories.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    public List<Task> getAllTask(){
        return todoListRepository.findAll();
    }

    public ApiResponse<Task> getTask(Long id){
        return new ApiResponse<>(HttpStatus.OK.value(),"Task Found Successfully",todoListRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task with id "+id+" not found.")));
    }

    public ApiResponse<Task> createTask(Task task){
        task.setAvailable(true);
        todoListRepository.save(task);
        return new ApiResponse<>(HttpStatus.CREATED.value(), "Task has been added to list", task);
    }

    public ApiResponse<Task> deleteTask(Long id){
        Task task = todoListRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Task with id "+id+" not found for deletion."));
        task.setAvailable(false);
        todoListRepository.save(task);
        return new ApiResponse<>(HttpStatus.OK.value(), "Task Has Been Deleted", task);
    }

    public ApiResponse<Task> updateTask(Task task){
        Task t = todoListRepository.findById(task.getId()).orElseThrow(()-> new ResourceNotFoundException("Task with id "+task.getId()+" not found for updation. "));
        t.setTaskDetails(task.getTaskDetails());
        t.setEndsOn(task.getEndsOn());
        t.setStartOn(task.getStartOn());

        return new ApiResponse<>(HttpStatus.OK.value(),"Task Has Been Updated", t);
    }

}
