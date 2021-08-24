package com.backend.api.domain.enums;

import lombok.Getter;

@Getter
public enum UserType {
    ADMIN,
    CUSTOMER;

    private final String authorityName = String.format("ROLE_%s", name());
}
