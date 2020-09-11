package com.backend.api.dto;

import java.io.Serializable;

public class ParameterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;
    private String value;
    private String note;

    public ParameterDTO() {
    }

    public ParameterDTO( String key, String value, String note) {
        this.key = key;
        this.value = value;
        this.note = note;
    }

    public String getChave() {
        return key;
    }

    public void setChave(String key) {
        this.key = key;
    }

    public String getValor() {
        return value;
    }

    public void setValor(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ParametroDTO [key=" + key +  "]";
    }

}