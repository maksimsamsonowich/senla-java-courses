package com.github.service;

import com.github.dto.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {

    ResponseEntity<?> login(AuthenticationRequestDto requestDto);

}
