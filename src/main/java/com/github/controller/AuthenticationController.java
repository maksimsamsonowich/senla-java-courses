package com.github.controller;

import com.github.dto.AuthenticationAnswerDto;
import com.github.dto.AuthenticationRequestDto;
import com.github.dto.CredentialDto;
import com.github.service.IAuthenticationService;
import com.github.service.ICredentialService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final ICredentialService iCredentialService;

    private final IAuthenticationService iAuthenticationService;

    @PostMapping("auth")
    public ResponseEntity<AuthenticationAnswerDto> customerAuthentication
            (@RequestBody AuthenticationRequestDto requestDto) {
        log.info("Authentication controller received the post request (customerAuthentication).");

        AuthenticationAnswerDto authenticationAnswer =
                iAuthenticationService.login(requestDto);

        return ResponseEntity.ok(authenticationAnswer);
    }

    @PostMapping("register")
    public ResponseEntity<AuthenticationAnswerDto> customerRegistration
            (@RequestBody CredentialDto credentialDto) {
        log.info("Authentication controller received the post request (customerRegistration).");

        iCredentialService.createCredential(credentialDto);

        AuthenticationRequestDto authenticationData =
                new AuthenticationRequestDto()
                        .setEmail(credentialDto.getEmail())
                        .setPassword(credentialDto.getPassword());

        AuthenticationAnswerDto authenticationAnswer =
                iAuthenticationService.login(authenticationData);

        return ResponseEntity.ok(authenticationAnswer);
    }

}
