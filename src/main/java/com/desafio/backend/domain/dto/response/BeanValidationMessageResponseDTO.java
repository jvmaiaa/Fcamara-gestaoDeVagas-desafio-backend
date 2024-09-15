package com.desafio.backend.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
public class BeanValidationMessageResponseDTO {

    private String campo;

    private String mensagem;

    public BeanValidationMessageResponseDTO(FieldError erro) {
        this(erro.getField(), erro.getDefaultMessage());
    }
}
