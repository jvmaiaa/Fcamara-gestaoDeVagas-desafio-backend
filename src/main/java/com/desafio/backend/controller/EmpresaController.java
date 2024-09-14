package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.EmpresaRequestDTO;
import com.desafio.backend.domain.dto.response.EmpresaResponseDTO;
import com.desafio.backend.service.EmpresaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

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

    @GetMapping(produces = {"application/json", "application/xml"})
    @ResponseStatus(OK)
    public Page<EmpresaResponseDTO> listaPaginada(@RequestParam(defaultValue = "0") int pagina,
                                                  @RequestParam(defaultValue = "5") int itens){
        return empresaService.buscaPaginada(pagina, itens);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public EmpresaResponseDTO buscaPorId(@PathVariable Long id){
        return empresaService.buscaPorId(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public EmpresaResponseDTO atualizada(@Valid @RequestBody EmpresaRequestDTO requestDTO,
                                         @PathVariable Long id){
        return empresaService.atualiza(requestDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id){
        empresaService.deleta(id);
    }


}
