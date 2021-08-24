package com.backend.api.config.security.permission;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.ArrayList;

@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    proxyTargetClass = true,
    securedEnabled = true
)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class PermissionSecurityConfiguration extends GlobalMethodSecurityConfiguration {

    @Override
    protected AccessDecisionManager accessDecisionManager() {
        final var affirmativeBased = (AffirmativeBased) super.accessDecisionManager();
        final var decisionVoters = new ArrayList<AccessDecisionVoter<?>>();

        decisionVoters.add(new PermissionVoter());
        decisionVoters.addAll(affirmativeBased.getDecisionVoters());

        return new AffirmativeBased(decisionVoters);
    }

    @Override
    protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return  new PermissionAnnotationBasedSecurityMetadataSource();
    }
}
