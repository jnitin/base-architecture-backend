package com.backend.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {
  ALL("Libera todos os acessos para o usuário", "Geral"),
  GET_USER_PROFILE("Listagem de perfis de usuário", "Requisição"),
  LIST_ALL_PARAMETERS("Listagem de parâmetros", "Requisição"),
  CREATE_PARAMETERS("Criação de parâmetros", "Requisição"),
  UPDATE_PARAMETERS("Alterar parâmetros", "Requisição"),
  FIND_BY_ID_PARAMETERS("Ler parâmetros por ID", "Requisição"),
  DELETE_PARAMETERS("Apagar parâmetros", "Requisição"),
  HOME_MENU("Menu home","Menu"),
  USERS_MENU("Menu de usuários","Menu"),
  PROFILES_MENU("Menu de perfis","Menu"),
  PARAMETERS_MENU("Menu de parâmetros","Menu"),
  COMPANIES_MENU("Menu de empresas","Menu"),
  ACCOUNTS_MENU("Menu de contas do livro caixa","Menu"),
  ENTRIES_MENU("Menu de lançamentos do livro caixa","Menu")
  ;

  private final String authorityName = String.format("PERM_%s", name());
  private final String description;
  private final String type;
}
