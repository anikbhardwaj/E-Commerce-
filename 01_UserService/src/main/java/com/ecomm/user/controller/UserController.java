package com.ecomm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.user.dto.UserDto;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;
import com.ecomm.user.response.ApiResponse;
import com.ecomm.user.service.UserService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService uservice;
	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request ,
			BindingResult result){
		UserDto dto=uservice.register(request);
		return ResponseEntity.ok(new ApiResponse<>("Login successful!", dto, HttpStatus.OK));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request){
		UserDto dto = uservice.login(request);
		return ResponseEntity.ok(new ApiResponse<>("Login successful!!",dto,HttpStatus.OK));
	}
	@GetMapping("/get/{uid}")
	public ResponseEntity<?>getbyid( @PathVariable Integer userId){
		   UserDto udto=uservice.getByUserUserId(userId);
		   return ResponseEntity.ok(udto);
	}
}