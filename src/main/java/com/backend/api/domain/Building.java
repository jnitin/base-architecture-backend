package com.backend.api.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Building extends Base {
  private LocalDate capitationDate;

  @ManyToOne
  private Address address;

  @ManyToMany
  @JoinTable(name = "building_lessee", joinColumns = @JoinColumn(name = "building_id"), inverseJoinColumns = @JoinColumn(name = "lessee_id"))
  private List<Lessee> lessees = new ArrayList<>();

  @OneToMany(mappedBy = "building")
  private List<Rent> rents = new ArrayList<>();

  @OneToMany(targetEntity = FileStorage.class)
  private List<FileStorage> photos = new ArrayList<>();

  @OneToMany(targetEntity = Document.class)
  List<Document> documents = new ArrayList<>();

  @ManyToMany
  private List<Company> companies = new ArrayList<>();

}
