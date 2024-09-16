package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.EmpresaRequestDTO;
import com.desafio.backend.domain.dto.response.EmpresaResponseDTO;
import com.desafio.backend.infra.swagger.interfaces.EmpresaControllerOpenApi;
import com.desafio.backend.service.EmpresaService;
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
@RequestMapping(value = "/empresa", produces = {"application/xml", "application/json"})
@RequiredArgsConstructor
public class EmpresaController implements EmpresaControllerOpenApi {

    private final EmpresaService empresaService;

    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping
    @ResponseStatus(CREATED)
    public EmpresaResponseDTO cadastra(@Valid @RequestBody EmpresaRequestDTO requestDTO,
                                       HttpServletResponse response){
        EmpresaResponseDTO empresaResponse = empresaService.cadastra(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(empresaResponse.getId())
                .toUri();
        response.setHeader("Location", uri.toString());
        return empresaResponse;
    }

    @PreAuthorize("hasRole('GERENTE')")
    @GetMapping
    @ResponseStatus(OK)
    public Page<EmpresaResponseDTO> listaPaginada(@RequestParam(defaultValue = "0") int pagina,
                                                  @RequestParam(defaultValue = "5") int itens){
        return empresaService.buscaPaginada(pagina, itens);
    }

    @PreAuthorize("hasRole('GERENTE')")
    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public EmpresaResponseDTO buscaPorId(@PathVariable Long id){
        return empresaService.buscaPorId(id);
    }

    @PreAuthorize("hasRole('GERENTE')")
    @PutMapping(value = "/{id}")
    @ResponseStatus(OK)
    public EmpresaResponseDTO atualiza(@Valid @RequestBody EmpresaRequestDTO requestDTO,
                                         @PathVariable Long id){
        return empresaService.atualiza(requestDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id){
        empresaService.deleta(id);
    }


}
