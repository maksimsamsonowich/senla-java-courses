package com.github.security.jwt.user;

import com.github.entity.Credential;
import com.github.entity.Role;
import com.github.security.jwt.user.JwtUser;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
final public class JwtUserFactory {

    public static JwtUser jwtUserCreate(Credential credential) {
        return new JwtUser(
                credential.getId(),
                credential.getEmail(),
                credential.getPassword(),
                credential.getUser().getFirstName(),
                credential.getUser().getSurname(),
                credential.getUser().getPhoneNumber(),
                mapToGrantedAuthority(credential.getRoles())
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthority(Set<Role> roleSet) {
        return  roleSet.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getRole()))
                .collect(Collectors.toList());

    }

}
