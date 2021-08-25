package com.backend.api.domain;

import com.backend.api.domain.enums.DocumentType;
import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Document extends Base {
  private DocumentType type;
  private String name;
}
