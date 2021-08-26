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
public class Lessor extends Base{ // Locador (dono do imóvel)
  private String name;
  @ManyToMany(mappedBy = "lessors")
  private List<Company> companies = new ArrayList<>();
}
