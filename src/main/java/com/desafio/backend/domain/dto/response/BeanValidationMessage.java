package com.desafio.backend.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
public class BeanValidationMessage {

    private String campo;

    private String mensagem;

    public BeanValidationMessage(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
