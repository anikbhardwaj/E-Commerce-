package com.ecomm.user.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.exception.AppException;
import com.ecomm.user.repository.ProfileRepository;
import com.ecomm.user.request.ProfileUpdateRequest;
import com.ecomm.user.response.CloudinaryResponse;
import com.ecomm.user.service.CloudinaryService;
import com.ecomm.user.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository prepo;
	
	@Autowired
	private CloudinaryService cservice;
	
	@Autowired
	private ModelMapper mapper;

	
	
	@Override
	public Profile addProfile(Profile profile) {
		return prepo.save(profile);
	}

	@Override
	public ProfileDto updateProfile(Integer profileId, ProfileUpdateRequest request , MultipartFile image) {
		//VALIDATING WHETHER PROFILE EXISTS OR NOT
				Profile existingProfile=prepo.findById(profileId).orElseThrow(()->new AppException("Profile Not Found!", HttpStatus.NOT_FOUND));
				
				//ADDING STRUCTURED DATA TO UPDATE
				mapper.map(request, existingProfile);
				//CHECKING IMAGE IS AVAILABLE OR NOT 
				if(image!=null&&!image.isEmpty()) {
					//CHECKING ALREADY ANY IMAGE IS UPLOADED OR NOT
					if(existingProfile.getPublicUrl()!=null) {
					    cservice.deleteImage(existingProfile.getPublicUrl());
					}
					CloudinaryResponse response=cservice.uploadImage(image);
					existingProfile.setImageUrl(response.getImageUrl());
					existingProfile.setPublicUrl(response.getPublicUrl());
				}
				existingProfile=prepo.save(existingProfile);
				return mapper.map(existingProfile, ProfileDto.class);
	}

	@Override
	public void deleteProfile(Integer profileId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ProfileDto getByProfileId(Integer profileId) {
		
		Profile p = prepo.findById(profileId)
				.orElseThrow(()->new AppException("User not found!!", HttpStatus.NOT_FOUND));
		ProfileDto pdto = mapper.map(p, ProfileDto.class);
		return pdto;
	}

	@Override
	public ProfileDto getProfileByuserid(Integer userId) {
		Profile p = prepo.findByUserUserId(userId)
				.orElseThrow(()->new AppException("User not found!!",HttpStatus.NOT_FOUND));
		ProfileDto pdto = mapper.map(p, ProfileDto.class);
		return pdto;
	}

}
