package com.ecomm.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.user.response.CloudinaryResponse;

public interface CloudinaryService {

    CloudinaryResponse uploadImage(MultipartFile image);
	
	void deleteImage(String publicId);
}
