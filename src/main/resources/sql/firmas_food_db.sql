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