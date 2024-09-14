package com.desafio.backend.exception;

import com.desafio.backend.domain.dto.response.BeanValidationMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.desafio.backend.exception.ExceptionMessages.ENTIDADE_NAO_ENCONTRADA;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public String handleCustomEntityNotFoundException(EntityNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(DadoInvalidoException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleDadoInvalidoException(DadoInvalidoException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(RelatorioCompletoException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleRelatorioCompletoException(RelatorioCompletoException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<List<BeanValidationMessage>>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(BeanValidationMessage::new).toList());
    }

    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public ResponseEntity<String> handleJakartaEntityNotFoundException(jakarta.persistence.EntityNotFoundException ex){
        return ResponseEntity.status(NOT_FOUND).body(ENTIDADE_NAO_ENCONTRADA);
    }
}
