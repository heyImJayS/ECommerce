package dev.jays.ecommerce.security;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Code taken from
// https://stackoverflow.com/questions/58205510/spring-security-mapping-oauth2-claims-with-roles-to-secure-resource-server-endp
public class CustomJWTAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private static Collection<? extends GrantedAuthority> extractResourceRoles(final Jwt jwt) {
        //This will read roles parameter received from JWT and provide access to the endpoints as per the
        //roles provided
        List<String> roles = jwt.getClaim("roles");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(String role : roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role));
        }
        return grantedAuthorities;
//        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
//        Map<String, Object> resource;
//        Collection<String> resourceRoles;
//        if (resourceAccess != null && (resource = (Map<String, Object>) resourceAccess.get(resourceId)) != null &&
//                (resourceRoles = (Collection<String>) resource.get("roles")) != null)
//            return resourceRoles.stream()
//                    .map(x -> new SimpleGrantedAuthority("ROLE_" + x))
//                    .collect(Collectors.toSet());
//        return Collections.emptySet();
    }

    private final JwtGrantedAuthoritiesConverter defaultGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Override
    public AbstractAuthenticationToken convert(final Jwt source)
    {
        Collection<GrantedAuthority> authorities = Stream.concat(defaultGrantedAuthoritiesConverter.convert(source)
                                .stream(),
                        extractResourceRoles(source).stream())
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(source, authorities);
    }
}
