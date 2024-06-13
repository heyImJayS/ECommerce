package dev.jays.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {

        //Every request that comes should be authenticated
/*
        http.authorizeHttpRequests((authorize) -> authorize
                        .anyRequest().authenticated())
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(Customizer.withDefaults());
*/

        //Only specific request that comes should be authenticated remaining all should be permitted
        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/products/").authenticated()
                .anyRequest().permitAll());


        return http.build();
    }
}
