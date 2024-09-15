package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.RelatorioRequestDTO;
import com.desafio.backend.domain.dto.response.RegistroEntradaSaida;
import com.desafio.backend.domain.dto.response.RelatorioResponseDTO;
import com.desafio.backend.infra.swagger.interfaces.RelatorioControllerOpenApi;
import com.desafio.backend.service.RelatorioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/relatorio", produces = {"application/xml", "application/json"})
public class RelatorioController implements RelatorioControllerOpenApi {

    private final RelatorioService relatorioService;

    @PostMapping
    @ResponseStatus(CREATED)
    public RelatorioResponseDTO registraEntrada(@Valid @RequestBody RelatorioRequestDTO relatorioRequestDTO,
                                         HttpServletResponse response){
        RelatorioResponseDTO relatorioResponse = relatorioService.registraEntrada(relatorioRequestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(relatorioResponse.getId())
                .toUri();
        response.setHeader("Location", uri.toString());
        return relatorioResponse;
    }

    @GetMapping(value = "/registro-geral")
    @ResponseStatus(OK)
    public Page<RelatorioResponseDTO> registrosPaginados(@RequestParam(defaultValue = "0") int pagina,
                                                   @RequestParam(defaultValue = "5") int itens){
        return relatorioService.buscaPaginada(pagina, itens);
    }

    @GetMapping(value = "/registro-geral/{id}")
    @ResponseStatus(OK)
    public RelatorioResponseDTO buscaRegistroPorID(@PathVariable Long id){
        return relatorioService.buscaPorID(id);
    }

    @PutMapping(value = "/registra-saida/{id}")
    @ResponseStatus(OK)
    public RelatorioResponseDTO registraSaida(@PathVariable Long id){
        return relatorioService.registraSaida(id);
    }

    @GetMapping(value = "/entrada-saida")
    @ResponseStatus(OK)
    public RegistroEntradaSaida contadorEntradaSaida(){
        return relatorioService.contadorEntradaSaida();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id){
        relatorioService.deleta(id);
    }


}


