package com.backend.api.config.security;

import com.backend.api.config.security.permission.UserAuthentication;
import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.exceptions.ObjectNotFoundException;
import com.backend.api.repositories.CompanyRepository;
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

  private final CompanyRepository companyRepository;

  private final UserDetailsService userDetailsService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil,
                                UserDetailsService userDetailsService, UserRepository userRepository, CompanyRepository companyRepository) {
    super(authenticationManager);
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
    this.userRepository = userRepository;
    this.companyRepository = companyRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String rawToken = header.substring(7);

      Long company = Long.valueOf(request.getHeader("company"));
      UsernamePasswordAuthenticationToken auth = getAuthentication(rawToken, company);
      if (auth != null) {
        User user = (User) auth.getPrincipal();
        Long id = user.getId();

        if (id == null) {
          chain.doFilter(request, response);
          return;
        }

        SecurityContextHolder.getContext().setAuthentication(auth);

      }
    }
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(String token, Long companyId) {
    if (jwtUtil.isValidToken(token)) {
      String username = jwtUtil.getUsername(token);
      User user = userRepository.findByEmail(username);
      Company company = companyId == -1 ? new Company() : companyId == 0L ? null : companyRepository.findById(companyId)
          .orElseThrow(() -> new ObjectNotFoundException("Empresa não encontrada"));
      return new UserAuthentication(user, company);
    }
    return null;
  }
}
