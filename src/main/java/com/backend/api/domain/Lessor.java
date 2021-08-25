package com.backend.api.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lessor extends Base{ // Locador (dono do imóvel)
  private String name;
}
