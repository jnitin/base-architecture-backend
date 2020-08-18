package com.backend.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rotina implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Integer codigo;

    private List<Usuario> usuarios = new ArrayList<>();

    public Rotina() {
    }

    public Rotina(Integer id, String descricao, Integer codigo) {
        this.id = id;
        this.descricao = descricao;
        this.codigo = codigo;
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

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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
        Rotina other = (Rotina) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rotina [codigo=" + codigo + ", descricao=" + descricao + ", id=" + id + "]";
    }

}