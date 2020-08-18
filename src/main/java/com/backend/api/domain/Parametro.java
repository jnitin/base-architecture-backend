package com.backend.api.domain;

import java.io.Serializable;

public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    public String chave;
    public String valor;
    public String observacao;

    public Parametro() {
    }

    public Parametro(Integer id, String chave, String valor, String observacao) {
        this.id = id;
        this.chave = chave;
        this.valor = valor;
        this.observacao = observacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        Parametro other = (Parametro) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Parametro [chave=" + chave + ", id=" + id + "]";
    }

}