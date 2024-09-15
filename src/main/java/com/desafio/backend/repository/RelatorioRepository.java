package com.desafio.backend.repository;

import com.desafio.backend.domain.entity.RelatorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RelatorioRepository extends JpaRepository<RelatorioEntity, Long> {

    @Query(value = """
        SELECT count(*)
        FROM tb_relatorio
        WHERE data_entrada IS NOT NULL
            """, nativeQuery = true)
    Optional<Integer> contadorDeEntrada();

    @Query(value = """
        SELECT count(*)
        FROM tb_relatorio
        WHERE data_saida IS NOT NULL
            """, nativeQuery = true)
    Optional<Integer> contadorDeSaida();

    @Query(value = """
    SELECT COUNT(*)
    FROM tb_relatorio r
    WHERE r.empresa_id = :empresaId
    AND r.data_entrada BETWEEN :horaInicio AND :horaFim
        """, nativeQuery = true)
    Integer contarEntradasPorHora(Long empresaId, LocalDateTime horaInicio, LocalDateTime horaFim);

    @Query(value = """
    SELECT COUNT(*)
    FROM tb_relatorio r
    WHERE r.empresa_id = :empresaId
    AND r.data_saida BETWEEN :horaInicio AND :horaFim
        """, nativeQuery = true)
    Integer contarSaidasPorHora(Long empresaId, LocalDateTime horaInicio, LocalDateTime horaFim);
}
