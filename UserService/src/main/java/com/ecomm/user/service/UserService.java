package com.ecomm.user.service;

import com.ecomm.user.dto.UserDto;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;

public interface UserService {
	public UserDto register(RegisterRequest request);
	
	public UserDto login(LoginRequest request);

	public UserDto getUserById(Integer userId);

}
