package com.desafio.backend.mapper;

import com.desafio.backend.domain.dto.request.VeiculoRequestDTO;
import com.desafio.backend.domain.dto.response.VeiculoResponseDTO;
import com.desafio.backend.domain.entity.VeiculoEntity;

public class VeiculoMapper {

    public static VeiculoEntity requestToEntity(VeiculoRequestDTO request){
        if (request == null){
            return null;
        }
        VeiculoEntity entity = new VeiculoEntity();
        entity.setMarca(request.getMarca());
        entity.setModelo(request.getModelo());
        entity.setCor(request.getCor());
        entity.setPlaca(request.getPlaca());
        entity.setTipoDeVeiculo(request.getTipoDeVeiculo());
        return entity;
    }

    public static VeiculoResponseDTO entityToResponse(VeiculoEntity entity){
        if (entity == null){
            return null;
        }
        VeiculoResponseDTO response = new VeiculoResponseDTO();
        response.setId(entity.getId());
        response.setMarca(entity.getMarca());
        response.setModelo(entity.getModelo());
        response.setCor(entity.getCor());
        response.setPlaca(entity.getPlaca());
        response.setTipoDeVeiculo(entity.getTipoDeVeiculo());
        return response;
    }
}
