package com.backend.api.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contract extends Base {
  private String test;

  @ManyToMany
  private List<Company> companies = new ArrayList<>();

}
