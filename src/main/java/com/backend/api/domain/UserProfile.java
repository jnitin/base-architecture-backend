package com.backend.api.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class UserProfile extends Base {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    private String description;
    private Integer nivel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "perfil_rota", joinColumns = @JoinColumn(name = "perfil_id"), inverseJoinColumns = @JoinColumn(name = "rota_id"))
    private List<Route> rotas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "perfil_rotina", joinColumns = @JoinColumn(name = "perfil_id"), inverseJoinColumns = @JoinColumn(name = "rotina_id"))
    private List<Routine> rotinas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "perfil_user", joinColumns = @JoinColumn(name = "perfil_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users = new ArrayList<>();

    public UserProfile() {
    }

    public UserProfile(Integer id, String description, Integer nivel) {
        this.id = id;
        this.description = description;
        this.nivel = nivel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public List<Route> getRoutes() {
        return rotas;
    }

    public void setRoutes(List<Route> rotas) {
        this.rotas = rotas;
    }

    public List<Routine> getRoutines() {
        return rotinas;
    }

    public void setRoutines(List<Routine> rotinas) {
        this.rotinas = rotinas;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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
        UserProfile other = (UserProfile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Perfil [description=" + description + ", id=" + id + "]";
    }

}