package com.backend.api.dto;

import java.io.Serializable;

public class RotinaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    
    private String descricao;
    private Integer codigo;

    public RotinaDTO() {
    }

    public RotinaDTO(String descricao, Integer codigo) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "RotinaDTO [codigo=" + codigo + "]";
    }

    

}