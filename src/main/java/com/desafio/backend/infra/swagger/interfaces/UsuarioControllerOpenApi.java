package com.desafio.backend.infra.swagger.interfaces;

import com.desafio.backend.domain.dto.request.CadastraRequestDTO;
import com.desafio.backend.domain.dto.response.CadastraResponseDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface UsuarioControllerOpenApi {

    CadastraResponseDTO cadastraUsuario(CadastraRequestDTO cadastraRequestDTO,
                                        HttpServletResponse response);
}
