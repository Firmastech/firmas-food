--07/02/2025
alter table papel
    drop column restaurante_id;

CREATE TABLE public.categoriaEntity (
                                  id VARCHAR(36) NOT NULL PRIMARY KEY,
                                  nome VARCHAR(255) NOT NULL,
                                  etiqueta VARCHAR(255) NOT NULL,
                                  criado TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Inicializado com o horário atual
                                  atualizado TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Inicializado com o horário atual
                                  deletado TIMESTAMP, -- Pode ser NULL
                                  ativo BOOLEAN DEFAULT TRUE -- Valor padrão TRUE
);

-- truncate table categoria_prato;

alter table categoria_prato
    drop column restaurante_id;

alter table categoria_prato
    rename column id to categoria_id;

alter table categoria_prato
    add column prato_id VARCHAR(36) NOT NULL;

ALTER TABLE categoria_prato DROP CONSTRAINT categoria_pkey;

ALTER TABLE categoria_prato ADD PRIMARY KEY (categoria_id, prato_id);

ALTER TABLE categoria_prato
    ADD CONSTRAINT fk_categoria_prato_categoria FOREIGN KEY (categoria_id) REFERENCES categoriaEntity(id),
    ADD CONSTRAINT fk_categoria_prato_prato FOREIGN KEY (prato_id) REFERENCES pratoEntity(id);


CREATE TABLE cardapio_categoria (
                                    cardapio_id VARCHAR(36) NOT NULL,
                                    categoria_id VARCHAR(36) NOT NULL,
                                    PRIMARY KEY (cardapio_id, categoria_id),
                                    FOREIGN KEY (cardapio_id) REFERENCES cardapioEntity(id),
                                    FOREIGN KEY (categoria_id) REFERENCES categoriaEntity(id)
);

alter table categoria_prato
    drop column nome,
    drop column criado,
    drop column atualizado,
    drop column esta_ativo,
    drop column deletado

drop table cardapio_prato


alter table contato
    drop    column restaurante_id



CREATE TABLE restaurante_contato (
                                    restaurante_id VARCHAR(36) NOT NULL,
                                    contato_id VARCHAR(36) NOT NULL,
                                    PRIMARY KEY (restaurante_id, contato_id),
                                    FOREIGN KEY (restaurante_id) REFERENCES restaurante(id),
                                    FOREIGN KEY (contato_id) REFERENCES contato(id)
);


CREATE TABLE usuario_contato (
                                     usuario_id VARCHAR(36) NOT NULL,
                                     contato_id VARCHAR(36) NOT NULL,
                                     PRIMARY KEY (usuario_id, contato_id),
                                     FOREIGN KEY (usuario_id) REFERENCES  usuario(id),
                                     FOREIGN KEY (contato_id) REFERENCES contato(id)
);

alter table usuario
    add column endereco_id VARCHAR(36),
    ADD CONSTRAINT fk_endereco_usuario FOREIGN KEY (endereco_id) REFERENCES endereco(id)

alter table pratoEntity
    rename column esta_ativo to ativo

alter table cardapioEntity
    rename column esta_ativo to ativo

alter table prato
    drop column categoria


alter table cardapio
    add column etiqueta VARCHAR(255)


