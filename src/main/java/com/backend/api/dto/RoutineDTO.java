package com.backend.api.dto;

import java.io.Serializable;

public class RoutineDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private String description;
    private Integer code;

    public RoutineDTO() {
    }

    public RoutineDTO(String description, Integer code) {
        this.description = description;
        this.code = code;
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

    @Override
    public String toString() {
        return "RoutineDTO [code=" + code + "]";
    }

    

}