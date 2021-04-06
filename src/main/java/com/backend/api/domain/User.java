package com.backend.api.domain;

import com.backend.api.domain.enums.Profile;
import com.backend.api.domain.enums.UserSituation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
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

    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PROFILES")
	private final Set<Integer> profiles = new HashSet<>();

    public User() {
    }


    public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		profiles.add(profile.getCod());
    }

}