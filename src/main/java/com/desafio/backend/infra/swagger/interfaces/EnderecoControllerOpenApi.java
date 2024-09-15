package com.desafio.backend.infra.swagger.interfaces;

import com.desafio.backend.domain.dto.request.EnderecoRequestDTO;
import com.desafio.backend.domain.dto.response.EnderecoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

@Tag(name = "Endereco")
public interface EnderecoControllerOpenApi {

    EnderecoResponseDTO cadastra(EnderecoRequestDTO enderecoRequestDTO,
                                 HttpServletResponse response);

    Page<EnderecoResponseDTO> listaPaginada(int pagina, int itens);

    EnderecoResponseDTO buscaPorId(Long id);

    EnderecoResponseDTO atualiza(Long id, EnderecoRequestDTO enderecoRequestDTO);

    void deleta(Long id);
}
