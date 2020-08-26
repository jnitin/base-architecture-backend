package com.backend.api.dto;

import java.io.Serializable;

import com.backend.api.domain.enums.UserSituation;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String email;
    private String senha;
    private Integer situacao;

    public UserDTO() {
    }

    public UserDTO(String nome, String email, String senha, UserSituation situacao) {
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

    public UserSituation getSituacao() {
        return UserSituation.toEnum(situacao);
    }

    public void setSituacao(UserSituation situacao) {
        this.situacao = situacao.getCod();
    }

    @Override
    public String toString() {
        return "UsuarioDTO [nome=" + nome + "]";
    }
    

   

}