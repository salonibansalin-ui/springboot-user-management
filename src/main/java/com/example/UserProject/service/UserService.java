package com.example.UserProject.service;

import com.example.UserProject.dto.UserDTO;
import com.example.UserProject.entity.User;
import com.example.UserProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    public User createUser(UserDTO dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        // here password is getting encoded using Bcrypt
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        if(dto.getRole() == null || dto.getRole().isEmpty()){
            user.setRole("ROLE_USER");
        } else {
            user.setRole(dto.getRole());
        }

        return userRepository.save(user);
    }
    public User getUserById(int id){
        return userRepository.findById(id).orElse(null);
    }
    public User updateUser(User user){
        return userRepository.save(user);
    }
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
