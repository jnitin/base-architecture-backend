package com.backend.api.dto.create;

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
public class CreateAccountDto implements Serializable {
  private String name;
  private String code;
  private AccountType type;
  private Float balance;
}