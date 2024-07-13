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