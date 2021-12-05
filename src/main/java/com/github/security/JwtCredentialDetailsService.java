package com.github.security;

import com.github.entity.Credential;
import com.github.repository.impl.CredentialRepository;
import com.github.security.jwt.user.JwtUserFactory;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class JwtCredentialDetailsService implements UserDetailsService {

    private static String USERNAME_EXCEPTION_MESSAGE = "User with email: %s not found";

    private final CredentialRepository credentialRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential currentCredential = credentialRepository.getCredentialByEmail(username);

        if (currentCredential == null) {
            throw new UsernameNotFoundException(String.format(USERNAME_EXCEPTION_MESSAGE, username));
        }

        return JwtUserFactory.jwtUserCreate(currentCredential);
    }

}
