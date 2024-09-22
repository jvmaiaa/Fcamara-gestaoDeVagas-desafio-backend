CREATE TABLE tb_empresa (
    id SERIAL NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(20) NOT NULL,
    telefone VARCHAR(255)[] NOT NULL,
    vagas_moto INTEGER NOT NULL,
    vagas_carro INTEGER NOT NULL,
    endereco_id INTEGER NOT NULL,
    FOREIGN KEY (endereco_id) REFERENCES tb_endereco(id) ON DELETE CASCADE
);