package com.github.exceptions;

import com.github.dto.ErrorDto;
import com.github.exceptions.database.DatabaseAccessException;
import com.github.exceptions.database.DatabaseCloseConnectionException;
import com.github.exceptions.event.NoSuchEventException;
import com.github.exceptions.location.NoSuchLocationException;
import com.github.exceptions.user.NoSuchUserException;
import com.github.exceptions.user.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ NoSuchUserException.class,
                        NoSuchEventException.class,
                        NoSuchLocationException.class,
                        EntityNotFoundException.class,
                        WrongPasswordException.class })
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
}
