package com.desafio.backend.infra.swagger.interfaces;

import com.desafio.backend.domain.dto.request.RelatorioRequestDTO;
import com.desafio.backend.domain.dto.response.EntradaSaidaPorHoraResponseDTO;
import com.desafio.backend.domain.dto.response.EntradaSaidaTotalResponseDTO;
import com.desafio.backend.domain.dto.response.RelatorioResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

@Tag(name = "Relatorio")
public interface RelatorioControllerOpenApi {

    RelatorioResponseDTO registraEntrada(RelatorioRequestDTO relatorioRequestDTO,
                                         HttpServletResponse response);

    Page<RelatorioResponseDTO> registrosPaginados(int pagina, int itens);

    RelatorioResponseDTO buscaRegistroPorID(Long id);

    RelatorioResponseDTO registraSaida(Long id);

    EntradaSaidaTotalResponseDTO contadorEntradaSaida();

    EntradaSaidaPorHoraResponseDTO contadorEntradaSaidaPorHora(Long empresaId, String horaInicio, String horaFim);

    void deleta(Long id);


}
