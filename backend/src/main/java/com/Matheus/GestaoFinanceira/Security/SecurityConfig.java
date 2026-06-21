package com.Matheus.GestaoFinanceira.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(response ->
                        response.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder){
        String adminPassword = encoder.encode("admin");
        String userPassword = encoder.encode("user");
        UserDetails admin = User.withUsername("admin")
                .password(adminPassword).roles("USER", "ADMIN").build();
        UserDetails user = User.withUsername("user")
                .password(userPassword).roles("USER").build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
