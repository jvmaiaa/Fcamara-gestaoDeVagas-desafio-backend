package com.desafio.backend.domain.mapper;

import com.desafio.backend.domain.dto.request.EnderecoRequestDTO;
import com.desafio.backend.domain.dto.response.EnderecoResponseDTO;
import com.desafio.backend.domain.entity.EnderecoEntity;

public class EnderecoMapper {

    public static EnderecoEntity requestToEntity(EnderecoRequestDTO request){
        if (request == null){
            return null;
        }
        EnderecoEntity entity = new EnderecoEntity();
        entity.setCep(request.getCep());
        entity.setRua(request.getRua());
        entity.setNumero(request.getNumero());
        entity.setBairro(request.getBairro());
        entity.setCidade(request.getCidade());
        entity.setEstado(request.getEstado());
        return entity;
    }

    public static EnderecoResponseDTO entityToResponse(EnderecoEntity entity){
        if (entity == null){
            return null;
        }
        EnderecoResponseDTO response = new EnderecoResponseDTO();
        response.setId(entity.getId());
        response.setCep(entity.getCep());
        response.setRua(entity.getRua());
        response.setNumero(entity.getNumero());
        response.setBairro(entity.getBairro());
        response.setCidade(entity.getCidade());
        response.setEstado(entity.getEstado());
        return response;
    }
}
