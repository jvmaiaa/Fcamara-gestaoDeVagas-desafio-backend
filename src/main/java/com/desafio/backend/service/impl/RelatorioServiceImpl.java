package com.desafio.backend.service.impl;

import com.desafio.backend.domain.dto.request.RelatorioRequestDTO;
import com.desafio.backend.domain.dto.response.RegistroEntradaSaida;
import com.desafio.backend.domain.dto.response.RelatorioResponseDTO;
import com.desafio.backend.domain.entity.EmpresaEntity;
import com.desafio.backend.domain.entity.RelatorioEntity;
import com.desafio.backend.domain.enums.TipoVeiculo;
import com.desafio.backend.domain.repository.EmpresaRepository;
import com.desafio.backend.domain.repository.RelatorioRepository;
import com.desafio.backend.domain.repository.VeiculoRepository;
import com.desafio.backend.exception.DadoInvalidoException;
import com.desafio.backend.exception.EntityNotFoundException;
import com.desafio.backend.exception.RelatorioCompletoException;
import com.desafio.backend.mapper.RelatorioMapper;
import com.desafio.backend.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.desafio.backend.exception.ExceptionMessages.*;

@Service
@RequiredArgsConstructor
public class RelatorioServiceImpl implements RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final VeiculoRepository veiculoRepository;
    private final EmpresaRepository empresaRepository;

    @Override
    @Transactional
    public RelatorioResponseDTO registraEntrada(RelatorioRequestDTO relatorioRequestDTO) {
        try {
            RelatorioEntity relatorioEntity = RelatorioMapper.requestToEntity(relatorioRequestDTO, veiculoRepository, empresaRepository);
            relatorioEntity.setDataEntrada(LocalDateTime.now());
            relatorioRepository.save(relatorioEntity);

            EmpresaEntity empresaEntity = empresaRepository.findById(relatorioRequestDTO.getIdEmpresa())
                    .orElseThrow(() -> new EntityNotFoundException(String.format(RELATORIO_NAO_ENCONTRADO, relatorioRequestDTO.getIdEmpresa())));

            diminuiVagas(empresaEntity, relatorioEntity);
            return RelatorioMapper.entityToResponse(relatorioEntity);

        } catch (DataIntegrityViolationException e) {
            throw new DadoInvalidoException(String.format(EMPRESA_OU_VEICULOS_JA_CADASTRADOS));
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RelatorioResponseDTO> buscaPaginada(int pagina, int itens) {
        Page<RelatorioEntity> relatorioPaginado = relatorioRepository.findAll(PageRequest.of(pagina, itens));
        return relatorioPaginado.map(RelatorioMapper::entityToResponse);
    }

    @Override
    @Transactional(readOnly = true)
    public RelatorioResponseDTO buscaPorID(Long id) {
        RelatorioEntity relatorioEntity = relatorioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(RELATORIO_NAO_ENCONTRADO, id)));
        return RelatorioMapper.entityToResponse(relatorioEntity);
    }

    @Override
    @Transactional
    public RelatorioResponseDTO registraSaida(Long id) {
        RelatorioEntity relatorioEntity = relatorioRepository.getReferenceById(id);
        if (relatorioEntity.getDataSaida() != null) {
            throw new RelatorioCompletoException(String.format(RELATORIO_COMPLETO, id));
        }
        relatorioEntity.setDataSaida(LocalDateTime.now());

        EmpresaEntity entity = empresaRepository.findById(relatorioEntity.getEmpresa().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(EMPRESA_NAO_ENCONTRADA, relatorioEntity.getEmpresa().getId())));
        aumentaVagas(entity, relatorioEntity);
        return RelatorioMapper.entityToResponse(relatorioEntity);
    }

    @Override
    @Transactional
    public void deleta(Long id) {
        RelatorioEntity relatorioEntity = relatorioRepository.getReferenceById(id);
        relatorioRepository.delete(relatorioEntity);
    }

    public RegistroEntradaSaida contadorEntradaSaida() {
        Optional<Integer> contadorDeEntrada = relatorioRepository.contadorDeEntrada();
        Optional<Integer> contadorDeSaida = relatorioRepository.contadorDeSaida();
        RegistroEntradaSaida registro = new RegistroEntradaSaida();
        registro.setQuantidadeEntrada(contadorDeEntrada.orElse(0));
        registro.setQuantidadeSaida(contadorDeSaida.orElse(0));
        return registro;
    }

    public static void diminuiVagas(EmpresaEntity empresa, RelatorioEntity relatorio){
        empresa.setVagasMoto(relatorio.getTipoVeiculo() == TipoVeiculo.MOTO
                ? empresa.getVagasMoto() - 1
                : empresa.getVagasMoto());

        empresa.setVagasCarro(relatorio.getTipoVeiculo() == TipoVeiculo.CARRO
                ? empresa.getVagasCarro() - 1
                : empresa.getVagasCarro());
    }

    public static void aumentaVagas(EmpresaEntity empresa, RelatorioEntity relatorio){
        empresa.setVagasMoto(relatorio.getTipoVeiculo() == TipoVeiculo.MOTO
                ? empresa.getVagasMoto() + 1
                : empresa.getVagasMoto());

        empresa.setVagasCarro(relatorio.getTipoVeiculo() == TipoVeiculo.CARRO
                ? empresa.getVagasCarro() + 1
                : empresa.getVagasCarro());
    }
}
