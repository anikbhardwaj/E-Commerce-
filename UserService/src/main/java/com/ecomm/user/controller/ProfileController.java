package com.ecomm.user.controller;


import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.request.ProfileUpdateRequest;
import com.ecomm.user.response.ApiResponse;
import com.ecomm.user.service.ProfileService;


@RestController
@RequestMapping("/profile")
public class ProfileController {

	@Autowired
	private ProfileService pservice;
	
	@PutMapping(value="/update/{profileId}",consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> updateProfile(
			@PathVariable Integer profileId,
			@RequestParam String firstName,
			@RequestParam String lastName,
			@RequestParam String phone,
			@RequestParam LocalDate dob,
			@RequestPart("image") MultipartFile image
			){
		ProfileUpdateRequest request=new ProfileUpdateRequest();
		request.setFirstName(firstName);
		request.setPhone(phone);
		request.setLastName(lastName);
		request.setDob(dob);
		ProfileDto dto=pservice.updateProfile(profileId, request, image);
		ApiResponse response=new ApiResponse<>("Profile Updated Successfully!", dto, HttpStatus.OK);
		return ResponseEntity.ok(response);
	}
	
}
