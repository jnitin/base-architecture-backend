package com.backend.api.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsServiceInterface extends UserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
