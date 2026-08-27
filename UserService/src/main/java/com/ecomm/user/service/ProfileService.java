package com.ecomm.user.service;

import org.springframework.web.multipart.MultipartFile;

import com.ecomm.user.dto.ProfileDto;
import com.ecomm.user.entity.Profile;
import com.ecomm.user.request.ProfileUpdateRequest;

public interface ProfileService {

	Profile addProfile(Profile profile);
	
    ProfileDto updateProfile(Integer profileId,ProfileUpdateRequest request , MultipartFile image);
    
    void deleteProfile(Integer profileId);
    
    ProfileDto getByProfileId(Integer profileId);
    
    ProfileDto getProfileByuserid(Integer userId);
}

