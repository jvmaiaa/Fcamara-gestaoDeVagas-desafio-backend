package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.CadastraRequestDTO;
import com.desafio.backend.domain.dto.response.CadastraResponseDTO;
import com.desafio.backend.domain.enums.RoleEnum;
import com.desafio.backend.infra.swagger.interfaces.UsuarioControllerOpenApi;
import com.desafio.backend.service.impl.UsuarioServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/usuario", produces = {"application/xml", "application/json"})
public class UsuarioController implements UsuarioControllerOpenApi {

    private final UsuarioServiceImpl authenticationService;

    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping("/gerente")
    @ResponseStatus(CREATED)
    public CadastraResponseDTO cadastraGerente(@RequestBody CadastraRequestDTO cadastraRequestDTO,
                                               HttpServletResponse response) {
        return cadastraUsuario(cadastraRequestDTO, RoleEnum.GERENTE, response);
    }

    @PreAuthorize("hasAnyRole('GERENTE', 'FUNCIONARIO')")
    @PostMapping("/funcionario")
    @ResponseStatus(CREATED)
    public CadastraResponseDTO cadastraFuncionario(@RequestBody CadastraRequestDTO cadastraRequestDTO,
                                                   HttpServletResponse response) {
        return cadastraUsuario(cadastraRequestDTO, RoleEnum.FUNCIONARIO, response);
    }


    @PostMapping("/cliente")
    @ResponseStatus(CREATED)
    public CadastraResponseDTO cadastraCliente(@RequestBody CadastraRequestDTO cadastraRequestDTO,
                                               HttpServletResponse response) {
        return cadastraUsuario(cadastraRequestDTO, RoleEnum.CLIENTE, response);
    }

    private CadastraResponseDTO cadastraUsuario(CadastraRequestDTO cadastraRequestDTO, RoleEnum role,
                                                HttpServletResponse response) {
        CadastraResponseDTO usuarioResponseDTO = authenticationService.cadastra(cadastraRequestDTO, role);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(usuarioResponseDTO.getId())
                .toUri();
        response.setHeader("Location", uri.toString());
        return usuarioResponseDTO;
    }
}
