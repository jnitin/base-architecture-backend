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

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Integer getCodigo() {
        return code;
    }

    public void setCodigo(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RoutineDTO [code=" + code + "]";
    }

    

}