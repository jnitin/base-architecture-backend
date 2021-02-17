package com.backend.api.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Routine extends Base {

    private static final long serialVersionUID = 1L;

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // protected Long id;

    private String description;
    private Integer code;

    @ManyToMany(mappedBy = "routines")
    private List<User> users = new ArrayList<>();

    @ManyToMany(mappedBy = "routines")
    private List<UserProfile> profiles = new ArrayList<>();

    public Routine() {
    }

    public Routine(Long id, String description, Integer code) {
        this.id = id;
        this.description = description;
        this.code = code;
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

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<UserProfile> getUserProfiles() {
        return profiles;
    }

    public void setUserProfiles(List<UserProfile> profiles) {
        this.profiles = profiles;
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
        Routine other = (Routine) obj;
        if (id == null) {
            return other.id == null;
        } else return id.equals(other.id);
    }

    @Override
    public String toString() {
        return "Routine [code=" + code + ", description=" + description + ", id=" + id + "]";
    }

}