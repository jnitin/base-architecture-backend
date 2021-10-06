package com.backend.api.domain;

import com.backend.api.dto.update.UpdateCompanyDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company extends Base {
  @NotNull
  private String name;

  private String cnpj;

  @ManyToMany
//  @JoinTable(name = "company_user", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> users;

  @ManyToMany
//  @JoinTable(name = "company_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
  private Set<Account> accounts;

  @ManyToMany
//  @JoinTable(name = "company_adress", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
  private Set<Address> adresses;

  @ManyToMany
//  @JoinTable(name = "company_building", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "buildings_id"))
  private Set<Building> buildings;

  @ManyToMany
//  @JoinTable(name = "contract_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "contracts_id"))
  private Set<Contract> contracts;

  @ManyToMany
//  @JoinTable(name = "document_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "documents_id"))
  private Set<Document> documents;

  @ManyToMany
//  @JoinTable(name = "entry_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "entry_id"))
  private Set<Entry> entries;

  @ManyToMany
//  @JoinTable(name = "file_storage_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "file_storage_id"))
  private Set<FileStorage> fileStorages;

  @ManyToMany
//  @JoinTable(name = "lessee_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "lessee_id"))
  private Set<Lessee> lessees;

  @ManyToMany
//  @JoinTable(name = "lessor_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "lessor_id"))
  private Set<Lessor> lessors;

  @ManyToMany
//  @JoinTable(name = "parameter_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
  private Set<Parameter> parameters = new HashSet<>();


  @ManyToMany
//  @JoinTable(name = "rent_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "rent_id"))
  private Set<Rent> rents;

  @ManyToMany
//  @JoinTable(name = "role_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @ManyToMany
//  @JoinTable(name = "routine_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "routine_id"))
  private Set<Routine> routines;

  @ManyToMany
//  @JoinTable(name = "user_profile_account", joinColumns = @JoinColumn(name = "company_id"), inverseJoinColumns = @JoinColumn(name = "user_profile_id"))
  private Set<UserProfile> userProfiles = new HashSet<>();

  public void update(UpdateCompanyDto dto) {
    name = dto.getName();
    cnpj = dto.getCnpj();
  }
}