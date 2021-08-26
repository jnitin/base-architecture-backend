package com.backend.api.domain;

import com.backend.api.domain.enums.DocumentType;
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
public class Document extends Base {
  private DocumentType type;
  private String name;

  @ManyToMany
  private List<Company> companies = new ArrayList<>();
}
