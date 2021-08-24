package com.backend.api.config.security;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.User;
import com.backend.api.repositories.UserRepository;
import com.backend.api.security.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private final JWTUtil jwtUtil;

  private final UserRepository userRepository;

  private final UserDetailsService userDetailsService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                                UserDetailsService userDetailsService, UserRepository userRepository) {
    super(authenticationManager);
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
    this.userRepository = userRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String rawToken = header.substring(7);

      UsernamePasswordAuthenticationToken auth = getAuthentication(rawToken);
      if (auth != null) {
        User user = (User) auth.getPrincipal();
        Long id = user.getId();

        if(id == null) {
          chain.doFilter(request, response);
          return;
        }

        SecurityContextHolder.getContext().setAuthentication(auth);

      }
    }
    chain.doFilter(request, response);
  }


  private UsernamePasswordAuthenticationToken getAuthentication(String token) {
    if (jwtUtil.isValidToken(token)) {
      String username = jwtUtil.getUsername(token);
      User user = userRepository.findByEmail(username);
      return new UserAuthentication(user);
    }
    return null;
  }
}
