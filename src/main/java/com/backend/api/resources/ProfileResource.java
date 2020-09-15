package com.backend.api.resources;

import com.backend.api.domain.UserProfile;
import com.backend.api.dto.ProfileDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/profiles")
public class ProfileResource extends CrudResource<UserProfile, ProfileDTO> {

    public ProfileResource() {
        super(ProfileDTO.class);
    }

}