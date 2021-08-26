package com.backend.api.config.security.permission;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.api.domain.Company;
import com.backend.api.domain.User;
import com.backend.api.enums.Permission;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode(callSuper = true)
public class UserAuthentication extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = 2851639072042759761L;

  private transient DecodedJWT decodedJwt;
  private final transient User user;
  private final Company company;

  public UserAuthentication(User user, DecodedJWT decodedJwt, Company company) {
    super(user, decodedJwt, constructAuthoritiesOfUser(user));
    this.decodedJwt = decodedJwt;
    this.user = user;
    this.company = company;
  }

  public UserAuthentication(User user, Company company) {
    super(user, null, constructAuthoritiesOfUser(user));
    this.company = company;
    this.user = user;
  }

  private static List<? extends GrantedAuthority> constructAuthoritiesOfUser(User user) {
    var permissions = user.fetchAndFlattenPermissions();
    var authorities = permissions.stream()
        .map(Permission::getAuthorityName)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    var typeAuthority = new SimpleGrantedAuthority(user.getType().getAuthorityName());

    authorities.add(typeAuthority);

    return authorities;
  }

}