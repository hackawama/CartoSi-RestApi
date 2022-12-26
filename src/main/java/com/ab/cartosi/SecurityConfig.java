package com.ab.cartosi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ab.cartosi.entities.AppUser;
import com.ab.cartosi.service.AccountService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private RsaKeysConfig rsaKeysConfig;
    private PasswordEncoder passwordEncoder;
    private AccountService accountService;

    public SecurityConfig(RsaKeysConfig rsaKeysConfig, PasswordEncoder passwordEncoder, AccountService accountService) {
        this.rsaKeysConfig = rsaKeysConfig;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf(csrf -> csrf.disable()) // Disable Cross-Site Request Forgery (CSRF)
                .authorizeRequests(auth -> auth.antMatchers("/token/**").permitAll())
                .authorizeRequests(auth -> auth.antMatchers("/users/**").permitAll())
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated() // The user should be authenticated for any request in the
                                                      // application.
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // (3)
                .httpBasic(Customizer.withDefaults()) // (4)
                .build();
    }

    // @Bean
    // public InMemoryUserDetailsManager users() {
    // return new InMemoryUserDetailsManager(
    // User.withUsername("saad")
    // .password(passwordEncoder.encode("saad"))
    // .authorities("USER")
    // .build(),
    // User.withUsername("saady")
    // .password(passwordEncoder.encode("saady"))
    // .authorities("USER", "ADMIN")
    // .build());
    // }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                AppUser appUser = accountService.loadUserByUsername(username);
                Collection<GrantedAuthority> auths = new ArrayList<>();
                appUser.getRoles().forEach(r -> {
                    auths.add(new SimpleGrantedAuthority(r.getRole()));
                });
                return new User(appUser.getName(), appUser.getPassword(), auths);
            }

        };

    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        var autProvider = new DaoAuthenticationProvider();
        autProvider.setPasswordEncoder(passwordEncoder);
        autProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(autProvider);
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKeysConfig.publicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKeysConfig.publicKey()).privateKey(rsaKeysConfig.privateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

}