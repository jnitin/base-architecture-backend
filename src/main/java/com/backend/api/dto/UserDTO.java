package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.enums.UserSituation;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String email;
    private String password;
    private Integer situation;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email, String password, UserSituation situation) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.situation = situation.getCod();
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDTO [name=" + name + "]";
    }

}