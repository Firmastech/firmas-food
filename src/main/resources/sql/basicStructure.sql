--CREATE DATABASE db_restaurante;

CREATE TABLE endereco (
                          id VARCHAR(36) NOT NULL PRIMARY KEY,
                          rua VARCHAR(255) NOT NULL,
                          numero INTEGER NOT NULL,
                          cep VARCHAR(9) NOT NULL,
                          cidade VARCHAR(100) NOT NULL,
                          uf CHAR(2) NOT NULL,
                          pontoReferencia VARCHAR(144) NULL
);

CREATE TABLE tag_desconto (
                              id VARCHAR(36) NOT NULL PRIMARY KEY,
                              nome VARCHAR(100) NOT NULL,
                              descricao VARCHAR(2000) NOT NULL,
                              porcentagemDesconto DECIMAL NOT NULL
);

CREATE TABLE culinaria (
                           id VARCHAR(36) NOT NULL PRIMARY KEY,
                           tipo VARCHAR(144) NOT NULL
);

-- CREATE TABLE privilegio (
--   uniqueId BIGINT AUTO_INCREMENT NOT NULL,
--   nome VARCHAR(100) NOT NULL,
--   PRIMARY KEY (uniqueId)
-- );

-- CREATE TABLE tag_acesso (
--   tagId BIGINT NOT NULL,
--   nome VARCHAR(100) NOT NULL,
--   descricao VARCHAR(3000) NOT NULL,
--   privilegioId BIGINT,
--   PRIMARY KEY (tagId),
--   FOREIGN KEY (privilegioId) REFERENCES privilegio (uniqueId)
-- );

CREATE TABLE restaurante (
                             id UUID NOT NULL PRIMARY KEY,
                             nome VARCHAR(100),
                             endereco_id VARCHAR(36),
                             tempo_estimado INT,
                             status_funcionamento VARCHAR(10),
                             cardapio_id VARCHAR(36),
                             data_inclusao timestamp,
                             data_atualizacao timestamp,
                             data_exclusao timestamp,
                             ativo BOOLEAN NOT NULL
);

CREATE TABLE prato (
                       id VARCHAR(36) NOT NULL PRIMARY KEY,
                       nome VARCHAR(100) NOT NULL,
                       descricao VARCHAR(2000) NOT NULL,
                       urlImagem VARCHAR(8000) NOT NULL,
                       preco DECIMAL(10, 2) NOT NULL,
                       tagDescontoId VARCHAR(36) NOT NULL,
                       FOREIGN KEY (tagDescontoId) REFERENCES tag_desconto (id)
);

CREATE TABLE cardapio (
                          id VARCHAR(36) NOT NULL PRIMARY KEY,
                          pratoId VARCHAR(36) NOT NULL,
                          tipoCulinariaId VARCHAR(36) NOT NULL,
                          FOREIGN KEY (pratoId) REFERENCES prato (id),
                          FOREIGN KEY (tipoCulinariaId) REFERENCES culinaria (id)
);

CREATE TABLE item_pedido (
                             id VARCHAR(36) NOT NULL PRIMARY KEY,
                             pratoId VARCHAR(36) NOT NULL,
                             descricao VARCHAR(600) NOT NULL,
                             quantidade INT NOT NULL,
                             FOREIGN KEY (pratoId) REFERENCES prato (id)
);

-- CREATE TABLE promocao (
--   promocaoId BIGINT AUTO_INCREMENT NOT NULL,
--   tagDescontoId BIGINT NOT NULL,
--   inicioPromocao DATETIME NOT NULL,
--   fimPromocao DATETIME NOT NULL,
--   usoLimitado BOOLEAN NOT NULL,
--   quantidadeUso BIGINT NOT NULL,
--   PRIMARY KEY (promocaoId),
--   FOREIGN KEY (tagDescontoId) REFERENCES tag_desconto (descontoId)
-- );

CREATE TABLE cronograma_funcionamento (
                                          id VARCHAR(36) NOT NULL,
                                          restauranteId VARCHAR(36) NOT NULL,
                                          FOREIGN KEY (restauranteId) REFERENCES restaurante (id),
                                          PRIMARY KEY (id, restauranteId)
);

CREATE TABLE horario_de_funcionamento (
                                          id VARCHAR(36) NOT NULL PRIMARY KEY,
                                          domingo BOOLEAN NOT NULL,
                                          segunda BOOLEAN NOT NULL,
                                          terca BOOLEAN NOT NULL,
                                          quarta BOOLEAN NOT NULL,
                                          quinta BOOLEAN NOT NULL,
                                          sexta BOOLEAN NOT NULL,
                                          sabado BOOLEAN NOT NULL,
                                          abertura TIME NOT NULL,
                                          fechamento TIME NOT NULL,
                                          cronogramaFuncionamentoId VARCHAR(36) NOT NULL,
                                          restauranteId VARCHAR(36) NOT NULL,
                                          FOREIGN KEY (cronogramaFuncionamentoId, restauranteId) REFERENCES cronograma_funcionamento (id, restauranteId)
);
alter table
restaurante add column estaAtivo boolean;

alter table
cardapio drop column pratoId;

alter table
prato add column pratoId VARCHAR(36) NOT NULL;

ALTER TABLE
prato
    ADD CONSTRAINT fk_prato_cardapio FOREIGN KEY (pratoId) REFERENCES cardapio(id);