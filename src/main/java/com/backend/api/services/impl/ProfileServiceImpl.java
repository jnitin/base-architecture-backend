package com.backend.api.services.impl;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.repositories.ProfileRepository;
import com.backend.api.repositories.RouteRepository;
import com.backend.api.services.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.backend.api.pagination.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final RouteRepository routeRepository;

    @Override
    public UserProfile create(CreateProfileDto createProfileDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserProfile findById(Long id) {
        return null;
    }

    @Override
    public void update(Long id, Object o) {

    }

    @Override
    public Page<Object> findAll(Pageable pageable) {
        return null;
    }

    public Page<Route> getRoutes(Long id, Pageable pageable) {
        return profileRepository.getRoutes(id, pageable);
    }

    public List<Route> getRoutes(Long id) {
        return profileRepository.getRoutes(id);
    }

    public Page<User> getUsers(Long id, Pageable pageable) {
        return profileRepository.getUsers(id, pageable);
    }

    public List<User> getUsers(Long id) {
        return profileRepository.getUsers(id);
    }
}
