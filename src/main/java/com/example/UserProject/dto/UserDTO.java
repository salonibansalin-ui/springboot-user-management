package com.example.UserProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Min(value = 18, message = "Age must be greater than or equal to 18")
    private int age;

    @NotBlank(message="Password cannot be empty")
    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;

    private String role;






}
