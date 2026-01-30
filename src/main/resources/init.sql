CREATE DATABASE IF NOT EXISTS biblioteca
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE biblioteca;

-- =========================
-- TABELA AUTOR
-- =========================
CREATE TABLE autor (
    id_autor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(50) NOT NULL,
    estilo VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- TABELA LIVRO
-- =========================
CREATE TABLE livro (
    id_livro INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    ano_publicacao INT,
    isbn VARCHAR(20) UNIQUE,
    status ENUM('disponivel', 'indisponivel') DEFAULT 'disponivel',
    id_autor INT NOT NULL,
    CONSTRAINT fk_livro_autor
        FOREIGN KEY (id_autor)
        REFERENCES autor(id_autor)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- =========================
-- DADOS INICIAIS (opcional)
-- =========================
INSERT INTO autor (nome, nacionalidade, estilo)
VALUES ('Machado de Assis', 'Brasileiro', 'Realismo');
