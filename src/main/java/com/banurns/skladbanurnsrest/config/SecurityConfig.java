package com.banurns.skladbanurnsrest.config;

import com.banurns.skladbanurnsrest.security.JwtUserDetailsService;
import com.banurns.skladbanurnsrest.security.jwt.JwtConfigurer;
import com.banurns.skladbanurnsrest.security.jwt.JwtTokenFilter;
import com.banurns.skladbanurnsrest.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String[] ADMIN_ENDPOINTS = {"/api/admin/**"};

    private static final String ANYROLE_ENDPOINT = "/api/anyrole/";
    private static final String SALESMANAGER_ENDPOINT = "/api/salesmanager/**";
    private static final String REQUESTMANAGER_ENDPOINT = "/api/reqprocessor/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter(jwtTokenProvider);
    }

    @Bean
    public SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> jwtConfigurer() {
        return new JwtConfigurer(jwtTokenProvider);
    }




    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(LOGIN_ENDPOINT).permitAll()
                .requestMatchers(ANYROLE_ENDPOINT).hasAnyRole("SALESMANAGER" , "REQUESTMANAGER" , "ADMIN")
                .requestMatchers(SALESMANAGER_ENDPOINT).hasRole("SALESMANAGER")
                .requestMatchers(REQUESTMANAGER_ENDPOINT).hasRole("REQUESTMANAGER")
                .requestMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        return http.build();
    }
}


//@Configuration
//public static class SecurityFilterChainConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//
//    private final JwtTokenFilter jwtTokenFilter;
//
//    @Autowired
//    public SecurityFilterChainConfig(JwtTokenFilter jwtTokenFilter) {
//        this.jwtTokenFilter = jwtTokenFilter;
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//
//                .httpBasic().disable()
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests()
//                .requestMatchers(LOGIN_ENDPOINT).permitAll()
//                .requestMatchers(ADMIN_ENDPOINTS).hasRole("ADMIN")
//                .anyRequest().authenticated()
//                .and()
//                .addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class);
//    }
//}