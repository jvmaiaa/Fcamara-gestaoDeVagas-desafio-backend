package com.desafio.backend.service;

import com.desafio.backend.domain.dto.request.VeiculoRequestDTO;
import com.desafio.backend.domain.dto.response.VeiculoResponseDTO;
import org.springframework.data.domain.Page;

public interface VeiculoService {

    VeiculoResponseDTO cadastra(VeiculoRequestDTO veiculoRequestDTO);

    Page<VeiculoResponseDTO> buscaPaginada(int pagina, int itens);

    VeiculoResponseDTO buscaPorID(Long id);

    VeiculoResponseDTO atualiza(VeiculoRequestDTO responseDTO, Long id);

    void deleta(Long id);
}
