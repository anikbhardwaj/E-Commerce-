package com.ecomm.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.request.AddRoleRequest;
import com.ecomm.user.response.ApiResponse;
import com.ecomm.user.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService rservice;
	
	@PostMapping("/add")
	public ResponseEntity<?> addRole(@Valid @RequestBody AddRoleRequest request){
		RoleDto dto=rservice.addRole(request.getRoleName());
		return ResponseEntity.ok(new ApiResponse<>("Role added successfully!",dto,HttpStatus.OK));
     }
}
