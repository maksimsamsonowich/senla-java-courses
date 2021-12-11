package com.github.exception;

import com.github.dto.ErrorDto;
import com.github.exception.database.DatabaseAccessException;
import com.github.exception.database.DatabaseCloseConnectionException;
import com.github.exception.event.NoSuchEventException;
import com.github.exception.jwt.JwtAuthenticationException;
import com.github.exception.location.NoSuchLocationException;
import com.github.exception.user.NoSuchUserException;
import com.github.exception.user.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ NoSuchUserException.class,
                        NoSuchEventException.class,
                        NoSuchLocationException.class,
                        EntityNotFoundException.class,
                        WrongPasswordException.class,
                        UsernameNotFoundException.class,
                        BadCredentialsException.class })
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto resourceNotFoundException(RuntimeException exception) {
        return new ErrorDto(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage()
        );
    }

    @ExceptionHandler(DatabaseAccessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto databaseAccessException(DatabaseAccessException exception) {
        return new ErrorDto(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage()
        );
    }

    @ExceptionHandler(DatabaseCloseConnectionException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto databaseCloseConnectionException(DatabaseCloseConnectionException exception) {
        return new ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage()
        );
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto jwtAuthException(JwtAuthenticationException authenticationException) {
        return new ErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                authenticationException.getMessage()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorDto accessDeniedException(AccessDeniedException accessDeniedException) {
        return new ErrorDto(
                HttpStatus.FORBIDDEN.value(),
                accessDeniedException.getMessage()
        );
    }

}
