package com.desafio.backend.domain.entity;

import com.desafio.backend.domain.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_veiculo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;

    private String modelo;

    private String cor;

    private String placa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_veiculo")
    private TipoVeiculo tipoVeiculo;

    @OneToOne(mappedBy = "veiculoEntity")
    private RelatorioEntity relatorioEntity;

}
