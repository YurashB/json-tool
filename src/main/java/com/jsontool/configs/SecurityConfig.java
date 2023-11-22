package com.jsontool.configs;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(securedEnabled = false)
public class SecurityConfig {

}
