package com.backend.api.controllers;

import com.backend.api.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

  @Autowired
  private JWTUtil jwtUtil;

  @RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
  public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
//    UserSS user = UserSSServiceImpl.authenticated();
//    assert user != null;
//    String token = jwtUtil.generateToken(user.getUsername());
//    response.addHeader("Authorization", "Bearer " + token);
//    response.addHeader("access-control-expose-headers", "Authorization");
    return ResponseEntity.noContent().build();
  }

  // @RequestMapping(value = "/forgot", method = RequestMethod.POST)
  // public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
  // 	service.sendNewPassword(objDto.getEmail());
  // 	return ResponseEntity.noContent().build();
  // }
}
