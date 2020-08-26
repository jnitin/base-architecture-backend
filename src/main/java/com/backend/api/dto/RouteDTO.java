package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.Rota;
import com.backend.api.domain.enums.RouteType;

public class RouteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String descricao;
    private Integer tipo;
    private String url;
    private String icone;
    private Rota pai;
    private String method;

    public RouteDTO() {
    }

    

    public RouteDTO(String descricao, RouteType tipo, String url, String icone, Rota pai, String method) {
        this.descricao = descricao;
        this.tipo = tipo.getCod();
        this.url = url;
        this.icone = icone;
        this.pai = pai;
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public RouteType getTipo() {
        return RouteType.toEnum(tipo);
    }

    public void setTipo(RouteType tipo) {
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