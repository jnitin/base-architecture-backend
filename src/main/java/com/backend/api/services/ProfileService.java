package com.backend.api.services;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.dto.read.ReadProfileDto;
import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfileService extends CrudService<UserProfile, CreateProfileDto, ReadProfileDto, UpdateProfileDto, Filter> {
    Page<Route> findLinkedRoutes(Long id, Pageable pageable);

    Page<Route> findUnlinkedRoutes(Long id, Pageable pageable);

    Page<User> findUnlinkedUsers(Long id, Pageable pageable);

    List<UserProfile> findByIds(List<Long> ids);

    void saveProfiles(List<UserProfile> profiles);

    Page<User> findProfileUsers(Long id, Pageable pageable);

    void saveRoutes(Long id, List<Long> ids);

    void deleteRoute(Long id, Long routeId);

    void addUsers(Long id, List<Long> ids);

    void deleteUser(Long id, Long userId);
}
