package com.backend.api.domain;

import com.backend.api.dto.update.UpdateProfileDto;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile extends Base {
    private String description;
    private Integer level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_route", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "route_id"))
    private Set<Route> routes;

    @ManyToMany
    @JoinTable(name = "profile_routine", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "routine_id"))
    private Set<Routine> routines;

    @ManyToMany
    @JoinTable(name = "profile_user", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

    public void update(UpdateProfileDto dto) {
        description = dto.getDescription();
        level = dto.getLevel();
    }

}