package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.CadastraRequestDTO;
import com.desafio.backend.domain.dto.response.CadastraResponseDTO;
import com.desafio.backend.infra.swagger.interfaces.UsuarioControllerOpenApi;
import com.desafio.backend.service.impl.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioControllerOpenApi {

    private final UsuarioServiceImpl authenticationService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CadastraResponseDTO cadastraUsuario(@RequestBody CadastraRequestDTO cadastraRequestDTO,
                                               HttpServletResponse response) {
        CadastraResponseDTO usuarioResponseDTO = authenticationService.cadastra(cadastraRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(usuarioResponseDTO.getId())
                .toUri();
        response.setHeader("Location", uri.toString());
        return usuarioResponseDTO;
    }
}
