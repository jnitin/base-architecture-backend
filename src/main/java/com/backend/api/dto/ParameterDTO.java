package com.backend.api.dto;

import java.io.Serializable;

public class ParameterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String key;
    private String value;
    private String observacao;

    public ParameterDTO() {
    }

    public ParameterDTO( String key, String value, String observacao) {
        this.key = key;
        this.value = value;
        this.observacao = observacao;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "ParametroDTO [key=" + key +  "]";
    }

}