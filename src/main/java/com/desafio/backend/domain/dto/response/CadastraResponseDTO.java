package com.desafio.backend.domain.dto.response;

import com.desafio.backend.domain.enums.RoleEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CadastraResponseDTO {

    private Long id;

    private String login;

    private RoleEnum role;
}
