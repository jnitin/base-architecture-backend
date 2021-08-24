package com.backend.api.annotation.security;

import com.backend.api.enums.Permission;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CrudPermission {
    Permission create();
    Permission update();
    Permission listAll();
    Permission findById();
    Permission delete();
}
