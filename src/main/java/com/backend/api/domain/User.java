package com.backend.api.domain;

import com.backend.api.domain.enums.Profile;
import com.backend.api.domain.enums.UserSituation;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User extends Base {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private String email;
    
    @NotNull
    private String password;

    @NotNull
    private Integer situation;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<UserProfile> userProfiles = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Company> companies = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "user_routine", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "routine_id"))
    private Set<Routine> routines = new HashSet<>();

    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PROFILES")
	private final Set<Integer> profiles = new HashSet<>();

    public User() {
    }

    public User(Integer id, String name, String email, String password, UserSituation situation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.situation = situation == null ? null : situation.getCod();
        addProfile(Profile.CLIENTE);
    }


    public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		profiles.add(profile.getCod());
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserSituation getSituation() {
        return UserSituation.toEnum(situation);
    }

    public void setSituation(UserSituation situation) {
        this.situation = situation.getCod();
    }

    public Set<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(Set<UserProfile> profiles) {
        this.userProfiles = profiles;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

}