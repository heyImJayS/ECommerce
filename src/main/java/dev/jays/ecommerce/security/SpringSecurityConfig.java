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

//        http.authorizeHttpRequests((authorize) -> authorize
//                        .anyRequest().authenticated())
//                // Form login handles the redirect to the login page from the
//                // authorization server filter chain
//                .formLogin(Customizer.withDefaults());


        //Only specific request that comes should be authenticated remaining all should be permitted
//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/products/").authenticated()
//                .anyRequest().permitAll());

        //All endpoints will be authenticated
//        http.authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
//                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
//                //Form login handles the redirect to the login page from the
//                //authorization server filter chain
//        .formLogin(Customizer.withDefaults());

        //Only allow users having role admin using Custom JWT converter instead of default Customizer
//        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/products/").hasAuthority("admin")
//                .anyRequest().authenticated())
//          //    .oauth2ResourceServer((oauth2)-> oauth2.jwt(Customizer.withDefaults()))
//                .oauth2ResourceServer((oauth2)-> oauth2.jwt(
//                        jwtConfigurer -> {
//                            jwtConfigurer.jwtAuthenticationConverter(new CustomJWTAuthenticationConverter());
//                        }
//                ))
//                ;


        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/products/").hasAuthority("admin")
                .requestMatchers("/products/{id}").authenticated()
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer((oauth2)-> oauth2.jwt(
                        jwtConfigurer -> {
                            jwtConfigurer.jwtAuthenticationConverter(new CustomJWTAuthenticationConverter());
                        }
                ))
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                ;

        return http.build();
    }
}
