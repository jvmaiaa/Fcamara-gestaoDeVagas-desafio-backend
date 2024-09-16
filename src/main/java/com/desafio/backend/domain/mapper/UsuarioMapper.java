package com.desafio.backend.domain.mapper;

import com.desafio.backend.domain.dto.request.CadastraRequestDTO;
import com.desafio.backend.domain.dto.response.CadastraResponseDTO;
import com.desafio.backend.domain.entity.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity requestToEntity(CadastraRequestDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setLogin(dto.getLogin());
        entity.setSenha(dto.getSenha());
        entity.setPapel(dto.getRole());
        return entity;
    }

    public static CadastraResponseDTO entityToResponse(UsuarioEntity entity) {
        CadastraResponseDTO dto = new CadastraResponseDTO();
        dto.setId(entity.getId());
        dto.setLogin(entity.getLogin());
        dto.setRole(entity.getPapel());
        return dto;
    }
}

