package com.desafio.backend.service.impl;

import com.desafio.backend.domain.dto.request.VeiculoRequestDTO;
import com.desafio.backend.domain.dto.response.VeiculoResponseDTO;
import com.desafio.backend.domain.entity.VeiculoEntity;
import com.desafio.backend.domain.repository.VeiculoRepository;
import com.desafio.backend.exception.DadoInvalidoException;
import com.desafio.backend.exception.EntityNotFoundException;
import com.desafio.backend.mapper.VeiculoMapper;
import com.desafio.backend.service.VeiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.desafio.backend.exception.ExceptionMessages.VEICULO_NAO_ENCONTRADO;
import static com.desafio.backend.exception.ExceptionMessages.VEICULO_VINCULADO_A_RELATORIO;

@Service
@RequiredArgsConstructor
public class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Override
    @Transactional
    public VeiculoResponseDTO cadastra(VeiculoRequestDTO veiculoRequestDTO) {
        VeiculoEntity entity = VeiculoMapper.requestToEntity(veiculoRequestDTO);
        veiculoRepository.save(entity);
        return VeiculoMapper.entityToResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VeiculoResponseDTO> buscaPaginada(int pagina, int itens) {
        Page<VeiculoEntity> veiculoPaginado = veiculoRepository.findAll(PageRequest.of(pagina, itens));
        return veiculoPaginado.map(VeiculoMapper::entityToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public VeiculoResponseDTO buscaPorID(Long id) {
        VeiculoEntity entity = veiculoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(VEICULO_NAO_ENCONTRADO, id)));
        return VeiculoMapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public VeiculoResponseDTO atualiza(VeiculoRequestDTO responseDTO, Long id) {
        VeiculoEntity entity = veiculoRepository.getReferenceById(id);
        atualizaCampos(entity, responseDTO);
        veiculoRepository.save(entity);
        return VeiculoMapper.entityToResponse(entity);
    }

    @Override
    @Transactional
    public void deleta(Long id) {
        try {
            VeiculoEntity entity = veiculoRepository.getReferenceById(id);
            veiculoRepository.delete(entity);
        } catch (DataIntegrityViolationException ex){
            throw new DadoInvalidoException(String.format(VEICULO_VINCULADO_A_RELATORIO, id));
        }
    }

    private void atualizaCampos(VeiculoEntity entity, VeiculoRequestDTO request) {
        entity.setMarca(request.getMarca() != null ? request.getMarca() : entity.getMarca());
        entity.setModelo(request.getModelo() != null ? request.getModelo() : entity.getModelo());
        entity.setCor(request.getCor() != null ? request.getCor() : entity.getCor());
        entity.setPlaca(request.getPlaca() != null ? request.getPlaca() : entity.getPlaca());
        entity.setTipoDeVeiculo(request.getTipoDeVeiculo() != null ? request.getTipoDeVeiculo() : entity.getTipoDeVeiculo());
    }
}
