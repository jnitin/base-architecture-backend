package com.backend.api.domain.enums;

public enum UserSituation {

	ATIVO(1, "Ativo"),
	INATIVO(2, "Inativo");

	private Integer cod;
	private String description;

	private UserSituation(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescription () {
		return description;
	}

	public static UserSituation toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (UserSituation x : UserSituation.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
