package com.desafio.backend.infra.swagger.interfaces;

import com.desafio.backend.domain.dto.request.AuthRequestDTO;

public interface AuthenticationControllerOpenApi {

    String pegaToken(AuthRequestDTO dto);
}