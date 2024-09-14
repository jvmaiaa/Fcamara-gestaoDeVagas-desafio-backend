package com.desafio.backend.service;

import com.desafio.backend.domain.dto.request.RelatorioRequestDTO;
import com.desafio.backend.domain.dto.response.RegistroEntradaSaida;
import com.desafio.backend.domain.dto.response.RelatorioResponseDTO;
import org.springframework.data.domain.Page;

public interface RelatorioService {

    RelatorioResponseDTO registraEntrada(RelatorioRequestDTO relatorioRequestDTO);

    Page<RelatorioResponseDTO> buscaPaginada(int pagina, int itens);

    RelatorioResponseDTO buscaPorID(Long id);

    RelatorioResponseDTO registraSaida(Long id);

    void deleta(Long id);

    RegistroEntradaSaida contadorEntradaSaida();
}
