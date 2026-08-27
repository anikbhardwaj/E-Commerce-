package com.ecomm.user.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.dto.UserDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.entity.Role;
import com.ecomm.user.entity.User;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.UserRepository;
import com.ecomm.user.request.LoginRequest;
import com.ecomm.user.request.RegisterRequest;
import com.ecomm.user.service.ProfileService;
import com.ecomm.user.service.RoleService;
import com.ecomm.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private RoleService rservice;
	
	@Autowired
	private ProfileService pservice;

	@Override
	public UserDto register(RegisterRequest request) {
RoleDto existingRole=rservice.getRoleByRoleName(request.getRoleName());
		
		if(existingRole==null) {
			throw new AppException("Role Not Found!", HttpStatus.NOT_FOUND);
		}
		
		
		User alreadyExists = urepo.findByEmail(request.getEmail()).orElse(null);
		
		
		if (alreadyExists != null) {
			throw new AppException("User already exists!", HttpStatus.BAD_REQUEST);
		}
		User u = mapper.map(request, User.class);
		Role r=mapper.map(existingRole, Role.class);
		u.setRole(r);
		u = urepo.save(u);
		Profile p=mapper.map(request, Profile.class);
		p.setUser(u);
		p=pservice.addProfile(p);
		
		
		UserDto dto = mapper.map(u, UserDto.class);
		ProfileDto pdto=mapper.map(p, ProfileDto.class);
		dto.setPdto(pdto);
		dto.setRdto(existingRole);
		
		return dto;
	}

	@Override
	public UserDto login(LoginRequest request) {
		//validating email 
		User alreadyExists = urepo.findByEmail(request.getEmail()).orElseThrow(()
				->new AppException("User not found",HttpStatus.NOT_FOUND)); 
		//validating password
		if(!alreadyExists.getPassword().equals(request.getPassword())) {
			throw new AppException("Incorrect Password!",HttpStatus.BAD_REQUEST);
		}
		return mapper.map(alreadyExists,UserDto.class);
	}

}
