package com.backend.api.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address extends Base{

  private String street;
  private String neighbourhood;
  private String city;
  private String cep;
  private String number;
  private String complement;

  @ManyToMany
  private List<Company> companies = new ArrayList<>();
}
