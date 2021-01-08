package com.backend.api.services;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;
import com.backend.api.repositories.ProfileRepository;
import com.backend.api.repositories.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService extends CrudService<UserProfile, ProfileDTO> {

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	RouteRepository routeRepository;

	public ProfileService() {
		super(UserProfile.class, ProfileDTO.class);
	}

	@Override
	public UserProfile fromDTO(ProfileDTO dto) {
		if (dto == null) {
			return null;
		}
		final UserProfile obj = new UserProfile(dto.getId(), dto.getDescription(), dto.getLevel());
		return obj;
	}

	@Override
	public ProfileDTO toDTO(UserProfile obj) {
		if (obj == null) {
			return null;
		}
		final ProfileDTO dto = new ProfileDTO(obj.getId(), obj.getDescription(), obj.getLevel());
		return dto;
	}

	public Page<Route> getRoutes(Integer id, PageRequest pageRequest) {
		return profileRepository.getRoutes(id, pageRequest);
	}

	public List<Route> getRoutes(Integer id) {
		return profileRepository.getRoutes(id);
	}

	public Page<User> getUsers(Integer id, PageRequest pageRequest) {
		return profileRepository.getUsers(id, pageRequest);
	}

	public List<User> getUsers(Integer id) {
		return profileRepository.getUsers(id);
	}

}
