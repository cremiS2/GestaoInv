package com.projeto.inventario.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.projeto.inventario.service.AutenticacaoService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;
    
    @Autowired
    private AutenticacaoService autenticacaoService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors()
        .and()
        .csrf().disable()
        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth

                .requestMatchers("/auth/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                .requestMatchers(HttpMethod.GET, "/produto/todos").hasAnyRole("ADMIN", "USUARIO")
                .requestMatchers(HttpMethod.POST, "/produto/cadastrar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/produto/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/produto/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/depositos/todos").hasAnyRole("ADMIN", "USUARIO")
                .requestMatchers(HttpMethod.POST, "/depositos/criar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/depositos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/depositos/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/estoques/todos").hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.POST, "/estoques/criar").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/estoques/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/estoques/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/movimentacoes").hasAnyRole("ADMIN", "USUARIO")
                .requestMatchers(HttpMethod.POST, "/movimentacoes").hasAnyRole("ADMIN", "USUARIO")
                .requestMatchers(HttpMethod.DELETE, "/movimentacoes/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/usuarios").hasRole("ADMIN")

                .anyRequest().authenticated()
            )
            .userDetailsService(autenticacaoService)
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}