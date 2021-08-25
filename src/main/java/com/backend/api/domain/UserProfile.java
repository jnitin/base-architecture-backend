package com.backend.api.domain;

import com.backend.api.dto.update.UpdateProfileDto;
import com.backend.api.enums.Permission;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfile extends Base {
    private String description;
    private Integer level;

    @NotNull
    @OneToOne
    Role role;


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