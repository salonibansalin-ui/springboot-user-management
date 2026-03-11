package com.example.UserProject.controller;

import com.example.UserProject.dto.UserDTO;
import com.example.UserProject.entity.User;
import com.example.UserProject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //create user
    @PostMapping
    public User createUser(@Valid @RequestBody UserDTO userDTO){
        return userService.createUser(userDTO);
    }
    // GET ALL USERS
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    // GET USER BY ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }
    // UPDATE USER
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user){
        user.setId(id);
        return userService.updateUser(user);
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}
