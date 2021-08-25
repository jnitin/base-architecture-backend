package com.backend.api.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address extends Base{

  private String street;
  private String neighbourhood;
  private String city;
  private String cep;
  private String number;
  private String complement;
}
