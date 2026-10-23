package com.ecomm.user.request;

import java.time.LocalDate;

import com.ecomm.user.enumm.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "Email Id cannot be empty")
    @Email(message = "Please enter a valid email Id")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password should not be less than 6 and more than 20 characters")
    private String password;

    @NotBlank(message = "Please enter firstName")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name should contain only alphabets")
    private String firstName;

    @NotBlank(message = "Please enter lastName")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Last name should contain only alphabets")
    private String lastName;

    @NotNull(message = "Please enter DOB")
    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotBlank(message = "Please enter phoneNo")
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number must be a valid 10 digit Indian mobile number")
    private String phone;

    @NotNull(message = "Please select a role")
    private RoleType roleName;
}