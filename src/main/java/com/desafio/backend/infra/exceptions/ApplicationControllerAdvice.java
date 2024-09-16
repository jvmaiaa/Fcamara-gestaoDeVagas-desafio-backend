package com.desafio.backend.infra.exceptions;

import com.desafio.backend.domain.dto.response.BeanValidationMessageResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static com.desafio.backend.infra.exceptions.ExceptionMessages.ENTIDADE_NAO_ENCONTRADA;
import static org.springframework.http.HttpStatus.*;

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

    @ExceptionHandler(UsuarioExisteException.class)
    @ResponseStatus(CONFLICT)
    public String handleUsuarioExisteException(UsuarioExisteException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(TokenGeneratioException.class)
    @ResponseStatus(BAD_REQUEST)
    public String handleTokenGeneratioException(TokenGeneratioException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<List<BeanValidationMessageResponseDTO>>
    handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(BeanValidationMessageResponseDTO::new).toList());
    }

    @ExceptionHandler(jakarta.persistence.EntityNotFoundException.class)
    public ResponseEntity<String> handleJakartaEntityNotFoundException(jakarta.persistence.EntityNotFoundException ex){
        return ResponseEntity.status(NOT_FOUND).body(ENTIDADE_NAO_ENCONTRADA);
    }
}
