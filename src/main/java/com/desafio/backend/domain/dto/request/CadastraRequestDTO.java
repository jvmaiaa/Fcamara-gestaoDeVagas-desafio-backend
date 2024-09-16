package com.desafio.backend.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastraRequestDTO {

    private String login;

    private String senha;
}
