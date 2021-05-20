package com.backend.api.services.impl;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.read.ReadRouteDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.mapper.DataMapper;
import com.backend.api.repositories.ProfileRepository;
import com.backend.api.repositories.RouteRepository;
import com.backend.api.services.ProfileService;
import com.backend.api.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import com.backend.api.pagination.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileServiceImpl implements ProfileService {
    private final UserService userService;
    private final ProfileRepository profileRepository;
    private final RouteRepository routeRepository;
    private final DataMapper mapper;

    @Override
    public UserProfile create(CreateProfileDto createProfileDto) {
        validate(createProfileDto.getLevel());

        final var profile = toEntity(createProfileDto);
        profileRepository.save(profile);
        return profile;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserProfile findById(Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Perfil não encontrado"));
    }

    @Override
    public void update(Long id, UpdateProfileDto dto) {
        validate(dto.getLevel());
        final var profile = findById(id);
        profile.update(dto);
        profileRepository.save(profile);

    }

    @Override
    public Page<ReadProfileDto> findAll(Pageable pageable) {
        final var profiles = profileRepository.findAll(pageable.getPageable());
        return mapper.mapAllTo(profiles, ReadProfileDto.class);
    }

    @Override
    public UserProfile toEntity(CreateProfileDto createProfileDto) {
        return UserProfile
                .builder()
                .description(createProfileDto.getDescription())
                .level(createProfileDto.getLevel())
                .build();
    }

    @Override
    public Page<Route> findLinkedRoutes(Long id, Pageable pageable) {
        return profileRepository.findLinkedRoutes(id, pageable.getPageable());
    }

    @Override
    public Page<Route> findUnlinkedRoutes(Long id, Pageable pageable) {
        return profileRepository.findUnlinkedRoutes(id, pageable.getPageable());
    }

    @Override
    public Page<User> findUnlinkedUsers(Long id, Pageable pageable) {
        return profileRepository.findUnlinkedUsers(id, pageable.getPageable());

    }

    @Override
    public List<UserProfile> findByIds(List<Long> ids) {
        return profileRepository.findByIds(ids);
    }


    private void validate(Integer level) {
        final var loggedInUser = userService.getLoggedInUser();
        if (loggedInUser.getMaxUserProfileLevel().getLevel() < level) {
            throw new RuntimeException("Não é possivel criar um perfil com nível maior que o seu.");
        }
    }
}
