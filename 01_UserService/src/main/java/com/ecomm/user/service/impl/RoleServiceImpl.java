package com.ecomm.user.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.entity.Role;
import com.ecomm.user.enumm.RoleType;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.RoleRepository;
import com.ecomm.user.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository rrepo;
	
	@Autowired
	private ModelMapper mapper;
	@Override
	public RoleDto addRole(RoleType roleName) {
		Role r = rrepo.findByRoleName(roleName).orElse(null);
		if(r!=null) {
			throw new AppException("Role already exists!!",HttpStatus.CONFLICT);
		}
		r = new Role();
		r.setRoleName(roleName);
		r = rrepo.save(r);
		return mapper.map(r, RoleDto.class);
	}
	@Override
	public RoleDto getRoleByRoleName(RoleType roleName) {
		Role r=rrepo.findByRoleName(roleName).orElseThrow(()->new AppException("Role Not Found!", HttpStatus.NOT_FOUND));
		RoleDto dto=mapper.map(r,RoleDto.class);
		return dto;
	}
	@Override
	public RoleDto getRoleById(Integer roleId) {
		Role r=rrepo.findById(roleId).orElseThrow(()->new AppException("Role Not Found!", HttpStatus.NOT_FOUND));
		return mapper.map(r,RoleDto.class);
	}

}
