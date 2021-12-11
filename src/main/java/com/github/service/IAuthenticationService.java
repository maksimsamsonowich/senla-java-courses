package com.github.service;

import com.github.dto.AuthenticationAnswerDto;
import com.github.dto.AuthenticationRequestDto;

public interface IAuthenticationService {

    AuthenticationAnswerDto login(AuthenticationRequestDto requestDto);

}
