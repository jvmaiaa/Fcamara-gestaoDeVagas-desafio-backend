CREATE TABLE tb_veiculo (
    id SERIAL NOT NULL PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    cor VARCHAR(50) NOT NULL,
    placa VARCHAR(20) NOT NULL,
    tipo_veiculo VARCHAR(50) NOT NULL CHECK (tipo_veiculo IN ('CARRO', 'MOTO'))
);