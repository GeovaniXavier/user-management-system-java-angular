-- Script de migração V2: Criação da tabela usuario
CREATE TABLE usuario
(
    usuario_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome            VARCHAR(100)        NOT NULL,
    email           VARCHAR(100) UNIQUE NOT NULL,
    senha           VARCHAR(255)        NOT NULL,
    telefone        VARCHAR(20),
    cpf             VARCHAR(14) UNIQUE,
    endereco        VARCHAR(255),
    departamento_id BIGINT,
    CONSTRAINT fk_departamento
        FOREIGN KEY (departamento_id)
            REFERENCES departamento (departamento_id)
            ON DELETE SET NULL
);
