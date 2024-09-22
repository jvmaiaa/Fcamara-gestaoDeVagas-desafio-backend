package com.desafio.backend.service.impl;

import com.desafio.backend.domain.dto.request.RelatorioRequestDTO;
import com.desafio.backend.domain.dto.response.EntradaSaidaPorHoraResponseDTO;
import com.desafio.backend.domain.dto.response.EntradaSaidaTotalResponseDTO;
import com.desafio.backend.domain.dto.response.RelatorioResponseDTO;
import com.desafio.backend.domain.entity.EmpresaEntity;
import com.desafio.backend.domain.entity.RelatorioEntity;
import com.desafio.backend.domain.enums.TipoVeiculo;
import com.desafio.backend.domain.mapper.RelatorioMapper;
import com.desafio.backend.infra.exceptions.DadoInvalidoException;
import com.desafio.backend.infra.exceptions.EntityNotFoundException;
import com.desafio.backend.infra.exceptions.RelatorioCompletoException;
import com.desafio.backend.repository.EmpresaRepository;
import com.desafio.backend.repository.RelatorioRepository;
import com.desafio.backend.repository.VeiculoRepository;
import com.desafio.backend.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static com.desafio.backend.infra.exceptions.ExceptionMessages.*;

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
                    .orElseThrow(() -> new EntityNotFoundException(String.format(EMPRESA_NAO_ENCONTRADA, relatorioRequestDTO.getIdEmpresa())));

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
        verificaDataSaida(relatorioEntity);
        EmpresaEntity entity = empresaRepository.findById(relatorioEntity.getEmpresa().getId())
                .orElseThrow(() -> new EntityNotFoundException(String.format(EMPRESA_NAO_ENCONTRADA, relatorioEntity.getEmpresa().getId())));
        aumentaVagas(entity, relatorioEntity);
        relatorioRepository.save(relatorioEntity);
        return RelatorioMapper.entityToResponse(relatorioEntity);
    }

    @Override
    @Transactional
    public void deleta(Long id) {
        RelatorioEntity relatorioEntity = relatorioRepository.getReferenceById(id);
        relatorioRepository.delete(relatorioEntity);
    }

    public EntradaSaidaTotalResponseDTO contadorEntradaSaida() {
        Optional<Integer> contadorDeEntrada = relatorioRepository.contadorDeEntrada();
        Optional<Integer> contadorDeSaida = relatorioRepository.contadorDeSaida();
        EntradaSaidaTotalResponseDTO registro = new EntradaSaidaTotalResponseDTO();
        registro.setQuantidadeEntrada(contadorDeEntrada.orElse(0));
        registro.setQuantidadeSaida(contadorDeSaida.orElse(0));
        return registro;
    }

    public EntradaSaidaPorHoraResponseDTO contarEntradaSaidaPorHora(Long empresaId, LocalDateTime dataInicio, LocalDateTime dataFim) {
        try {
            if (dataFim.isBefore(dataInicio)) {
                throw new DadoInvalidoException(String.format(DATA_COM_ORDEM_INVALIDA));
            }
            Integer entradas = relatorioRepository.contarEntradasPorHora(empresaId, dataInicio, dataFim);
            Integer saidas = relatorioRepository.contarSaidasPorHora(empresaId, dataInicio, dataFim);

            return new EntradaSaidaPorHoraResponseDTO(entradas, saidas);
        } catch (DateTimeParseException e) {
            throw new DadoInvalidoException(String.format(DATA_INVALIDA));
        }
    }

    public static void diminuiVagas(EmpresaEntity empresa, RelatorioEntity relatorio){
        empresa.setVagasMoto(relatorio.getVeiculoEntity().getTipoVeiculo() == TipoVeiculo.MOTO
                ? empresa.getVagasMoto() - 1
                : empresa.getVagasMoto());

        empresa.setVagasCarro(relatorio.getVeiculoEntity().getTipoVeiculo() == TipoVeiculo.CARRO
                ? empresa.getVagasCarro() - 1
                : empresa.getVagasCarro());
    }

    public static void aumentaVagas(EmpresaEntity empresa, RelatorioEntity relatorio){
        empresa.setVagasMoto(relatorio.getVeiculoEntity().getTipoVeiculo() == TipoVeiculo.MOTO
                ? empresa.getVagasMoto() + 1
                : empresa.getVagasMoto());

        empresa.setVagasCarro(relatorio.getVeiculoEntity().getTipoVeiculo() == TipoVeiculo.CARRO
                ? empresa.getVagasCarro() + 1
                : empresa.getVagasCarro());
    }

    public static void verificaDataSaida(RelatorioEntity relatorioEntity) {
        if (relatorioEntity.getDataSaida().isBefore(relatorioEntity.getDataEntrada())) {
            throw new DadoInvalidoException(String.format(RELATORIO_SAIDA_ANTES_ENTRADA));
        }
    }
}
