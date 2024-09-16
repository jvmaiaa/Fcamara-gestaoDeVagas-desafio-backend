package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.VeiculoRequestDTO;
import com.desafio.backend.domain.dto.response.VeiculoResponseDTO;
import com.desafio.backend.infra.swagger.interfaces.VeiculoControllerOpenApi;
import com.desafio.backend.service.VeiculoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/veiculo", produces = {"application/xml", "application/json"})
public class VeiculoController implements VeiculoControllerOpenApi {

    private final VeiculoService veiculoService;

    @PreAuthorize("hasAnyRole('GERENTE', 'FUNCIONARIO', 'CLIENTE')")
    @PostMapping
    @ResponseStatus(CREATED)
    public VeiculoResponseDTO cadastra(@Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO,
                                       HttpServletResponse response) {
        VeiculoResponseDTO veiculoResponseDTO = veiculoService.cadastra(veiculoRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(veiculoResponseDTO.getId())
                .toUri();
        response.setHeader("Location", uri.toString());
        return veiculoResponseDTO;
    }

    @PreAuthorize("hasAnyRole('GERENTE', 'FUNCIONARIO')")
    @GetMapping(produces = {"application/xml", "application/json"})
    @ResponseStatus(OK)
    public Page<VeiculoResponseDTO> listaPaginada(@RequestParam(defaultValue = "0") int pagina,
                                                  @RequestParam(defaultValue = "5") int itens) {
        return veiculoService.buscaPaginada(pagina, itens);
    }

    @PreAuthorize("hasAnyRole('GERENTE', 'FUNCIONARIO')")
    @GetMapping(value = "/{id}", produces = {"application/xml", "application/json"})
    @ResponseStatus(OK)
    public VeiculoResponseDTO buscaPorId(@PathVariable Long id) {
        return veiculoService.buscaPorID(id);
    }

    @PreAuthorize("hasAnyRole('GERENTE', 'FUNCIONARIO')")
    @PutMapping(value = "/{id}", produces = {"application/xml", "application/json"})
    @ResponseStatus(OK)
    public VeiculoResponseDTO atualiza(@Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO,
                                       @PathVariable Long id) {
        return veiculoService.atualiza(veiculoRequestDTO, id);
    }

    @PreAuthorize("hasAnyRole('GERENTE', 'FUNCIONARIO')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        veiculoService.deleta(id);
    }
}

