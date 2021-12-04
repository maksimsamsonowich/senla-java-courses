package com.github.security.jwt;

import com.github.entity.Role;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtTokenProvider {

    public String createToken(String email, List<Role> roles) {
        return null;
    }

    public Authentication getAuthentication(String token) {
        return null;
    }

    public String getUsername(String token) {
        return null;
    }

    public boolean validateToken() {
        return true;
    }

    private List<String> getRolesNames(List<Role> userRoles) {
        return null;
    }


}
