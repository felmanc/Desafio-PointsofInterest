-- Criação da tabela pointscoordnates
CREATE TABLE locations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    x BIGINT NOT NULL,
    y BIGINT NOT NULL
);
