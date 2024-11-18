-- Script de migração V1: Criação da tabela departamento
CREATE TABLE departamento (
                              departamento_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              nome VARCHAR(100) NOT NULL
);
