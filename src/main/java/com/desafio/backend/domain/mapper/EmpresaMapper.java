package com.desafio.backend.domain.mapper;

import com.desafio.backend.domain.dto.request.EmpresaRequestDTO;
import com.desafio.backend.domain.dto.response.EmpresaResponseDTO;
import com.desafio.backend.domain.entity.EmpresaEntity;

public class EmpresaMapper {

    public static EmpresaEntity requestToEntity(EmpresaRequestDTO request){
        if (request == null){
            return null;
        }
        EmpresaEntity entity = new EmpresaEntity();
        entity.setNome(request.getNome());
        entity.setCnpj(request.getCnpj());
        entity.setTelefone(request.getTelefone());
        entity.setVagasMoto(request.getVagasMoto());
        entity.setVagasCarro(request.getVagasCarro());
        return entity;
    }

    public static EmpresaResponseDTO entityToResponse(EmpresaEntity entity){
        if (entity == null){
            return null;
        }
        EmpresaResponseDTO response = new EmpresaResponseDTO();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setCnpj(entity.getCnpj());
        response.setTelefone(entity.getTelefone());
        response.setVagasMoto(entity.getVagasMoto());
        response.setVagasCarro(entity.getVagasCarro());
        response.setIdEndereco(entity.getEndereco().getId());
        return response;
    }
}
