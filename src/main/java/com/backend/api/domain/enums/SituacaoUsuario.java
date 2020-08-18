package com.backend.api.domain.enums;

public enum SituacaoUsuario {

	ATIVO(1, "Ativo"),
	INATIVO(2, "Inativo");

	private int cod;
	private String descricao;

	private SituacaoUsuario(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao () {
		return descricao;
	}

	public static SituacaoUsuario toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (SituacaoUsuario x : SituacaoUsuario.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
