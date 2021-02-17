package com.backend.api.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserProfile extends Base {

    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // protected Long id;

    private String description;
    private Integer level;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_route", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "route_id"))
    private Set<Route> routes = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "profile_routine", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "routine_id"))
    private Set<Routine> routines = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "profile_user", joinColumns = @JoinColumn(name = "profile_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public UserProfile() {
    }

    public UserProfile(Long id, String description, Integer level) {
        this.id = id;
        this.description = description;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Set<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Set<Route> routes) {
        this.routes = routes;
    }

    public Set<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(Set<Routine> routines) {
        this.routines = routines;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
        UserProfile other = (UserProfile) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Profile [description=" + description + ", id=" + id + "]";
    }

}