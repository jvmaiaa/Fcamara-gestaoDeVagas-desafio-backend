package com.desafio.backend.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroEntradaSaida {

    private Integer quantidadeEntrada;

    private Integer quantidadeSaida;
}
