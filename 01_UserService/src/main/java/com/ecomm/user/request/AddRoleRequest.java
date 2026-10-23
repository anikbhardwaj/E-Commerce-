package com.ecomm.user.request;

import com.ecomm.user.enumm.RoleType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddRoleRequest {

	 @NotNull(message = "Role name is required")
	private RoleType roleName;
}
