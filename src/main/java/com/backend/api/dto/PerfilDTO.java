package com.backend.api.dto;

import java.io.Serializable;

public class PerfilDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descricao;
    private Integer nivel;

    public PerfilDTO(String descricao, Integer nivel) {
        this.descricao = descricao;
        this.nivel = nivel;
    }

    public PerfilDTO() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        return "PerfilDTO [descricao=" + descricao + ", nivel=" + nivel + "]";
    }

    
}