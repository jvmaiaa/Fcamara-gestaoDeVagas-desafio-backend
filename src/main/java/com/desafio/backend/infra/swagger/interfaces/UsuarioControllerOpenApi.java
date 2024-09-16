package com.desafio.backend.infra.swagger.interfaces;

import com.desafio.backend.domain.dto.request.CadastraRequestDTO;
import com.desafio.backend.domain.dto.response.CadastraResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioControllerOpenApi {

    CadastraResponseDTO cadastraGerente(CadastraRequestDTO cadastraRequestDTO,
                                        HttpServletResponse response);

    CadastraResponseDTO cadastraFuncionario(@RequestBody CadastraRequestDTO cadastraRequestDTO,
                                        HttpServletResponse response);

    CadastraResponseDTO cadastraCliente(@RequestBody CadastraRequestDTO cadastraRequestDTO,
                                        HttpServletResponse response);
}
