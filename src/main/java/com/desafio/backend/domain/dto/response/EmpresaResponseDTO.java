package com.desafio.backend.domain.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EmpresaResponseDTO {

    private Long id;

    private String nome;

    private String cnpj;

    private List<String> telefone;

    private Integer vagasMoto;

    private Integer vagasCarro;

    private Long idEndereco;
}
