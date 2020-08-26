package com.backend.api.services;

import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;

import org.springframework.stereotype.Service;

@Service
public class ProfileService extends CrudService<UserProfile, ProfileDTO> {

	@Override
	public UserProfile fromDTO(ProfileDTO dto) {
		if (dto == null) {
			return null;
		}
		final UserProfile obj = new UserProfile(null, dto.getDescription(), dto.getLevel());
		return obj;
	}

	@Override
	public ProfileDTO toDTO(UserProfile obj) {
		if (obj == null) {
			return null;
		}
		final ProfileDTO dto = new ProfileDTO(obj.getDescription(), obj.getLevel());
		return dto;
	}

}
