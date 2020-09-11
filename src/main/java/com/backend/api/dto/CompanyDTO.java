package com.backend.api.dto;

import java.io.Serializable;

public class CompanyDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String cnpj;

    public CompanyDTO(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public CompanyDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "EmpresaDTO [cnpj=" + cnpj + ", name=" + name + "]";
    }

}