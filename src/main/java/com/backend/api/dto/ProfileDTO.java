package com.backend.api.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private Integer nivel;

    public ProfileDTO(String description, Integer nivel) {
        this.description = description;
        this.nivel = nivel;
    }

    public ProfileDTO() {
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "PerfilDTO [description=" + description + ", nivel=" + nivel + "]";
    }

    
}