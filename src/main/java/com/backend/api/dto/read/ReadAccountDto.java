package com.backend.api.dto.read;

import com.backend.api.domain.enums.AccountType;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@ToString
@Setter
public class ReadAccountDto implements Serializable {
  private Long id;
  private String name;
  private String code;
  private String typeName;
  private Float balance;
  private Long companyId;
  private AccountType type;

}