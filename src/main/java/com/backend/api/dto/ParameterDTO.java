package com.backend.api.dto;

import java.io.Serializable;

public class ParameterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String key;
    private String value;
    private String note;

    public ParameterDTO() {
    }

    public ParameterDTO(Integer id, String key, String value, String note) {
        this.key = key;
        this.value = value;
        this.note = note;
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ParameterDTO [key=" + key +  "]";
    }


    

}