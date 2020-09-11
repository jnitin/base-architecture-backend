package com.backend.api.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private Integer level;

    public ProfileDTO(String description, Integer level) {
        this.description = description;
        this.level = level;
    }

    public ProfileDTO() {
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

    @Override
    public String toString() {
        return "ProfileDTO [description=" + description + ", level=" + level + "]";
    }

    
}