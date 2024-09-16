package com.desafio.backend.service.impl;

import com.desafio.backend.domain.dto.request.CadastraRequestDTO;
import com.desafio.backend.domain.dto.response.CadastraResponseDTO;
import com.desafio.backend.domain.entity.UsuarioEntity;
import com.desafio.backend.domain.mapper.UsuarioMapper;
import com.desafio.backend.infra.exceptions.UsuarioExisteException;
import com.desafio.backend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.desafio.backend.infra.exceptions.ExceptionMessages.USUARIO_JA_EXISTE;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public CadastraResponseDTO cadastra(CadastraRequestDTO usuario) {
        UsuarioEntity usuarioExistente = usuarioRepository.findByLogin(usuario.getLogin());
        if (usuarioExistente != null) {
            throw new UsuarioExisteException(USUARIO_JA_EXISTE);

        }
        var senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        UsuarioEntity usuarioEntity = UsuarioMapper.requestToEntity(usuario);
        usuarioRepository.save(usuarioEntity);
        return UsuarioMapper.entityToResponse(usuarioEntity);
    }
}
