package com.ecomm.user.service;

import com.ecomm.user.dto.RoleDto;
import com.ecomm.user.enumm.RoleType;

public interface RoleService {

	RoleDto addRole(RoleType roleName);
	
	RoleDto getRoleByRoleName(RoleType roleName);
	
	RoleDto getRoleById(Integer roleId);
}
