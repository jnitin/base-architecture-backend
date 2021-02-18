package com.backend.api.domain;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends Base {

}