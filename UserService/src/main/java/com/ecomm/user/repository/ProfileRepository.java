package com.ecomm.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecomm.user.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	Optional<Profile> findByUserUserId(Integer userId);

	
	
}
