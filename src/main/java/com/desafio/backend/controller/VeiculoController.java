package com.desafio.backend.controller;

import com.desafio.backend.domain.dto.request.VeiculoRequestDTO;
import com.desafio.backend.domain.dto.response.EmpresaResponseDTO;
import com.desafio.backend.domain.dto.response.VeiculoResponseDTO;
import com.desafio.backend.service.VeiculoService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.PushBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

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

    @GetMapping
    @ResponseStatus(OK)
    public Page<VeiculoResponseDTO> listaPaginada(@RequestParam(defaultValue = "0") int pagina,
                                                  @RequestParam(defaultValue = "5") int itens) {
        return veiculoService.buscaPaginada(pagina, itens);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public VeiculoResponseDTO buscaPorId(@PathVariable Long id) {
        return veiculoService.buscaPorID(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public VeiculoResponseDTO atualiza(@Valid @RequestBody VeiculoRequestDTO veiculoRequestDTO,
                                       @PathVariable Long id) {
        return veiculoService.atualiza(veiculoRequestDTO, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleta(@PathVariable Long id) {
        veiculoService.deleta(id);
    }
}

