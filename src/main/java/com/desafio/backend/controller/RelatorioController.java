package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.RelatorioRequestDTO;
import com.desafio.backend.domain.dto.response.RegistroEntradaSaida;
import com.desafio.backend.domain.dto.response.RelatorioResponseDTO;
import com.desafio.backend.service.RelatorioService;
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
@RequestMapping("/relatorio")
public class RelatorioController {

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

    @GetMapping("/registro-geral")
    @ResponseStatus(OK)
    public Page<RelatorioResponseDTO> registrosPaginados(@RequestParam(defaultValue = "0") int pagina,
                                                   @RequestParam(defaultValue = "5") int itens){
        return relatorioService.buscaPaginada(pagina, itens);
    }

    @GetMapping("/registro-geral/{id}")
    @ResponseStatus(OK)
    public RelatorioResponseDTO buscaRegistroPorID(@PathVariable Long id){
        return relatorioService.buscaPorID(id);
    }

    @PutMapping("/registra-saida/{id}")
    @ResponseStatus(OK)
    public RelatorioResponseDTO registraSaida(@PathVariable Long id){
        return relatorioService.registraSaida(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id){
        relatorioService.deleta(id);
    }

    @GetMapping("/entrada-saida")
    @ResponseStatus(OK)
    public RegistroEntradaSaida contadorEntradaSaida(){
        return relatorioService.contadorEntradaSaida();
    }



}


