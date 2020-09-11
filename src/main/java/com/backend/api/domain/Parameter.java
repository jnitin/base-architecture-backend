package com.backend.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Parameter extends Base {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotNull
    private String key;

    @NotNull
    private String value;

    
    private String note;

    public Parameter() {
    }

    public Parameter(Integer id, String key, String value, String note) {
        this.id = id;
        this.key = key;
        this.value = value;
        this.note = note;
    }

    public String getChave() {
        return key;
    }

    public void setChave(String key) {
        this.key = key;
    }

    public String getValor() {
        return value;
    }

    public void setValor(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Parameter other = (Parameter) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Parametro [key=" + key + ", id=" + id + ", note=" + note + ", value=" + value + "]";
    }

  
}