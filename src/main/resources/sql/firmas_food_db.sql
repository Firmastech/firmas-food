CREATE TABLE public.endereco (
                          id VARCHAR(36) NOT NULL PRIMARY KEY,
                          rua VARCHAR(255) NOT NULL,
                          numero INTEGER NOT NULL,
                          cep VARCHAR(9) NOT NULL,
                          cidade VARCHAR(100) NOT NULL,
                          uf CHAR(2) NOT NULL,
                          pontoReferencia VARCHAR(144) NULL
);

CREATE TABLE public.restaurante (
                                    id VARCHAR(36) NOT NULL PRIMARY KEY,
                                    nome VARCHAR(100) NOT NULL,
                                    enderecoId VARCHAR(36) NOT NULL,
                                    tempoEstimado INT NOT NULL,
                                    statusFuncionamento VARCHAR(10) NOT NULL CHECK (statusFuncionamento IN ('ABERTO', 'FECHADO', 'INATIVO')),
                                    FOREIGN KEY (enderecoId) REFERENCES endereco (id));


CREATE TABLE public.cardapio (
                                 id VARCHAR(36) NOT NULL,
                                 nome VARCHAR(255) NOT NULL,
                                 descricao VARCHAR(8000),
                                 restaurante_id VARCHAR(36) NOT NULL,
                                 criado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 atualizado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 esta_ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                 deletado TIMESTAMP,
                                 CONSTRAINT cardapio_pkey PRIMARY KEY (id),
                                 CONSTRAINT cardapio_restaurante_id FOREIGN KEY (restaurante_id)
                                     REFERENCES public.restaurante(id)
);
CREATE TABLE public.prato (
                              id VARCHAR(36) NOT NULL,
                              descricao VARCHAR(8000),
                              nome VARCHAR(255) NOT NULL,
                              preco NUMERIC(10, 2) NOT NULL,
                              categoria VARCHAR(255),
                              url_imagem VARCHAR(8000),
                              porcentagem_desconto DECIMAL(10, 2),
                              criado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              atualizado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              esta_ativo BOOLEAN NOT NULL DEFAULT TRUE,
                              deletado TIMESTAMP,
                              CONSTRAINT prato_pkey PRIMARY KEY (id)
);

CREATE TABLE public.cardapio_prato (
                                       cardapio_id VARCHAR(36) NOT NULL,
                                       prato_id VARCHAR(36) NOT NULL,
                                       PRIMARY KEY (cardapio_id, prato_id),
                                       CONSTRAINT fk_cardapio
                                           FOREIGN KEY (cardapio_id)
                                               REFERENCES public.cardapio(id)
                                               ON DELETE CASCADE,
                                       CONSTRAINT fk_prato
                                           FOREIGN KEY (prato_id)
                                               REFERENCES public.prato(id)
                                               ON DELETE CASCADE
);

CREATE TABLE public.categoria_prato (
                                        id VARCHAR(36) NOT NULL,
                                        nome VARCHAR(255) NOT NULL,
                                        restaurante_id VARCHAR(36) NOT NULL,
                                        criado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        atualizado TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        esta_ativo BOOLEAN NOT NULL DEFAULT TRUE,
                                        deletado TIMESTAMP,
                                        CONSTRAINT categoria_pkey PRIMARY KEY (id),
                                        CONSTRAINT restaurante_id FOREIGN KEY (restaurante_id)
                                            REFERENCES public.restaurante(id)
);


create table public.usuario(
    id VARCHAR(255) not null,
    CONSTRAINT usuario_pkey PRIMARY KEY (id)
);

CREATE TABLE public.pedido (
                               id VARCHAR(36) NOT NULL,
                               usuario_id VARCHAR(255) NOT NULL,
                               restaurante_id varchar(36) NOT NULL,
                               valor numeric(10, 2) NOT NULL,
                               status varchar(255) NOT NULL,
                               data_hora_confirmacao timestamp,
                               data_hora_pedido timestamp not null,
                               data_hora_entrega timestamp,
                               CONSTRAINT pedidos_pkey PRIMARY KEY (id),
                               CONSTRAINT fk_pedido_restaurante FOREIGN KEY (restaurante_id)
                                   REFERENCES public.restaurante(id),
                               CONSTRAINT fk_pedido_usuario_id FOREIGN KEY (usuario_id)
                                   REFERENCES public.usuario(id)
);


CREATE TABLE public.item_pedido (
                                    id varchar(36) NOT NULL,
                                    prato_id varchar(36) NOT NULL,
                                    observacao varchar(600) NOT NULL,
                                    pedido_id varchar(36) not null,
                                        quantidade int4 NOT NULL,
                                    CONSTRAINT item_pedido_pkey PRIMARY KEY (id),
                                    CONSTRAINT item_pedido_pratoid_fkey FOREIGN KEY (prato_id)
                                        REFERENCES public.prato(id),
                                        CONSTRAINT fk_item_pedido FOREIGN KEY (pedido_id)
                                        REFERENCES public.pedido(id)
);
--Padronizando para snake_case
ALTER TABLE public.endereco
    RENAME COLUMN pontoReferencia TO ponto_referencia;

ALTER TABLE public.restaurante
    RENAME COLUMN enderecoId TO endereco_id;
ALTER TABLE public.restaurante
    RENAME COLUMN tempoEstimado TO tempo_estimado;
ALTER TABLE public.restaurante
    RENAME COLUMN statusFuncionamento TO status_funcionamento;

-- Adicionar novas colunas
ALTER TABLE public.restaurante
    ADD COLUMN cardapio_id VARCHAR(36); -- Pode ser NULL

ALTER TABLE public.restaurante
    ADD COLUMN criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP; -- Inicializado com o horário atual

ALTER TABLE public.restaurante
    ADD COLUMN atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP; -- Inicializado com o horário atual

ALTER TABLE public.restaurante
    ADD COLUMN deletado TIMESTAMP; -- Pode ser NULL

ALTER TABLE public.restaurante
    ADD COLUMN ativo BOOLEAN DEFAULT TRUE;

ALTER TABLE public.restaurante
    ADD CONSTRAINT fk_restaurante_cardapio_id
        FOREIGN KEY (cardapio_id) REFERENCES public.cardapio (id);

-- Adicionar novas colunas
ALTER TABLE public.endereco
    ADD COLUMN criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP; -- Inicializado com o horário atual

ALTER TABLE public.endereco
    ADD COLUMN atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP; -- Inicializado com o horário atual

ALTER TABLE public.endereco
    ADD COLUMN deletado TIMESTAMP; -- Pode ser NULL

ALTER TABLE public.endereco
    ADD COLUMN ativo BOOLEAN DEFAULT TRUE;

CREATE TABLE public.contato (
                                id VARCHAR(36) NOT NULL PRIMARY KEY,
                                restaurante_id VARCHAR(36) NOT NULL,
                                contato VARCHAR(255) NOT NULL,
                                tipo_contato VARCHAR(50) NOT NULL,
                                criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Inicializado com o horário atual
                                atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Inicializado com o horário atual
                                deletado TIMESTAMP, -- Pode ser NULL
                                ativo BOOLEAN DEFAULT TRUE -- Valor padrão TRUE
);

ALTER TABLE public.contato
    ADD CONSTRAINT fk_contato_restaurante_id
        FOREIGN KEY (restaurante_id) REFERENCES public.restaurante (id);

alter table endereco
    drop column numero;
alter table endereco
    add column numero varchar (12);

alter table restaurante
    add subdominio varchar(50);

alter table restaurante
    add unique (subdominio);

alter table restaurante
    alter column subdominio set not null;

-- Tabela de Papéis (Roles)
CREATE TABLE public.papel (
                              id VARCHAR(36) NOT NULL PRIMARY KEY,
                              nome VARCHAR(255) NOT NULL, -- Nome do papel (ex: "Gerente", "Garçom")
                              restaurante_id VARCHAR(36) NOT NULL, -- Papel pertence a um restaurante específico
                              criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              deletado TIMESTAMP,
                              ativo BOOLEAN DEFAULT TRUE,
                              CONSTRAINT fk_papel_restaurante FOREIGN KEY (restaurante_id)
                                  REFERENCES public.restaurante(id)
);

-- Tabela de Permissões (Permissions)
CREATE TABLE public.permissao (
                                  id VARCHAR(36) NOT NULL PRIMARY KEY,
                                  nome VARCHAR(255) NOT NULL, -- Nome da permissão (ex: "criar_pedido", "editar_cardapio")
                                  descricao VARCHAR(8000), -- Descrição opcional da permissão
                                  criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  deletado TIMESTAMP,
                                  ativo BOOLEAN DEFAULT TRUE
);

-- Tabela de Relacionamento entre Papéis e Permissões (Role_Permissions)
CREATE TABLE public.papel_permissao (
                                        papel_id VARCHAR(36) NOT NULL,
                                        permissao_id VARCHAR(36) NOT NULL,
                                        PRIMARY KEY (papel_id, permissao_id),
                                        CONSTRAINT fk_papel_permissao_papel FOREIGN KEY (papel_id)
                                            REFERENCES public.papel(id)
                                            ON DELETE CASCADE,
                                        CONSTRAINT fk_papel_permissao_permissao FOREIGN KEY (permissao_id)
                                            REFERENCES public.permissao(id)
                                            ON DELETE CASCADE
);

-- Tabela de Relacionamento entre Usuários e Papéis (User_Roles)
CREATE TABLE public.usuario_papel (
                                      usuario_id VARCHAR(255) NOT NULL,
                                      papel_id VARCHAR(36) NOT NULL,
                                      restaurante_id VARCHAR(36) NOT NULL, -- Usuário tem um papel em um restaurante específico
                                      PRIMARY KEY (usuario_id, papel_id, restaurante_id),
                                      CONSTRAINT fk_usuario_papel_usuario FOREIGN KEY (usuario_id)
                                          REFERENCES public.usuario(id)
                                          ON DELETE CASCADE,
                                      CONSTRAINT fk_usuario_papel_papel FOREIGN KEY (papel_id)
                                          REFERENCES public.papel(id)
                                          ON DELETE CASCADE,
                                      CONSTRAINT fk_usuario_papel_restaurante FOREIGN KEY (restaurante_id)
                                          REFERENCES public.restaurante(id)
                                          ON DELETE CASCADE
);


ALTER TABLE public.usuario
    ADD COLUMN restaurante_id VARCHAR(36);

ALTER TABLE public.usuario
    ADD CONSTRAINT fk_usuario_restaurante
        FOREIGN KEY (restaurante_id)
            REFERENCES public.restaurante(id);

ALTER TABLE public.usuario
    ADD COLUMN email VARCHAR(255) NOT NULL;

ALTER TABLE public.usuario
    ADD COLUMN senha VARCHAR(255) NOT NULL;

ALTER TABLE public.usuario
    ADD COLUMN ativo bool NOT NULL default false;

ALTER TABLE public.usuario
    ADD COLUMN primeiro_nome VARCHAR(255) NOT NULL;

ALTER TABLE public.usuario
    ADD COLUMN segundo_nome VARCHAR(255) NOT NULL;