package com.github.security;

import com.github.entity.Credential;
import com.github.entity.User;
import com.github.repository.CredentialRepository;
import com.github.repository.UserRepository;
import com.github.security.jwt.JwtUser;
import com.github.security.jwt.JwtUserFactory;
import com.github.service.CredentialService;
import com.github.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtCredentialDetailsService implements UserDetailsService {

    private final CredentialRepository credentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserFactory.jwtUserCreate(credentialRepository.getCredentialByEmail(username));
    }

}
