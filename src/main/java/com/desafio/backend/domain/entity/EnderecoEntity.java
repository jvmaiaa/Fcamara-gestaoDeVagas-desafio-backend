package com.desafio.backend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_endereco")
public class EnderecoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    @OneToOne(mappedBy = "endereco")
    private EmpresaEntity empresa;
}
