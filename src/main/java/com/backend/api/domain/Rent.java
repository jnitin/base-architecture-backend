package com.backend.api.domain;

import com.backend.api.domain.enums.PaymentType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rent extends Base {
  private LocalDate paymentDay;
  private LocalDate contractInitialDay;
  private LocalDate contractEndDay;
  private LocalDate firstPaymentDay;
  private PaymentType paymentType;

  @ManyToOne
  private Building building;


  private Float value;
  private String assurance; // Garantia
}
