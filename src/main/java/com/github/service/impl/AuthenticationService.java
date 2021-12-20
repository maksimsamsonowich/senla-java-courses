package com.github.service.impl;

import com.github.dto.AuthenticationAnswerDto;
import com.github.dto.AuthenticationRequestDto;
import com.github.entity.Credential;
import com.github.repository.CredentialRepository;
import com.github.security.jwt.token.JwtTokenProvider;
import com.github.service.IAuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final JwtTokenProvider tokenProvider;

    private final CredentialRepository credentialRepository;

    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationAnswerDto login(AuthenticationRequestDto requestDto) {
        try {
            String email = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, requestDto.getPassword()));

            Credential currentCredential = credentialRepository.getCredentialByEmail(email);

            if (currentCredential == null) {
                throw new UsernameNotFoundException("User with email: " + email + " not found");
            }

            String token = tokenProvider.createToken(email, currentCredential.getRoles().stream().toList());

            return new AuthenticationAnswerDto()
                    .setStatus(HttpStatus.OK.value())
                    .setEmail(email)
                    .setToken(token);

        } catch (AuthenticationException authenticationException) {
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
