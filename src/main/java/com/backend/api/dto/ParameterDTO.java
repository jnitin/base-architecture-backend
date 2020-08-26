package com.backend.api.dto;

import java.io.Serializable;

public class ParameterDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String chave;
    private String valor;
    private String observacao;

    public ParameterDTO() {
    }

    public ParameterDTO( String chave, String valor, String observacao) {
        this.chave = chave;
        this.valor = valor;
        this.observacao = observacao;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "ParametroDTO [chave=" + chave +  "]";
    }

}