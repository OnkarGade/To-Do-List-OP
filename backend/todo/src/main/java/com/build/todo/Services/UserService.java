package com.build.todo.Services;

import com.build.todo.Dtos.ApiResponse;
import com.build.todo.Dtos.UserDto;
import com.build.todo.Exceptions.ResourceNotFoundException;
import com.build.todo.Models.User;
import com.build.todo.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ModelMapper modelMapper;

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public ApiResponse<User> getUser(Long id){
        return new ApiResponse<>(HttpStatus.FOUND.value(), "Found The Resource", userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No User found with id "+id)));
    }

    public ApiResponse<User> createUser(UserDto userDto){
        User user = modelMapper.map(userDto, User.class);
        user.setAvailable(true);
        userRepo.save(user);
        return new ApiResponse<>(HttpStatus.CREATED.value(),"User Created Successfully", user);
    }

    public ApiResponse<User> deleteUser(Long id){
        User user = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User with id"+id+" not found for deletion."));
        user.setAvailable(false);
        userRepo.save(user);
        return new ApiResponse<>(HttpStatus.OK.value(),"User Deleted Successfully", user);
    }

    public ApiResponse<User> updateUser(UserDto userDto){

        User user = modelMapper.map(userDto, User.class);
        User u = userRepo.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("User with id"+user.getId()+" not found for Updation."));
        u.setEmail(user.getEmail());
        u.setContactNo(user.getContactNo());
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setPassword(user.getPassword());
        u.setTask(user.getTask());

        userRepo.save(u);

        return new ApiResponse<User>(HttpStatus.OK.value(),"User Has Been Updated", u);
    }

}
