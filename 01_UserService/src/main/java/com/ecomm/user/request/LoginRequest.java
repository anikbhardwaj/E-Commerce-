package com.ecomm.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Email Id cannot be empty!")
    @Email(message = "Please enter a valid email Id")
    private String email;

    @NotBlank(message = "Password cannot be empty!")
    @Size(min = 6, max = 20, message = "Password should not be less than 6 and more than 20 characters")
    private String password;
}