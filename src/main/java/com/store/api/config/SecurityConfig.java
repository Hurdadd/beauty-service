package com.store.api.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf(csrf -> csrf
                        .csrfTokenRepository)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/requests/get/report" , "/api/requests/get/" , "/api/requests/national-code/").hasAuthority("admin")

                                .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}1234")
                .authorities("read")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("{bcrypt}$2a$04$BXAMxhY77i.6.Mik5tkg5./Gfo/pNT6u0tH56qBayflO9fnsLFgM6")
                .authorities("admin")
                .build();
        return new InMemoryUserDetailsManager(user, admin);



    }

}
