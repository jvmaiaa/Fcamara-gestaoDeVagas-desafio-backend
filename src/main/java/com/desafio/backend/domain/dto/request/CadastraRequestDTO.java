package com.desafio.backend.domain.dto.request;

import com.desafio.backend.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CadastraRequestDTO {

    private String login;

    private String senha;

    private RoleEnum role;
}
