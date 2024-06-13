package dev.jays.ecommerce.security;

import java.util.ArrayList;
import java.util.List;

public class JwtObject {
    private String email;
    private String createdAt;
    private String expireAt;
    private List<Role> roles = new ArrayList<>();
}
