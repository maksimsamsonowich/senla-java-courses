package com.github.exception.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.dto.ErrorDto;
import com.github.mapper.impl.JsonMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Http403ForbiddenEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        JsonMapper jsonMapper = new JsonMapper(new ObjectMapper());

        ErrorDto errorDto = new ErrorDto(
                HttpStatus.FORBIDDEN.value(),
                "You need to login first in order to perform this action."
        );

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(jsonMapper.toJson(errorDto));
    }

}
