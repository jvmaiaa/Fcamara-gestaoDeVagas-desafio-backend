package com.desafio.backend.domain.dto.response;

import com.desafio.backend.domain.enums.TipoVeiculo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VeiculoResponseDTO {

    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    private TipoVeiculo tipoDeVeiculo;
}
