package com.desafio.backend.service;

import com.desafio.backend.domain.dto.request.EnderecoRequestDTO;
import com.desafio.backend.domain.dto.response.EnderecoResponseDTO;
import org.springframework.data.domain.Page;

public interface EnderecoService {

    EnderecoResponseDTO cadastra(EnderecoRequestDTO enderecoRequestDTO);

    Page<EnderecoResponseDTO> buscaPaginada(int pagina, int itens);

    EnderecoResponseDTO buscaPorId(Long id);

    EnderecoResponseDTO atualiza(EnderecoRequestDTO enderecoRequestDTO, Long id);

    void deleta(Long id);
}
