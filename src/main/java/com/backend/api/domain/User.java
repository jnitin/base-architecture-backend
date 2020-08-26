package com.backend.api.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import com.backend.api.domain.enums.Profile;
import com.backend.api.domain.enums.UserSituation;

@Entity
public class User extends Base {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @NotNull
    private String nome;

    @NotNull
    private String email;
    
    @NotNull
    private String senha;

    @NotNull
    private Integer situacao;

    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    private List<UserProfile> perfis = new ArrayList<>();

    @ManyToMany(mappedBy = "usuarios")
    private List<Enterprise> empresas = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "usuario_rotina", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rotina_id"))
    private List<Rotina> rotinas = new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PROFILES")
	private Set<Integer> profiles = new HashSet<>();

    public User() {
    }

    public User(Integer id, String nome, String email, String senha, UserSituation situacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.situacao = situacao == null ? null : situacao.getCod();
        addProfile(Profile.CLIENTE);
    }


    public Set<Profile> getProfiles() {
		return profiles.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(Profile profile) {
		profiles.add(profile.getCod());
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<UserProfile> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<UserProfile> perfis) {
        this.perfis = perfis;
    }

    public List<Rotina> getRotinas() {
        return rotinas;
    }

    public void setRotinas(List<Rotina> rotinas) {
        this.rotinas = rotinas;
    }

    public List<Enterprise> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Enterprise> empresas) {
        this.empresas = empresas;
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}