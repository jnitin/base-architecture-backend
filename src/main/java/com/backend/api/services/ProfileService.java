package com.backend.api.services;

import com.backend.api.domain.Route;
import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateProfileDto;
import com.backend.api.pagination.Filter;
import org.springframework.data.domain.Page;
import com.backend.api.pagination.Pageable;

import java.util.List;

public interface ProfileService extends CrudService<UserProfile, CreateProfileDto, Object, Object, Filter> {
    Page<Route> getRoutes(Long id, Pageable pageable);
    List<Route> getRoutes(Long id);
    Page<User> getUsers(Long id, Pageable pageable);
    List<User> getUsers(Long id);
}
