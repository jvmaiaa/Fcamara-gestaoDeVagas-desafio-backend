package com.desafio.backend.domain.dto.response;

import com.desafio.backend.domain.enums.TipoVeiculo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioResponseDTO {

    private Long id;

    private Long idEmpresa;

    private Long idVeiculo;

    private LocalDateTime dataEntrada;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDateTime dataSaida;

    private TipoVeiculo tipoVeiculo;
}
