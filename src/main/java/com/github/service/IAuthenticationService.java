package com.github.service;

import com.github.dto.AuthenticationAnswerDto;
import com.github.dto.AuthenticationRequestDto;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {

    AuthenticationAnswerDto login(AuthenticationRequestDto requestDto);

}
