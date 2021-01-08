package com.backend.api.services;

import com.backend.api.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSSService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
