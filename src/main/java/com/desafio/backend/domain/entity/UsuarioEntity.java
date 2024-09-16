package com.desafio.backend.domain.entity;

import com.desafio.backend.domain.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class UsuarioEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "papel")
    private RoleEnum papel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.papel == RoleEnum.GERENTE) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_GERENTE"),
                    new SimpleGrantedAuthority("ROLE_FUNCIONARIO"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
        }
        if (this.papel == RoleEnum.FUNCIONARIO) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_FUNCIONARIO"),
                    new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
        }
        if (this.papel == RoleEnum.CLIENTE) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_CLIENTE")
            );
        }
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }
}
