package com.desafio.backend.repository;

import com.desafio.backend.domain.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    UsuarioEntity findByLogin(String login);
}
