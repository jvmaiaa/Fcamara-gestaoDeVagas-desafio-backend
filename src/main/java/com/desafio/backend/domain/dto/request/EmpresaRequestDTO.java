package com.desafio.backend.domain.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EmpresaRequestDTO {

    @NotEmpty(message = "Campo 'nome' não pode ficar em branco.")
    private String nome;

    @NotEmpty(message = "Campo 'cnpj' não pode ficar em branco.")
    private String cnpj;

    @NotEmpty(message = "Campo 'telefone' não pode ficar em branco.")
    private List<String> telefone;

    @NotNull(message = "Campo 'vagasMoto' não pode ficar em branco.")
    private Integer vagasMoto;

    @NotNull(message = "Campo 'vagasCarro' não pode ficar em branco.")
    private Integer vagasCarro;

    @NotNull(message = "Campo 'idEndereco' não pode ficar em branco.")
    private Long idEndereco;
}
