package com.backend.api.dto;

import java.io.Serializable;

public class ProfileDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private Integer level;

    public ProfileDTO(Integer id, String description, Integer level) {
        this.description = description;
        this.level = level;
        this.id = id;
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

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ProfileDTO [description=" + description + ", level=" + level + "]";
    }

    

}