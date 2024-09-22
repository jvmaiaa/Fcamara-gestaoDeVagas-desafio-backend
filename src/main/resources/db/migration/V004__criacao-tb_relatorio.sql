CREATE TABLE tb_relatorio (
    id SERIAL NOT NULL PRIMARY KEY,
    data_entrada TIMESTAMP NOT NULL,
    data_saida TIMESTAMP,
    empresa_id INTEGER NOT NULL,
    veiculo_id INTEGER NOT NULL,
    FOREIGN KEY (empresa_id) REFERENCES tb_empresa(id) ON DELETE CASCADE,
    FOREIGN KEY (veiculo_id) REFERENCES tb_veiculo(id) ON DELETE CASCADE
);