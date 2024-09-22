package com.desafio.backend.domain.entity;

import com.desafio.backend.domain.enums.TipoVeiculo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_relatorio")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataEntrada;

    private LocalDateTime dataSaida;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresaEntity empresa;

    @OneToOne
    @JoinColumn(name = "veiculo_id")
    private VeiculoEntity veiculoEntity;
}
