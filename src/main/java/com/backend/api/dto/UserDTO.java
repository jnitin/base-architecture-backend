package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.enums.UserSituation;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String senha;
    private Integer situacao;

    public UserDTO() {
    }

    public UserDTO(String name, String email, String senha, UserSituation situacao) {
        this.name = name;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao.getCod();
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
        return senha;
    }

    public void setPassword(String senha) {
        this.senha = senha;
    }

    public UserSituation getSituation() {
        return UserSituation.toEnum(situacao);
    }

    public void setSituation(UserSituation situacao) {
        this.situacao = situacao.getCod();
    }

    @Override
    public String toString() {
        return "UserDTO [name=" + name + "]";
    }
    

   

}