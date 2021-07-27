package com.backend.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum PaymentType {
  ADVANCE_PAYMENT ("Pagamento antecipado"),
  OVERDUE_PAYMENT ("Pagamento vencido");

  private String name;
}
