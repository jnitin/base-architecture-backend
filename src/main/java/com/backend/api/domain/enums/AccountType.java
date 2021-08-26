package com.backend.api.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccountType {
  INCOME_ACCOUNT("Conta de receita"),
  EXPENSE_ACCOUNT("Conta de despesa"),
  FINANCIAL_ACCOUNT("Conta financeira"),
  ;
  private String name;
}
