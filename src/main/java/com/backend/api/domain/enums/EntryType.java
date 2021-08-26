package com.backend.api.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EntryType {
  ENTRADA("Entrada"),
  SAIDA("Saída")
  ;
  private String name;
}
