package com.github.controller;

import com.github.dto.AuthenticationRequestDto;
import com.github.service.impl.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        return authenticationService.login(requestDto);
    }

}
