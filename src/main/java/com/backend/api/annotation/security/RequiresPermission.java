package com.backend.api.annotation.security;


import com.backend.api.enums.Permission;

import java.lang.annotation.*;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RequiresPermission {
    Permission[] value();
}
