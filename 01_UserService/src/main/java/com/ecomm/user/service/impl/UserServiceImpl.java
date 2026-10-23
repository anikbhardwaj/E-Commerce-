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
import com.ecomm.user.service.MailSenderService;
import com.ecomm.user.service.ProfileService;
import com.ecomm.user.service.RoleService;
import com.ecomm.user.service.UserService;

import jakarta.transaction.Transactional;


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

	@Autowired MailSenderService mservice;
	
	@Transactional
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
		
		mservice.sendmail(request.getEmail(), "Welcome to E-Commerce",
				request.getFirstName()+" " + "your account Created Sucessfully");
		
		UserDto dto = mapper.map(u, UserDto.class);
		ProfileDto pdto=mapper.map(p, ProfileDto.class);
		dto.setPdto(pdto);
		dto.setRdto(existingRole);
		System.out.println("========== REGISTER CALLED ==========");
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
		mservice.sendmail(request.getEmail(), "E-commerce"," "+ "your Account Login Successfully");
        
		UserDto dto = mapper.map(alreadyExists, UserDto.class);
		ProfileDto pdto=pservice.getProfileByuserid(dto.getUserId());
		dto.setPdto(pdto);
		return dto;
	}

	@Override
	public UserDto getByUserUserId(Integer userId) {
		User u = urepo.findById(userId).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
		ProfileDto pdto=pservice.getProfileByuserid(userId);
		RoleDto rdto=rservice.getRoleById(userId);
		UserDto dto=mapper.map(u, UserDto.class);
		dto.setPdto(pdto);
		dto.setRdto(rdto);
		
		return dto;
	}

}
