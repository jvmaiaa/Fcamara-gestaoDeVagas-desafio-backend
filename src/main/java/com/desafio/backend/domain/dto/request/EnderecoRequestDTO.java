package com.desafio.backend.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoRequestDTO {

    @NotEmpty(message = "Campo 'cep' não pode ficar em branco.")
    private String cep;

    @NotEmpty(message = "Campo 'rua' não pode ficar em branco.")
    private String rua;

    @Positive
    @NotEmpty(message = "Campo 'numero' não pode ficar em branco.")
    private String numero;

    @NotEmpty(message = "Campo 'bairro' não pode ficar em branco.")
    private String bairro;

    @NotEmpty(message = "Campo 'cidade' não pode ficar em branco.")
    private String cidade;

    @NotEmpty(message = "Campo 'estado' não pode ficar em branco.")
    private String estado;
}
