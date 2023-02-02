package com.example.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(
    prePostEnabled = true, // @PreAuthorize, @PostAuthorize
    securedEnabled = true, // @Secured
    jsr250Enabled = true)  // @RolesAllowed
public class MyMethodSecurityConfig extends GlobalMethodSecurityConfiguration { }