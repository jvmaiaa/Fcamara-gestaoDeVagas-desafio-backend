package com.desafio.backend.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.desafio.backend.domain.dto.request.AuthRequestDTO;
import com.desafio.backend.domain.entity.UsuarioEntity;
import com.desafio.backend.infra.exceptions.TokenGeneratioException;
import com.desafio.backend.repository.UsuarioRepository;
import com.desafio.backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static com.desafio.backend.infra.exceptions.ExceptionMessages.ERRO_GERACAO_TOKEN;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    public String obterToken(AuthRequestDTO auth) {
        UsuarioEntity entity = usuarioRepository.findByLogin(auth.getLogin());
        return geraTokenJwt(entity);
    }

    public String geraTokenJwt(UsuarioEntity usuario){
        try {
            // TODO: Ajustar depois a chave privada para um .env
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(gerarDataExpiracao())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new TokenGeneratioException(ERRO_GERACAO_TOKEN);
        }
    }

    public String validaTokenJwt(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant gerarDataExpiracao() {
        return LocalDateTime.now()
                .plusMinutes(60)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}
