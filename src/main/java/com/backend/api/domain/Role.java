package com.backend.api.domain;

import com.backend.api.enums.Permission;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Role extends Base {

  private static final long serialVersionUID = 6576788537364875554L;

  @Column(length = 30, nullable = false)
  @Size(max = 30)
  private String name;

  @CollectionTable(
      joinColumns = {
          @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
      },
      name = "role_permission"
  )
  @Column(name = "permission", nullable = false)
  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  @Singular
  private Set<Permission> permissions;

  @OneToOne
  UserProfile userProfile;

  @ManyToMany(mappedBy = "roles")
  private List<Company> companies = new ArrayList<>();

}
