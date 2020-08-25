package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.enums.SituacaoUsuario;

public class UsuarioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String senha;
    private Integer situacao;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nome, String email, String senha, SituacaoUsuario situacao) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao.getCod();
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

    @Override
    public String toString() {
        return "UsuarioDTO [nome=" + nome + "]";
    }
    

   

}