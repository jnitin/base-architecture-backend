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
@Data
public class FileStorage extends Base {

  @Column(name = "uuid", unique = true, nullable = false)
  private String uuid;

  @Column(name = "filename", nullable = false)
  private String filename;

  @Column(name = "extension", nullable = false, length = 10)
  private String extension;

  @Column(name = "content_type")
  private String contentType;

  @Column(name = "size")
  private Long size;

  public String getUuidWithExtension() {
    return this.uuid + FilenameUtils.EXTENSION_SEPARATOR_STR + this.extension;
  }

}