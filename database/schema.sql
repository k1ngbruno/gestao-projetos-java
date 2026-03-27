CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome_completo VARCHAR(120) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(120) NOT NULL UNIQUE,
    cargo VARCHAR(80) NOT NULL,
    login VARCHAR(60) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    perfil VARCHAR(20) NOT NULL
);

CREATE TABLE equipe (
    id_equipe INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE TABLE equipe_usuario (
    id_equipe INT NOT NULL,
    id_usuario INT NOT NULL,
    PRIMARY KEY (id_equipe, id_usuario),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id_equipe),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

CREATE TABLE projeto (
    id_projeto INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_inicio DATE NOT NULL,
    data_termino_prevista DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    id_gerente INT NOT NULL,
    FOREIGN KEY (id_gerente) REFERENCES usuario(id_usuario)
);

CREATE TABLE projeto_equipe (
    id_projeto INT NOT NULL,
    id_equipe INT NOT NULL,
    PRIMARY KEY (id_projeto, id_equipe),
    FOREIGN KEY (id_projeto) REFERENCES projeto(id_projeto),
    FOREIGN KEY (id_equipe) REFERENCES equipe(id_equipe)
);

CREATE TABLE tarefa (
    id_tarefa INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim_prevista DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    id_projeto INT NOT NULL,
    id_responsavel INT NOT NULL,
    FOREIGN KEY (id_projeto) REFERENCES projeto(id_projeto),
    FOREIGN KEY (id_responsavel) REFERENCES usuario(id_usuario)
);
