package com.desafio.backend.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class DadoInvalidoException extends DataIntegrityViolationException {

    public DadoInvalidoException(String msg) {
        super(msg);
    }
}
