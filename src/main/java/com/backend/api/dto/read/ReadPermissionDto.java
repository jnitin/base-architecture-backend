package com.backend.api.dto.read;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor
@Builder
@Data
public class ReadPermissionDto {
  private String description;
  private String name;
  private String type;
}
