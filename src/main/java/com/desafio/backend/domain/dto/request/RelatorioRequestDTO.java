package com.desafio.backend.domain.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioRequestDTO {

    @NotNull(message = "Campo 'idEmpresa' não pode ficar em branco.")
    private Long idEmpresa;

    @NotNull(message = "Campo 'idVeiculo' não pode ficar em branco.")
    private Long idVeiculo;
}
