package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.Rota;
import com.backend.api.domain.enums.TipoRota;

public class RotaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descricao;
    private Integer tipo;
    private String url;
    private String icone;
    private Rota pai;

    public RotaDTO() {
    }

    public RotaDTO(String descricao, TipoRota tipo, String url, String icone, Rota pai) {
        this.descricao = descricao;
        this.tipo = tipo.getCod();
        this.url = url;
        this.icone = icone;
        this.pai = pai;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoRota getTipo() {
        return TipoRota.toEnum(tipo);
    }

    public void setTipo(TipoRota tipo) {
        this.tipo = tipo.getCod();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public Rota getPai() {
        return pai;
    }

    public void setPai(Rota pai) {
        this.pai = pai;
    }

    @Override
    public String toString() {
        return "RotaDTO [descricao=" + descricao + ", url=" + url + "]";
    }
    

   

}