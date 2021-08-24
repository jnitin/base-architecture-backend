package com.backend.api.domain;

import com.backend.api.domain.enums.Profile;
import com.backend.api.domain.enums.UserSituation;
import com.backend.api.domain.enums.UserType;
import com.backend.api.enums.Permission;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Base {
  @NotNull
  private String name;

  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  private UserSituation situation;

  @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
  private Set<UserProfile> userProfiles;

  @ManyToMany(mappedBy = "users")
  private Set<Company> companies;

  @ManyToMany
  @JoinTable(name = "user_routine", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "routine_id"))
  private Set<Routine> routines;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "PROFILES")
  private final Set<Integer> profiles = new HashSet<>();

  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  private List<Role> roles;

  @Column(length = 20, nullable = false)
  @Enumerated(EnumType.STRING)
  private UserType type;

  public User() {
  }


  public Set<Profile> getProfiles() {
    return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
  }

  public void addProfile(Profile profile) {
    profiles.add(profile.getCod());
  }

  public UserProfile getMaxUserProfileLevel() {
    UserProfile ret = null;
    int max = 0;
    for (UserProfile up : userProfiles) {
      if (up.getLevel() > max) {
        ret = up;
        max = up.getLevel();
      }
    }
    return ret;
  }

  public Set<Permission> fetchAndFlattenPermissions() {
    var permissions = getRoles().stream()
        .flatMap(role -> role.getPermissions().stream())
        .collect(Collectors.toSet());
    var hasAllPermission = permissions.stream()
        .anyMatch(permission -> permission == Permission.ALL);

    if (hasAllPermission) {
      return Set.of(Permission.values());
    }

    return permissions;
  }

}