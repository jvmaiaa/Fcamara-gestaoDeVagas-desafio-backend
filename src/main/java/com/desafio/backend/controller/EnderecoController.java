package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.EnderecoRequestDTO;
import com.desafio.backend.domain.dto.response.EnderecoResponseDTO;
import com.desafio.backend.service.EnderecoService;
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
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping(produces = {"application/xml", "application/json"})
    @ResponseStatus(CREATED)
    public EnderecoResponseDTO cadastra(@Valid @RequestBody EnderecoRequestDTO requestDTO,
                                        HttpServletResponse response){
        EnderecoResponseDTO enderecoResponse = enderecoService.cadastra(requestDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(enderecoResponse.getId())
                .toUri();
        response.setHeader("Location", uri.toString());
        return enderecoResponse;
    }

    @GetMapping(produces = {"application/xml", "application/json"})
    @ResponseStatus(OK)
    public Page<EnderecoResponseDTO> listaPaginada(@RequestParam(defaultValue = "0") int pagina,
                                                   @RequestParam(defaultValue = "5") int itens){
        return enderecoService.buscaPaginada(pagina, itens);
    }

    @GetMapping(value = "/{id}", produces = {"application/xml", "application/json"})
    @ResponseStatus(OK)
    public EnderecoResponseDTO buscaPorId(@PathVariable Long id){
        return enderecoService.buscaPorId(id);
    }

    @PutMapping(value = "/{id}", produces = {"application/xml", "application/json"})
    @ResponseStatus(OK)
    public EnderecoResponseDTO atualiza(@PathVariable Long id,
                                        @RequestBody EnderecoRequestDTO enderecoRequestDTO){
        return enderecoService.atualiza(enderecoRequestDTO, id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id){
        enderecoService.deleta(id);
    }
}
