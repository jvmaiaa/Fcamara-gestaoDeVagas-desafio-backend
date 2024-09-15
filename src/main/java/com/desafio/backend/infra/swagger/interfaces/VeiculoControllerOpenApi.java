package com.desafio.backend.infra.swagger.interfaces;

import com.desafio.backend.domain.dto.request.VeiculoRequestDTO;
import com.desafio.backend.domain.dto.response.VeiculoResponseDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;

@Tag(name = "Veiculo")
public interface VeiculoControllerOpenApi {

    VeiculoResponseDTO cadastra(VeiculoRequestDTO veiculoRequestDTO,
                                HttpServletResponse response);

    Page<VeiculoResponseDTO> listaPaginada(int pagina, int itens);

    VeiculoResponseDTO buscaPorId(Long id);

    VeiculoResponseDTO atualiza(VeiculoRequestDTO veiculoRequestDTO, Long id);

    void deleta(Long id);
}
