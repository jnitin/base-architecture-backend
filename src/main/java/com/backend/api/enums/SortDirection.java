package com.backend.api.enums;

public enum SortDirection {

    ASC,
    DESC;

    public boolean isAsc() {
        return ASC.equals(this);
    }

}