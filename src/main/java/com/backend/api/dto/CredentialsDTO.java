package com.backend.api.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
	public CredentialsDTO() {
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

	@Override
	public String toString() {
		return "CredenciaisDTO [email=" + email + ", senha=" + senha + "]";
	}
}
