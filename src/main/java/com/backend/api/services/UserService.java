package com.backend.api.services;

import com.backend.api.domain.User;
import com.backend.api.domain.UserProfile;
import com.backend.api.dto.create.CreateUserDto;
import com.backend.api.dto.read.ReadUserDto;
import com.backend.api.dto.update.UpdateUserDto;
import com.backend.api.pagination.Filter;
import com.backend.api.pagination.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService extends CrudService<User, CreateUserDto, ReadUserDto, UpdateUserDto, Filter> {
    List<UserProfile> getUserProfiles(Long id);

    Page<UserProfile> getUserProfiles(Long id, Pageable pageable);

    User getLoggedInUser();
}
