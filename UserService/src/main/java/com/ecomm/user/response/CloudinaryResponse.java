package com.ecomm.user.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CloudinaryResponse {

    private String imageUrl;
	
	private String publicUrl;
}
