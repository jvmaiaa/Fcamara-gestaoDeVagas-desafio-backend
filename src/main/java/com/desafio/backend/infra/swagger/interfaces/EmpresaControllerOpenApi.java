package com.desafio.backend.infra.swagger.interfaces;

import com.desafio.backend.domain.dto.request.EmpresaRequestDTO;
import com.desafio.backend.domain.dto.response.EmpresaResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

@Tag(name = "Empresa")
public interface EmpresaControllerOpenApi {

    EmpresaResponseDTO cadastra(EmpresaRequestDTO requestDTO,
                                HttpServletResponse response);

    Page<EmpresaResponseDTO> listaPaginada(int pagina, int itens);

    EmpresaResponseDTO buscaPorId(Long id);

    EmpresaResponseDTO atualiza(EmpresaRequestDTO requestDTO, Long id);

    void deleta(Long id);
}
