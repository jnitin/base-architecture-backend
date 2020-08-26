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
    private String name;

    @NotNull
    private String email;
    
    @NotNull
    private String senha;

    @NotNull
    private Integer situacao;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<UserProfile> userProfiles = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    private List<Company> companies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_rotina", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rotina_id"))
    private List<Routine> rotinas = new ArrayList<>();

    @ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PROFILES")
	private Set<Integer> profiles = new HashSet<>();

    public User() {
    }

    public User(Integer id, String name, String email, String senha, UserSituation situacao) {
        this.id = id;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return senha;
    }

    public void setPassword(String senha) {
        this.senha = senha;
    }

    public UserSituation getSituation() {
        return UserSituation.toEnum(situacao);
    }

    public void setSituation(UserSituation situacao) {
        this.situacao = situacao.getCod();
    }

    public List<UserProfile> getUserProfiles() {
        return userProfiles;
    }

    public void setUserProfiles(List<UserProfile> profiles) {
        this.userProfiles = profiles;
    }

    public List<Routine> getRoutines() {
        return rotinas;
    }

    public void setRoutines(List<Routine> rotinas) {
        this.rotinas = rotinas;
    }

    public List<Company> getEmpresas() {
        return companies;
    }

    public void setEmpresas(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + "]";
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