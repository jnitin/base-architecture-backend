package com.backend.api.config.security.permission;

import com.backend.api.enums.Permission;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.access.ConfigAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
class PermissionSecurityAttribute implements ConfigAttribute {
	private final List<Permission> permissions;

    @Override
    public String getAttribute() {
        return getPermissions().stream()
            .map(Permission::getAuthorityName)
            .collect(Collectors.joining(","));
    }
}
