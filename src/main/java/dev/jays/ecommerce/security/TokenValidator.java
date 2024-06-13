package dev.jays.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class TokenValidator {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    /**
     * Calls user service to validate the token.
     * If token is not valid, optional is empty.
     * Else optional contains all of the data in payload
     * @param token
     * @return
     */
    public Optional<JwtObject> validateToken(String token) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        return Optional.empty();
    }
}

