package com.github.service.impl;

import com.github.dto.AuthenticationRequestDto;
import com.github.entity.Credential;
import com.github.repository.impl.CredentialRepository;
import com.github.security.jwt.token.JwtTokenProvider;
import com.github.service.IAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final JwtTokenProvider tokenProvider;

    private final CredentialRepository credentialRepository;

    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseEntity<?> login(AuthenticationRequestDto requestDto) {
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));

            Credential currentCredential = credentialRepository.getCredentialByEmail(email);

            if (currentCredential == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            String token = tokenProvider.createToken(email, currentCredential.getRoles().stream().toList());

            Map<Object, Object> response = new HashMap<>();
            response.put("email", email);
            response.put("token", token);

            return ResponseEntity.ok(response);

        } catch (AuthenticationException authenticationException) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
