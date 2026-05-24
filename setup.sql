-- =======================================
-- SISTEMA DE TUTORIA ACADÊMICA – FMP
-- Nome do banco: fmp_tutoria_db
-- Criação do banco e tabelas (MySQL)
-- =======================================

-- Criar banco de dados
CREATE DATABASE IF NOT EXISTS fmp_tutoria_db;
USE fmp_tutoria_db;

-- 1. Usuario
CREATE TABLE usuario (
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    dataCadastro TIMESTAMP NOT NULL,
    tipoPerfil VARCHAR(20) NOT NULL CHECK (tipoPerfil IN ('TUTOR', 'ESTUDANTE', 'COORDENADOR'))
);

-- 2. Coordenador
CREATE TABLE coordenador (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matriculaProfessor INT NOT NULL UNIQUE,
    usuario_id VARCHAR(36) NOT NULL UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- 3. Tutor
CREATE TABLE tutor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    curriculo TEXT,
    aprovado BOOLEAN NOT NULL DEFAULT FALSE,
    especialidade VARCHAR(50),
    usuario_id VARCHAR(36) NOT NULL UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- 4. Estudante
CREATE TABLE estudante (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    matricula INT NOT NULL UNIQUE,
    curso VARCHAR(100) NOT NULL,
    usuario_id VARCHAR(36) NOT NULL UNIQUE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id) ON DELETE CASCADE
);

-- 5. AreaConhecimento
CREATE TABLE area_conhecimento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT
);

-- 6. Tutor_Area (Tabela Intermediária M:N)
CREATE TABLE tutor_area (
    tutor_id BIGINT NOT NULL,
    area_id BIGINT NOT NULL,
    PRIMARY KEY (tutor_id, area_id),
    FOREIGN KEY (tutor_id) REFERENCES tutor(id) ON DELETE CASCADE,
    FOREIGN KEY (area_id) REFERENCES area_conhecimento(id) ON DELETE CASCADE
);

-- 7. Disponibilidade
CREATE TABLE disponibilidade (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    diaSemana VARCHAR(20) NOT NULL,
    horario TIME NOT NULL,
    agenda BOOLEAN NOT NULL DEFAULT TRUE,
    tutor_id BIGINT NOT NULL,
    FOREIGN KEY (tutor_id) REFERENCES tutor(id) ON DELETE CASCADE,
    CONSTRAINT uk_tutor_horario UNIQUE (tutor_id, horario)
);

-- 8. Agendamento
CREATE TABLE agendamento (
    id VARCHAR(36) PRIMARY KEY,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    dataHora TIMESTAMP NOT NULL,
    disponibilidade_id BIGINT NOT NULL UNIQUE,
    estudante_id BIGINT NOT NULL,
    tutor_id BIGINT NOT NULL,
    FOREIGN KEY (disponibilidade_id) REFERENCES disponibilidade(id) ON DELETE CASCADE,
    FOREIGN KEY (estudante_id) REFERENCES estudante(id) ON DELETE CASCADE,
    FOREIGN KEY (tutor_id) REFERENCES tutor(id) ON DELETE CASCADE
);

-- 9. Avaliacao
CREATE TABLE avaliacao (
    id VARCHAR(36) PRIMARY KEY,
    nota INTEGER NOT NULL CHECK (nota BETWEEN 1 AND 5),
    comentario TEXT,
    dataAvaliacao TIMESTAMP NOT NULL,
    agendamento_id VARCHAR(36) NOT NULL UNIQUE,
    FOREIGN KEY (agendamento_id) REFERENCES agendamento(id) ON DELETE CASCADE
);
