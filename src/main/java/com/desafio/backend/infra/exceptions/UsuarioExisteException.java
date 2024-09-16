package com.desafio.backend.infra.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class UsuarioExisteException extends DataIntegrityViolationException {

    public UsuarioExisteException(String msg) {
        super(msg);
    }
}
