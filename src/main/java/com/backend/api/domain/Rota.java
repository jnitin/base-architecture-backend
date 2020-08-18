package com.backend.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.backend.api.domain.enums.TipoRota;

public class Rota implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private TipoRota tipo;
    private String url;
    private String icone;
    private Rota pai;

    private List<Perfil> perfis = new ArrayList<>();

    public Rota() {
    }

    public Rota(Integer id, String descricao, TipoRota tipo, String url, String icone, Rota pai) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.url = url;
        this.icone = icone;
        this.pai = pai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoRota getTipo() {
        return tipo;
    }

    public void setTipo(TipoRota tipo) {
        this.tipo = tipo;
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

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rota other = (Rota) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rota [id=" + id + ", url=" + url + "]";
    }

}