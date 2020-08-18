package com.backend.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Perfil implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Integer nivel;

    private List<Rota> rotas = new ArrayList<>();

    private List<Rotina> rotinas = new ArrayList<>();

    private List<Usuario> usuarios = new ArrayList<>();

    public Perfil() {
    }

    public Perfil(Integer id, String descricao, Integer nivel) {
        this.id = id;
        this.descricao = descricao;
        this.nivel = nivel;
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

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public List<Rota> getRotas() {
        return rotas;
    }

    public void setRotas(List<Rota> rotas) {
        this.rotas = rotas;
    }

    public List<Rotina> getRotinas() {
        return rotinas;
    }

    public void setRotinas(List<Rotina> rotinas) {
        this.rotinas = rotinas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
        Perfil other = (Perfil) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Perfil [descricao=" + descricao + ", id=" + id + "]";
    }

}