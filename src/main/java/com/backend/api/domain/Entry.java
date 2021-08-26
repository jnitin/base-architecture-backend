package com.backend.api.domain;

import com.backend.api.domain.enums.EntryType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entry extends Base{
  private Float value;
  private EntryType type;
  private LocalDate date;
  private String history;

  @ManyToOne
  private Account financialAccount;

  @ManyToOne
  private Account classificationAccount;

  @ManyToOne
  private Company company;

  @ManyToMany
  private List<Company> companies = new ArrayList<>();

}
