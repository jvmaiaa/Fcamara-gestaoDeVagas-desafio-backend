package com.desafio.backend.service;

import com.desafio.backend.domain.dto.request.AuthRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {

    String obterToken(AuthRequestDTO dto);

    String validaTokenJwt(String login);
}
