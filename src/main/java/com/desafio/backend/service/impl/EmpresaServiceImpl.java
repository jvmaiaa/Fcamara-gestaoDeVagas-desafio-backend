package com.desafio.backend.service.impl;

import com.desafio.backend.domain.dto.request.EmpresaRequestDTO;
import com.desafio.backend.domain.dto.response.EmpresaResponseDTO;
import com.desafio.backend.domain.entity.EmpresaEntity;
import com.desafio.backend.domain.entity.EnderecoEntity;
import com.desafio.backend.repository.EmpresaRepository;
import com.desafio.backend.repository.EnderecoRepository;
import com.desafio.backend.infra.exceptions.DadoInvalidoException;
import com.desafio.backend.infra.exceptions.EntityNotFoundException;
import com.desafio.backend.domain.mapper.EmpresaMapper;
import com.desafio.backend.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.desafio.backend.infra.exceptions.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public EmpresaResponseDTO cadastra(EmpresaRequestDTO requestDTO) {
        try {
            EmpresaEntity empresaEntity = EmpresaMapper.requestToEntity(requestDTO);
            Long idEndereco = requestDTO.getIdEndereco();
            EnderecoEntity enderecoEntity = enderecoRepository.findById(idEndereco)
                    .orElseThrow(() -> new EntityNotFoundException(String.format(ENDERECO_NAO_ENCONTRADO, idEndereco)));
            empresaEntity.setEndereco(enderecoEntity);
            empresaRepository.save(empresaEntity);
            EmpresaResponseDTO response = EmpresaMapper.entityToResponse(empresaEntity);
            response.setIdEndereco(idEndereco);
            return response;
        } catch (DataIntegrityViolationException e){
            throw new DadoInvalidoException(ENDERECO_JA_CADASTRADO);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmpresaResponseDTO> buscaPaginada(int pagina, int itens) {
        Page<EmpresaEntity> empresaPaginada = empresaRepository.findAll(PageRequest.of(pagina, itens));
        return empresaPaginada.map(EmpresaMapper::entityToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public EmpresaResponseDTO buscaPorId(Long id) {
        EmpresaEntity entity = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(EMPRESA_NAO_ENCONTRADA, id)));
        return EmpresaMapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public EmpresaResponseDTO atualiza(EmpresaRequestDTO empresaRequestDTO, Long id) {
        EmpresaEntity entity = empresaRepository.getReferenceById(id);
        atualizaCampos(entity, empresaRequestDTO, enderecoRepository);
        empresaRepository.save(entity);
        return EmpresaMapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public void deleta(Long id) {
        try {
            EmpresaEntity entity = empresaRepository.getReferenceById(id);
            empresaRepository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DadoInvalidoException(String.format(EMPRESA_VINCULADA_A_RELATORIO, id));
        }
    }

    private static void atualizaCampos(EmpresaEntity entity, EmpresaRequestDTO request, EnderecoRepository enderecoRepository){
        entity.setNome(request.getNome() != null ? request.getNome() : entity.getNome());
        entity.setCnpj(request.getCnpj() != null ? request.getCnpj() : entity.getCnpj());
        entity.setTelefone(request.getCnpj() != null ? request.getTelefone() : entity.getTelefone());
        entity.setVagasMoto(request.getVagasMoto() != null ? request.getVagasMoto(): entity.getVagasMoto());
        entity.setVagasCarro(request.getVagasCarro() != null ? request.getVagasCarro() : entity.getVagasCarro());
        if (request.getIdEndereco() != null){
            EnderecoEntity enderecoEntity = enderecoRepository.findById(request.getIdEndereco())
                    .orElseThrow(() -> new EntityNotFoundException(String.format(ENDERECO_NAO_ENCONTRADO, request.getIdEndereco())));
        }
    }
}
