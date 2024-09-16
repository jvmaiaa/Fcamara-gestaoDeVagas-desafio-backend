package com.desafio.backend.infra.security;

import com.desafio.backend.domain.entity.UsuarioEntity;
import com.desafio.backend.infra.exceptions.EntityNotFoundException;
import com.desafio.backend.repository.UsuarioRepository;
import com.desafio.backend.service.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.desafio.backend.infra.exceptions.ExceptionMessages.SEM_AUTORIZACAO;

@RequiredArgsConstructor
@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final AuthenticationService authenticationService;
    private final UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraiTokenHeader(request);

        if (token != null) {
            String login = authenticationService.validaTokenJwt(token);
            UsuarioEntity usuario = usuarioRepository.findByLogin(login);

            if (usuario == null) {
                throw new EntityNotFoundException(SEM_AUTORIZACAO);
            }
            var authentication =
                    new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    public String extraiTokenHeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }

        //   [0]     [1]
        // Bearer xxxxxxxxx
        if (!authHeader.split(" ")[0].equals("Bearer")) {
            return null;
        }

        return authHeader.split(" ")[1];
    }
}