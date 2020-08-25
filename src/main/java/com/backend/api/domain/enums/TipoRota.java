package com.backend.api.domain.enums;

public enum TipoRota {

	MENU(1, "Menu"),
	REQUISICAO(2, "Requisição"),
	CRUD(3, "CRUD");

	private Integer cod;
	private String descricao;

	private TipoRota(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public String getDescricao () {
		return descricao;
	}

	public static TipoRota toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (TipoRota x : TipoRota.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
