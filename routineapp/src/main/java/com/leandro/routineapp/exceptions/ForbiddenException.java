package com.leandro.routineapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends ResponseStatusException {
    public ForbiddenException(String mensaje) {
        super(HttpStatus.FORBIDDEN, mensaje);
    }
}
