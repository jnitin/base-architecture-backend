package com.backend.api.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.backend.api.domain.enums.SituacaoUsuario;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Integer situacao;

    private List<Perfil> perfis = new ArrayList<>();

    private List<Rotina> rotinas = new ArrayList<>();

    private List<Empresa> empresas = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha, SituacaoUsuario situacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao == null ? null : situacao.getCod();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public SituacaoUsuario getSituacao() {
        return SituacaoUsuario.toEnum(situacao);
    }

    public void setSituacao(SituacaoUsuario situacao) {
        this.situacao = situacao.getCod();
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }

    public List<Rotina> getRotinas() {
        return rotinas;
    }

    public void setRotinas(List<Rotina> rotinas) {
        this.rotinas = rotinas;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + "]";
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}