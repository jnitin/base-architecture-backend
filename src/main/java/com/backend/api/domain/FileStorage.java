package com.backend.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;
import org.apache.commons.io.FilenameUtils;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class FileStorage extends Base {

  @Column(unique = true, nullable = false)
  private String uuid;

  @Column(nullable = false)
  private String filename;

  @Column(nullable = false, length = 10)
  private String extension;

  private String contentType;

  @Column()
  private Long size;

  public String getUuidWithExtension() {
    return this.uuid + FilenameUtils.EXTENSION_SEPARATOR_STR + this.extension;
  }

}