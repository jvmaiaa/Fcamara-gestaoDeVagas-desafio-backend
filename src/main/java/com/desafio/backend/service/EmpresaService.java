package com.desafio.backend.service;

import com.desafio.backend.domain.dto.request.EmpresaRequestDTO;
import com.desafio.backend.domain.dto.response.EmpresaResponseDTO;
import org.springframework.data.domain.Page;

public interface EmpresaService {

    EmpresaResponseDTO cadastra(EmpresaRequestDTO requestDTO);

    Page<EmpresaResponseDTO> buscaPaginada(int pagina, int itens);

    EmpresaResponseDTO buscaPorId(Long id);

    EmpresaResponseDTO atualiza(EmpresaRequestDTO enderecoRequestDTO, Long id);

    void deleta(Long id);
}
