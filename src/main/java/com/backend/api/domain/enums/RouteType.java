package com.backend.api.domain.enums;

public enum RouteType {

	MENU(1, "Menu"),
	REQUISICAO(2, "Requisição"),
	CRUD(3, "CRUD");

	private Integer cod;
	private String description;

	private RouteType(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public String getdescription () {
		return description;
	}

	public static RouteType toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (RouteType x : RouteType.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
