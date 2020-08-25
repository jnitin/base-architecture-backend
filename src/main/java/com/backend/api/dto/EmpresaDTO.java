package com.backend.api.dto;

import java.io.Serializable;

public class EmpresaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String cnpj;

    public EmpresaDTO(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public EmpresaDTO() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "EmpresaDTO [cnpj=" + cnpj + ", nome=" + nome + "]";
    }

}