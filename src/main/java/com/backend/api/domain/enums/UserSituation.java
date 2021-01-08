package com.backend.api.domain.enums;

public enum UserSituation {

	ACTIVE(1, "Ativo"),
	INACTIVE(2, "Inativo");

	private final Integer cod;
	private final String description;

	UserSituation(int cod, String description) {
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
