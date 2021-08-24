package com.backend.api.config.security.permission;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;

import java.util.Collection;

class PermissionVoter implements AccessDecisionVoter<MethodInvocation> {
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute instanceof PermissionSecurityAttribute;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MethodInvocation.class.isAssignableFrom(clazz);
    }

    @Override
    public int vote(
        Authentication authentication,
        MethodInvocation object,
        Collection<ConfigAttribute> attributes
    ) {
        var securityAttribute = attributes.stream()
            .filter(attr -> attr instanceof PermissionSecurityAttribute)
            .map(PermissionSecurityAttribute.class::cast)
            .findFirst()
            .orElse(null);

        if (securityAttribute == null) {
            return AccessDecisionVoter.ACCESS_ABSTAIN;
        }

        var requiredPermissions = securityAttribute.getPermissions();
        var userAuth = (UserAuthentication) authentication;
        var userPermissions = userAuth.getUser().fetchAndFlattenPermissions();
        var userHaveAllRequiredPermissions = userPermissions.containsAll(requiredPermissions);

        if (!userHaveAllRequiredPermissions) {
            return AccessDecisionVoter.ACCESS_DENIED;
        }

        return AccessDecisionVoter.ACCESS_GRANTED;
    }
}
