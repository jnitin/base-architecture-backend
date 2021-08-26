package com.backend.api.domain;

import com.backend.api.domain.enums.PaymentType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

  @ManyToMany(mappedBy = "rents")
  private List<Company> companies = new ArrayList<>();

  private Float value;
  private String assurance; // Garantia
}
