package com.desafio.backend.service.impl;

import com.desafio.backend.domain.dto.request.EnderecoRequestDTO;
import com.desafio.backend.domain.dto.response.EnderecoResponseDTO;
import com.desafio.backend.domain.entity.EnderecoEntity;
import com.desafio.backend.domain.repository.EnderecoRepository;
import com.desafio.backend.exception.DadoInvalidoException;
import com.desafio.backend.exception.EntityNotFoundException;
import com.desafio.backend.exception.ExceptionMessages;
import com.desafio.backend.mapper.EnderecoMapper;
import com.desafio.backend.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.desafio.backend.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Override
    @Transactional
    public EnderecoResponseDTO cadastra(EnderecoRequestDTO enderecoRequestDTO) {
        EnderecoEntity entity = EnderecoMapper.requestToEntity(enderecoRequestDTO);
        enderecoRepository.save(entity);
        return EnderecoMapper.entityToResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EnderecoResponseDTO> buscaPaginada(int pagina, int itens) {
        Page<EnderecoEntity> enderecoPaginado =  enderecoRepository.findAll(PageRequest.of(pagina, itens));
        return enderecoPaginado.map(EnderecoMapper::entityToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public EnderecoResponseDTO buscaPorId(Long id) {
        EnderecoEntity endereco = enderecoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ENDERECO_NAO_ENCONTRADO, id)));
        return EnderecoMapper.entityToResponse(endereco);
    }

    @Override
    @Transactional
    public EnderecoResponseDTO atualiza(EnderecoRequestDTO enderecoRequestDTO, Long id) {
        EnderecoEntity entity = enderecoRepository.getReferenceById(id);
        atualizaCampos(entity, enderecoRequestDTO);
        enderecoRepository.save(entity);
        return EnderecoMapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public void deleta(Long id) {
        try {
            EnderecoEntity entity = enderecoRepository.getReferenceById(id);
            enderecoRepository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DadoInvalidoException(String.format(ENDERECO_VINCULADO_A_EMPRESA));
        }
    }

    private void atualizaCampos(EnderecoEntity entity, EnderecoRequestDTO request){
        entity.setCep(request.getCep() != null ? request.getCep() : entity.getCep());
        entity.setRua(request.getRua() != null ? request.getRua() : entity.getRua());
        entity.setNumero(request.getNumero() != null ? request.getNumero() : entity.getNumero());
        entity.setBairro(request.getBairro() != null ? request.getBairro() : entity.getBairro());
        entity.setCidade(request.getCidade() != null ? request.getCidade() : entity.getCidade());
        entity.setEstado(request.getEstado() != null ? request.getEstado() : entity.getEstado());
    }
}
