package com.ecomm.user.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.response.CloudinaryResponse;
import com.ecomm.user.service.CloudinaryService;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	@Autowired
	private Cloudinary cloudinary;
	
	
	
	@Override
	public CloudinaryResponse uploadImage(MultipartFile image) {
		
		CloudinaryResponse cloudinaryResponse=null;
		if(image!=null&&!image.isEmpty()) {
			try {
				Map<?,?> cloudResponse=cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
				String publicId=(String)cloudResponse.get("public_id");
				String imageUrl=(String)cloudResponse.get("secure_url");
				cloudinaryResponse=new CloudinaryResponse(imageUrl, publicId);
		
				
			} catch (IOException e) {
				throw new AppException("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		else {
			throw new AppException("Image Not Found!", HttpStatus.NOT_FOUND);
		}
		return cloudinaryResponse;
	}

	@Override
	public void deleteImage(String publicUrl) {
		try {
			Map<?,?> result=cloudinary.uploader().destroy(publicUrl, ObjectUtils.emptyMap());
			if(!result.get("result").equals("ok")) {
				throw new AppException("Failed to delete the Image!", HttpStatus.EXPECTATION_FAILED);
			}
			
		} catch (IOException e) {
			throw new AppException("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
