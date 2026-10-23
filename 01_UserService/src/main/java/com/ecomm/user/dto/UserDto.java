package com.ecomm.user.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDto {

	private Integer userId;
	
	private String email;
	
	private LocalDateTime createdAt;
	
	private ProfileDto pdto;
	
	private RoleDto rdto;
}
