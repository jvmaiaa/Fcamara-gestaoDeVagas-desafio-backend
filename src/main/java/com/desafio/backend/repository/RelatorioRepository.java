package com.desafio.backend.repository;

import com.desafio.backend.domain.entity.RelatorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
