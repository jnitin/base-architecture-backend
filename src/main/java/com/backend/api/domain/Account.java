package com.backend.api.domain;

import com.backend.api.domain.enums.AccountType;
import com.backend.api.dto.update.UpdateAccountDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends Base {
  private String name;
  private String code;
  private AccountType type;
  private Float balance;

  @ManyToMany(mappedBy = "accounts")
  private Set<Company> companies = new HashSet<>();

  public void update(UpdateAccountDto dto, Company company) {
    getCompanies().add(company);
    setName(dto.getName());
    setCode(dto.getCode());
    setType(dto.getType());
  }
}
