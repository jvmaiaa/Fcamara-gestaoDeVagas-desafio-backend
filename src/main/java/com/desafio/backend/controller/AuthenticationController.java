package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.AuthRequestDTO;
import com.desafio.backend.infra.swagger.interfaces.AuthenticationControllerOpenApi;
import com.desafio.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController implements AuthenticationControllerOpenApi {

    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    @ResponseStatus(OK)
    public String pegaToken(@RequestBody AuthRequestDTO authDto) {
        var usuarioAutenticationToken =
                new UsernamePasswordAuthenticationToken(authDto.getLogin(), authDto.getSenha());
        authenticationManager.authenticate(usuarioAutenticationToken);

        return authenticationService.obterToken(authDto);
    }

}
