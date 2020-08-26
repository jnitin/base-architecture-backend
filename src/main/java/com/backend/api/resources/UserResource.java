package com.backend.api.resources;

import com.backend.api.domain.User;
import com.backend.api.dto.UserDTO;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UserResource extends CrudResource<User, UserDTO> {

}