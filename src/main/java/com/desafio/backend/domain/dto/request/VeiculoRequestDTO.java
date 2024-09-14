package com.desafio.backend.domain.dto.request;

import com.desafio.backend.domain.enums.TipoVeiculo;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VeiculoRequestDTO {

    @NotEmpty(message = "Campo 'marca' não pode ficar em branco.")
    private String marca;

    @NotEmpty(message = "Campo 'modelo' não pode ficar em branco.")
    private String modelo;

    @NotEmpty(message = "Campo 'cor' não pode ficar em branco.")
    private String cor;

    @NotEmpty(message = "Campo 'placa' não pode ficar em branco.")
    private String placa;

    @NotNull(message = "Campo 'tipoDeVeiculo' não pode ficar em branco.")
    private TipoVeiculo tipoDeVeiculo;
}
