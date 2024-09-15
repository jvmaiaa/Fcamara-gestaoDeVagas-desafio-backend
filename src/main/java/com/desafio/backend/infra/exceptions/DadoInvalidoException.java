package com.desafio.backend.infra.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DadoInvalidoException extends DataIntegrityViolationException {

    public DadoInvalidoException(String msg) {
        super(msg);
    }
}
