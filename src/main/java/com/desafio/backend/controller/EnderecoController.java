package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.EnderecoRequestDTO;
import com.desafio.backend.domain.dto.response.EnderecoResponseDTO;
import com.desafio.backend.infra.swagger.interfaces.EnderecoControllerOpenApi;
import com.desafio.backend.service.EnderecoService;
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
@RequestMapping(value = "/endereco", produces = {"application/xml", "application/json"})
public class EnderecoController implements EnderecoControllerOpenApi {

    private final EnderecoService enderecoService;

    @PreAuthorize("hasRole('GERENTE')")
    @PostMapping
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

    @PreAuthorize("hasRole('GERENTE')")
    @GetMapping
    @ResponseStatus(OK)
    public Page<EnderecoResponseDTO> listaPaginada(@RequestParam(defaultValue = "0") int pagina,
                                                   @RequestParam(defaultValue = "5") int itens){
        return enderecoService.buscaPaginada(pagina, itens);
    }

    @PreAuthorize("hasRole('GERENTE')")
    @GetMapping(value = "/{id}")
    @ResponseStatus(OK)
    public EnderecoResponseDTO buscaPorId(@PathVariable Long id){
        return enderecoService.buscaPorId(id);
    }

    @PreAuthorize("hasRole('GERENTE')")
    @PutMapping(value = "/{id}")
    @ResponseStatus(OK)
    public EnderecoResponseDTO atualiza(@PathVariable Long id,
                                        @RequestBody EnderecoRequestDTO enderecoRequestDTO){
        return enderecoService.atualiza(enderecoRequestDTO, id);
    }

    @PreAuthorize("hasRole('GERENTE')")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id){
        enderecoService.deleta(id);
    }
}
