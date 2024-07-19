--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Drop databases (except postgres and template1)
--

DROP DATABASE db_restaurante;
DROP DATABASE keycloak;




--
-- Drop roles
--

DROP ROLE dbo;
DROP ROLE keycloak_user;
DROP ROLE postgres;
DROP ROLE restaurante_user;


--
-- Roles
--

CREATE ROLE dbo;
ALTER ROLE dbo WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:LC/MZx2gPTtSAUNT1cWWnw==$5HggIuA2ugGzhm6vLViDCKXW5Zb2zNI31u89bcQe/6s=:lDq4jyu6iTPQr7uzwJiAeIR+6rRr4ps/B8IVscbpBgg=';
CREATE ROLE keycloak_user;
ALTER ROLE keycloak_user WITH NOSUPERUSER INHERIT NOCREATEROLE NOCREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:eI70QyMYJJfC/rOUd0mSxg==$rkMIE3mzO0AmZp9pOGmdmMLb63NIAq52FBIMHKEzvdQ=:7gb9OjF/eQvgMwOFiDv1/TRL/fYyWfZ1LYf7nFnQuIA=';
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:y8R9wvNrFxjfnHmg3p7NjA==$wcHCYOc8D8+jd0QuPXcPe3r6cX3BOQWY/qfG2Z/1UtU=:9vhz8V/BZTI5Im6nNj9F2cvWL1orkkE9/7JMLldRrHI=';
CREATE ROLE restaurante_user;
ALTER ROLE restaurante_user WITH NOSUPERUSER INHERIT NOCREATEROLE NOCREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:DE80OBX6TSJDPMr3bK2wXA==$upBU3GctZwCkYHCv3GFyFyIEfEyYJp+41sNssQdIukU=:y81XBRXIGtXP+u0ilaYlZdUAmb9mK0V8Do7LQmBNPVw=';

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Debian 16.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

UPDATE pg_catalog.pg_database SET datistemplate = false WHERE datname = 'template1';
DROP DATABASE template1;
--
-- Name: template1; Type: DATABASE; Schema: -; Owner: dbo
--

CREATE DATABASE template1 WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE template1 OWNER TO dbo;

\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: COMMENT; Schema: -; Owner: dbo
--

COMMENT ON DATABASE template1 IS 'default template for new databases';


--
-- Name: template1; Type: DATABASE PROPERTIES; Schema: -; Owner: dbo
--

ALTER DATABASE template1 IS_TEMPLATE = true;


\connect template1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE template1; Type: ACL; Schema: -; Owner: dbo
--

REVOKE CONNECT,TEMPORARY ON DATABASE template1 FROM PUBLIC;
GRANT CONNECT ON DATABASE template1 TO PUBLIC;


--
-- PostgreSQL database dump complete
--

--
-- Database "db_restaurante" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Debian 16.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: db_restaurante; Type: DATABASE; Schema: -; Owner: dbo
--

CREATE DATABASE db_restaurante WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE db_restaurante OWNER TO dbo;

\connect db_restaurante

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: cardapio; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.cardapio (
    id character varying(36) NOT NULL,
    nome character varying(255) NOT NULL,
    descricao character varying(8000),
    restaurante_id character varying(36) NOT NULL,
    criado timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    atualizado timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    esta_ativo boolean DEFAULT true NOT NULL,
    deletado timestamp without time zone
);


ALTER TABLE public.cardapio OWNER TO dbo;

--
-- Name: cardapio_prato; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.cardapio_prato (
    cardapio_id character varying(36) NOT NULL,
    prato_id character varying(36) NOT NULL
);


ALTER TABLE public.cardapio_prato OWNER TO dbo;

--
-- Name: categoria_prato; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.categoria_prato (
    id character varying(36) NOT NULL,
    nome character varying(255) NOT NULL,
    restaurante_id character varying(36) NOT NULL,
    criado timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    atualizado timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    esta_ativo boolean DEFAULT true NOT NULL,
    deletado timestamp without time zone
);


ALTER TABLE public.categoria_prato OWNER TO dbo;

--
-- Name: endereco; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.endereco (
    id character varying(36) NOT NULL,
    rua character varying(255) NOT NULL,
    numero integer NOT NULL,
    cep character varying(9) NOT NULL,
    cidade character varying(100) NOT NULL,
    uf character(2) NOT NULL,
    pontoreferencia character varying(144)
);


ALTER TABLE public.endereco OWNER TO dbo;

--
-- Name: item_pedido; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.item_pedido (
    id character varying(36) NOT NULL,
    prato_id character varying(36) NOT NULL,
    observacao character varying(600) NOT NULL,
    pedido_id character varying(36) NOT NULL,
    quantidade integer NOT NULL
);


ALTER TABLE public.item_pedido OWNER TO dbo;

--
-- Name: pedido; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.pedido (
    id character varying(36) NOT NULL,
    usuario_id character varying(255) NOT NULL,
    restaurante_id character varying(36) NOT NULL,
    valor numeric(10,2) NOT NULL,
    status character varying(255) NOT NULL,
    data_hora_confirmacao timestamp without time zone,
    data_hora_pedido timestamp without time zone NOT NULL,
    data_hora_entrega timestamp without time zone
);


ALTER TABLE public.pedido OWNER TO dbo;

--
-- Name: prato; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.prato (
    id character varying(36) NOT NULL,
    descricao character varying(8000),
    nome character varying(255) NOT NULL,
    preco numeric(10,2) NOT NULL,
    categoria character varying(255),
    url_imagem character varying(8000),
    porcentagem_desconto numeric(10,2),
    criado timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    atualizado timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    esta_ativo boolean DEFAULT true NOT NULL,
    deletado timestamp without time zone
);


ALTER TABLE public.prato OWNER TO dbo;

--
-- Name: restaurante; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.restaurante (
    id character varying(36) NOT NULL,
    nome character varying(100) NOT NULL,
    enderecoid character varying(36) NOT NULL,
    tempoestimado integer NOT NULL,
    statusfuncionamento character varying(10) NOT NULL,
    CONSTRAINT restaurante_statusfuncionamento_check CHECK (((statusfuncionamento)::text = ANY (ARRAY[('ABERTO'::character varying)::text, ('FECHADO'::character varying)::text, ('INATIVO'::character varying)::text])))
);


ALTER TABLE public.restaurante OWNER TO dbo;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.usuario (
    id character varying(255) NOT NULL
);


ALTER TABLE public.usuario OWNER TO dbo;

--
-- Data for Name: cardapio; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.cardapio (id, nome, descricao, restaurante_id, criado, atualizado, esta_ativo, deletado) FROM stdin;
\.


--
-- Data for Name: cardapio_prato; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.cardapio_prato (cardapio_id, prato_id) FROM stdin;
\.


--
-- Data for Name: categoria_prato; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.categoria_prato (id, nome, restaurante_id, criado, atualizado, esta_ativo, deletado) FROM stdin;
\.


--
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.endereco (id, rua, numero, cep, cidade, uf, pontoreferencia) FROM stdin;
\.


--
-- Data for Name: item_pedido; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.item_pedido (id, prato_id, observacao, pedido_id, quantidade) FROM stdin;
\.


--
-- Data for Name: pedido; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.pedido (id, usuario_id, restaurante_id, valor, status, data_hora_confirmacao, data_hora_pedido, data_hora_entrega) FROM stdin;
\.


--
-- Data for Name: prato; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.prato (id, descricao, nome, preco, categoria, url_imagem, porcentagem_desconto, criado, atualizado, esta_ativo, deletado) FROM stdin;
\.


--
-- Data for Name: restaurante; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.restaurante (id, nome, enderecoid, tempoestimado, statusfuncionamento) FROM stdin;
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.usuario (id) FROM stdin;
\.


--
-- Name: cardapio cardapio_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.cardapio
    ADD CONSTRAINT cardapio_pkey PRIMARY KEY (id);


--
-- Name: cardapio_prato cardapio_prato_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.cardapio_prato
    ADD CONSTRAINT cardapio_prato_pkey PRIMARY KEY (cardapio_id, prato_id);


--
-- Name: categoria_prato categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.categoria_prato
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- Name: endereco endereco_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.endereco
    ADD CONSTRAINT endereco_pkey PRIMARY KEY (id);


--
-- Name: item_pedido item_pedido_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pkey PRIMARY KEY (id);


--
-- Name: pedido pedidos_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT pedidos_pkey PRIMARY KEY (id);


--
-- Name: prato prato_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.prato
    ADD CONSTRAINT prato_pkey PRIMARY KEY (id);


--
-- Name: restaurante restaurante_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.restaurante
    ADD CONSTRAINT restaurante_pkey PRIMARY KEY (id);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);


--
-- Name: cardapio cardapio_restaurante_id; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.cardapio
    ADD CONSTRAINT cardapio_restaurante_id FOREIGN KEY (restaurante_id) REFERENCES public.restaurante(id);


--
-- Name: cardapio_prato fk_cardapio; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.cardapio_prato
    ADD CONSTRAINT fk_cardapio FOREIGN KEY (cardapio_id) REFERENCES public.cardapio(id) ON DELETE CASCADE;


--
-- Name: item_pedido fk_item_pedido; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT fk_item_pedido FOREIGN KEY (pedido_id) REFERENCES public.pedido(id);


--
-- Name: pedido fk_pedido_restaurante; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk_pedido_restaurante FOREIGN KEY (restaurante_id) REFERENCES public.restaurante(id);


--
-- Name: pedido fk_pedido_usuario_id; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.pedido
    ADD CONSTRAINT fk_pedido_usuario_id FOREIGN KEY (usuario_id) REFERENCES public.usuario(id);


--
-- Name: cardapio_prato fk_prato; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.cardapio_prato
    ADD CONSTRAINT fk_prato FOREIGN KEY (prato_id) REFERENCES public.prato(id) ON DELETE CASCADE;


--
-- Name: item_pedido item_pedido_pratoid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.item_pedido
    ADD CONSTRAINT item_pedido_pratoid_fkey FOREIGN KEY (prato_id) REFERENCES public.prato(id);


--
-- Name: restaurante restaurante_enderecoid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.restaurante
    ADD CONSTRAINT restaurante_enderecoid_fkey FOREIGN KEY (enderecoid) REFERENCES public.endereco(id);


--
-- Name: categoria_prato restaurante_id; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.categoria_prato
    ADD CONSTRAINT restaurante_id FOREIGN KEY (restaurante_id) REFERENCES public.restaurante(id);


--
-- Name: DATABASE db_restaurante; Type: ACL; Schema: -; Owner: dbo
--

REVOKE ALL ON DATABASE db_restaurante FROM dbo;
GRANT CONNECT ON DATABASE db_restaurante TO restaurante_user;


--
-- Name: TABLE cardapio; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.cardapio FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.cardapio TO dbo;


--
-- Name: TABLE cardapio_prato; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.cardapio_prato FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.cardapio_prato TO dbo;


--
-- Name: TABLE categoria_prato; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.categoria_prato FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.categoria_prato TO dbo;


--
-- Name: TABLE endereco; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.endereco FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.endereco TO dbo;


--
-- Name: TABLE item_pedido; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.item_pedido FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.item_pedido TO dbo;


--
-- Name: TABLE pedido; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.pedido FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.pedido TO dbo;


--
-- Name: TABLE prato; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.prato FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.prato TO dbo;


--
-- Name: TABLE restaurante; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.restaurante FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.restaurante TO dbo;


--
-- Name: TABLE usuario; Type: ACL; Schema: public; Owner: dbo
--

REVOKE ALL ON TABLE public.usuario FROM dbo;
GRANT REFERENCES,TRIGGER,TRUNCATE ON TABLE public.usuario TO dbo;


--
-- PostgreSQL database dump complete
--

--
-- Database "keycloak" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Debian 16.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: keycloak; Type: DATABASE; Schema: -; Owner: dbo
--

CREATE DATABASE keycloak WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE keycloak OWNER TO dbo;

\connect keycloak

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: admin_event_entity; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.admin_event_entity (
    id character varying(36) NOT NULL,
    admin_event_time bigint,
    realm_id character varying(255),
    operation_type character varying(255),
    auth_realm_id character varying(255),
    auth_client_id character varying(255),
    auth_user_id character varying(255),
    ip_address character varying(255),
    resource_path character varying(2550),
    representation text,
    error character varying(255),
    resource_type character varying(64)
);


ALTER TABLE public.admin_event_entity OWNER TO dbo;

--
-- Name: associated_policy; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.associated_policy (
    policy_id character varying(36) NOT NULL,
    associated_policy_id character varying(36) NOT NULL
);


ALTER TABLE public.associated_policy OWNER TO dbo;

--
-- Name: authentication_execution; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.authentication_execution (
    id character varying(36) NOT NULL,
    alias character varying(255),
    authenticator character varying(36),
    realm_id character varying(36),
    flow_id character varying(36),
    requirement integer,
    priority integer,
    authenticator_flow boolean DEFAULT false NOT NULL,
    auth_flow_id character varying(36),
    auth_config character varying(36)
);


ALTER TABLE public.authentication_execution OWNER TO dbo;

--
-- Name: authentication_flow; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.authentication_flow (
    id character varying(36) NOT NULL,
    alias character varying(255),
    description character varying(255),
    realm_id character varying(36),
    provider_id character varying(36) DEFAULT 'basic-flow'::character varying NOT NULL,
    top_level boolean DEFAULT false NOT NULL,
    built_in boolean DEFAULT false NOT NULL
);


ALTER TABLE public.authentication_flow OWNER TO dbo;

--
-- Name: authenticator_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.authenticator_config (
    id character varying(36) NOT NULL,
    alias character varying(255),
    realm_id character varying(36)
);


ALTER TABLE public.authenticator_config OWNER TO dbo;

--
-- Name: authenticator_config_entry; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.authenticator_config_entry (
    authenticator_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.authenticator_config_entry OWNER TO dbo;

--
-- Name: broker_link; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.broker_link (
    identity_provider character varying(255) NOT NULL,
    storage_provider_id character varying(255),
    realm_id character varying(36) NOT NULL,
    broker_user_id character varying(255),
    broker_username character varying(255),
    token text,
    user_id character varying(255) NOT NULL
);


ALTER TABLE public.broker_link OWNER TO dbo;

--
-- Name: client; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client (
    id character varying(36) NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    full_scope_allowed boolean DEFAULT false NOT NULL,
    client_id character varying(255),
    not_before integer,
    public_client boolean DEFAULT false NOT NULL,
    secret character varying(255),
    base_url character varying(255),
    bearer_only boolean DEFAULT false NOT NULL,
    management_url character varying(255),
    surrogate_auth_required boolean DEFAULT false NOT NULL,
    realm_id character varying(36),
    protocol character varying(255),
    node_rereg_timeout integer DEFAULT 0,
    frontchannel_logout boolean DEFAULT false NOT NULL,
    consent_required boolean DEFAULT false NOT NULL,
    name character varying(255),
    service_accounts_enabled boolean DEFAULT false NOT NULL,
    client_authenticator_type character varying(255),
    root_url character varying(255),
    description character varying(255),
    registration_token character varying(255),
    standard_flow_enabled boolean DEFAULT true NOT NULL,
    implicit_flow_enabled boolean DEFAULT false NOT NULL,
    direct_access_grants_enabled boolean DEFAULT false NOT NULL,
    always_display_in_console boolean DEFAULT false NOT NULL
);


ALTER TABLE public.client OWNER TO dbo;

--
-- Name: client_attributes; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_attributes (
    client_id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value text
);


ALTER TABLE public.client_attributes OWNER TO dbo;

--
-- Name: client_auth_flow_bindings; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_auth_flow_bindings (
    client_id character varying(36) NOT NULL,
    flow_id character varying(36),
    binding_name character varying(255) NOT NULL
);


ALTER TABLE public.client_auth_flow_bindings OWNER TO dbo;

--
-- Name: client_initial_access; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_initial_access (
    id character varying(36) NOT NULL,
    realm_id character varying(36) NOT NULL,
    "timestamp" integer,
    expiration integer,
    count integer,
    remaining_count integer
);


ALTER TABLE public.client_initial_access OWNER TO dbo;

--
-- Name: client_node_registrations; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_node_registrations (
    client_id character varying(36) NOT NULL,
    value integer,
    name character varying(255) NOT NULL
);


ALTER TABLE public.client_node_registrations OWNER TO dbo;

--
-- Name: client_scope; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_scope (
    id character varying(36) NOT NULL,
    name character varying(255),
    realm_id character varying(36),
    description character varying(255),
    protocol character varying(255)
);


ALTER TABLE public.client_scope OWNER TO dbo;

--
-- Name: client_scope_attributes; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_scope_attributes (
    scope_id character varying(36) NOT NULL,
    value character varying(2048),
    name character varying(255) NOT NULL
);


ALTER TABLE public.client_scope_attributes OWNER TO dbo;

--
-- Name: client_scope_client; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_scope_client (
    client_id character varying(255) NOT NULL,
    scope_id character varying(255) NOT NULL,
    default_scope boolean DEFAULT false NOT NULL
);


ALTER TABLE public.client_scope_client OWNER TO dbo;

--
-- Name: client_scope_role_mapping; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_scope_role_mapping (
    scope_id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL
);


ALTER TABLE public.client_scope_role_mapping OWNER TO dbo;

--
-- Name: client_session; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_session (
    id character varying(36) NOT NULL,
    client_id character varying(36),
    redirect_uri character varying(255),
    state character varying(255),
    "timestamp" integer,
    session_id character varying(36),
    auth_method character varying(255),
    realm_id character varying(255),
    auth_user_id character varying(36),
    current_action character varying(36)
);


ALTER TABLE public.client_session OWNER TO dbo;

--
-- Name: client_session_auth_status; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_session_auth_status (
    authenticator character varying(36) NOT NULL,
    status integer,
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_auth_status OWNER TO dbo;

--
-- Name: client_session_note; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_session_note (
    name character varying(255) NOT NULL,
    value character varying(255),
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_note OWNER TO dbo;

--
-- Name: client_session_prot_mapper; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_session_prot_mapper (
    protocol_mapper_id character varying(36) NOT NULL,
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_prot_mapper OWNER TO dbo;

--
-- Name: client_session_role; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_session_role (
    role_id character varying(255) NOT NULL,
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_session_role OWNER TO dbo;

--
-- Name: client_user_session_note; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.client_user_session_note (
    name character varying(255) NOT NULL,
    value character varying(2048),
    client_session character varying(36) NOT NULL
);


ALTER TABLE public.client_user_session_note OWNER TO dbo;

--
-- Name: component; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.component (
    id character varying(36) NOT NULL,
    name character varying(255),
    parent_id character varying(36),
    provider_id character varying(36),
    provider_type character varying(255),
    realm_id character varying(36),
    sub_type character varying(255)
);


ALTER TABLE public.component OWNER TO dbo;

--
-- Name: component_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.component_config (
    id character varying(36) NOT NULL,
    component_id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value text
);


ALTER TABLE public.component_config OWNER TO dbo;

--
-- Name: composite_role; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.composite_role (
    composite character varying(36) NOT NULL,
    child_role character varying(36) NOT NULL
);


ALTER TABLE public.composite_role OWNER TO dbo;

--
-- Name: credential; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.credential (
    id character varying(36) NOT NULL,
    salt bytea,
    type character varying(255),
    user_id character varying(36),
    created_date bigint,
    user_label character varying(255),
    secret_data text,
    credential_data text,
    priority integer
);


ALTER TABLE public.credential OWNER TO dbo;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO dbo;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO dbo;

--
-- Name: default_client_scope; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.default_client_scope (
    realm_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL,
    default_scope boolean DEFAULT false NOT NULL
);


ALTER TABLE public.default_client_scope OWNER TO dbo;

--
-- Name: event_entity; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.event_entity (
    id character varying(36) NOT NULL,
    client_id character varying(255),
    details_json character varying(2550),
    error character varying(255),
    ip_address character varying(255),
    realm_id character varying(255),
    session_id character varying(255),
    event_time bigint,
    type character varying(255),
    user_id character varying(255),
    details_json_long_value text
);


ALTER TABLE public.event_entity OWNER TO dbo;

--
-- Name: fed_user_attribute; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.fed_user_attribute (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36),
    value character varying(2024),
    long_value_hash bytea,
    long_value_hash_lower_case bytea,
    long_value text
);


ALTER TABLE public.fed_user_attribute OWNER TO dbo;

--
-- Name: fed_user_consent; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.fed_user_consent (
    id character varying(36) NOT NULL,
    client_id character varying(255),
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36),
    created_date bigint,
    last_updated_date bigint,
    client_storage_provider character varying(36),
    external_client_id character varying(255)
);


ALTER TABLE public.fed_user_consent OWNER TO dbo;

--
-- Name: fed_user_consent_cl_scope; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.fed_user_consent_cl_scope (
    user_consent_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL
);


ALTER TABLE public.fed_user_consent_cl_scope OWNER TO dbo;

--
-- Name: fed_user_credential; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.fed_user_credential (
    id character varying(36) NOT NULL,
    salt bytea,
    type character varying(255),
    created_date bigint,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36),
    user_label character varying(255),
    secret_data text,
    credential_data text,
    priority integer
);


ALTER TABLE public.fed_user_credential OWNER TO dbo;

--
-- Name: fed_user_group_membership; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.fed_user_group_membership (
    group_id character varying(36) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36)
);


ALTER TABLE public.fed_user_group_membership OWNER TO dbo;

--
-- Name: fed_user_required_action; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.fed_user_required_action (
    required_action character varying(255) DEFAULT ' '::character varying NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36)
);


ALTER TABLE public.fed_user_required_action OWNER TO dbo;

--
-- Name: fed_user_role_mapping; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.fed_user_role_mapping (
    role_id character varying(36) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    storage_provider_id character varying(36)
);


ALTER TABLE public.fed_user_role_mapping OWNER TO dbo;

--
-- Name: federated_identity; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.federated_identity (
    identity_provider character varying(255) NOT NULL,
    realm_id character varying(36),
    federated_user_id character varying(255),
    federated_username character varying(255),
    token text,
    user_id character varying(36) NOT NULL
);


ALTER TABLE public.federated_identity OWNER TO dbo;

--
-- Name: federated_user; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.federated_user (
    id character varying(255) NOT NULL,
    storage_provider_id character varying(255),
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.federated_user OWNER TO dbo;

--
-- Name: group_attribute; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.group_attribute (
    id character varying(36) DEFAULT 'sybase-needs-something-here'::character varying NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(255),
    group_id character varying(36) NOT NULL
);


ALTER TABLE public.group_attribute OWNER TO dbo;

--
-- Name: group_role_mapping; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.group_role_mapping (
    role_id character varying(36) NOT NULL,
    group_id character varying(36) NOT NULL
);


ALTER TABLE public.group_role_mapping OWNER TO dbo;

--
-- Name: identity_provider; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.identity_provider (
    internal_id character varying(36) NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    provider_alias character varying(255),
    provider_id character varying(255),
    store_token boolean DEFAULT false NOT NULL,
    authenticate_by_default boolean DEFAULT false NOT NULL,
    realm_id character varying(36),
    add_token_role boolean DEFAULT true NOT NULL,
    trust_email boolean DEFAULT false NOT NULL,
    first_broker_login_flow_id character varying(36),
    post_broker_login_flow_id character varying(36),
    provider_display_name character varying(255),
    link_only boolean DEFAULT false NOT NULL
);


ALTER TABLE public.identity_provider OWNER TO dbo;

--
-- Name: identity_provider_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.identity_provider_config (
    identity_provider_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.identity_provider_config OWNER TO dbo;

--
-- Name: identity_provider_mapper; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.identity_provider_mapper (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    idp_alias character varying(255) NOT NULL,
    idp_mapper_name character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.identity_provider_mapper OWNER TO dbo;

--
-- Name: idp_mapper_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.idp_mapper_config (
    idp_mapper_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.idp_mapper_config OWNER TO dbo;

--
-- Name: keycloak_group; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.keycloak_group (
    id character varying(36) NOT NULL,
    name character varying(255),
    parent_group character varying(36) NOT NULL,
    realm_id character varying(36)
);


ALTER TABLE public.keycloak_group OWNER TO dbo;

--
-- Name: keycloak_role; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.keycloak_role (
    id character varying(36) NOT NULL,
    client_realm_constraint character varying(255),
    client_role boolean DEFAULT false NOT NULL,
    description character varying(255),
    name character varying(255),
    realm_id character varying(255),
    client character varying(36),
    realm character varying(36)
);


ALTER TABLE public.keycloak_role OWNER TO dbo;

--
-- Name: migration_model; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.migration_model (
    id character varying(36) NOT NULL,
    version character varying(36),
    update_time bigint DEFAULT 0 NOT NULL
);


ALTER TABLE public.migration_model OWNER TO dbo;

--
-- Name: offline_client_session; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.offline_client_session (
    user_session_id character varying(36) NOT NULL,
    client_id character varying(255) NOT NULL,
    offline_flag character varying(4) NOT NULL,
    "timestamp" integer,
    data text,
    client_storage_provider character varying(36) DEFAULT 'local'::character varying NOT NULL,
    external_client_id character varying(255) DEFAULT 'local'::character varying NOT NULL
);


ALTER TABLE public.offline_client_session OWNER TO dbo;

--
-- Name: offline_user_session; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.offline_user_session (
    user_session_id character varying(36) NOT NULL,
    user_id character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    created_on integer NOT NULL,
    offline_flag character varying(4) NOT NULL,
    data text,
    last_session_refresh integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.offline_user_session OWNER TO dbo;

--
-- Name: policy_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.policy_config (
    policy_id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value text
);


ALTER TABLE public.policy_config OWNER TO dbo;

--
-- Name: protocol_mapper; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.protocol_mapper (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    protocol character varying(255) NOT NULL,
    protocol_mapper_name character varying(255) NOT NULL,
    client_id character varying(36),
    client_scope_id character varying(36)
);


ALTER TABLE public.protocol_mapper OWNER TO dbo;

--
-- Name: protocol_mapper_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.protocol_mapper_config (
    protocol_mapper_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.protocol_mapper_config OWNER TO dbo;

--
-- Name: realm; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm (
    id character varying(36) NOT NULL,
    access_code_lifespan integer,
    user_action_lifespan integer,
    access_token_lifespan integer,
    account_theme character varying(255),
    admin_theme character varying(255),
    email_theme character varying(255),
    enabled boolean DEFAULT false NOT NULL,
    events_enabled boolean DEFAULT false NOT NULL,
    events_expiration bigint,
    login_theme character varying(255),
    name character varying(255),
    not_before integer,
    password_policy character varying(2550),
    registration_allowed boolean DEFAULT false NOT NULL,
    remember_me boolean DEFAULT false NOT NULL,
    reset_password_allowed boolean DEFAULT false NOT NULL,
    social boolean DEFAULT false NOT NULL,
    ssl_required character varying(255),
    sso_idle_timeout integer,
    sso_max_lifespan integer,
    update_profile_on_soc_login boolean DEFAULT false NOT NULL,
    verify_email boolean DEFAULT false NOT NULL,
    master_admin_client character varying(36),
    login_lifespan integer,
    internationalization_enabled boolean DEFAULT false NOT NULL,
    default_locale character varying(255),
    reg_email_as_username boolean DEFAULT false NOT NULL,
    admin_events_enabled boolean DEFAULT false NOT NULL,
    admin_events_details_enabled boolean DEFAULT false NOT NULL,
    edit_username_allowed boolean DEFAULT false NOT NULL,
    otp_policy_counter integer DEFAULT 0,
    otp_policy_window integer DEFAULT 1,
    otp_policy_period integer DEFAULT 30,
    otp_policy_digits integer DEFAULT 6,
    otp_policy_alg character varying(36) DEFAULT 'HmacSHA1'::character varying,
    otp_policy_type character varying(36) DEFAULT 'totp'::character varying,
    browser_flow character varying(36),
    registration_flow character varying(36),
    direct_grant_flow character varying(36),
    reset_credentials_flow character varying(36),
    client_auth_flow character varying(36),
    offline_session_idle_timeout integer DEFAULT 0,
    revoke_refresh_token boolean DEFAULT false NOT NULL,
    access_token_life_implicit integer DEFAULT 0,
    login_with_email_allowed boolean DEFAULT true NOT NULL,
    duplicate_emails_allowed boolean DEFAULT false NOT NULL,
    docker_auth_flow character varying(36),
    refresh_token_max_reuse integer DEFAULT 0,
    allow_user_managed_access boolean DEFAULT false NOT NULL,
    sso_max_lifespan_remember_me integer DEFAULT 0 NOT NULL,
    sso_idle_timeout_remember_me integer DEFAULT 0 NOT NULL,
    default_role character varying(255)
);


ALTER TABLE public.realm OWNER TO dbo;

--
-- Name: realm_attribute; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_attribute (
    name character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL,
    value text
);


ALTER TABLE public.realm_attribute OWNER TO dbo;

--
-- Name: realm_default_groups; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_default_groups (
    realm_id character varying(36) NOT NULL,
    group_id character varying(36) NOT NULL
);


ALTER TABLE public.realm_default_groups OWNER TO dbo;

--
-- Name: realm_enabled_event_types; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_enabled_event_types (
    realm_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.realm_enabled_event_types OWNER TO dbo;

--
-- Name: realm_events_listeners; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_events_listeners (
    realm_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.realm_events_listeners OWNER TO dbo;

--
-- Name: realm_localizations; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_localizations (
    realm_id character varying(255) NOT NULL,
    locale character varying(255) NOT NULL,
    texts text NOT NULL
);


ALTER TABLE public.realm_localizations OWNER TO dbo;

--
-- Name: realm_required_credential; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_required_credential (
    type character varying(255) NOT NULL,
    form_label character varying(255),
    input boolean DEFAULT false NOT NULL,
    secret boolean DEFAULT false NOT NULL,
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.realm_required_credential OWNER TO dbo;

--
-- Name: realm_smtp_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_smtp_config (
    realm_id character varying(36) NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.realm_smtp_config OWNER TO dbo;

--
-- Name: realm_supported_locales; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.realm_supported_locales (
    realm_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.realm_supported_locales OWNER TO dbo;

--
-- Name: redirect_uris; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.redirect_uris (
    client_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.redirect_uris OWNER TO dbo;

--
-- Name: required_action_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.required_action_config (
    required_action_id character varying(36) NOT NULL,
    value text,
    name character varying(255) NOT NULL
);


ALTER TABLE public.required_action_config OWNER TO dbo;

--
-- Name: required_action_provider; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.required_action_provider (
    id character varying(36) NOT NULL,
    alias character varying(255),
    name character varying(255),
    realm_id character varying(36),
    enabled boolean DEFAULT false NOT NULL,
    default_action boolean DEFAULT false NOT NULL,
    provider_id character varying(255),
    priority integer
);


ALTER TABLE public.required_action_provider OWNER TO dbo;

--
-- Name: resource_attribute; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_attribute (
    id character varying(36) DEFAULT 'sybase-needs-something-here'::character varying NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(255),
    resource_id character varying(36) NOT NULL
);


ALTER TABLE public.resource_attribute OWNER TO dbo;

--
-- Name: resource_policy; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_policy (
    resource_id character varying(36) NOT NULL,
    policy_id character varying(36) NOT NULL
);


ALTER TABLE public.resource_policy OWNER TO dbo;

--
-- Name: resource_scope; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_scope (
    resource_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL
);


ALTER TABLE public.resource_scope OWNER TO dbo;

--
-- Name: resource_server; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_server (
    id character varying(36) NOT NULL,
    allow_rs_remote_mgmt boolean DEFAULT false NOT NULL,
    policy_enforce_mode smallint NOT NULL,
    decision_strategy smallint DEFAULT 1 NOT NULL
);


ALTER TABLE public.resource_server OWNER TO dbo;

--
-- Name: resource_server_perm_ticket; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_server_perm_ticket (
    id character varying(36) NOT NULL,
    owner character varying(255) NOT NULL,
    requester character varying(255) NOT NULL,
    created_timestamp bigint NOT NULL,
    granted_timestamp bigint,
    resource_id character varying(36) NOT NULL,
    scope_id character varying(36),
    resource_server_id character varying(36) NOT NULL,
    policy_id character varying(36)
);


ALTER TABLE public.resource_server_perm_ticket OWNER TO dbo;

--
-- Name: resource_server_policy; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_server_policy (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    description character varying(255),
    type character varying(255) NOT NULL,
    decision_strategy smallint,
    logic smallint,
    resource_server_id character varying(36) NOT NULL,
    owner character varying(255)
);


ALTER TABLE public.resource_server_policy OWNER TO dbo;

--
-- Name: resource_server_resource; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_server_resource (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    type character varying(255),
    icon_uri character varying(255),
    owner character varying(255) NOT NULL,
    resource_server_id character varying(36) NOT NULL,
    owner_managed_access boolean DEFAULT false NOT NULL,
    display_name character varying(255)
);


ALTER TABLE public.resource_server_resource OWNER TO dbo;

--
-- Name: resource_server_scope; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_server_scope (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    icon_uri character varying(255),
    resource_server_id character varying(36) NOT NULL,
    display_name character varying(255)
);


ALTER TABLE public.resource_server_scope OWNER TO dbo;

--
-- Name: resource_uris; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.resource_uris (
    resource_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.resource_uris OWNER TO dbo;

--
-- Name: role_attribute; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.role_attribute (
    id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(255)
);


ALTER TABLE public.role_attribute OWNER TO dbo;

--
-- Name: scope_mapping; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.scope_mapping (
    client_id character varying(36) NOT NULL,
    role_id character varying(36) NOT NULL
);


ALTER TABLE public.scope_mapping OWNER TO dbo;

--
-- Name: scope_policy; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.scope_policy (
    scope_id character varying(36) NOT NULL,
    policy_id character varying(36) NOT NULL
);


ALTER TABLE public.scope_policy OWNER TO dbo;

--
-- Name: user_attribute; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_attribute (
    name character varying(255) NOT NULL,
    value character varying(255),
    user_id character varying(36) NOT NULL,
    id character varying(36) DEFAULT 'sybase-needs-something-here'::character varying NOT NULL,
    long_value_hash bytea,
    long_value_hash_lower_case bytea,
    long_value text
);


ALTER TABLE public.user_attribute OWNER TO dbo;

--
-- Name: user_consent; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_consent (
    id character varying(36) NOT NULL,
    client_id character varying(255),
    user_id character varying(36) NOT NULL,
    created_date bigint,
    last_updated_date bigint,
    client_storage_provider character varying(36),
    external_client_id character varying(255)
);


ALTER TABLE public.user_consent OWNER TO dbo;

--
-- Name: user_consent_client_scope; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_consent_client_scope (
    user_consent_id character varying(36) NOT NULL,
    scope_id character varying(36) NOT NULL
);


ALTER TABLE public.user_consent_client_scope OWNER TO dbo;

--
-- Name: user_entity; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_entity (
    id character varying(36) NOT NULL,
    email character varying(255),
    email_constraint character varying(255),
    email_verified boolean DEFAULT false NOT NULL,
    enabled boolean DEFAULT false NOT NULL,
    federation_link character varying(255),
    first_name character varying(255),
    last_name character varying(255),
    realm_id character varying(255),
    username character varying(255),
    created_timestamp bigint,
    service_account_client_link character varying(255),
    not_before integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.user_entity OWNER TO dbo;

--
-- Name: user_federation_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_federation_config (
    user_federation_provider_id character varying(36) NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.user_federation_config OWNER TO dbo;

--
-- Name: user_federation_mapper; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_federation_mapper (
    id character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    federation_provider_id character varying(36) NOT NULL,
    federation_mapper_type character varying(255) NOT NULL,
    realm_id character varying(36) NOT NULL
);


ALTER TABLE public.user_federation_mapper OWNER TO dbo;

--
-- Name: user_federation_mapper_config; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_federation_mapper_config (
    user_federation_mapper_id character varying(36) NOT NULL,
    value character varying(255),
    name character varying(255) NOT NULL
);


ALTER TABLE public.user_federation_mapper_config OWNER TO dbo;

--
-- Name: user_federation_provider; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_federation_provider (
    id character varying(36) NOT NULL,
    changed_sync_period integer,
    display_name character varying(255),
    full_sync_period integer,
    last_sync integer,
    priority integer,
    provider_name character varying(255),
    realm_id character varying(36)
);


ALTER TABLE public.user_federation_provider OWNER TO dbo;

--
-- Name: user_group_membership; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_group_membership (
    group_id character varying(36) NOT NULL,
    user_id character varying(36) NOT NULL
);


ALTER TABLE public.user_group_membership OWNER TO dbo;

--
-- Name: user_required_action; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_required_action (
    user_id character varying(36) NOT NULL,
    required_action character varying(255) DEFAULT ' '::character varying NOT NULL
);


ALTER TABLE public.user_required_action OWNER TO dbo;

--
-- Name: user_role_mapping; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_role_mapping (
    role_id character varying(255) NOT NULL,
    user_id character varying(36) NOT NULL
);


ALTER TABLE public.user_role_mapping OWNER TO dbo;

--
-- Name: user_session; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_session (
    id character varying(36) NOT NULL,
    auth_method character varying(255),
    ip_address character varying(255),
    last_session_refresh integer,
    login_username character varying(255),
    realm_id character varying(255),
    remember_me boolean DEFAULT false NOT NULL,
    started integer,
    user_id character varying(255),
    user_session_state integer,
    broker_session_id character varying(255),
    broker_user_id character varying(255)
);


ALTER TABLE public.user_session OWNER TO dbo;

--
-- Name: user_session_note; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.user_session_note (
    user_session character varying(36) NOT NULL,
    name character varying(255) NOT NULL,
    value character varying(2048)
);


ALTER TABLE public.user_session_note OWNER TO dbo;

--
-- Name: username_login_failure; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.username_login_failure (
    realm_id character varying(36) NOT NULL,
    username character varying(255) NOT NULL,
    failed_login_not_before integer,
    last_failure bigint,
    last_ip_failure character varying(255),
    num_failures integer
);


ALTER TABLE public.username_login_failure OWNER TO dbo;

--
-- Name: web_origins; Type: TABLE; Schema: public; Owner: dbo
--

CREATE TABLE public.web_origins (
    client_id character varying(36) NOT NULL,
    value character varying(255) NOT NULL
);


ALTER TABLE public.web_origins OWNER TO dbo;

--
-- Data for Name: admin_event_entity; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.admin_event_entity (id, admin_event_time, realm_id, operation_type, auth_realm_id, auth_client_id, auth_user_id, ip_address, resource_path, representation, error, resource_type) FROM stdin;
\.


--
-- Data for Name: associated_policy; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.associated_policy (policy_id, associated_policy_id) FROM stdin;
\.


--
-- Data for Name: authentication_execution; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.authentication_execution (id, alias, authenticator, realm_id, flow_id, requirement, priority, authenticator_flow, auth_flow_id, auth_config) FROM stdin;
494a93dd-7671-4cf1-a8d9-2327ec1f5c1a	\N	auth-cookie	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	d8811f56-e82f-41ce-988b-c2fda3bf34f2	2	10	f	\N	\N
75c1aae6-ee9b-449f-9393-91766c14e9ac	\N	auth-spnego	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	d8811f56-e82f-41ce-988b-c2fda3bf34f2	3	20	f	\N	\N
ad492993-4c93-443e-9737-cbc55fbcf418	\N	identity-provider-redirector	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	d8811f56-e82f-41ce-988b-c2fda3bf34f2	2	25	f	\N	\N
c6a355ea-42a8-456f-9719-c34ea8f44e88	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	d8811f56-e82f-41ce-988b-c2fda3bf34f2	2	30	t	27e9b597-c017-46b5-820f-eceb689a5206	\N
311d2ee5-5252-42b6-b458-a3d06dbaab78	\N	auth-username-password-form	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	27e9b597-c017-46b5-820f-eceb689a5206	0	10	f	\N	\N
36a157f8-aead-4905-a681-5d71b9f9b817	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	27e9b597-c017-46b5-820f-eceb689a5206	1	20	t	f10690d2-fd64-4769-ae1d-efc7925dfbcc	\N
ffc57324-9c52-4f31-99f7-7cc89fa6f2f2	\N	conditional-user-configured	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f10690d2-fd64-4769-ae1d-efc7925dfbcc	0	10	f	\N	\N
b5ff1100-764e-4f1c-b62c-254a08a21be7	\N	auth-otp-form	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f10690d2-fd64-4769-ae1d-efc7925dfbcc	0	20	f	\N	\N
81c72d7d-ad34-4114-86d2-c23fe9b09b4c	\N	direct-grant-validate-username	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	9bbd3553-f605-4afe-bb24-a5e48875ef43	0	10	f	\N	\N
7e9fd2ad-d33c-4518-8ea2-8c3a6a29f212	\N	direct-grant-validate-password	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	9bbd3553-f605-4afe-bb24-a5e48875ef43	0	20	f	\N	\N
e6e9a153-d9f7-4896-bccc-f65360b59aab	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	9bbd3553-f605-4afe-bb24-a5e48875ef43	1	30	t	e0fcda24-823b-466e-8d52-6ebb7e90e75b	\N
1b30aeb3-3c4a-4821-b490-5cd027282fca	\N	conditional-user-configured	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	e0fcda24-823b-466e-8d52-6ebb7e90e75b	0	10	f	\N	\N
74fd7dba-862b-4555-af68-b0aea37a2a80	\N	direct-grant-validate-otp	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	e0fcda24-823b-466e-8d52-6ebb7e90e75b	0	20	f	\N	\N
fcbcdb31-9a52-4419-bbfa-c09c26a34bef	\N	registration-page-form	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	5d0bc900-ffc9-4784-96a5-7c9702a3aecd	0	10	t	7d824445-c7dc-4be3-9137-9f3e09c3c6f4	\N
0b762ef7-6892-40a8-9fce-4fd63b4b7808	\N	registration-user-creation	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7d824445-c7dc-4be3-9137-9f3e09c3c6f4	0	20	f	\N	\N
a17b47bd-7b54-4d32-9d08-5db9e3c39b38	\N	registration-password-action	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7d824445-c7dc-4be3-9137-9f3e09c3c6f4	0	50	f	\N	\N
0bda5829-6fa6-4c02-84b1-151b9f7091c1	\N	registration-recaptcha-action	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7d824445-c7dc-4be3-9137-9f3e09c3c6f4	3	60	f	\N	\N
7389046d-0dad-4d97-adbe-9311f2831c1e	\N	registration-terms-and-conditions	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7d824445-c7dc-4be3-9137-9f3e09c3c6f4	3	70	f	\N	\N
aac46bec-c081-48f9-9606-313e3787de78	\N	reset-credentials-choose-user	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	63066cc3-55aa-4fdc-a6e2-b2d9ba0ac862	0	10	f	\N	\N
a54408ed-2a54-4d00-baf6-023229f4eb14	\N	reset-credential-email	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	63066cc3-55aa-4fdc-a6e2-b2d9ba0ac862	0	20	f	\N	\N
59b52e76-cbb0-4d1a-ac8a-6fea991364c3	\N	reset-password	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	63066cc3-55aa-4fdc-a6e2-b2d9ba0ac862	0	30	f	\N	\N
43d3e790-bdb9-46d2-a79c-437c40f76dfa	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	63066cc3-55aa-4fdc-a6e2-b2d9ba0ac862	1	40	t	49e34eb9-302b-426a-8acf-a75510a1c925	\N
d5293564-6fa6-423b-be76-361c5a733487	\N	conditional-user-configured	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	49e34eb9-302b-426a-8acf-a75510a1c925	0	10	f	\N	\N
ee3f025a-5a94-4dfa-877c-ea934b9c85cf	\N	reset-otp	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	49e34eb9-302b-426a-8acf-a75510a1c925	0	20	f	\N	\N
83766a3a-1f33-4104-a2cb-971c964f599e	\N	client-secret	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	72f31022-3844-49bc-ae87-48bd47e58680	2	10	f	\N	\N
4f16eec4-eace-4a0c-81fb-fc593437a067	\N	client-jwt	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	72f31022-3844-49bc-ae87-48bd47e58680	2	20	f	\N	\N
a4998aaa-ea3d-4a76-9998-00700f3810b2	\N	client-secret-jwt	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	72f31022-3844-49bc-ae87-48bd47e58680	2	30	f	\N	\N
daa981a7-9891-4448-bc5d-7b0fdcff57f0	\N	client-x509	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	72f31022-3844-49bc-ae87-48bd47e58680	2	40	f	\N	\N
9482df28-897d-4e8e-b216-79caa552375e	\N	idp-review-profile	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	5d10ca24-692b-466e-973e-3db49db59a1a	0	10	f	\N	c2fb02d3-84cb-472a-b6b3-d0e00a9b13e0
a055c00b-8c8f-4ce2-ab82-2068589de6c1	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	5d10ca24-692b-466e-973e-3db49db59a1a	0	20	t	041b6a8c-4524-4e1a-8d21-2a231ab597dd	\N
829706c2-3a4f-4190-be61-9b2657edc8c5	\N	idp-create-user-if-unique	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	041b6a8c-4524-4e1a-8d21-2a231ab597dd	2	10	f	\N	43056f87-6b45-452a-9d9d-23e98d70095a
ab0dd154-f04b-4d20-afa4-efb72c7e86e1	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	041b6a8c-4524-4e1a-8d21-2a231ab597dd	2	20	t	20a5ac0a-b865-42c5-9dd0-b145cb925710	\N
b776dea5-00e8-460c-9cc2-dd77220eddd2	\N	idp-confirm-link	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	20a5ac0a-b865-42c5-9dd0-b145cb925710	0	10	f	\N	\N
45453518-805e-4ea2-a84f-8c339edd7498	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	20a5ac0a-b865-42c5-9dd0-b145cb925710	0	20	t	f7ae4dd4-01d2-4298-a901-b0aad9ee2a85	\N
fbc5db49-263d-44af-8452-17f0c489aaa0	\N	idp-email-verification	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f7ae4dd4-01d2-4298-a901-b0aad9ee2a85	2	10	f	\N	\N
7ec88fff-2732-472d-bec2-8898bbb66074	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f7ae4dd4-01d2-4298-a901-b0aad9ee2a85	2	20	t	0124c8cf-0bb9-4417-9c92-2f55d9bb8ce1	\N
b7b1f33e-c380-47cc-b02b-8a82322db0f7	\N	idp-username-password-form	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	0124c8cf-0bb9-4417-9c92-2f55d9bb8ce1	0	10	f	\N	\N
eb0acc61-efa7-4c1e-a2d9-bb25cab118c8	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	0124c8cf-0bb9-4417-9c92-2f55d9bb8ce1	1	20	t	7c60d8e8-b7f2-4f2a-ad4c-83bd5e7aa6b0	\N
d66c1aaf-07ca-44bb-a0a9-d4ccaec0bce8	\N	conditional-user-configured	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7c60d8e8-b7f2-4f2a-ad4c-83bd5e7aa6b0	0	10	f	\N	\N
8f487403-f991-4b92-bf2d-1dea85f1d2c3	\N	auth-otp-form	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7c60d8e8-b7f2-4f2a-ad4c-83bd5e7aa6b0	0	20	f	\N	\N
3a7331b9-27ab-43c7-8d19-4189d5594a5d	\N	http-basic-authenticator	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	53eb00d4-bcde-4aea-893c-23b9fd89225f	0	10	f	\N	\N
d686b058-f256-45cf-96e0-5759ca72edfc	\N	docker-http-basic-authenticator	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f313d64f-617c-4f1f-9ef6-c4043335ce0c	0	10	f	\N	\N
4f7b59f8-4309-4425-90a4-83f57739e61b	\N	idp-email-verification	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b1fe70a5-b2e8-4eae-a661-e11e32af86c7	2	10	f	\N	\N
be79858f-6062-4e6c-bf44-a8429678bc40	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b1fe70a5-b2e8-4eae-a661-e11e32af86c7	2	20	t	68fcb96c-24bd-44b5-8275-d8a818d904d0	\N
62054ceb-94b3-41bd-a1bb-479bb099a1f9	\N	conditional-user-configured	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f9833e2b-44ce-4401-ac3c-fcfdd7eb72f8	0	10	f	\N	\N
746d8e7b-b62e-45dc-a643-f1e4be4fe6b3	\N	auth-otp-form	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f9833e2b-44ce-4401-ac3c-fcfdd7eb72f8	0	20	f	\N	\N
2957b9ce-4fc0-4317-959f-f642b0a25fb3	\N	conditional-user-configured	43c36b1e-22ce-4293-8d6e-181a68f70b2a	bb563f91-d4bd-4e94-832b-b5390345ffc7	0	10	f	\N	\N
705c6758-5c95-4621-8142-b2555111877a	\N	direct-grant-validate-otp	43c36b1e-22ce-4293-8d6e-181a68f70b2a	bb563f91-d4bd-4e94-832b-b5390345ffc7	0	20	f	\N	\N
9b98e5d0-c37a-4516-9196-0aac480965d7	\N	conditional-user-configured	43c36b1e-22ce-4293-8d6e-181a68f70b2a	33ed6dc2-d69b-4a13-9521-1461c2ac9c4e	0	10	f	\N	\N
b6582fcf-6749-4ead-a0e7-44ad6cfe709a	\N	auth-otp-form	43c36b1e-22ce-4293-8d6e-181a68f70b2a	33ed6dc2-d69b-4a13-9521-1461c2ac9c4e	0	20	f	\N	\N
b420cb0a-2d6b-4023-8822-507f081e3a95	\N	idp-confirm-link	43c36b1e-22ce-4293-8d6e-181a68f70b2a	bd9fcd04-213c-4457-923c-68a444fb0bbb	0	10	f	\N	\N
512bbb62-07a2-4ee1-be4d-98d2c41fe91a	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	bd9fcd04-213c-4457-923c-68a444fb0bbb	0	20	t	b1fe70a5-b2e8-4eae-a661-e11e32af86c7	\N
1de3de6e-cb9f-4277-bff4-e70f07db84a0	\N	conditional-user-configured	43c36b1e-22ce-4293-8d6e-181a68f70b2a	bdb8522e-21ac-4021-b264-bdc51ab75259	0	10	f	\N	\N
8d12a2cc-07de-4167-b3a5-fae4599f38cb	\N	reset-otp	43c36b1e-22ce-4293-8d6e-181a68f70b2a	bdb8522e-21ac-4021-b264-bdc51ab75259	0	20	f	\N	\N
8db9d4da-e286-45f7-8ee2-4007c3778a58	\N	idp-create-user-if-unique	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f38ae262-8cdf-406e-a123-3f4d162eebfb	2	10	f	\N	2b6bbf87-9a4e-48e9-ba2b-633a7d816799
02ec502e-fb7f-43b7-a43e-d61821027762	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f38ae262-8cdf-406e-a123-3f4d162eebfb	2	20	t	bd9fcd04-213c-4457-923c-68a444fb0bbb	\N
1b00932e-fb1b-40e4-84cd-0877169ebeed	\N	idp-username-password-form	43c36b1e-22ce-4293-8d6e-181a68f70b2a	68fcb96c-24bd-44b5-8275-d8a818d904d0	0	10	f	\N	\N
0d7bc779-8170-49cc-8586-c38402cfd1c2	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	68fcb96c-24bd-44b5-8275-d8a818d904d0	1	20	t	33ed6dc2-d69b-4a13-9521-1461c2ac9c4e	\N
0bab32cd-8bc5-4ac4-84d2-03bd4e9e7c01	\N	auth-cookie	43c36b1e-22ce-4293-8d6e-181a68f70b2a	d2297650-b0c7-4e35-99a6-3b8864aca1f5	2	10	f	\N	\N
df37c6a6-8caf-4378-a080-d09fb2e0475d	\N	auth-spnego	43c36b1e-22ce-4293-8d6e-181a68f70b2a	d2297650-b0c7-4e35-99a6-3b8864aca1f5	3	20	f	\N	\N
bf2aa3c9-40eb-4124-872c-7fe5f801b25b	\N	identity-provider-redirector	43c36b1e-22ce-4293-8d6e-181a68f70b2a	d2297650-b0c7-4e35-99a6-3b8864aca1f5	2	25	f	\N	\N
c0fdb2b6-54c6-4b53-bda1-6f2b1e51bd30	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	d2297650-b0c7-4e35-99a6-3b8864aca1f5	2	30	t	f98ae30d-4ad4-4f01-819c-1f6426de40ef	\N
c7c055a6-106a-48db-9c97-21a1e010c69c	\N	client-secret	43c36b1e-22ce-4293-8d6e-181a68f70b2a	841698fe-1b21-43e0-9182-f5f552558cfa	2	10	f	\N	\N
c0293746-a237-4cb7-9f0e-ad1e6f686181	\N	client-jwt	43c36b1e-22ce-4293-8d6e-181a68f70b2a	841698fe-1b21-43e0-9182-f5f552558cfa	2	20	f	\N	\N
884e6900-3a8e-44e2-8393-7ec39ff34160	\N	client-secret-jwt	43c36b1e-22ce-4293-8d6e-181a68f70b2a	841698fe-1b21-43e0-9182-f5f552558cfa	2	30	f	\N	\N
c659b8dc-15c2-4540-b5ee-f589d285cdb9	\N	client-x509	43c36b1e-22ce-4293-8d6e-181a68f70b2a	841698fe-1b21-43e0-9182-f5f552558cfa	2	40	f	\N	\N
5409f65f-6b9f-4f25-8677-c003e3738667	\N	direct-grant-validate-username	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b9a5f815-36aa-45bf-a5a5-702ce4ab093d	0	10	f	\N	\N
890a54fd-8709-4a4b-9929-d263bdd44398	\N	direct-grant-validate-password	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b9a5f815-36aa-45bf-a5a5-702ce4ab093d	0	20	f	\N	\N
e46cbeb8-15e4-475f-9c07-b5e0077fbb7f	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b9a5f815-36aa-45bf-a5a5-702ce4ab093d	1	30	t	bb563f91-d4bd-4e94-832b-b5390345ffc7	\N
dd52e661-84c3-4ab9-8b54-0bb14513f2e4	\N	docker-http-basic-authenticator	43c36b1e-22ce-4293-8d6e-181a68f70b2a	97c9b442-8a95-4510-9477-25ad65d795d6	0	10	f	\N	\N
643e0616-b5e2-4fcb-b7ca-5139e2a822e3	\N	idp-review-profile	43c36b1e-22ce-4293-8d6e-181a68f70b2a	8b5d0c5f-21c0-4084-b118-9f86ef06fadb	0	10	f	\N	23c5094a-1bc1-4aa5-9180-0a2485b9034b
5fa7e403-e067-4375-9372-5938ebe77a46	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	8b5d0c5f-21c0-4084-b118-9f86ef06fadb	0	20	t	f38ae262-8cdf-406e-a123-3f4d162eebfb	\N
ae2d1983-49e7-4466-be9e-6f51f1443c52	\N	auth-username-password-form	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f98ae30d-4ad4-4f01-819c-1f6426de40ef	0	10	f	\N	\N
b2a8f39e-3ba2-4559-b6a7-dc3e7b65d6db	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f98ae30d-4ad4-4f01-819c-1f6426de40ef	1	20	t	f9833e2b-44ce-4401-ac3c-fcfdd7eb72f8	\N
c39c4250-3278-458d-a6ed-0f99086e3b64	\N	registration-page-form	43c36b1e-22ce-4293-8d6e-181a68f70b2a	9b3d29f8-295f-43c5-b6d2-e5cc34e402c3	0	10	t	41cfec5a-4f04-4023-a009-63d9d61e4657	\N
dc0b0814-35bf-467d-84cf-70a174a64040	\N	registration-user-creation	43c36b1e-22ce-4293-8d6e-181a68f70b2a	41cfec5a-4f04-4023-a009-63d9d61e4657	0	20	f	\N	\N
451c48c5-3470-43b6-88d9-29b4f6edd9d8	\N	registration-password-action	43c36b1e-22ce-4293-8d6e-181a68f70b2a	41cfec5a-4f04-4023-a009-63d9d61e4657	0	50	f	\N	\N
207e9903-695f-402c-a8b9-63188f4c9346	\N	registration-recaptcha-action	43c36b1e-22ce-4293-8d6e-181a68f70b2a	41cfec5a-4f04-4023-a009-63d9d61e4657	3	60	f	\N	\N
58f9b03d-6a7f-4b73-979c-828661a381fa	\N	registration-terms-and-conditions	43c36b1e-22ce-4293-8d6e-181a68f70b2a	41cfec5a-4f04-4023-a009-63d9d61e4657	3	70	f	\N	\N
2ff97f31-6766-44f0-a4a8-1fa5b718e22a	\N	reset-credentials-choose-user	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f4f059a1-55a8-4469-b047-ef0df376de0f	0	10	f	\N	\N
a618420d-de57-4b9e-aca0-b5cc8ee3a0a6	\N	reset-credential-email	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f4f059a1-55a8-4469-b047-ef0df376de0f	0	20	f	\N	\N
d3f028dd-ddfa-4be3-9217-3b44f29a6e5a	\N	reset-password	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f4f059a1-55a8-4469-b047-ef0df376de0f	0	30	f	\N	\N
1f184744-5c42-4edb-a757-71132993900b	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f4f059a1-55a8-4469-b047-ef0df376de0f	1	40	t	bdb8522e-21ac-4021-b264-bdc51ab75259	\N
0f5ad9a9-40cd-448b-81da-c8cd7586049a	\N	http-basic-authenticator	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0289abd3-1f93-4b51-bb0d-c103105ec14c	0	10	f	\N	\N
\.


--
-- Data for Name: authentication_flow; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.authentication_flow (id, alias, description, realm_id, provider_id, top_level, built_in) FROM stdin;
d8811f56-e82f-41ce-988b-c2fda3bf34f2	browser	browser based authentication	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	t	t
27e9b597-c017-46b5-820f-eceb689a5206	forms	Username, password, otp and other auth forms.	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
f10690d2-fd64-4769-ae1d-efc7925dfbcc	Browser - Conditional OTP	Flow to determine if the OTP is required for the authentication	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
9bbd3553-f605-4afe-bb24-a5e48875ef43	direct grant	OpenID Connect Resource Owner Grant	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	t	t
e0fcda24-823b-466e-8d52-6ebb7e90e75b	Direct Grant - Conditional OTP	Flow to determine if the OTP is required for the authentication	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
5d0bc900-ffc9-4784-96a5-7c9702a3aecd	registration	registration flow	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	t	t
7d824445-c7dc-4be3-9137-9f3e09c3c6f4	registration form	registration form	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	form-flow	f	t
63066cc3-55aa-4fdc-a6e2-b2d9ba0ac862	reset credentials	Reset credentials for a user if they forgot their password or something	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	t	t
49e34eb9-302b-426a-8acf-a75510a1c925	Reset - Conditional OTP	Flow to determine if the OTP should be reset or not. Set to REQUIRED to force.	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
72f31022-3844-49bc-ae87-48bd47e58680	clients	Base authentication for clients	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	client-flow	t	t
5d10ca24-692b-466e-973e-3db49db59a1a	first broker login	Actions taken after first broker login with identity provider account, which is not yet linked to any Keycloak account	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	t	t
041b6a8c-4524-4e1a-8d21-2a231ab597dd	User creation or linking	Flow for the existing/non-existing user alternatives	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
20a5ac0a-b865-42c5-9dd0-b145cb925710	Handle Existing Account	Handle what to do if there is existing account with same email/username like authenticated identity provider	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
f7ae4dd4-01d2-4298-a901-b0aad9ee2a85	Account verification options	Method with which to verity the existing account	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
0124c8cf-0bb9-4417-9c92-2f55d9bb8ce1	Verify Existing Account by Re-authentication	Reauthentication of existing account	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
7c60d8e8-b7f2-4f2a-ad4c-83bd5e7aa6b0	First broker login - Conditional OTP	Flow to determine if the OTP is required for the authentication	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	f	t
53eb00d4-bcde-4aea-893c-23b9fd89225f	saml ecp	SAML ECP Profile Authentication Flow	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	t	t
f313d64f-617c-4f1f-9ef6-c4043335ce0c	docker auth	Used by Docker clients to authenticate against the IDP	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	basic-flow	t	t
b1fe70a5-b2e8-4eae-a661-e11e32af86c7	Account verification options	Method with which to verity the existing account	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
f9833e2b-44ce-4401-ac3c-fcfdd7eb72f8	Browser - Conditional OTP	Flow to determine if the OTP is required for the authentication	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
bb563f91-d4bd-4e94-832b-b5390345ffc7	Direct Grant - Conditional OTP	Flow to determine if the OTP is required for the authentication	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
33ed6dc2-d69b-4a13-9521-1461c2ac9c4e	First broker login - Conditional OTP	Flow to determine if the OTP is required for the authentication	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
bd9fcd04-213c-4457-923c-68a444fb0bbb	Handle Existing Account	Handle what to do if there is existing account with same email/username like authenticated identity provider	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
bdb8522e-21ac-4021-b264-bdc51ab75259	Reset - Conditional OTP	Flow to determine if the OTP should be reset or not. Set to REQUIRED to force.	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
f38ae262-8cdf-406e-a123-3f4d162eebfb	User creation or linking	Flow for the existing/non-existing user alternatives	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
68fcb96c-24bd-44b5-8275-d8a818d904d0	Verify Existing Account by Re-authentication	Reauthentication of existing account	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
d2297650-b0c7-4e35-99a6-3b8864aca1f5	browser	browser based authentication	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	t	t
841698fe-1b21-43e0-9182-f5f552558cfa	clients	Base authentication for clients	43c36b1e-22ce-4293-8d6e-181a68f70b2a	client-flow	t	t
b9a5f815-36aa-45bf-a5a5-702ce4ab093d	direct grant	OpenID Connect Resource Owner Grant	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	t	t
97c9b442-8a95-4510-9477-25ad65d795d6	docker auth	Used by Docker clients to authenticate against the IDP	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	t	t
8b5d0c5f-21c0-4084-b118-9f86ef06fadb	first broker login	Actions taken after first broker login with identity provider account, which is not yet linked to any Keycloak account	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	t	t
f98ae30d-4ad4-4f01-819c-1f6426de40ef	forms	Username, password, otp and other auth forms.	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	f	t
9b3d29f8-295f-43c5-b6d2-e5cc34e402c3	registration	registration flow	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	t	t
41cfec5a-4f04-4023-a009-63d9d61e4657	registration form	registration form	43c36b1e-22ce-4293-8d6e-181a68f70b2a	form-flow	f	t
f4f059a1-55a8-4469-b047-ef0df376de0f	reset credentials	Reset credentials for a user if they forgot their password or something	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	t	t
0289abd3-1f93-4b51-bb0d-c103105ec14c	saml ecp	SAML ECP Profile Authentication Flow	43c36b1e-22ce-4293-8d6e-181a68f70b2a	basic-flow	t	t
\.


--
-- Data for Name: authenticator_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.authenticator_config (id, alias, realm_id) FROM stdin;
c2fb02d3-84cb-472a-b6b3-d0e00a9b13e0	review profile config	d5be0b10-5b50-455d-a9e0-a7c62d0334e5
43056f87-6b45-452a-9d9d-23e98d70095a	create unique user config	d5be0b10-5b50-455d-a9e0-a7c62d0334e5
2b6bbf87-9a4e-48e9-ba2b-633a7d816799	create unique user config	43c36b1e-22ce-4293-8d6e-181a68f70b2a
23c5094a-1bc1-4aa5-9180-0a2485b9034b	review profile config	43c36b1e-22ce-4293-8d6e-181a68f70b2a
\.


--
-- Data for Name: authenticator_config_entry; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.authenticator_config_entry (authenticator_id, value, name) FROM stdin;
43056f87-6b45-452a-9d9d-23e98d70095a	false	require.password.update.after.registration
c2fb02d3-84cb-472a-b6b3-d0e00a9b13e0	missing	update.profile.on.first.login
23c5094a-1bc1-4aa5-9180-0a2485b9034b	missing	update.profile.on.first.login
2b6bbf87-9a4e-48e9-ba2b-633a7d816799	false	require.password.update.after.registration
\.


--
-- Data for Name: broker_link; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.broker_link (identity_provider, storage_provider_id, realm_id, broker_user_id, broker_username, token, user_id) FROM stdin;
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client (id, enabled, full_scope_allowed, client_id, not_before, public_client, secret, base_url, bearer_only, management_url, surrogate_auth_required, realm_id, protocol, node_rereg_timeout, frontchannel_logout, consent_required, name, service_accounts_enabled, client_authenticator_type, root_url, description, registration_token, standard_flow_enabled, implicit_flow_enabled, direct_access_grants_enabled, always_display_in_console) FROM stdin;
122557f0-b934-4094-a8f5-36579c416a87	t	f	master-realm	0	f	\N	\N	t	\N	f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N	0	f	f	master Realm	f	client-secret	\N	\N	\N	t	f	f	f
3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	f	account	0	t	\N	/realms/master/account/	f	\N	f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	openid-connect	0	f	f	${client_account}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
795b4b60-c45a-4377-8da9-8471c55091d0	t	f	account-console	0	t	\N	/realms/master/account/	f	\N	f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	openid-connect	0	f	f	${client_account-console}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
14139360-cf57-4820-ba85-7e1479912b93	t	f	broker	0	f	\N	\N	t	\N	f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	openid-connect	0	f	f	${client_broker}	f	client-secret	\N	\N	\N	t	f	f	f
787aea5c-fd15-4af6-b6a9-442b26950af5	t	f	security-admin-console	0	t	\N	/admin/master/console/	f	\N	f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	openid-connect	0	f	f	${client_security-admin-console}	f	client-secret	${authAdminUrl}	\N	\N	t	f	f	f
55e3fd0b-11f4-497b-b963-3bd4885e6766	t	f	admin-cli	0	t	\N	\N	f	\N	f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	openid-connect	0	f	f	${client_admin-cli}	f	client-secret	\N	\N	\N	f	f	t	f
450ad3d9-9b7c-4b7d-9044-d828e8a708af	t	f	account-console	0	t	\N	/realms/firmas-food-dev/account/	f	\N	f	43c36b1e-22ce-4293-8d6e-181a68f70b2a	openid-connect	0	f	f	${client_account-console}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
c91b6aba-8136-4311-b037-bcc7f590dc7e	t	f	admin-cli	0	f	**********		f		f	43c36b1e-22ce-4293-8d6e-181a68f70b2a	openid-connect	0	f	f	${client_admin-cli}	t	client-secret			\N	f	f	t	f
19f7fd43-a831-423f-b424-96312d17bfd5	t	f	broker	0	f	\N	\N	t	\N	f	43c36b1e-22ce-4293-8d6e-181a68f70b2a	openid-connect	0	f	f	${client_broker}	f	client-secret	\N	\N	\N	t	f	f	f
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	t	t	firmas-food	0	f	**********	http://localhost:8081	f	http://localhost:8081	f	43c36b1e-22ce-4293-8d6e-181a68f70b2a	openid-connect	-1	t	f	firmas-food	t	client-secret	http://localhost:8081		\N	t	f	t	f
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	f	realm-management	0	f	\N	\N	t	\N	f	43c36b1e-22ce-4293-8d6e-181a68f70b2a	openid-connect	0	f	f	${client_realm-management}	f	client-secret	\N	\N	\N	t	f	f	f
7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	f	firmas-food-dev-realm	0	f	\N	\N	t	\N	f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N	0	f	f	firmas-food-dev Realm	f	client-secret	\N	\N	\N	t	f	f	f
e87b6b71-1178-409e-b4ff-5969eb94b377	t	f	account	0	t	\N	/realms/firmas-food-dev/account/	f	\N	f	43c36b1e-22ce-4293-8d6e-181a68f70b2a	openid-connect	0	f	f	${client_account}	f	client-secret	${authBaseUrl}	\N	\N	t	f	f	f
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	t	f	security-admin-console	0	t	\N	/admin/firmas-food-dev/console/	f	\N	f	43c36b1e-22ce-4293-8d6e-181a68f70b2a	openid-connect	0	f	f	${client_security-admin-console}	f	client-secret	${authAdminUrl}	\N	\N	t	f	f	f
\.


--
-- Data for Name: client_attributes; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_attributes (client_id, name, value) FROM stdin;
3f63aef2-fb73-4e1a-884e-5cd61ef71406	post.logout.redirect.uris	+
795b4b60-c45a-4377-8da9-8471c55091d0	post.logout.redirect.uris	+
795b4b60-c45a-4377-8da9-8471c55091d0	pkce.code.challenge.method	S256
787aea5c-fd15-4af6-b6a9-442b26950af5	post.logout.redirect.uris	+
787aea5c-fd15-4af6-b6a9-442b26950af5	pkce.code.challenge.method	S256
e87b6b71-1178-409e-b4ff-5969eb94b377	post.logout.redirect.uris	+
450ad3d9-9b7c-4b7d-9044-d828e8a708af	post.logout.redirect.uris	+
450ad3d9-9b7c-4b7d-9044-d828e8a708af	pkce.code.challenge.method	S256
c91b6aba-8136-4311-b037-bcc7f590dc7e	client.secret.creation.time	1720657193
c91b6aba-8136-4311-b037-bcc7f590dc7e	oauth2.device.authorization.grant.enabled	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	backchannel.logout.revoke.offline.tokens	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	use.refresh.tokens	true
c91b6aba-8136-4311-b037-bcc7f590dc7e	oidc.ciba.grant.enabled	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	client.use.lightweight.access.token.enabled	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	backchannel.logout.session.required	true
c91b6aba-8136-4311-b037-bcc7f590dc7e	client_credentials.use_refresh_token	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	acr.loa.map	{}
c91b6aba-8136-4311-b037-bcc7f590dc7e	require.pushed.authorization.requests	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	tls.client.certificate.bound.access.tokens	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	display.on.consent.screen	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	token.response.type.bearer.lower-case	false
c91b6aba-8136-4311-b037-bcc7f590dc7e	post.logout.redirect.uris	+
19f7fd43-a831-423f-b424-96312d17bfd5	post.logout.redirect.uris	+
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	oidc.ciba.grant.enabled	false
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	oauth2.device.authorization.grant.enabled	false
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	client.secret.creation.time	1720657385
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	backchannel.logout.session.required	true
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	backchannel.logout.revoke.offline.tokens	false
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	post.logout.redirect.uris	+
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	post.logout.redirect.uris	+
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	post.logout.redirect.uris	+
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	pkce.code.challenge.method	S256
\.


--
-- Data for Name: client_auth_flow_bindings; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_auth_flow_bindings (client_id, flow_id, binding_name) FROM stdin;
\.


--
-- Data for Name: client_initial_access; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_initial_access (id, realm_id, "timestamp", expiration, count, remaining_count) FROM stdin;
\.


--
-- Data for Name: client_node_registrations; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_node_registrations (client_id, value, name) FROM stdin;
\.


--
-- Data for Name: client_scope; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_scope (id, name, realm_id, description, protocol) FROM stdin;
9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	offline_access	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect built-in scope: offline_access	openid-connect
f4edda41-8fc3-4995-89b0-1b67e5471969	role_list	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	SAML role list	saml
efa99282-8b1b-45d3-83c2-92994b4b1f34	profile	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect built-in scope: profile	openid-connect
a27fab96-b997-482d-97b6-3feafd230f88	email	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect built-in scope: email	openid-connect
98c490bc-f91f-46d7-a43e-692ad6680fb4	address	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect built-in scope: address	openid-connect
f4922d7f-6e65-45d2-8236-6d0b9373f9ec	phone	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect built-in scope: phone	openid-connect
c119aa7e-e6f4-44b2-9319-5ab700773d95	roles	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect scope for add user roles to the access token	openid-connect
f8ea87f2-5324-41ae-80b2-6211b7264dfb	web-origins	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect scope for add allowed web origins to the access token	openid-connect
2fa7959e-6a12-45bd-b7de-1d0d899ecea9	microprofile-jwt	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	Microprofile - JWT built-in scope	openid-connect
3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	acr	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	OpenID Connect scope for add acr (authentication context class reference) to the token	openid-connect
3cca6cb8-d31d-47c6-b906-6edc41b12173	acr	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect scope for add acr (authentication context class reference) to the token	openid-connect
7b70bab9-acbf-46fd-afd3-07a922e1e248	profile	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect built-in scope: profile	openid-connect
53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	web-origins	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect scope for add allowed web origins to the access token	openid-connect
868bf2e4-0846-432a-83f8-a3d1722d7b8b	role_list	43c36b1e-22ce-4293-8d6e-181a68f70b2a	SAML role list	saml
7fe8e74f-ddf7-4936-bd43-8280c0443525	microprofile-jwt	43c36b1e-22ce-4293-8d6e-181a68f70b2a	Microprofile - JWT built-in scope	openid-connect
cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	roles	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect scope for add user roles to the access token	openid-connect
f54202d6-f14e-420f-b60c-64775b2b9132	address	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect built-in scope: address	openid-connect
74e2fe9c-cf28-414b-9ac1-69b8341d5413	email	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect built-in scope: email	openid-connect
df80f1b1-5b4e-4032-b0f1-d02f24a48d17	phone	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect built-in scope: phone	openid-connect
fd9ab351-6886-426c-9911-c98ab6cd5e95	offline_access	43c36b1e-22ce-4293-8d6e-181a68f70b2a	OpenID Connect built-in scope: offline_access	openid-connect
\.


--
-- Data for Name: client_scope_attributes; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_scope_attributes (scope_id, value, name) FROM stdin;
9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	true	display.on.consent.screen
9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	${offlineAccessScopeConsentText}	consent.screen.text
f4edda41-8fc3-4995-89b0-1b67e5471969	true	display.on.consent.screen
f4edda41-8fc3-4995-89b0-1b67e5471969	${samlRoleListScopeConsentText}	consent.screen.text
efa99282-8b1b-45d3-83c2-92994b4b1f34	true	display.on.consent.screen
efa99282-8b1b-45d3-83c2-92994b4b1f34	${profileScopeConsentText}	consent.screen.text
efa99282-8b1b-45d3-83c2-92994b4b1f34	true	include.in.token.scope
a27fab96-b997-482d-97b6-3feafd230f88	true	display.on.consent.screen
a27fab96-b997-482d-97b6-3feafd230f88	${emailScopeConsentText}	consent.screen.text
a27fab96-b997-482d-97b6-3feafd230f88	true	include.in.token.scope
98c490bc-f91f-46d7-a43e-692ad6680fb4	true	display.on.consent.screen
98c490bc-f91f-46d7-a43e-692ad6680fb4	${addressScopeConsentText}	consent.screen.text
98c490bc-f91f-46d7-a43e-692ad6680fb4	true	include.in.token.scope
f4922d7f-6e65-45d2-8236-6d0b9373f9ec	true	display.on.consent.screen
f4922d7f-6e65-45d2-8236-6d0b9373f9ec	${phoneScopeConsentText}	consent.screen.text
f4922d7f-6e65-45d2-8236-6d0b9373f9ec	true	include.in.token.scope
c119aa7e-e6f4-44b2-9319-5ab700773d95	true	display.on.consent.screen
c119aa7e-e6f4-44b2-9319-5ab700773d95	${rolesScopeConsentText}	consent.screen.text
c119aa7e-e6f4-44b2-9319-5ab700773d95	false	include.in.token.scope
f8ea87f2-5324-41ae-80b2-6211b7264dfb	false	display.on.consent.screen
f8ea87f2-5324-41ae-80b2-6211b7264dfb		consent.screen.text
f8ea87f2-5324-41ae-80b2-6211b7264dfb	false	include.in.token.scope
2fa7959e-6a12-45bd-b7de-1d0d899ecea9	false	display.on.consent.screen
2fa7959e-6a12-45bd-b7de-1d0d899ecea9	true	include.in.token.scope
3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	false	display.on.consent.screen
3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	false	include.in.token.scope
7b70bab9-acbf-46fd-afd3-07a922e1e248	true	include.in.token.scope
7b70bab9-acbf-46fd-afd3-07a922e1e248	true	display.on.consent.screen
7b70bab9-acbf-46fd-afd3-07a922e1e248	${profileScopeConsentText}	consent.screen.text
53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	false	include.in.token.scope
53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	false	display.on.consent.screen
53f54bb2-17d2-4c8d-badc-fa85cff6a4ce		consent.screen.text
868bf2e4-0846-432a-83f8-a3d1722d7b8b	${samlRoleListScopeConsentText}	consent.screen.text
868bf2e4-0846-432a-83f8-a3d1722d7b8b	true	display.on.consent.screen
cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	false	include.in.token.scope
cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	true	display.on.consent.screen
cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	${rolesScopeConsentText}	consent.screen.text
f54202d6-f14e-420f-b60c-64775b2b9132	true	include.in.token.scope
f54202d6-f14e-420f-b60c-64775b2b9132	true	display.on.consent.screen
f54202d6-f14e-420f-b60c-64775b2b9132	${addressScopeConsentText}	consent.screen.text
74e2fe9c-cf28-414b-9ac1-69b8341d5413	true	include.in.token.scope
74e2fe9c-cf28-414b-9ac1-69b8341d5413	true	display.on.consent.screen
74e2fe9c-cf28-414b-9ac1-69b8341d5413	${emailScopeConsentText}	consent.screen.text
df80f1b1-5b4e-4032-b0f1-d02f24a48d17	true	include.in.token.scope
df80f1b1-5b4e-4032-b0f1-d02f24a48d17	true	display.on.consent.screen
df80f1b1-5b4e-4032-b0f1-d02f24a48d17	${phoneScopeConsentText}	consent.screen.text
fd9ab351-6886-426c-9911-c98ab6cd5e95	${offlineAccessScopeConsentText}	consent.screen.text
fd9ab351-6886-426c-9911-c98ab6cd5e95	true	display.on.consent.screen
3cca6cb8-d31d-47c6-b906-6edc41b12173	false	include.in.token.scope
3cca6cb8-d31d-47c6-b906-6edc41b12173	false	display.on.consent.screen
7fe8e74f-ddf7-4936-bd43-8280c0443525	true	include.in.token.scope
7fe8e74f-ddf7-4936-bd43-8280c0443525	false	display.on.consent.screen
\.


--
-- Data for Name: client_scope_client; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_scope_client (client_id, scope_id, default_scope) FROM stdin;
3f63aef2-fb73-4e1a-884e-5cd61ef71406	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	t
3f63aef2-fb73-4e1a-884e-5cd61ef71406	efa99282-8b1b-45d3-83c2-92994b4b1f34	t
3f63aef2-fb73-4e1a-884e-5cd61ef71406	c119aa7e-e6f4-44b2-9319-5ab700773d95	t
3f63aef2-fb73-4e1a-884e-5cd61ef71406	f8ea87f2-5324-41ae-80b2-6211b7264dfb	t
3f63aef2-fb73-4e1a-884e-5cd61ef71406	a27fab96-b997-482d-97b6-3feafd230f88	t
3f63aef2-fb73-4e1a-884e-5cd61ef71406	2fa7959e-6a12-45bd-b7de-1d0d899ecea9	f
3f63aef2-fb73-4e1a-884e-5cd61ef71406	f4922d7f-6e65-45d2-8236-6d0b9373f9ec	f
3f63aef2-fb73-4e1a-884e-5cd61ef71406	98c490bc-f91f-46d7-a43e-692ad6680fb4	f
3f63aef2-fb73-4e1a-884e-5cd61ef71406	9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	f
795b4b60-c45a-4377-8da9-8471c55091d0	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	t
795b4b60-c45a-4377-8da9-8471c55091d0	efa99282-8b1b-45d3-83c2-92994b4b1f34	t
795b4b60-c45a-4377-8da9-8471c55091d0	c119aa7e-e6f4-44b2-9319-5ab700773d95	t
795b4b60-c45a-4377-8da9-8471c55091d0	f8ea87f2-5324-41ae-80b2-6211b7264dfb	t
795b4b60-c45a-4377-8da9-8471c55091d0	a27fab96-b997-482d-97b6-3feafd230f88	t
795b4b60-c45a-4377-8da9-8471c55091d0	2fa7959e-6a12-45bd-b7de-1d0d899ecea9	f
795b4b60-c45a-4377-8da9-8471c55091d0	f4922d7f-6e65-45d2-8236-6d0b9373f9ec	f
795b4b60-c45a-4377-8da9-8471c55091d0	98c490bc-f91f-46d7-a43e-692ad6680fb4	f
795b4b60-c45a-4377-8da9-8471c55091d0	9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	f
55e3fd0b-11f4-497b-b963-3bd4885e6766	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	t
55e3fd0b-11f4-497b-b963-3bd4885e6766	efa99282-8b1b-45d3-83c2-92994b4b1f34	t
55e3fd0b-11f4-497b-b963-3bd4885e6766	c119aa7e-e6f4-44b2-9319-5ab700773d95	t
55e3fd0b-11f4-497b-b963-3bd4885e6766	f8ea87f2-5324-41ae-80b2-6211b7264dfb	t
55e3fd0b-11f4-497b-b963-3bd4885e6766	a27fab96-b997-482d-97b6-3feafd230f88	t
55e3fd0b-11f4-497b-b963-3bd4885e6766	2fa7959e-6a12-45bd-b7de-1d0d899ecea9	f
55e3fd0b-11f4-497b-b963-3bd4885e6766	f4922d7f-6e65-45d2-8236-6d0b9373f9ec	f
55e3fd0b-11f4-497b-b963-3bd4885e6766	98c490bc-f91f-46d7-a43e-692ad6680fb4	f
55e3fd0b-11f4-497b-b963-3bd4885e6766	9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	f
14139360-cf57-4820-ba85-7e1479912b93	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	t
14139360-cf57-4820-ba85-7e1479912b93	efa99282-8b1b-45d3-83c2-92994b4b1f34	t
14139360-cf57-4820-ba85-7e1479912b93	c119aa7e-e6f4-44b2-9319-5ab700773d95	t
14139360-cf57-4820-ba85-7e1479912b93	f8ea87f2-5324-41ae-80b2-6211b7264dfb	t
14139360-cf57-4820-ba85-7e1479912b93	a27fab96-b997-482d-97b6-3feafd230f88	t
14139360-cf57-4820-ba85-7e1479912b93	2fa7959e-6a12-45bd-b7de-1d0d899ecea9	f
14139360-cf57-4820-ba85-7e1479912b93	f4922d7f-6e65-45d2-8236-6d0b9373f9ec	f
14139360-cf57-4820-ba85-7e1479912b93	98c490bc-f91f-46d7-a43e-692ad6680fb4	f
14139360-cf57-4820-ba85-7e1479912b93	9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	f
122557f0-b934-4094-a8f5-36579c416a87	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	t
122557f0-b934-4094-a8f5-36579c416a87	efa99282-8b1b-45d3-83c2-92994b4b1f34	t
122557f0-b934-4094-a8f5-36579c416a87	c119aa7e-e6f4-44b2-9319-5ab700773d95	t
122557f0-b934-4094-a8f5-36579c416a87	f8ea87f2-5324-41ae-80b2-6211b7264dfb	t
122557f0-b934-4094-a8f5-36579c416a87	a27fab96-b997-482d-97b6-3feafd230f88	t
122557f0-b934-4094-a8f5-36579c416a87	2fa7959e-6a12-45bd-b7de-1d0d899ecea9	f
122557f0-b934-4094-a8f5-36579c416a87	f4922d7f-6e65-45d2-8236-6d0b9373f9ec	f
122557f0-b934-4094-a8f5-36579c416a87	98c490bc-f91f-46d7-a43e-692ad6680fb4	f
122557f0-b934-4094-a8f5-36579c416a87	9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	f
787aea5c-fd15-4af6-b6a9-442b26950af5	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	t
787aea5c-fd15-4af6-b6a9-442b26950af5	efa99282-8b1b-45d3-83c2-92994b4b1f34	t
787aea5c-fd15-4af6-b6a9-442b26950af5	c119aa7e-e6f4-44b2-9319-5ab700773d95	t
787aea5c-fd15-4af6-b6a9-442b26950af5	f8ea87f2-5324-41ae-80b2-6211b7264dfb	t
787aea5c-fd15-4af6-b6a9-442b26950af5	a27fab96-b997-482d-97b6-3feafd230f88	t
787aea5c-fd15-4af6-b6a9-442b26950af5	2fa7959e-6a12-45bd-b7de-1d0d899ecea9	f
787aea5c-fd15-4af6-b6a9-442b26950af5	f4922d7f-6e65-45d2-8236-6d0b9373f9ec	f
787aea5c-fd15-4af6-b6a9-442b26950af5	98c490bc-f91f-46d7-a43e-692ad6680fb4	f
787aea5c-fd15-4af6-b6a9-442b26950af5	9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	f
e87b6b71-1178-409e-b4ff-5969eb94b377	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
e87b6b71-1178-409e-b4ff-5969eb94b377	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
e87b6b71-1178-409e-b4ff-5969eb94b377	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
e87b6b71-1178-409e-b4ff-5969eb94b377	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
e87b6b71-1178-409e-b4ff-5969eb94b377	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
e87b6b71-1178-409e-b4ff-5969eb94b377	f54202d6-f14e-420f-b60c-64775b2b9132	f
e87b6b71-1178-409e-b4ff-5969eb94b377	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
e87b6b71-1178-409e-b4ff-5969eb94b377	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
e87b6b71-1178-409e-b4ff-5969eb94b377	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
450ad3d9-9b7c-4b7d-9044-d828e8a708af	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
450ad3d9-9b7c-4b7d-9044-d828e8a708af	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
450ad3d9-9b7c-4b7d-9044-d828e8a708af	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
450ad3d9-9b7c-4b7d-9044-d828e8a708af	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
450ad3d9-9b7c-4b7d-9044-d828e8a708af	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
450ad3d9-9b7c-4b7d-9044-d828e8a708af	f54202d6-f14e-420f-b60c-64775b2b9132	f
450ad3d9-9b7c-4b7d-9044-d828e8a708af	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
450ad3d9-9b7c-4b7d-9044-d828e8a708af	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
450ad3d9-9b7c-4b7d-9044-d828e8a708af	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
c91b6aba-8136-4311-b037-bcc7f590dc7e	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
c91b6aba-8136-4311-b037-bcc7f590dc7e	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
c91b6aba-8136-4311-b037-bcc7f590dc7e	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
c91b6aba-8136-4311-b037-bcc7f590dc7e	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
c91b6aba-8136-4311-b037-bcc7f590dc7e	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
c91b6aba-8136-4311-b037-bcc7f590dc7e	f54202d6-f14e-420f-b60c-64775b2b9132	f
c91b6aba-8136-4311-b037-bcc7f590dc7e	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
c91b6aba-8136-4311-b037-bcc7f590dc7e	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
c91b6aba-8136-4311-b037-bcc7f590dc7e	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
19f7fd43-a831-423f-b424-96312d17bfd5	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
19f7fd43-a831-423f-b424-96312d17bfd5	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
19f7fd43-a831-423f-b424-96312d17bfd5	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
19f7fd43-a831-423f-b424-96312d17bfd5	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
19f7fd43-a831-423f-b424-96312d17bfd5	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
19f7fd43-a831-423f-b424-96312d17bfd5	f54202d6-f14e-420f-b60c-64775b2b9132	f
19f7fd43-a831-423f-b424-96312d17bfd5	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
19f7fd43-a831-423f-b424-96312d17bfd5	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
19f7fd43-a831-423f-b424-96312d17bfd5	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	f54202d6-f14e-420f-b60c-64775b2b9132	f
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	f54202d6-f14e-420f-b60c-64775b2b9132	f
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
b7725f50-c6db-4a3f-a057-ea61ce3ed36c	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	f54202d6-f14e-420f-b60c-64775b2b9132	f
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
\.


--
-- Data for Name: client_scope_role_mapping; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_scope_role_mapping (scope_id, role_id) FROM stdin;
9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	3b9dced1-74c3-4f76-ab51-9a07df38585c
fd9ab351-6886-426c-9911-c98ab6cd5e95	b24f38be-3778-4b07-87e1-2af29038a967
\.


--
-- Data for Name: client_session; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_session (id, client_id, redirect_uri, state, "timestamp", session_id, auth_method, realm_id, auth_user_id, current_action) FROM stdin;
\.


--
-- Data for Name: client_session_auth_status; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_session_auth_status (authenticator, status, client_session) FROM stdin;
\.


--
-- Data for Name: client_session_note; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_session_note (name, value, client_session) FROM stdin;
\.


--
-- Data for Name: client_session_prot_mapper; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_session_prot_mapper (protocol_mapper_id, client_session) FROM stdin;
\.


--
-- Data for Name: client_session_role; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_session_role (role_id, client_session) FROM stdin;
\.


--
-- Data for Name: client_user_session_note; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.client_user_session_note (name, value, client_session) FROM stdin;
\.


--
-- Data for Name: component; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.component (id, name, parent_id, provider_id, provider_type, realm_id, sub_type) FROM stdin;
3874df2d-9ddd-43f2-a914-a466f35df999	Trusted Hosts	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	trusted-hosts	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	anonymous
fe9a3ab5-a1c0-4927-b33c-f2d0ec64fa3c	Consent Required	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	consent-required	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	anonymous
8ac21002-16ec-41a4-ae11-978c0e7ca3d8	Full Scope Disabled	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	scope	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	anonymous
fda44867-715d-4c25-9f59-fe1b7bf45ddf	Max Clients Limit	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	max-clients	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	anonymous
e97862e1-2a55-43cb-be33-e074342b7f1c	Allowed Protocol Mapper Types	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	anonymous
9249024b-ddf0-494d-bd86-508539b4a33f	Allowed Client Scopes	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	anonymous
66270865-16c4-4ab0-b417-321b73b79d98	Allowed Protocol Mapper Types	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	authenticated
d9a8e64c-5c5b-4cc1-94be-b5237e431c02	Allowed Client Scopes	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	authenticated
fa6b1fef-353e-4ad2-9219-5a35c4b25288	rsa-generated	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	rsa-generated	org.keycloak.keys.KeyProvider	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N
4c536f1f-664d-413a-80ef-d11c1baf7a33	rsa-enc-generated	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	rsa-enc-generated	org.keycloak.keys.KeyProvider	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N
2762eae0-4e12-4147-b7a4-55f85859da17	hmac-generated-hs512	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	hmac-generated	org.keycloak.keys.KeyProvider	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N
bc5dd8a3-3a55-4368-a40e-d53d729624b1	aes-generated	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	aes-generated	org.keycloak.keys.KeyProvider	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N
f4f57500-a292-4f68-90b3-8679e1f1ed02	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	declarative-user-profile	org.keycloak.userprofile.UserProfileProvider	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N
e371721b-74be-46df-a822-a74116664f7a	Allowed Client Scopes	43c36b1e-22ce-4293-8d6e-181a68f70b2a	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	authenticated
0c4e4dfb-dcd5-440d-9fb9-a94b331f837c	Consent Required	43c36b1e-22ce-4293-8d6e-181a68f70b2a	consent-required	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	anonymous
d50aa33b-05f4-4585-a6b5-4759fe6bb98b	Trusted Hosts	43c36b1e-22ce-4293-8d6e-181a68f70b2a	trusted-hosts	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	anonymous
5f00f51e-55c9-469b-90a7-5478319f1e78	Max Clients Limit	43c36b1e-22ce-4293-8d6e-181a68f70b2a	max-clients	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	anonymous
1c0b55a6-f9dc-4b62-80a2-25e4b70dc96c	Full Scope Disabled	43c36b1e-22ce-4293-8d6e-181a68f70b2a	scope	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	anonymous
36a661a6-d86b-4fe1-aac2-1792d5c435c0	Allowed Client Scopes	43c36b1e-22ce-4293-8d6e-181a68f70b2a	allowed-client-templates	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	anonymous
fbcffc16-fdef-4eee-ba28-e96bb75a1987	Allowed Protocol Mapper Types	43c36b1e-22ce-4293-8d6e-181a68f70b2a	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	authenticated
24a5c29f-a62b-4ec1-9a57-cf7121486215	Allowed Protocol Mapper Types	43c36b1e-22ce-4293-8d6e-181a68f70b2a	allowed-protocol-mappers	org.keycloak.services.clientregistration.policy.ClientRegistrationPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	anonymous
33334609-2f4b-414a-935b-2138f4aebd95	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	declarative-user-profile	org.keycloak.userprofile.UserProfileProvider	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N
17a83a99-87e9-4f21-8266-9d29431de220	hmac-generated-hs512	43c36b1e-22ce-4293-8d6e-181a68f70b2a	hmac-generated	org.keycloak.keys.KeyProvider	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N
afff4b1d-d074-4b0a-b24a-dfe01689cbe3	aes-generated	43c36b1e-22ce-4293-8d6e-181a68f70b2a	aes-generated	org.keycloak.keys.KeyProvider	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N
5420ceb5-63db-4143-bec5-8fafde01b4e9	rsa-generated	43c36b1e-22ce-4293-8d6e-181a68f70b2a	rsa-generated	org.keycloak.keys.KeyProvider	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N
aaca220e-a8df-4433-accf-02d64cdf99af	rsa-enc-generated	43c36b1e-22ce-4293-8d6e-181a68f70b2a	rsa-enc-generated	org.keycloak.keys.KeyProvider	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N
\.


--
-- Data for Name: component_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.component_config (id, component_id, name, value) FROM stdin;
3b6eca2c-8a13-4450-b20f-3f486710afd5	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
0a261653-786f-4161-bf33-a63b412db50f	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	saml-user-attribute-mapper
0e997d54-647d-4e12-85b0-bb12e32267da	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
2c23102b-865e-4c4e-8b8b-514364dd9460	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	oidc-full-name-mapper
62745750-24cf-425d-bbd5-ab56dd3e7a88	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	saml-user-property-mapper
1740802d-c5cd-44b8-89a9-c0ce79378dc0	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	saml-role-list-mapper
18b1d205-ebdd-4d45-8a72-c97967bcfe70	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
6e02c138-623a-4f9d-b8df-18188b8a1727	66270865-16c4-4ab0-b417-321b73b79d98	allowed-protocol-mapper-types	oidc-address-mapper
574d4faf-e0e2-407f-b0e8-cb750dd1a67f	3874df2d-9ddd-43f2-a914-a466f35df999	host-sending-registration-request-must-match	true
6e885108-db7d-4b59-8054-8106b68e352a	3874df2d-9ddd-43f2-a914-a466f35df999	client-uris-must-match	true
195ba3ff-c658-496c-819d-47ec737f7625	d9a8e64c-5c5b-4cc1-94be-b5237e431c02	allow-default-scopes	true
b1ce640e-6c4b-4bc4-bcb4-8e60057121a8	9249024b-ddf0-494d-bd86-508539b4a33f	allow-default-scopes	true
537d31cf-b2c3-40bf-a0d4-382c2016bae4	fda44867-715d-4c25-9f59-fe1b7bf45ddf	max-clients	200
555a115e-58f9-4730-a678-7904f4bbbb84	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
02bb8519-4011-4d05-8a35-b37f67ec9807	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	saml-role-list-mapper
0b352f43-959b-4f11-a6ab-43169335639a	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
4e0575aa-8a6f-49d9-a7e1-c946eabc1c53	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	oidc-full-name-mapper
57c403ad-4b47-4186-8ee1-818c4c1ad009	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	saml-user-attribute-mapper
b1ce2920-d2e5-4ac9-b6ae-b9298fece97d	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
d8a8f5a5-21be-48de-ab63-4da53e00e0d1	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	saml-user-property-mapper
3391e38e-f610-45ea-bba5-84feae1e8590	e97862e1-2a55-43cb-be33-e074342b7f1c	allowed-protocol-mapper-types	oidc-address-mapper
d5289c9a-d903-4cae-ac27-64623d81a113	fa6b1fef-353e-4ad2-9219-5a35c4b25288	priority	100
38c7c988-b785-41f6-86bf-9a44998a5d34	fa6b1fef-353e-4ad2-9219-5a35c4b25288	privateKey	MIIEowIBAAKCAQEAwJ5jn14Q3QLxf6QXgRAdFKYF4U7GQd2v++InCnnhX4HsmkfooUNWf/L/sWK+kdsww/PCtIs1MZ9KFoSruT89iM6kccVnp07Se0lbpZeehjZHVeZZtcezh+CuEoCKR0X7I5wV7E75bOkgw3gkUzn43EOVlp0NYfzeERlFouNSt8Sq6b5Y4T8stvgGGuoknHnWofBnxIYOeuaIEoDxYjQ9ZKoe5i7MDg84WAboascLxh5J+yGXPSSYj8r75daRCgsWqmtLYM5MCxApZAJig2NbDuaUpAHVK0uR5GKYOotl4/DDmtDj6UH1Y8Szg+9RLJj52aVLVUD7I/LwcAl1F93TRwIDAQABAoIBAAjjJfs+nAOT37BWfLkj6luUtMpqV+PGcUNrcgazN0QGBXSG4y5m93uW1YJfPntnWXl+apo+9A4ykyNf2Avc5Z9wKA6RylBiEXxxFuKYkM67fBDJZz6SahpXsmBrKuDSqJ2XgDweLAxWOKtG2fzhvLWZBAgQ01hqkj/IDTpQnR/vxV9K+wwffNU6gVEIhCzVx7jSBFLWmArblI/DBkCMfULaDdbIdIfrxKB0EPYmMy+eZaC72N31tAzndpdasTW0mZLQYG/yaIVuV1qvGojRIP/q1cVOhukb3MU1BeP1GlaPabjNrdxZUtMXYvn/xvZdfYJIjh+DrVCwv3SCquBjgAECgYEA7JZUUGQOGnEoH8gDlUi/JMwscMVOWosf99aXtKs6+r8r/z/JtjWzehSyhoPZyOOiG44lxhkfwzcnGXZ+uZsufT9G1iqFBbvQ41fAtdfJhNXjBVm4Il/xyoLCZwfwcF+jhCs/dw+NafFaaY/NZkid1+YBTNzFKt09EyWyNDq9U0cCgYEA0Gx43MDDGc2mXqp0ONSZ0DEGDv8VXnKNT7UqjT/HdB2rjmLL6+ubhFYYBsnx7nqrfF+Ctx+1lw4YYMrdmaAQ01HVQ8HDhwg+F6pvQopqCHqj1bdHCpqwowq5kWqRDpOh3tyl0IWfHGEVjUTDtR5xeIW7Pcbh31fK3LCkmb0bgAECgYEAgDC8Ior0MVOawA2qE1tFZnTSp4/g8qJrgehebuBQpsiq2DUB4J6cTsukJ8qNWJIupeuRgBLg6O+bWeqgo4T33ZlJkoWbag3oEybbHix73mPeDGoeoVGHO4k6OIVOvDS17slYK/TbVMoXiRcH3iH+lJrlnXYJxz3098j3E+G378UCgYAs+muuwA6af+3aWhCQvxiefCXhkJzM8A2c2Ozg5JWncku2g42cURETovy2YlhKbcCaK4Zq/HXUrl8GbGEcKRXYdL2ATM1kU5wKwWYXRwWQ5N58XXwARte9p6/aONQrACMblCRYdq2akzzyapeNe49jQgDhWQ0fhLOropcy8kOAAQKBgDSvMYKZM2vdYlsnkj+5OVRJ618j2RPF/e9FEM8T6OL3YKp61bkmAxWxBiCikMN3lQ0VmrTGW3xYR3pPyZANBXpWpgbzM0Ofw6rVxLgwmhpLCTqEjvCa3s/TU6FdghEeDyGkZzo49ne8kVRc9JerNiVZv6Ba88geyFfwOrNoSmj5
832a1bc6-a219-4a7b-9bc4-5b408591a18d	fa6b1fef-353e-4ad2-9219-5a35c4b25288	keyUse	SIG
a335a1d7-2562-47ae-8014-05ff2880e8a3	fa6b1fef-353e-4ad2-9219-5a35c4b25288	certificate	MIICmzCCAYMCBgGQnwda7TANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDDAZtYXN0ZXIwHhcNMjQwNzEwMjM0MTE3WhcNMzQwNzEwMjM0MjU3WjARMQ8wDQYDVQQDDAZtYXN0ZXIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDAnmOfXhDdAvF/pBeBEB0UpgXhTsZB3a/74icKeeFfgeyaR+ihQ1Z/8v+xYr6R2zDD88K0izUxn0oWhKu5Pz2IzqRxxWenTtJ7SVull56GNkdV5lm1x7OH4K4SgIpHRfsjnBXsTvls6SDDeCRTOfjcQ5WWnQ1h/N4RGUWi41K3xKrpvljhPyy2+AYa6iScedah8GfEhg565ogSgPFiND1kqh7mLswODzhYBuhqxwvGHkn7IZc9JJiPyvvl1pEKCxaqa0tgzkwLEClkAmKDY1sO5pSkAdUrS5HkYpg6i2Xj8MOa0OPpQfVjxLOD71EsmPnZpUtVQPsj8vBwCXUX3dNHAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAJBR2sjUGlXFOqwIdroUc/lSwd2paAmAGSEDLtQm6m5IE/GtcqnLbL9SnCHWX1FBjo5aZ53Ssj1HvUjpcWcQNdGYB9v732GtgXg8gMwNGN8DNHd0nIOXzXvmeGF+QDU7+BjBO7iHpQo3U004S5y/zE6wfar7Z5jQHBNqCD/idCRdrm/SNGz1gKE0bSL+o9wHYpy5X9YKVGLPA/4SMJ9zlKiaiEjKH9objRTN9biLOJsm0Rf0uNasObpYu9SnbOW64CqRscPRDRJh8+4IUN3dVnuSHxHLDL812cCXQG63zLzK1f/gYlcYUnHNBYCYfqj70rOuFmxKOGtBRs494ps4oIw=
a95b113b-621d-423c-98ec-f614e5064557	bc5dd8a3-3a55-4368-a40e-d53d729624b1	secret	p3zVpOqOz95p8zUXxERw5Q
8997071e-a481-4ec4-901b-1f9e97f5ea81	bc5dd8a3-3a55-4368-a40e-d53d729624b1	kid	b385f6a7-a0e1-45bd-bea3-bbef513cf42e
b8d1f3a9-a188-4f0a-9409-d2517c4896c6	bc5dd8a3-3a55-4368-a40e-d53d729624b1	priority	100
1203149e-1df3-4da7-b8b3-87573527e789	4c536f1f-664d-413a-80ef-d11c1baf7a33	keyUse	ENC
a6e11ea3-9e36-4dec-a8a7-f7c8294d953e	4c536f1f-664d-413a-80ef-d11c1baf7a33	priority	100
6863882a-415b-4379-8183-501f6677e9d8	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	saml-role-list-mapper
13d2d3df-3f1a-4454-8c01-2395f4b3337e	4c536f1f-664d-413a-80ef-d11c1baf7a33	privateKey	MIIEpAIBAAKCAQEA6BoYGZC+aTz0XBkKMgAoTCfbhcJIIiMsqk9Nv0r9L4lhOr3s3kYaondQdo6MYkE/j6PfthEBgS2AGnEf0qB3iIrytit7rJE7vnbIRPYqul+fq4H6XnqyJjEBN3+oZB/8qotrSlNIYwQURyIPI97TUE77DAMc8NQRPb3VOrd7GBaB2aqDiiaMTjcXpOiU8CT1mwexH457jpCklWaG8+srE171WCf+szOCkRFTnwAE6aE/35bkBmjqDGp4oNqQ95BonREqzS5u5+qRIaDLXF13vof8cfPSiynbya0tpmhowq52k5/JHNknAdOj9NpxNC/V5H7UroCZO/l7lq+PchugIwIDAQABAoIBAGYYAEzSjeclsEkInKL0zcT97UJvfKJDfaSmgIjp9+eekt2vaE83YATh3ctglaCsmcRZrZBc95/2PziiVQClpB60G+IEy4BTXAfCIQKwk4fqgwYChPplFxLUkHf0l8H/DfmInReS/a7MPw0xDp7VJ3dWXnhgsCzA45qYc4BB8AveF/nCitX9CQdAxn0qcBZdcDmQUo9zvLsdzy4HKJNUxe8I9Jio+65wx1Ir6cQBGOZgxiaB9NAzirGsFsnkHrPXEEThdIQmcSdElID6N6n/WrfcQMNYVrrlNgvYFheT3wEiYEAbMjz6cJP13EEY11dF10jssjpyyifrMoFiXU0cvU0CgYEA/k53jy99JGxIoGLdAn44Uxiz/QwEzoWATuGz2IQ4DcV05F+m5YgYxafJd8zyAZTHxrthoEPrfhJvdZwJv/F12N4yUUdA9gYsfv922PPvGJ5tg1Gagyn+G3lkGn+ZAb0swQyyd6UH4EHkRgkfhxOehtTGqXWK1WVE+dt8uZpSf40CgYEA6aXGBIZs8F4QSOTWSrKPaE+x5HMtWoLU5UObP+AVJJjMoqzNcvRVumlvrdIb9wlniYzIZr4hhPSGgpKfkcF29zPuGSjDfMPfAqL6BubqJjV9/GVMxlN9vFWu/cwVc0tP7A6vzzjSnecpDDQj4IowiLg0ATnLbBpTh2e0xHNqGm8CgYB7VkEkP8r45E+hAfHgqKjWnG+G9dJK+3KMT2jzRMUO4MpdjIqcXhJton/Sy6I/5BG0L+McYHSRU42dQ81yu0AFq1ZKFz3JVJs2CN5yQHSgww2oF+bRYIduecR0GZhywHY5NthDsNWODUcSNJCG6ZwEFqQ3QdvQvWnjqHDob5E9bQKBgQCn8iT4M7gyvyptezAd2WQD0uVIFRJQsSJe+c2qiGQqPjs544R6UO8eY5OHGjRHylbGJJAYaiql6y6UxNg2aR4eaxGGvCpokFLJDmUyeOFuPZ0lw5sAJBuJOP3kKMJycXwtbhEpnuql3fc/cmsnh62DWQTorKZwOiw4dG8Xlu55uQKBgQCyNB8DCI7F28gwvYPISX0wuO0BqBG0CfTJy6zc0A0i/w7bQAXqk9klNl2g6l1NSV84q3jkBQLZN5ZvnHTKvT8ZCqjpglJku75kMyYqWVeEt6LBfN6yRRiEp1CJFUeP6Pew9aXqs19EhSuurDTIRSG4CpfXNrw3eFFT0i5iroOcMg==
a4479fc2-26f3-4150-956f-2e326b3cb73c	4c536f1f-664d-413a-80ef-d11c1baf7a33	algorithm	RSA-OAEP
018c404c-ea65-42e2-ab1b-44db6e43bb05	4c536f1f-664d-413a-80ef-d11c1baf7a33	certificate	MIICmzCCAYMCBgGQnwdciDANBgkqhkiG9w0BAQsFADARMQ8wDQYDVQQDDAZtYXN0ZXIwHhcNMjQwNzEwMjM0MTE4WhcNMzQwNzEwMjM0MjU4WjARMQ8wDQYDVQQDDAZtYXN0ZXIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDoGhgZkL5pPPRcGQoyAChMJ9uFwkgiIyyqT02/Sv0viWE6vezeRhqid1B2joxiQT+Po9+2EQGBLYAacR/SoHeIivK2K3uskTu+dshE9iq6X5+rgfpeerImMQE3f6hkH/yqi2tKU0hjBBRHIg8j3tNQTvsMAxzw1BE9vdU6t3sYFoHZqoOKJoxONxek6JTwJPWbB7EfjnuOkKSVZobz6ysTXvVYJ/6zM4KREVOfAATpoT/fluQGaOoManig2pD3kGidESrNLm7n6pEhoMtcXXe+h/xx89KLKdvJrS2maGjCrnaTn8kc2ScB06P02nE0L9XkftSugJk7+XuWr49yG6AjAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAGOzszisY9n7OS/mxAkQZNNj4DSLb1V1TrNKFkmeyvkk5zM4zeHo/4IH5YiBjP6PbC+tb/mZk9cpIXmJBjOfV8HTdDl0mGXJRm6GdtoDri33fdnFHceE2Ni9Wrxg8X1GjIvmp6vTnhkXLEwEGhypLUDhmm5u5PCmOlt4Ho7cbkPjvxs5XhOASzEzOIyX8AmhBk1jXeiEopU8FvNzyGiYnk22hvd37PW4HuTlPOdhplCyWsjw4dk3mn+oHAtYMU/KUBE9RnfqqecVJU9/hhVpkbAAWibEX1hs66QWvyo5fUHZ+dTMyOKCMa0TNLnIin5+NCTA4qYbMK0i8qyQvHG9Jbo=
88bb3496-ea00-4e35-b489-3fbbb9202494	f4f57500-a292-4f68-90b3-8679e1f1ed02	kc.user.profile.config	{"attributes":[{"name":"username","displayName":"${username}","validations":{"length":{"min":3,"max":255},"username-prohibited-characters":{},"up-username-not-idn-homograph":{}},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false},{"name":"email","displayName":"${email}","validations":{"email":{},"length":{"max":255}},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false},{"name":"firstName","displayName":"${firstName}","validations":{"length":{"max":255},"person-name-prohibited-characters":{}},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false},{"name":"lastName","displayName":"${lastName}","validations":{"length":{"max":255},"person-name-prohibited-characters":{}},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false}],"groups":[{"name":"user-metadata","displayHeader":"User metadata","displayDescription":"Attributes, which refer to user metadata"}]}
cbce52ff-e9dc-466d-9c6f-b658ee86feb4	2762eae0-4e12-4147-b7a4-55f85859da17	secret	i9DKpajDtaf6KoFm1nNoGfQEQe_FEiNzHRyO7D5zQ2UBXwL06MxuEFGjknsdOwXESty3zL7MYc5Vq_fc1Rpcmmz4qUTVDoTi7BpT1Ukjkzh2_WRnDOszVuXWr03kqNXeLL2q3yAMUW_1Sl_ZJIShI8oOFigLdKviIGVh7XReq7Y
b03d4d8a-1833-46df-948e-c2012c22cf34	2762eae0-4e12-4147-b7a4-55f85859da17	priority	100
a6a222a5-bb91-4e9e-9efe-41b6ecc6baf6	2762eae0-4e12-4147-b7a4-55f85859da17	algorithm	HS512
71ac7b93-d220-4807-aee2-e634687f6113	2762eae0-4e12-4147-b7a4-55f85859da17	kid	6b45973f-bd2e-46b1-96ae-e4adf03ad3ed
2254f9c4-48e2-4aab-9732-ba0deacbeef4	e371721b-74be-46df-a822-a74116664f7a	allow-default-scopes	true
cf37a0c7-8eed-42d8-8721-124987cd618c	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	oidc-address-mapper
a7d31fec-f232-4347-95e7-13152e64f8be	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	oidc-full-name-mapper
a1581112-67c2-4021-8b13-b9f4663ce73a	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
fc125efb-960b-4445-b0c8-b69dfeb57e66	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	saml-user-property-mapper
ca86d541-2fff-418d-ad12-942588620abe	aaca220e-a8df-4433-accf-02d64cdf99af	certificate	MIICrTCCAZUCBgGQnz4XcjANBgkqhkiG9w0BAQsFADAaMRgwFgYDVQQDDA9maXJtYXMtZm9vZC1kZXYwHhcNMjQwNzExMDA0MTA0WhcNMzQwNzExMDA0MjQ0WjAaMRgwFgYDVQQDDA9maXJtYXMtZm9vZC1kZXYwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC4mkzslrQHSMOhFlGHDw8cPc5cxPXJ0JkGdnITfqzos1EP58AAGS/RMmGLGF1mt2SNHN9v62ayESCwqHbyWekq45P1jakEPjm85cTgyr7uySu5hoTH3pJEq6zcFOvvyga6rlOa05/pkZ/2vGS+OPgDT8dP+oFkOivjpiSdb068+lowAP9Q0PepLdzBtc1oHpW0N8L4XUoV1AIlc9GZ1P99Aiw/FvRZ2Hho+ZijManQ5nkBhlgjrUN+905pe52U6USL4Mc3peJwvFk6smBkVqVRFDeD7bats9pwiPlRaCFvuzM0wf8EzZpTab/hp0qUyqKI3i6n9qnk/fswjxcjZkbNAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAAiGurjSbUdKVMUPwEs09ykQKAZgiLj84Eakz60VOq2cn6G9hcVa6BW0KxA52owiQda8FuPahpEY1gv/ATUX+gbqqmE0Ci2qDua1RRdtUAbDR10b3cFJjL7/I0kuturNw4vTyGphTciBPTzI6KzREoy7MsYM+3S3PZ+0KNZadkh0mdGnoP+JRAkdNI+MOCcjVTb5mVsm4L5j+C4NjvDBdS00xbW7w2Z9FS7RBdL6/Hfj1vIysJhBZ+ftfFU1F2dbMqBkEMqgxHFb8EAxZIp7jvYKeJCxWktjYI7yu38xrwZxGfLIwWV2erUHmWAo+RZ8ZvhF6mOoXmTKQz6uXSXkQtU=
cd456b45-413b-4289-afbe-6325b92e38a9	aaca220e-a8df-4433-accf-02d64cdf99af	priority	100
718ea3f0-62de-4f58-8c1c-4941899d2c80	33334609-2f4b-414a-935b-2138f4aebd95	kc.user.profile.config	{"attributes":[{"name":"username","displayName":"${username}","validations":{"length":{"min":3,"max":255},"username-prohibited-characters":{},"up-username-not-idn-homograph":{}},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false},{"name":"email","displayName":"${email}","validations":{"email":{},"length":{"max":255}},"required":{"roles":["user"]},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false},{"name":"firstName","displayName":"${firstName}","validations":{"length":{"max":255},"person-name-prohibited-characters":{}},"required":{"roles":["user"]},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false},{"name":"lastName","displayName":"${lastName}","validations":{"length":{"max":255},"person-name-prohibited-characters":{}},"required":{"roles":["user"]},"permissions":{"view":["admin","user"],"edit":["admin","user"]},"multivalued":false},{"name":"restaurante","displayName":"restaurante","validations":{},"annotations":{},"required":{"roles":["admin","user"]},"permissions":{"view":["user"],"edit":["admin","user"]},"multivalued":false}],"groups":[{"name":"user-metadata","displayHeader":"User metadata","displayDescription":"Attributes, which refer to user metadata"}]}
c2cb8c2b-d830-4c97-8b64-11cfa30456e4	17a83a99-87e9-4f21-8266-9d29431de220	kid	53781c68-db58-4eb6-95ec-e32b2a1bc576
d3c7c517-82e3-4197-8aa7-49e9d9692f67	17a83a99-87e9-4f21-8266-9d29431de220	secret	kIS3lxuPGoyAiiXwqFI4lSn8CaNZL0KQYXcsVDzsGCHxoMOLrSNSfm8_YhT53WE7DNedigyf-9VsSJhHYy-a_XCq7K8m9h61FizqY9WSVbAwiku-crbIipCb6GPDqjCgupyNthDuBD5yejEJmpqIlD7j4zv8fC7cx_B_fZkHaK8
9208f053-dca1-4d6f-a491-b026e5cc7da4	17a83a99-87e9-4f21-8266-9d29431de220	algorithm	HS512
b6ccc58e-4009-40e1-ad7c-d5fc81f13b73	17a83a99-87e9-4f21-8266-9d29431de220	priority	100
26cbc007-ba13-4745-97cf-f90cb4554e68	afff4b1d-d074-4b0a-b24a-dfe01689cbe3	kid	d20789d8-a309-4f4f-b13c-cbf076c35bd3
5edd34ec-0453-4070-a9d9-67970150ea70	afff4b1d-d074-4b0a-b24a-dfe01689cbe3	priority	100
8a794040-00c0-4ef9-8b52-560a71533ea8	afff4b1d-d074-4b0a-b24a-dfe01689cbe3	secret	VHQaE6bvOrQsspayjMOeVw
ac032a72-4875-423b-bd74-b860f55fdfde	5420ceb5-63db-4143-bec5-8fafde01b4e9	priority	100
529f9418-6125-4640-99a7-8611f9326fcd	5420ceb5-63db-4143-bec5-8fafde01b4e9	privateKey	MIIEpAIBAAKCAQEAmR8uFUFHAnJxu+BJZ2WQm3lRrKIalWVgA0NglgYaALnOuCvauGA5CewRcVsbX1YlnABg2nTPDLvhyh4zEk51dlrfftIjynvCNchjcvH6Zgp6w3YN54dgO6ZkIzbDEYJycliDhx26sUUawZ2rYPO78TFU58wFdU8GTAdurJ2tKpX/D+hEnp73wKjpTWGBy99IhU/ZY72qWYMxfNvQFwwmtU99G5H3wUgbFqo74K+2aZNzfhX5J1xLwo5X0EPy/aOt6ifDo+2eYZ8vYSYz+9rAPTWfKFctXt65KXGXX7xL3sGYPtkghojxnqDcEBrKL9NZUW43pBTHiU9KZdeWOWb22QIDAQABAoIBAAf9LnmsFuOzy5H9LmyXSfzdTuZDlut7MeU5sLe4cxwylZcvrTu4GYHBBVqgDETkc8CN7H+5XyF6SVoy9SsxWMOSE4hvDQWq1gPZrrFNRWk+3A+IAKBh2c2C7ZqWEttpA5ufrqJwFKYxxXxF7lkinl2uheWKKYa+AXAaEIDaa9yFiPJNWroSh7dfUGIX15u3I9a7M+mE/yVxNo7kiukx8IQfGsfuzsFUMx/FvsxToON37XdsefAU3d8Jy1Xm9pJ8AqlVf63C6pJ8cEUTqLXnB/VyilWncm3Iuf1Ek8/WtGCm2cVmm4qaRZEShp5fts0Nr49mCOl8shsRv0vrv6netTkCgYEAyoGgM6BC3YJMe6CnafeQ/exP59OqcbUNnW72+7oGttYX38zNLyrLDMdQxCXs1C+uIlij4CabJ4/69iZTwobr2Os+GqtK3WkO3JhdnYsOgxq6Z2tZ1WVANg92ZcJd1p16s/Ss59G2q5YOJbN9SHOy9Zf5VZi3Jsq8FUwSq+Fzl9cCgYEAwZHz1lb1sshkIhoeI73W1bZ4SKfQsDMIHub6zj5pSHyRsD8ghUu/jhZiaMAermKQJYWH9QY8B2fB3yp9Z87RzOi9XKkuSJPQp1JyZ7Sl3d3Dwa5htgR2pjCy49H83QVXFOKtH88wZTpUJT/XRGKY7n/Jz3aBvrWaEWNpTr7kUM8CgYEAgVbdfVvEmTnZo7RueQ33+1ZtisH0QP/EnGL9OLYOB8UiJnzmKFQKsDS1bzJR2wxBCgrXUBkYS+J4BbNhGAOYq0lEtz/kZxnsthRS5oakVDREDj3QeK1cn6qskj3cNXELp9iEfuPA6kLC8m17fUT4bkgMphEOGaavDmMyIbmzXK0CgYEAi5ZQnFSBQbe4Xc8vxN51OhAnlV1O5IEobgBrBKeFDLyn4szjCryhGjmSJa/FcEOrZyNv84XXbA+qguAFJwLB0F5Vj6iAs93ELaUK0zY2FlSBgf3agIOffCDKH/ehvVDIh0sfRT0HzTfOzj3HYo0nc3W7dlwjG/+hZsClhB+sTYkCgYBtIJISxreN0pwxcOfZwxN/5QtCquK1ucU9vgmN+UVoeaLJLu9GsA3FLv6AGIb24aCGIo73dRBtjOaQqaWSbZKB1JuKTXL3P3RBAolZwVLT5pvoCb3QANaGy8xtF9Iqetet/1u3Vw2kHLKMmnsjXnJBgIZVJB9WaJpq7I5j2n7MEw==
7ac99c6c-f6ca-4536-b654-23389ae3bec9	5420ceb5-63db-4143-bec5-8fafde01b4e9	certificate	MIICrTCCAZUCBgGQnz4UrDANBgkqhkiG9w0BAQsFADAaMRgwFgYDVQQDDA9maXJtYXMtZm9vZC1kZXYwHhcNMjQwNzExMDA0MTA0WhcNMzQwNzExMDA0MjQ0WjAaMRgwFgYDVQQDDA9maXJtYXMtZm9vZC1kZXYwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCZHy4VQUcCcnG74ElnZZCbeVGsohqVZWADQ2CWBhoAuc64K9q4YDkJ7BFxWxtfViWcAGDadM8Mu+HKHjMSTnV2Wt9+0iPKe8I1yGNy8fpmCnrDdg3nh2A7pmQjNsMRgnJyWIOHHbqxRRrBnatg87vxMVTnzAV1TwZMB26sna0qlf8P6ESenvfAqOlNYYHL30iFT9ljvapZgzF829AXDCa1T30bkffBSBsWqjvgr7Zpk3N+FfknXEvCjlfQQ/L9o63qJ8Oj7Z5hny9hJjP72sA9NZ8oVy1e3rkpcZdfvEvewZg+2SCGiPGeoNwQGsov01lRbjekFMeJT0pl15Y5ZvbZAgMBAAEwDQYJKoZIhvcNAQELBQADggEBAEo8NaPst05SL0vODNE3vD0fC9Z0QfpHQNgQecigAKhmcheWhdwVKjkCd766uiKiaLhzcFjC91stzXM8PqOYZ93/9gSUOiTqo4hMevFSbj+DRWc4a4J2eND1eulrNMO/+Z5LdsQPHFmtGdOaeWiETtnUsUDiCxxF7JfB748ON4fDjwY0+qBul8CkLZlxbhIuUHQVrVogsr2jUkuBzsX4EKzWXW5GZcQraAxpHuH2dzktyOU0+lCBiWjYNdEeyCJy76qu9IxJgFKvrFQkyyDVYUCm0vQN4gXgCTS0SNYk6IwyXU6qeG/E04SQE2WPu2e/0xtRNBzSynCPu31cJWX5pxI=
c11d4d15-5348-4697-a2dc-11c30fd7b7a8	d50aa33b-05f4-4585-a6b5-4759fe6bb98b	host-sending-registration-request-must-match	true
2519122c-3f8c-4593-8822-215455598176	d50aa33b-05f4-4585-a6b5-4759fe6bb98b	client-uris-must-match	true
282ea00e-ce1f-443a-be5d-85cae5ac56b7	5f00f51e-55c9-469b-90a7-5478319f1e78	max-clients	200
9d92a361-e4ae-4a23-826f-80e5ff7394b4	aaca220e-a8df-4433-accf-02d64cdf99af	algorithm	RSA-OAEP
a405050a-2f0b-4ece-9574-502b5329101f	aaca220e-a8df-4433-accf-02d64cdf99af	privateKey	MIIEowIBAAKCAQEAuJpM7Ja0B0jDoRZRhw8PHD3OXMT1ydCZBnZyE36s6LNRD+fAABkv0TJhixhdZrdkjRzfb+tmshEgsKh28lnpKuOT9Y2pBD45vOXE4Mq+7skruYaEx96SRKus3BTr78oGuq5TmtOf6ZGf9rxkvjj4A0/HT/qBZDor46YknW9OvPpaMAD/UND3qS3cwbXNaB6VtDfC+F1KFdQCJXPRmdT/fQIsPxb0Wdh4aPmYozGp0OZ5AYZYI61DfvdOaXudlOlEi+DHN6XicLxZOrJgZFalURQ3g+22rbPacIj5UWghb7szNMH/BM2aU2m/4adKlMqiiN4up/ap5P37MI8XI2ZGzQIDAQABAoIBAAeiKcgmuy8J7Tz+BUUGdGOAksX3RwqfW5SO1UzFEjVVJOb5eFEIuZd2892ilkonDztelEa2fzqhxq5HaRPAcAnb+0ROJU0NnamX/rok6BxLqUk0rMzvATFhv/Yhz1fNDNYvWzmtwpEP+oNjau8KwAazB9Tg0PTqCuKQ/jacYa8iwQ2jzHExnD34y2Rr/rfeaE+A/8FVy8LJBFQMHuBqjLnvkvSVJyixpA4XQEByuPO2vMBcIntNU3Py3cz0k0uvul6qYJALeg5isWru3V02aL8MlsVQgdTHBx1ivo0Jn2VRx0Hlx8JJp8biYYDedEVYcMbLbvtbswuYk36C7ImoVmkCgYEA6YHmmRXfAbW4VdhvMQJ1phyCM+3aFlYr4Wnqaq59Tdif0kuKnwnEOzVbexDOG+TZLCTU0A6PyTa/vlMhB8fvoajscFX6McZBN1oz9iofyVRScyPncJn3t8LI1eeocaqUgiGJu/OMOuS4e3BwdAEvSrx42+nOwx74qzuJ50yqy58CgYEAymJzYKhUoqwidTnRXd/KjGms8B6U26cFu12YnjmdHLgxhfUMpBxs9yJNOJ4CsKTaz+AzDte7Ka10C0lUU6aGkpn0+qTU3gWp0Qzdm4fiyhcUuW0g/lnviNnI1ciTP0SRYfGuYCf7dm5MuFO+wpGmenHg54t1UxjOzCuU3QhWlhMCgYAYH/6mJFdVAuRXojPiuWFUVy+wk8trcV25PUsl2H9DsrTYckuRIN4wS9P2xAa3ZQ52/Bssn8SFQm3PCSGpfyOn7R74gDmIOPZ19JDXxwRftaGLGdOIk6+/QZWkW/nQPJJ5W2OykngTgZv5zOUhZxnuu8g02FQvXpzMfCtERWyD+wKBgAKRvWU8vdZS2R0j3sSSUr/1l3x05u7i7++xAH8BvDzhGb/ZnI94zOlWRiSx3NbhQOXissM9eeLNkOuTwr5NHug9jB6ieRFYgQ9mWmnfD8IBCQJRoFmaSrAyE5qjMTtcY3wa1VvjJg4a2F5+sa1GrBZh27kUzCiB6B4vb3Re5rqBAoGBANW9PwfYVk4f1EP926UyPGl9etnngn6nnGi3spMWpWlxiDcTv8GD/ATLMRjLjgnvFrfnsNFtwUhKhfAsv4jHcg9L/haGMS2ZsWUindPWBk2fJvOJfvamp+F7YX1IEjQhgTKA1cm9lyizPUfLuKvlu5KPa0kXeLifQmyJoi62mYBY
ee79ab66-fa49-4796-be47-4942076ab4c8	36a661a6-d86b-4fe1-aac2-1792d5c435c0	allow-default-scopes	true
cdb3592c-d28e-4551-a775-13c79ea0f332	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	saml-user-attribute-mapper
036d877f-bc5c-4993-9b67-b4036252b9b7	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
15d52870-ad74-4c53-8731-b3421714f6b7	fbcffc16-fdef-4eee-ba28-e96bb75a1987	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
95ece266-e649-49be-9640-c0fe077ce0fa	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	saml-user-property-mapper
c3f529d0-e690-49fd-ac9d-07943ad0832a	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	saml-role-list-mapper
e29de7b0-c4ac-49dd-9ef1-0e2b9a86e5b7	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	oidc-usermodel-property-mapper
66540b5c-b152-4624-a897-8e27f870d613	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	oidc-address-mapper
ee31aa98-3fd2-405a-9288-18c36680324d	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	saml-user-attribute-mapper
10a3a1be-dca2-4ad0-892f-12ab3cf0e66c	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	oidc-usermodel-attribute-mapper
518d61a2-c0fe-4fe2-bd55-d426bab1486c	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	oidc-full-name-mapper
0b834c0a-2868-4bc4-951a-8380beeeaa90	24a5c29f-a62b-4ec1-9a57-cf7121486215	allowed-protocol-mapper-types	oidc-sha256-pairwise-sub-mapper
\.


--
-- Data for Name: composite_role; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.composite_role (composite, child_role) FROM stdin;
6b04c0c3-31eb-4180-a753-2d67b4e540a5	d00996f9-3ced-4b67-90dc-d87bf7651e79
6b04c0c3-31eb-4180-a753-2d67b4e540a5	b0e7ba35-f8a8-497e-9952-58983b62f295
6b04c0c3-31eb-4180-a753-2d67b4e540a5	6afdadc5-36ed-4281-b0e2-a001afa458b7
6b04c0c3-31eb-4180-a753-2d67b4e540a5	6514cf70-3b5b-4c9b-ab40-0bc400dd5a28
6b04c0c3-31eb-4180-a753-2d67b4e540a5	c950aa8a-1bf4-4da3-af04-153a0e742291
6b04c0c3-31eb-4180-a753-2d67b4e540a5	64977b56-2d0a-426e-98f2-659b3fb78f25
6b04c0c3-31eb-4180-a753-2d67b4e540a5	48653960-6b09-47b8-afb4-d3bfdadf9994
6b04c0c3-31eb-4180-a753-2d67b4e540a5	9efd6572-16a7-4422-9268-8b99c12efc68
6b04c0c3-31eb-4180-a753-2d67b4e540a5	d709e2c5-3ce1-4aa4-a371-dfc2623bbfdf
6b04c0c3-31eb-4180-a753-2d67b4e540a5	ff9f52ed-2e3d-49dd-8f51-2e3b9542e35a
6b04c0c3-31eb-4180-a753-2d67b4e540a5	60801c0e-15a7-49fd-a61c-105ea63360de
6b04c0c3-31eb-4180-a753-2d67b4e540a5	c7e3af63-8147-4b34-ac12-463a219c75d1
6b04c0c3-31eb-4180-a753-2d67b4e540a5	8c8600e2-a36e-47e6-8173-0fe10ae0f3b2
6b04c0c3-31eb-4180-a753-2d67b4e540a5	20e99be8-9755-499f-b8f3-f80a7132e0e0
6b04c0c3-31eb-4180-a753-2d67b4e540a5	d6c7b275-03de-43d7-abea-63490168d3d9
6b04c0c3-31eb-4180-a753-2d67b4e540a5	a2bc9a43-ec83-43a5-b302-e5a719788fff
6b04c0c3-31eb-4180-a753-2d67b4e540a5	ba74ce3f-9931-4334-ab6b-5f5ae65df4d3
6b04c0c3-31eb-4180-a753-2d67b4e540a5	3c340347-77ab-465c-ad5b-01d95d231264
1f0f7753-5ef0-4083-8641-173682edf64d	ba976cf2-8c29-4513-a933-6b8c1148330d
6514cf70-3b5b-4c9b-ab40-0bc400dd5a28	d6c7b275-03de-43d7-abea-63490168d3d9
6514cf70-3b5b-4c9b-ab40-0bc400dd5a28	3c340347-77ab-465c-ad5b-01d95d231264
c950aa8a-1bf4-4da3-af04-153a0e742291	a2bc9a43-ec83-43a5-b302-e5a719788fff
1f0f7753-5ef0-4083-8641-173682edf64d	a1b72506-aa0a-4955-aee3-138855abe878
a1b72506-aa0a-4955-aee3-138855abe878	4ecbb52c-9bfe-4d73-a7d7-6962290de3ad
d00f018f-f848-42c5-8221-c46227f6be19	c7592195-a988-4074-a994-d08e5f62ab60
6b04c0c3-31eb-4180-a753-2d67b4e540a5	c49fcb47-86fb-4693-836e-ed1dc3318345
1f0f7753-5ef0-4083-8641-173682edf64d	3b9dced1-74c3-4f76-ab51-9a07df38585c
1f0f7753-5ef0-4083-8641-173682edf64d	d75717c5-50b3-487f-ad0f-8c514e213b5f
6b04c0c3-31eb-4180-a753-2d67b4e540a5	857bf84e-fb3f-43ae-b05c-ebdc2259a352
6b04c0c3-31eb-4180-a753-2d67b4e540a5	4507d89d-0b96-4b5d-86d3-69e61ea853e5
6b04c0c3-31eb-4180-a753-2d67b4e540a5	76bf2360-9e8b-47c1-937a-dcc5b78ec0c1
6b04c0c3-31eb-4180-a753-2d67b4e540a5	dd403b4c-068f-481f-8022-16aba8402363
6b04c0c3-31eb-4180-a753-2d67b4e540a5	54b9cfda-1a0a-4804-9bb0-c51c014fabc0
6b04c0c3-31eb-4180-a753-2d67b4e540a5	59efd6b6-b872-428d-9421-598cc3d37fd4
6b04c0c3-31eb-4180-a753-2d67b4e540a5	1dc9d122-7749-4ead-ae84-a22497f4d675
6b04c0c3-31eb-4180-a753-2d67b4e540a5	9b39afcd-e04a-44e9-8074-6e42485a9a92
6b04c0c3-31eb-4180-a753-2d67b4e540a5	74766a90-cae1-4b4d-84cc-969b06d3dbee
6b04c0c3-31eb-4180-a753-2d67b4e540a5	9c10ddad-0f44-4ebd-993e-384b379c7b86
6b04c0c3-31eb-4180-a753-2d67b4e540a5	f3d51c75-5f7e-4b36-a237-c155baf04e61
6b04c0c3-31eb-4180-a753-2d67b4e540a5	f4f660ba-5b65-42cd-8aab-4fc460ed586a
6b04c0c3-31eb-4180-a753-2d67b4e540a5	a791dd88-dd20-4f37-a7b5-faf2bcf236e8
6b04c0c3-31eb-4180-a753-2d67b4e540a5	6c9d8878-fef9-4125-9d63-399b331c737b
6b04c0c3-31eb-4180-a753-2d67b4e540a5	a0ace9e3-7276-4734-9501-7f1213bfb65d
6b04c0c3-31eb-4180-a753-2d67b4e540a5	00d13db5-59d0-46fe-8dfa-3473f82dc34e
6b04c0c3-31eb-4180-a753-2d67b4e540a5	72670e98-92aa-440c-8a37-5765552ae6c1
76bf2360-9e8b-47c1-937a-dcc5b78ec0c1	6c9d8878-fef9-4125-9d63-399b331c737b
76bf2360-9e8b-47c1-937a-dcc5b78ec0c1	72670e98-92aa-440c-8a37-5765552ae6c1
dd403b4c-068f-481f-8022-16aba8402363	a0ace9e3-7276-4734-9501-7f1213bfb65d
38d7dd11-c64f-4786-a4cc-16802299f6b4	9f113135-3b37-4cf1-af3f-e425ffb5e21d
38d7dd11-c64f-4786-a4cc-16802299f6b4	7b8376ce-61ae-487a-95ea-5543b57e4cfb
4e409c41-94ea-4fa9-9c2f-fd7f5049a2f5	d151fa43-d5e8-4a33-a40b-9e743c6fcd74
5bdbbfdb-a86e-4161-be1d-eeb87f472128	9178d8a6-1dfe-45d9-bd93-b510a48397e9
5bdbbfdb-a86e-4161-be1d-eeb87f472128	d151fa43-d5e8-4a33-a40b-9e743c6fcd74
5bdbbfdb-a86e-4161-be1d-eeb87f472128	0f3aef0e-84d6-4c9c-ab98-c4796ecaf842
5bdbbfdb-a86e-4161-be1d-eeb87f472128	c1aa3daf-4831-4762-81d1-4b3ca015308a
5bdbbfdb-a86e-4161-be1d-eeb87f472128	9f113135-3b37-4cf1-af3f-e425ffb5e21d
5bdbbfdb-a86e-4161-be1d-eeb87f472128	5703a051-85e8-4e23-b201-8e88b163ccb5
5bdbbfdb-a86e-4161-be1d-eeb87f472128	7b8376ce-61ae-487a-95ea-5543b57e4cfb
5bdbbfdb-a86e-4161-be1d-eeb87f472128	2c3289bf-5864-48d7-8bae-53fdc7879c35
5bdbbfdb-a86e-4161-be1d-eeb87f472128	38d7dd11-c64f-4786-a4cc-16802299f6b4
5bdbbfdb-a86e-4161-be1d-eeb87f472128	5bb1acb3-0813-4fbb-a02b-0b62428977e0
5bdbbfdb-a86e-4161-be1d-eeb87f472128	d87ebd33-9c7d-44bd-86e3-73b55f2b9281
5bdbbfdb-a86e-4161-be1d-eeb87f472128	a2a5dbd1-194a-4363-a521-feea3f81a1db
5bdbbfdb-a86e-4161-be1d-eeb87f472128	c6ca4f6d-2295-4a3b-9109-904534fc8c80
5bdbbfdb-a86e-4161-be1d-eeb87f472128	4af4e5c5-995d-479f-be5d-2de1eec9c44a
5bdbbfdb-a86e-4161-be1d-eeb87f472128	77fa02bf-af89-41d8-95b4-b0a6e3c09496
5bdbbfdb-a86e-4161-be1d-eeb87f472128	4e409c41-94ea-4fa9-9c2f-fd7f5049a2f5
5bdbbfdb-a86e-4161-be1d-eeb87f472128	ac9d1596-c34b-4daf-8e8f-ac0b1ffce403
5bdbbfdb-a86e-4161-be1d-eeb87f472128	172ecf05-e058-4f34-9d49-fe27633d80a3
889ae762-90c3-4660-a1ab-116b97c91387	d87ebd33-9c7d-44bd-86e3-73b55f2b9281
889ae762-90c3-4660-a1ab-116b97c91387	b80002c9-23f4-466b-bfb8-19bc45888640
889ae762-90c3-4660-a1ab-116b97c91387	b24f38be-3778-4b07-87e1-2af29038a967
889ae762-90c3-4660-a1ab-116b97c91387	d56ee8dc-3e1b-4963-8917-35b8934e352b
889ae762-90c3-4660-a1ab-116b97c91387	f4e266f8-9f0f-4352-8a3b-3c05b91f9dc1
e9715274-3949-45ea-8156-6d5b9de32421	0954fe84-e6c1-4183-85ac-55d4ddcc7521
f4e266f8-9f0f-4352-8a3b-3c05b91f9dc1	af20813b-efa1-4862-8ed5-5bb4092a3112
6b04c0c3-31eb-4180-a753-2d67b4e540a5	d854bde3-932b-45c6-ac3c-dbaaffe3d2ac
\.


--
-- Data for Name: credential; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.credential (id, salt, type, user_id, created_date, user_label, secret_data, credential_data, priority) FROM stdin;
f9c99bce-6ee6-445a-b719-34c78555a026	\N	password	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4	1720654979680	\N	{"value":"awhpfQfeLITOEk42Lcy6xFzQVO4w0eV00qKb3ulLARxR8tV+noTGE1GnDG1lHAYz+KcROck+3AgNS4Ml+kLZTA==","salt":"0B/7/Fc7Bmg8itJmcL5DRw==","additionalParameters":{}}	{"hashIterations":210000,"algorithm":"pbkdf2-sha512","additionalParameters":{}}	10
114269c5-9f26-4941-a229-8e7d04b017da	\N	password	a48915d6-8cda-42bf-bbc9-5f8a2d2d2137	1720659369110	\N	{"value":"4gYJWC51optPB+TlidI10iiZWJgiuJEhhJ6qpEDUb3fO7rU6rccXvNUDQw2tygbF8KoUcwIIh+VE+954uyhu/A==","salt":"LSvP5cdBG5OPkQbv6koOnw==","additionalParameters":{}}	{"hashIterations":210000,"algorithm":"pbkdf2-sha512","additionalParameters":{}}	10
ca2df6ad-489b-4ab6-8059-770db6ecc54f	\N	password	7f3d7400-dc5e-42ab-9278-f902389d55c2	1720659681136	\N	{"value":"lalmpFAW1JuYJ54GIxhcVdb061U2WEg6DsykX8pMyYa2m4H01gjht9l6vuIR7S8s6WtKThLx+JATVBePQ/B06w==","salt":"XhMMxPDcbIfQcs/fgs0K0Q==","additionalParameters":{}}	{"hashIterations":210000,"algorithm":"pbkdf2-sha512","additionalParameters":{}}	10
09b5a8eb-9fe4-4030-8c82-f2cdf07a0e9e	\N	password	76290f27-6ada-4f08-9ea6-82558836fce8	1721143653543	My password	{"value":"vZy4Pz2H8/7H9VCm6rTfWuPTAEs2MJ4frgFThM48qOD88tTdDH0A3rPG0aG2m32HaGKQxN1DUSSsS3Ebn6RISw==","salt":"J0P+6rhFHvmmIRde8YvgQg==","additionalParameters":{}}	{"hashIterations":210000,"algorithm":"pbkdf2-sha512","additionalParameters":{}}	10
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/jpa-changelog-1.0.0.Final.xml	2024-07-10 23:42:50.137495	1	EXECUTED	9:6f1016664e21e16d26517a4418f5e3df	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	4.25.1	\N	\N	0654969304
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/db2-jpa-changelog-1.0.0.Final.xml	2024-07-10 23:42:50.156611	2	MARK_RAN	9:828775b1596a07d1200ba1d49e5e3941	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	4.25.1	\N	\N	0654969304
1.1.0.Beta1	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Beta1.xml	2024-07-10 23:42:50.416243	3	EXECUTED	9:5f090e44a7d595883c1fb61f4b41fd38	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=CLIENT_ATTRIBUTES; createTable tableName=CLIENT_SESSION_NOTE; createTable tableName=APP_NODE_REGISTRATIONS; addColumn table...		\N	4.25.1	\N	\N	0654969304
1.1.0.Final	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Final.xml	2024-07-10 23:42:50.425611	4	EXECUTED	9:c07e577387a3d2c04d1adc9aaad8730e	renameColumn newColumnName=EVENT_TIME, oldColumnName=TIME, tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
1.2.0.Beta1	psilva@redhat.com	META-INF/jpa-changelog-1.2.0.Beta1.xml	2024-07-10 23:42:50.762414	5	EXECUTED	9:b68ce996c655922dbcd2fe6b6ae72686	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	4.25.1	\N	\N	0654969304
1.2.0.Beta1	psilva@redhat.com	META-INF/db2-jpa-changelog-1.2.0.Beta1.xml	2024-07-10 23:42:50.779551	6	MARK_RAN	9:543b5c9989f024fe35c6f6c5a97de88e	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	4.25.1	\N	\N	0654969304
1.2.0.RC1	bburke@redhat.com	META-INF/jpa-changelog-1.2.0.CR1.xml	2024-07-10 23:42:51.205963	7	EXECUTED	9:765afebbe21cf5bbca048e632df38336	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	4.25.1	\N	\N	0654969304
1.2.0.RC1	bburke@redhat.com	META-INF/db2-jpa-changelog-1.2.0.CR1.xml	2024-07-10 23:42:51.217186	8	MARK_RAN	9:db4a145ba11a6fdaefb397f6dbf829a1	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	4.25.1	\N	\N	0654969304
1.2.0.Final	keycloak	META-INF/jpa-changelog-1.2.0.Final.xml	2024-07-10 23:42:51.22428	9	EXECUTED	9:9d05c7be10cdb873f8bcb41bc3a8ab23	update tableName=CLIENT; update tableName=CLIENT; update tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
1.3.0	bburke@redhat.com	META-INF/jpa-changelog-1.3.0.xml	2024-07-10 23:42:51.562331	10	EXECUTED	9:18593702353128d53111f9b1ff0b82b8	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=ADMI...		\N	4.25.1	\N	\N	0654969304
1.4.0	bburke@redhat.com	META-INF/jpa-changelog-1.4.0.xml	2024-07-10 23:42:51.722454	11	EXECUTED	9:6122efe5f090e41a85c0f1c9e52cbb62	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.4.0	bburke@redhat.com	META-INF/db2-jpa-changelog-1.4.0.xml	2024-07-10 23:42:51.733592	12	MARK_RAN	9:e1ff28bf7568451453f844c5d54bb0b5	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.5.0	bburke@redhat.com	META-INF/jpa-changelog-1.5.0.xml	2024-07-10 23:42:51.760427	13	EXECUTED	9:7af32cd8957fbc069f796b61217483fd	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.6.1_from15	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.814505	14	EXECUTED	9:6005e15e84714cd83226bf7879f54190	addColumn tableName=REALM; addColumn tableName=KEYCLOAK_ROLE; addColumn tableName=CLIENT; createTable tableName=OFFLINE_USER_SESSION; createTable tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_US_SES_PK2, tableName=...		\N	4.25.1	\N	\N	0654969304
1.6.1_from16-pre	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.817727	15	MARK_RAN	9:bf656f5a2b055d07f314431cae76f06c	delete tableName=OFFLINE_CLIENT_SESSION; delete tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
1.6.1_from16	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.820926	16	MARK_RAN	9:f8dadc9284440469dcf71e25ca6ab99b	dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_US_SES_PK, tableName=OFFLINE_USER_SESSION; dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_CL_SES_PK, tableName=OFFLINE_CLIENT_SESSION; addColumn tableName=OFFLINE_USER_SESSION; update tableName=OF...		\N	4.25.1	\N	\N	0654969304
1.6.1	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.831141	17	EXECUTED	9:d41d8cd98f00b204e9800998ecf8427e	empty		\N	4.25.1	\N	\N	0654969304
1.7.0	bburke@redhat.com	META-INF/jpa-changelog-1.7.0.xml	2024-07-10 23:42:52.007747	18	EXECUTED	9:3368ff0be4c2855ee2dd9ca813b38d8e	createTable tableName=KEYCLOAK_GROUP; createTable tableName=GROUP_ROLE_MAPPING; createTable tableName=GROUP_ATTRIBUTE; createTable tableName=USER_GROUP_MEMBERSHIP; createTable tableName=REALM_DEFAULT_GROUPS; addColumn tableName=IDENTITY_PROVIDER; ...		\N	4.25.1	\N	\N	0654969304
1.8.0	mposolda@redhat.com	META-INF/jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.243452	19	EXECUTED	9:8ac2fb5dd030b24c0570a763ed75ed20	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	4.25.1	\N	\N	0654969304
1.8.0-2	keycloak	META-INF/jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.269569	20	EXECUTED	9:f91ddca9b19743db60e3057679810e6c	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	4.25.1	\N	\N	0654969304
24.0.0-9758-2	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.658752	119	EXECUTED	9:bf0fdee10afdf597a987adbf291db7b2	customChange		\N	4.25.1	\N	\N	0654969304
1.8.0	mposolda@redhat.com	META-INF/db2-jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.273842	21	MARK_RAN	9:831e82914316dc8a57dc09d755f23c51	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	4.25.1	\N	\N	0654969304
1.8.0-2	keycloak	META-INF/db2-jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.280893	22	MARK_RAN	9:f91ddca9b19743db60e3057679810e6c	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	4.25.1	\N	\N	0654969304
1.9.0	mposolda@redhat.com	META-INF/jpa-changelog-1.9.0.xml	2024-07-10 23:42:52.317185	23	EXECUTED	9:bc3d0f9e823a69dc21e23e94c7a94bb1	update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=REALM; update tableName=REALM; customChange; dr...		\N	4.25.1	\N	\N	0654969304
1.9.1	keycloak	META-INF/jpa-changelog-1.9.1.xml	2024-07-10 23:42:52.325334	24	EXECUTED	9:c9999da42f543575ab790e76439a2679	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=PUBLIC_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	4.25.1	\N	\N	0654969304
1.9.1	keycloak	META-INF/db2-jpa-changelog-1.9.1.xml	2024-07-10 23:42:52.327705	25	MARK_RAN	9:0d6c65c6f58732d81569e77b10ba301d	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	4.25.1	\N	\N	0654969304
1.9.2	keycloak	META-INF/jpa-changelog-1.9.2.xml	2024-07-10 23:42:52.399749	26	EXECUTED	9:fc576660fc016ae53d2d4778d84d86d0	createIndex indexName=IDX_USER_EMAIL, tableName=USER_ENTITY; createIndex indexName=IDX_USER_ROLE_MAPPING, tableName=USER_ROLE_MAPPING; createIndex indexName=IDX_USER_GROUP_MAPPING, tableName=USER_GROUP_MEMBERSHIP; createIndex indexName=IDX_USER_CO...		\N	4.25.1	\N	\N	0654969304
authz-2.0.0	psilva@redhat.com	META-INF/jpa-changelog-authz-2.0.0.xml	2024-07-10 23:42:52.633093	27	EXECUTED	9:43ed6b0da89ff77206289e87eaa9c024	createTable tableName=RESOURCE_SERVER; addPrimaryKey constraintName=CONSTRAINT_FARS, tableName=RESOURCE_SERVER; addUniqueConstraint constraintName=UK_AU8TT6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER; createTable tableName=RESOURCE_SERVER_RESOU...		\N	4.25.1	\N	\N	0654969304
authz-2.5.1	psilva@redhat.com	META-INF/jpa-changelog-authz-2.5.1.xml	2024-07-10 23:42:52.645142	28	EXECUTED	9:44bae577f551b3738740281eceb4ea70	update tableName=RESOURCE_SERVER_POLICY		\N	4.25.1	\N	\N	0654969304
2.1.0-KEYCLOAK-5461	bburke@redhat.com	META-INF/jpa-changelog-2.1.0.xml	2024-07-10 23:42:52.795264	29	EXECUTED	9:bd88e1f833df0420b01e114533aee5e8	createTable tableName=BROKER_LINK; createTable tableName=FED_USER_ATTRIBUTE; createTable tableName=FED_USER_CONSENT; createTable tableName=FED_USER_CONSENT_ROLE; createTable tableName=FED_USER_CONSENT_PROT_MAPPER; createTable tableName=FED_USER_CR...		\N	4.25.1	\N	\N	0654969304
2.2.0	bburke@redhat.com	META-INF/jpa-changelog-2.2.0.xml	2024-07-10 23:42:52.826773	30	EXECUTED	9:a7022af5267f019d020edfe316ef4371	addColumn tableName=ADMIN_EVENT_ENTITY; createTable tableName=CREDENTIAL_ATTRIBUTE; createTable tableName=FED_CREDENTIAL_ATTRIBUTE; modifyDataType columnName=VALUE, tableName=CREDENTIAL; addForeignKeyConstraint baseTableName=FED_CREDENTIAL_ATTRIBU...		\N	4.25.1	\N	\N	0654969304
2.3.0	bburke@redhat.com	META-INF/jpa-changelog-2.3.0.xml	2024-07-10 23:42:52.85627	31	EXECUTED	9:fc155c394040654d6a79227e56f5e25a	createTable tableName=FEDERATED_USER; addPrimaryKey constraintName=CONSTR_FEDERATED_USER, tableName=FEDERATED_USER; dropDefaultValue columnName=TOTP, tableName=USER_ENTITY; dropColumn columnName=TOTP, tableName=USER_ENTITY; addColumn tableName=IDE...		\N	4.25.1	\N	\N	0654969304
2.4.0	bburke@redhat.com	META-INF/jpa-changelog-2.4.0.xml	2024-07-10 23:42:52.863165	32	EXECUTED	9:eac4ffb2a14795e5dc7b426063e54d88	customChange		\N	4.25.1	\N	\N	0654969304
2.5.0	bburke@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.870924	33	EXECUTED	9:54937c05672568c4c64fc9524c1e9462	customChange; modifyDataType columnName=USER_ID, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
2.5.0-unicode-oracle	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.873463	34	MARK_RAN	9:3a32bace77c84d7678d035a7f5a8084e	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	4.25.1	\N	\N	0654969304
2.5.0-unicode-other-dbs	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.922201	35	EXECUTED	9:33d72168746f81f98ae3a1e8e0ca3554	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	4.25.1	\N	\N	0654969304
2.5.0-duplicate-email-support	slawomir@dabek.name	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.928458	36	EXECUTED	9:61b6d3d7a4c0e0024b0c839da283da0c	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
2.5.0-unique-group-names	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.937732	37	EXECUTED	9:8dcac7bdf7378e7d823cdfddebf72fda	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
2.5.1	bburke@redhat.com	META-INF/jpa-changelog-2.5.1.xml	2024-07-10 23:42:52.943241	38	EXECUTED	9:a2b870802540cb3faa72098db5388af3	addColumn tableName=FED_USER_CONSENT		\N	4.25.1	\N	\N	0654969304
3.0.0	bburke@redhat.com	META-INF/jpa-changelog-3.0.0.xml	2024-07-10 23:42:52.948102	39	EXECUTED	9:132a67499ba24bcc54fb5cbdcfe7e4c0	addColumn tableName=IDENTITY_PROVIDER		\N	4.25.1	\N	\N	0654969304
3.2.0-fix	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.950108	40	MARK_RAN	9:938f894c032f5430f2b0fafb1a243462	addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS		\N	4.25.1	\N	\N	0654969304
3.2.0-fix-with-keycloak-5416	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.952434	41	MARK_RAN	9:845c332ff1874dc5d35974b0babf3006	dropIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS; addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS; createIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS		\N	4.25.1	\N	\N	0654969304
3.2.0-fix-offline-sessions	hmlnarik	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.959399	42	EXECUTED	9:fc86359c079781adc577c5a217e4d04c	customChange		\N	4.25.1	\N	\N	0654969304
3.2.0-fixed	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:53.244705	43	EXECUTED	9:59a64800e3c0d09b825f8a3b444fa8f4	addColumn tableName=REALM; dropPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_PK2, tableName=OFFLINE_CLIENT_SESSION; dropColumn columnName=CLIENT_SESSION_ID, tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_P...		\N	4.25.1	\N	\N	0654969304
3.3.0	keycloak	META-INF/jpa-changelog-3.3.0.xml	2024-07-10 23:42:53.252083	44	EXECUTED	9:d48d6da5c6ccf667807f633fe489ce88	addColumn tableName=USER_ENTITY		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part1	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.258022	45	EXECUTED	9:dde36f7973e80d71fceee683bc5d2951	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_RESOURCE; addColumn tableName=RESOURCE_SERVER_SCOPE		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part2-KEYCLOAK-6095	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.265429	46	EXECUTED	9:b855e9b0a406b34fa323235a0cf4f640	customChange		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.26752	47	MARK_RAN	9:51abbacd7b416c50c4421a8cabf7927e	dropIndex indexName=IDX_RES_SERV_POL_RES_SERV, tableName=RESOURCE_SERVER_POLICY; dropIndex indexName=IDX_RES_SRV_RES_RES_SRV, tableName=RESOURCE_SERVER_RESOURCE; dropIndex indexName=IDX_RES_SRV_SCOPE_RES_SRV, tableName=RESOURCE_SERVER_SCOPE		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed-nodropindex	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.353602	48	EXECUTED	9:bdc99e567b3398bac83263d375aad143	addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_POLICY; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_RESOURCE; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, ...		\N	4.25.1	\N	\N	0654969304
authn-3.4.0.CR1-refresh-token-max-reuse	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.361863	49	EXECUTED	9:d198654156881c46bfba39abd7769e69	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
3.4.0	keycloak	META-INF/jpa-changelog-3.4.0.xml	2024-07-10 23:42:53.477374	50	EXECUTED	9:cfdd8736332ccdd72c5256ccb42335db	addPrimaryKey constraintName=CONSTRAINT_REALM_DEFAULT_ROLES, tableName=REALM_DEFAULT_ROLES; addPrimaryKey constraintName=CONSTRAINT_COMPOSITE_ROLE, tableName=COMPOSITE_ROLE; addPrimaryKey constraintName=CONSTR_REALM_DEFAULT_GROUPS, tableName=REALM...		\N	4.25.1	\N	\N	0654969304
3.4.0-KEYCLOAK-5230	hmlnarik@redhat.com	META-INF/jpa-changelog-3.4.0.xml	2024-07-10 23:42:53.543976	51	EXECUTED	9:7c84de3d9bd84d7f077607c1a4dcb714	createIndex indexName=IDX_FU_ATTRIBUTE, tableName=FED_USER_ATTRIBUTE; createIndex indexName=IDX_FU_CONSENT, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CONSENT_RU, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CREDENTIAL, t...		\N	4.25.1	\N	\N	0654969304
3.4.1	psilva@redhat.com	META-INF/jpa-changelog-3.4.1.xml	2024-07-10 23:42:53.548333	52	EXECUTED	9:5a6bb36cbefb6a9d6928452c0852af2d	modifyDataType columnName=VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
3.4.2	keycloak	META-INF/jpa-changelog-3.4.2.xml	2024-07-10 23:42:53.551911	53	EXECUTED	9:8f23e334dbc59f82e0a328373ca6ced0	update tableName=REALM		\N	4.25.1	\N	\N	0654969304
3.4.2-KEYCLOAK-5172	mkanis@redhat.com	META-INF/jpa-changelog-3.4.2.xml	2024-07-10 23:42:53.556029	54	EXECUTED	9:9156214268f09d970cdf0e1564d866af	update tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-6335	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.567132	55	EXECUTED	9:db806613b1ed154826c02610b7dbdf74	createTable tableName=CLIENT_AUTH_FLOW_BINDINGS; addPrimaryKey constraintName=C_CLI_FLOW_BIND, tableName=CLIENT_AUTH_FLOW_BINDINGS		\N	4.25.1	\N	\N	0654969304
4.0.0-CLEANUP-UNUSED-TABLE	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.57746	56	EXECUTED	9:229a041fb72d5beac76bb94a5fa709de	dropTable tableName=CLIENT_IDENTITY_PROV_MAPPING		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-6228	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.614704	57	EXECUTED	9:079899dade9c1e683f26b2aa9ca6ff04	dropUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHOGM8UEWRT, tableName=USER_CONSENT; dropNotNullConstraint columnName=CLIENT_ID, tableName=USER_CONSENT; addColumn tableName=USER_CONSENT; addUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHO...		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-5579-fixed	mposolda@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:54.020307	58	EXECUTED	9:139b79bcbbfe903bb1c2d2a4dbf001d9	dropForeignKeyConstraint baseTableName=CLIENT_TEMPLATE_ATTRIBUTES, constraintName=FK_CL_TEMPL_ATTR_TEMPL; renameTable newTableName=CLIENT_SCOPE_ATTRIBUTES, oldTableName=CLIENT_TEMPLATE_ATTRIBUTES; renameColumn newColumnName=SCOPE_ID, oldColumnName...		\N	4.25.1	\N	\N	0654969304
authz-4.0.0.CR1	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.CR1.xml	2024-07-10 23:42:54.162355	59	EXECUTED	9:b55738ad889860c625ba2bf483495a04	createTable tableName=RESOURCE_SERVER_PERM_TICKET; addPrimaryKey constraintName=CONSTRAINT_FAPMT, tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRHO213XCX4WNKOG82SSPMT...		\N	4.25.1	\N	\N	0654969304
authz-4.0.0.Beta3	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.Beta3.xml	2024-07-10 23:42:54.179609	60	EXECUTED	9:e0057eac39aa8fc8e09ac6cfa4ae15fe	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRPO2128CX4WNKOG82SSRFY, referencedTableName=RESOURCE_SERVER_POLICY		\N	4.25.1	\N	\N	0654969304
authz-4.2.0.Final	mhajas@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2024-07-10 23:42:54.200768	61	EXECUTED	9:42a33806f3a0443fe0e7feeec821326c	createTable tableName=RESOURCE_URIS; addForeignKeyConstraint baseTableName=RESOURCE_URIS, constraintName=FK_RESOURCE_SERVER_URIS, referencedTableName=RESOURCE_SERVER_RESOURCE; customChange; dropColumn columnName=URI, tableName=RESOURCE_SERVER_RESO...		\N	4.25.1	\N	\N	0654969304
authz-4.2.0.Final-KEYCLOAK-9944	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2024-07-10 23:42:54.215337	62	EXECUTED	9:9968206fca46eecc1f51db9c024bfe56	addPrimaryKey constraintName=CONSTRAINT_RESOUR_URIS_PK, tableName=RESOURCE_URIS		\N	4.25.1	\N	\N	0654969304
4.2.0-KEYCLOAK-6313	wadahiro@gmail.com	META-INF/jpa-changelog-4.2.0.xml	2024-07-10 23:42:54.22302	63	EXECUTED	9:92143a6daea0a3f3b8f598c97ce55c3d	addColumn tableName=REQUIRED_ACTION_PROVIDER		\N	4.25.1	\N	\N	0654969304
4.3.0-KEYCLOAK-7984	wadahiro@gmail.com	META-INF/jpa-changelog-4.3.0.xml	2024-07-10 23:42:54.231767	64	EXECUTED	9:82bab26a27195d889fb0429003b18f40	update tableName=REQUIRED_ACTION_PROVIDER		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-7950	psilva@redhat.com	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.23731	65	EXECUTED	9:e590c88ddc0b38b0ae4249bbfcb5abc3	update tableName=RESOURCE_SERVER_RESOURCE		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-8377	keycloak	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.294076	66	EXECUTED	9:5c1f475536118dbdc38d5d7977950cc0	createTable tableName=ROLE_ATTRIBUTE; addPrimaryKey constraintName=CONSTRAINT_ROLE_ATTRIBUTE_PK, tableName=ROLE_ATTRIBUTE; addForeignKeyConstraint baseTableName=ROLE_ATTRIBUTE, constraintName=FK_ROLE_ATTRIBUTE_ID, referencedTableName=KEYCLOAK_ROLE...		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-8555	gideonray@gmail.com	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.309051	67	EXECUTED	9:e7c9f5f9c4d67ccbbcc215440c718a17	createIndex indexName=IDX_COMPONENT_PROVIDER_TYPE, tableName=COMPONENT		\N	4.25.1	\N	\N	0654969304
4.7.0-KEYCLOAK-1267	sguilhen@redhat.com	META-INF/jpa-changelog-4.7.0.xml	2024-07-10 23:42:54.317403	68	EXECUTED	9:88e0bfdda924690d6f4e430c53447dd5	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
4.7.0-KEYCLOAK-7275	keycloak	META-INF/jpa-changelog-4.7.0.xml	2024-07-10 23:42:54.343453	69	EXECUTED	9:f53177f137e1c46b6a88c59ec1cb5218	renameColumn newColumnName=CREATED_ON, oldColumnName=LAST_SESSION_REFRESH, tableName=OFFLINE_USER_SESSION; addNotNullConstraint columnName=CREATED_ON, tableName=OFFLINE_USER_SESSION; addColumn tableName=OFFLINE_USER_SESSION; customChange; createIn...		\N	4.25.1	\N	\N	0654969304
4.8.0-KEYCLOAK-8835	sguilhen@redhat.com	META-INF/jpa-changelog-4.8.0.xml	2024-07-10 23:42:54.354756	70	EXECUTED	9:a74d33da4dc42a37ec27121580d1459f	addNotNullConstraint columnName=SSO_MAX_LIFESPAN_REMEMBER_ME, tableName=REALM; addNotNullConstraint columnName=SSO_IDLE_TIMEOUT_REMEMBER_ME, tableName=REALM		\N	4.25.1	\N	\N	0654969304
authz-7.0.0-KEYCLOAK-10443	psilva@redhat.com	META-INF/jpa-changelog-authz-7.0.0.xml	2024-07-10 23:42:54.361354	71	EXECUTED	9:fd4ade7b90c3b67fae0bfcfcb42dfb5f	addColumn tableName=RESOURCE_SERVER		\N	4.25.1	\N	\N	0654969304
8.0.0-adding-credential-columns	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.37	72	EXECUTED	9:aa072ad090bbba210d8f18781b8cebf4	addColumn tableName=CREDENTIAL; addColumn tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-updating-credential-data-not-oracle-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.384233	73	EXECUTED	9:1ae6be29bab7c2aa376f6983b932be37	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-updating-credential-data-oracle-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.388733	74	MARK_RAN	9:14706f286953fc9a25286dbd8fb30d97	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-credential-cleanup-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.426784	75	EXECUTED	9:2b9cc12779be32c5b40e2e67711a218b	dropDefaultValue columnName=COUNTER, tableName=CREDENTIAL; dropDefaultValue columnName=DIGITS, tableName=CREDENTIAL; dropDefaultValue columnName=PERIOD, tableName=CREDENTIAL; dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; dropColumn ...		\N	4.25.1	\N	\N	0654969304
8.0.0-resource-tag-support	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.440353	76	EXECUTED	9:91fa186ce7a5af127a2d7a91ee083cc5	addColumn tableName=MIGRATION_MODEL; createIndex indexName=IDX_UPDATE_TIME, tableName=MIGRATION_MODEL		\N	4.25.1	\N	\N	0654969304
9.0.0-always-display-client	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.447429	77	EXECUTED	9:6335e5c94e83a2639ccd68dd24e2e5ad	addColumn tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
9.0.0-drop-constraints-for-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.451032	78	MARK_RAN	9:6bdb5658951e028bfe16fa0a8228b530	dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5PMT, tableName=RESOURCE_SERVER_PERM_TICKET; dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER_RESOURCE; dropPrimaryKey constraintName=CONSTRAINT_O...		\N	4.25.1	\N	\N	0654969304
9.0.0-increase-column-size-federated-fk	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.492482	79	EXECUTED	9:d5bc15a64117ccad481ce8792d4c608f	modifyDataType columnName=CLIENT_ID, tableName=FED_USER_CONSENT; modifyDataType columnName=CLIENT_REALM_CONSTRAINT, tableName=KEYCLOAK_ROLE; modifyDataType columnName=OWNER, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=CLIENT_ID, ta...		\N	4.25.1	\N	\N	0654969304
9.0.0-recreate-constraints-after-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.496219	80	MARK_RAN	9:077cba51999515f4d3e7ad5619ab592c	addNotNullConstraint columnName=CLIENT_ID, tableName=OFFLINE_CLIENT_SESSION; addNotNullConstraint columnName=OWNER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNullConstraint columnName=REQUESTER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNull...		\N	4.25.1	\N	\N	0654969304
9.0.1-add-index-to-client.client_id	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.509934	81	EXECUTED	9:be969f08a163bf47c6b9e9ead8ac2afb	createIndex indexName=IDX_CLIENT_ID, tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-drop-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.513367	82	MARK_RAN	9:6d3bb4408ba5a72f39bd8a0b301ec6e3	dropUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-add-not-null-constraint	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.523944	83	EXECUTED	9:966bda61e46bebf3cc39518fbed52fa7	addNotNullConstraint columnName=PARENT_GROUP, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-recreate-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.527109	84	MARK_RAN	9:8dcac7bdf7378e7d823cdfddebf72fda	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-add-index-to-events	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.539626	85	EXECUTED	9:7d93d602352a30c0c317e6a609b56599	createIndex indexName=IDX_EVENT_TIME, tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
map-remove-ri	keycloak	META-INF/jpa-changelog-11.0.0.xml	2024-07-10 23:42:54.546342	86	EXECUTED	9:71c5969e6cdd8d7b6f47cebc86d37627	dropForeignKeyConstraint baseTableName=REALM, constraintName=FK_TRAF444KK6QRKMS7N56AIWQ5Y; dropForeignKeyConstraint baseTableName=KEYCLOAK_ROLE, constraintName=FK_KJHO5LE2C0RAL09FL8CM9WFW9		\N	4.25.1	\N	\N	0654969304
map-remove-ri	keycloak	META-INF/jpa-changelog-12.0.0.xml	2024-07-10 23:42:54.555574	87	EXECUTED	9:a9ba7d47f065f041b7da856a81762021	dropForeignKeyConstraint baseTableName=REALM_DEFAULT_GROUPS, constraintName=FK_DEF_GROUPS_GROUP; dropForeignKeyConstraint baseTableName=REALM_DEFAULT_ROLES, constraintName=FK_H4WPD7W4HSOOLNI3H0SW7BTJE; dropForeignKeyConstraint baseTableName=CLIENT...		\N	4.25.1	\N	\N	0654969304
12.1.0-add-realm-localization-table	keycloak	META-INF/jpa-changelog-12.0.0.xml	2024-07-10 23:42:54.576967	88	EXECUTED	9:fffabce2bc01e1a8f5110d5278500065	createTable tableName=REALM_LOCALIZATIONS; addPrimaryKey tableName=REALM_LOCALIZATIONS		\N	4.25.1	\N	\N	0654969304
default-roles	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.61746	89	EXECUTED	9:fa8a5b5445e3857f4b010bafb5009957	addColumn tableName=REALM; customChange		\N	4.25.1	\N	\N	0654969304
default-roles-cleanup	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.692185	90	EXECUTED	9:67ac3241df9a8582d591c5ed87125f39	dropTable tableName=REALM_DEFAULT_ROLES; dropTable tableName=CLIENT_DEFAULT_ROLES		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-16844	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.71104	91	EXECUTED	9:ad1194d66c937e3ffc82386c050ba089	createIndex indexName=IDX_OFFLINE_USS_PRELOAD, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
map-remove-ri-13.0.0	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.719296	92	EXECUTED	9:d9be619d94af5a2f5d07b9f003543b91	dropForeignKeyConstraint baseTableName=DEFAULT_CLIENT_SCOPE, constraintName=FK_R_DEF_CLI_SCOPE_SCOPE; dropForeignKeyConstraint baseTableName=CLIENT_SCOPE_CLIENT, constraintName=FK_C_CLI_SCOPE_SCOPE; dropForeignKeyConstraint baseTableName=CLIENT_SC...		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-17992-drop-constraints	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.722417	93	MARK_RAN	9:544d201116a0fcc5a5da0925fbbc3bde	dropPrimaryKey constraintName=C_CLI_SCOPE_BIND, tableName=CLIENT_SCOPE_CLIENT; dropIndex indexName=IDX_CLSCOPE_CL, tableName=CLIENT_SCOPE_CLIENT; dropIndex indexName=IDX_CL_CLSCOPE, tableName=CLIENT_SCOPE_CLIENT		\N	4.25.1	\N	\N	0654969304
13.0.0-increase-column-size-federated	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.756742	94	EXECUTED	9:43c0c1055b6761b4b3e89de76d612ccf	modifyDataType columnName=CLIENT_ID, tableName=CLIENT_SCOPE_CLIENT; modifyDataType columnName=SCOPE_ID, tableName=CLIENT_SCOPE_CLIENT		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-17992-recreate-constraints	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.760308	95	MARK_RAN	9:8bd711fd0330f4fe980494ca43ab1139	addNotNullConstraint columnName=CLIENT_ID, tableName=CLIENT_SCOPE_CLIENT; addNotNullConstraint columnName=SCOPE_ID, tableName=CLIENT_SCOPE_CLIENT; addPrimaryKey constraintName=C_CLI_SCOPE_BIND, tableName=CLIENT_SCOPE_CLIENT; createIndex indexName=...		\N	4.25.1	\N	\N	0654969304
json-string-accomodation-fixed	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.838755	96	EXECUTED	9:e07d2bc0970c348bb06fb63b1f82ddbf	addColumn tableName=REALM_ATTRIBUTE; update tableName=REALM_ATTRIBUTE; dropColumn columnName=VALUE, tableName=REALM_ATTRIBUTE; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=REALM_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-11019	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.896035	97	EXECUTED	9:24fb8611e97f29989bea412aa38d12b7	createIndex indexName=IDX_OFFLINE_CSS_PRELOAD, tableName=OFFLINE_CLIENT_SESSION; createIndex indexName=IDX_OFFLINE_USS_BY_USER, tableName=OFFLINE_USER_SESSION; createIndex indexName=IDX_OFFLINE_USS_BY_USERSESS, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.898701	98	MARK_RAN	9:259f89014ce2506ee84740cbf7163aa7	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-revert	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.918684	99	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-supported-dbs	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.930654	100	EXECUTED	9:60ca84a0f8c94ec8c3504a5a3bc88ee8	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-unsupported-dbs	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.934431	101	MARK_RAN	9:d3d977031d431db16e2c181ce49d73e9	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
KEYCLOAK-17267-add-index-to-user-attributes	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.952961	102	EXECUTED	9:0b305d8d1277f3a89a0a53a659ad274c	createIndex indexName=IDX_USER_ATTRIBUTE_NAME, tableName=USER_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
KEYCLOAK-18146-add-saml-art-binding-identifier	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.960903	103	EXECUTED	9:2c374ad2cdfe20e2905a84c8fac48460	customChange		\N	4.25.1	\N	\N	0654969304
15.0.0-KEYCLOAK-18467	keycloak	META-INF/jpa-changelog-15.0.0.xml	2024-07-10 23:42:54.97174	104	EXECUTED	9:47a760639ac597360a8219f5b768b4de	addColumn tableName=REALM_LOCALIZATIONS; update tableName=REALM_LOCALIZATIONS; dropColumn columnName=TEXTS, tableName=REALM_LOCALIZATIONS; renameColumn newColumnName=TEXTS, oldColumnName=TEXTS_NEW, tableName=REALM_LOCALIZATIONS; addNotNullConstrai...		\N	4.25.1	\N	\N	0654969304
17.0.0-9562	keycloak	META-INF/jpa-changelog-17.0.0.xml	2024-07-10 23:42:54.98224	105	EXECUTED	9:a6272f0576727dd8cad2522335f5d99e	createIndex indexName=IDX_USER_SERVICE_ACCOUNT, tableName=USER_ENTITY		\N	4.25.1	\N	\N	0654969304
18.0.0-10625-IDX_ADMIN_EVENT_TIME	keycloak	META-INF/jpa-changelog-18.0.0.xml	2024-07-10 23:42:54.997442	106	EXECUTED	9:015479dbd691d9cc8669282f4828c41d	createIndex indexName=IDX_ADMIN_EVENT_TIME, tableName=ADMIN_EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
19.0.0-10135	keycloak	META-INF/jpa-changelog-19.0.0.xml	2024-07-10 23:42:55.005062	107	EXECUTED	9:9518e495fdd22f78ad6425cc30630221	customChange		\N	4.25.1	\N	\N	0654969304
20.0.0-12964-supported-dbs	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.017665	108	EXECUTED	9:e5f243877199fd96bcc842f27a1656ac	createIndex indexName=IDX_GROUP_ATT_BY_NAME_VALUE, tableName=GROUP_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
20.0.0-12964-unsupported-dbs	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.020086	109	MARK_RAN	9:1a6fcaa85e20bdeae0a9ce49b41946a5	createIndex indexName=IDX_GROUP_ATT_BY_NAME_VALUE, tableName=GROUP_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
client-attributes-string-accomodation-fixed	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.039726	110	EXECUTED	9:3f332e13e90739ed0c35b0b25b7822ca	addColumn tableName=CLIENT_ATTRIBUTES; update tableName=CLIENT_ATTRIBUTES; dropColumn columnName=VALUE, tableName=CLIENT_ATTRIBUTES; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
21.0.2-17277	keycloak	META-INF/jpa-changelog-21.0.2.xml	2024-07-10 23:42:55.045973	111	EXECUTED	9:7ee1f7a3fb8f5588f171fb9a6ab623c0	customChange		\N	4.25.1	\N	\N	0654969304
21.1.0-19404	keycloak	META-INF/jpa-changelog-21.1.0.xml	2024-07-10 23:42:55.270408	112	EXECUTED	9:3d7e830b52f33676b9d64f7f2b2ea634	modifyDataType columnName=DECISION_STRATEGY, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=LOGIC, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=POLICY_ENFORCE_MODE, tableName=RESOURCE_SERVER		\N	4.25.1	\N	\N	0654969304
21.1.0-19404-2	keycloak	META-INF/jpa-changelog-21.1.0.xml	2024-07-10 23:42:55.281114	113	MARK_RAN	9:627d032e3ef2c06c0e1f73d2ae25c26c	addColumn tableName=RESOURCE_SERVER_POLICY; update tableName=RESOURCE_SERVER_POLICY; dropColumn columnName=DECISION_STRATEGY, tableName=RESOURCE_SERVER_POLICY; renameColumn newColumnName=DECISION_STRATEGY, oldColumnName=DECISION_STRATEGY_NEW, tabl...		\N	4.25.1	\N	\N	0654969304
22.0.0-17484-updated	keycloak	META-INF/jpa-changelog-22.0.0.xml	2024-07-10 23:42:55.292055	114	EXECUTED	9:90af0bfd30cafc17b9f4d6eccd92b8b3	customChange		\N	4.25.1	\N	\N	0654969304
22.0.5-24031	keycloak	META-INF/jpa-changelog-22.0.0.xml	2024-07-10 23:42:55.297618	115	MARK_RAN	9:a60d2d7b315ec2d3eba9e2f145f9df28	customChange		\N	4.25.1	\N	\N	0654969304
23.0.0-12062	keycloak	META-INF/jpa-changelog-23.0.0.xml	2024-07-10 23:42:55.517095	116	EXECUTED	9:2168fbe728fec46ae9baf15bf80927b8	addColumn tableName=COMPONENT_CONFIG; update tableName=COMPONENT_CONFIG; dropColumn columnName=VALUE, tableName=COMPONENT_CONFIG; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=COMPONENT_CONFIG		\N	4.25.1	\N	\N	0654969304
23.0.0-17258	keycloak	META-INF/jpa-changelog-23.0.0.xml	2024-07-10 23:42:55.533685	117	EXECUTED	9:36506d679a83bbfda85a27ea1864dca8	addColumn tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
24.0.0-9758	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.650221	118	EXECUTED	9:502c557a5189f600f0f445a9b49ebbce	addColumn tableName=USER_ATTRIBUTE; addColumn tableName=FED_USER_ATTRIBUTE; createIndex indexName=USER_ATTR_LONG_VALUES, tableName=USER_ATTRIBUTE; createIndex indexName=FED_USER_ATTR_LONG_VALUES, tableName=FED_USER_ATTRIBUTE; createIndex indexName...		\N	4.25.1	\N	\N	0654969304
24.0.0-26618-drop-index-if-present	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.664885	120	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.0-26618-reindex	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.704023	121	EXECUTED	9:08707c0f0db1cef6b352db03a60edc7f	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.2-27228	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.713875	122	EXECUTED	9:eaee11f6b8aa25d2cc6a84fb86fc6238	customChange		\N	4.25.1	\N	\N	0654969304
24.0.2-27967-drop-index-if-present	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.720165	123	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.2-27967-reindex	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.724372	124	MARK_RAN	9:d3d977031d431db16e2c181ce49d73e9	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/jpa-changelog-1.0.0.Final.xml	2024-07-10 23:42:50.137495	1	EXECUTED	9:6f1016664e21e16d26517a4418f5e3df	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	4.25.1	\N	\N	0654969304
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/db2-jpa-changelog-1.0.0.Final.xml	2024-07-10 23:42:50.156611	2	MARK_RAN	9:828775b1596a07d1200ba1d49e5e3941	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	4.25.1	\N	\N	0654969304
1.1.0.Beta1	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Beta1.xml	2024-07-10 23:42:50.416243	3	EXECUTED	9:5f090e44a7d595883c1fb61f4b41fd38	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=CLIENT_ATTRIBUTES; createTable tableName=CLIENT_SESSION_NOTE; createTable tableName=APP_NODE_REGISTRATIONS; addColumn table...		\N	4.25.1	\N	\N	0654969304
1.1.0.Final	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Final.xml	2024-07-10 23:42:50.425611	4	EXECUTED	9:c07e577387a3d2c04d1adc9aaad8730e	renameColumn newColumnName=EVENT_TIME, oldColumnName=TIME, tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
1.2.0.Beta1	psilva@redhat.com	META-INF/jpa-changelog-1.2.0.Beta1.xml	2024-07-10 23:42:50.762414	5	EXECUTED	9:b68ce996c655922dbcd2fe6b6ae72686	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	4.25.1	\N	\N	0654969304
1.2.0.Beta1	psilva@redhat.com	META-INF/db2-jpa-changelog-1.2.0.Beta1.xml	2024-07-10 23:42:50.779551	6	MARK_RAN	9:543b5c9989f024fe35c6f6c5a97de88e	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	4.25.1	\N	\N	0654969304
1.2.0.RC1	bburke@redhat.com	META-INF/jpa-changelog-1.2.0.CR1.xml	2024-07-10 23:42:51.205963	7	EXECUTED	9:765afebbe21cf5bbca048e632df38336	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	4.25.1	\N	\N	0654969304
1.2.0.RC1	bburke@redhat.com	META-INF/db2-jpa-changelog-1.2.0.CR1.xml	2024-07-10 23:42:51.217186	8	MARK_RAN	9:db4a145ba11a6fdaefb397f6dbf829a1	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	4.25.1	\N	\N	0654969304
1.2.0.Final	keycloak	META-INF/jpa-changelog-1.2.0.Final.xml	2024-07-10 23:42:51.22428	9	EXECUTED	9:9d05c7be10cdb873f8bcb41bc3a8ab23	update tableName=CLIENT; update tableName=CLIENT; update tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
1.3.0	bburke@redhat.com	META-INF/jpa-changelog-1.3.0.xml	2024-07-10 23:42:51.562331	10	EXECUTED	9:18593702353128d53111f9b1ff0b82b8	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=ADMI...		\N	4.25.1	\N	\N	0654969304
1.4.0	bburke@redhat.com	META-INF/jpa-changelog-1.4.0.xml	2024-07-10 23:42:51.722454	11	EXECUTED	9:6122efe5f090e41a85c0f1c9e52cbb62	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.4.0	bburke@redhat.com	META-INF/db2-jpa-changelog-1.4.0.xml	2024-07-10 23:42:51.733592	12	MARK_RAN	9:e1ff28bf7568451453f844c5d54bb0b5	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.5.0	bburke@redhat.com	META-INF/jpa-changelog-1.5.0.xml	2024-07-10 23:42:51.760427	13	EXECUTED	9:7af32cd8957fbc069f796b61217483fd	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.6.1_from15	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.814505	14	EXECUTED	9:6005e15e84714cd83226bf7879f54190	addColumn tableName=REALM; addColumn tableName=KEYCLOAK_ROLE; addColumn tableName=CLIENT; createTable tableName=OFFLINE_USER_SESSION; createTable tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_US_SES_PK2, tableName=...		\N	4.25.1	\N	\N	0654969304
1.6.1_from16-pre	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.817727	15	MARK_RAN	9:bf656f5a2b055d07f314431cae76f06c	delete tableName=OFFLINE_CLIENT_SESSION; delete tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
1.6.1_from16	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.820926	16	MARK_RAN	9:f8dadc9284440469dcf71e25ca6ab99b	dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_US_SES_PK, tableName=OFFLINE_USER_SESSION; dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_CL_SES_PK, tableName=OFFLINE_CLIENT_SESSION; addColumn tableName=OFFLINE_USER_SESSION; update tableName=OF...		\N	4.25.1	\N	\N	0654969304
1.6.1	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.831141	17	EXECUTED	9:d41d8cd98f00b204e9800998ecf8427e	empty		\N	4.25.1	\N	\N	0654969304
1.7.0	bburke@redhat.com	META-INF/jpa-changelog-1.7.0.xml	2024-07-10 23:42:52.007747	18	EXECUTED	9:3368ff0be4c2855ee2dd9ca813b38d8e	createTable tableName=KEYCLOAK_GROUP; createTable tableName=GROUP_ROLE_MAPPING; createTable tableName=GROUP_ATTRIBUTE; createTable tableName=USER_GROUP_MEMBERSHIP; createTable tableName=REALM_DEFAULT_GROUPS; addColumn tableName=IDENTITY_PROVIDER; ...		\N	4.25.1	\N	\N	0654969304
1.8.0	mposolda@redhat.com	META-INF/jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.243452	19	EXECUTED	9:8ac2fb5dd030b24c0570a763ed75ed20	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	4.25.1	\N	\N	0654969304
1.8.0-2	keycloak	META-INF/jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.269569	20	EXECUTED	9:f91ddca9b19743db60e3057679810e6c	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	4.25.1	\N	\N	0654969304
24.0.0-9758-2	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.658752	119	EXECUTED	9:bf0fdee10afdf597a987adbf291db7b2	customChange		\N	4.25.1	\N	\N	0654969304
1.8.0	mposolda@redhat.com	META-INF/db2-jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.273842	21	MARK_RAN	9:831e82914316dc8a57dc09d755f23c51	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	4.25.1	\N	\N	0654969304
1.8.0-2	keycloak	META-INF/db2-jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.280893	22	MARK_RAN	9:f91ddca9b19743db60e3057679810e6c	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	4.25.1	\N	\N	0654969304
1.9.0	mposolda@redhat.com	META-INF/jpa-changelog-1.9.0.xml	2024-07-10 23:42:52.317185	23	EXECUTED	9:bc3d0f9e823a69dc21e23e94c7a94bb1	update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=REALM; update tableName=REALM; customChange; dr...		\N	4.25.1	\N	\N	0654969304
1.9.1	keycloak	META-INF/jpa-changelog-1.9.1.xml	2024-07-10 23:42:52.325334	24	EXECUTED	9:c9999da42f543575ab790e76439a2679	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=PUBLIC_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	4.25.1	\N	\N	0654969304
1.9.1	keycloak	META-INF/db2-jpa-changelog-1.9.1.xml	2024-07-10 23:42:52.327705	25	MARK_RAN	9:0d6c65c6f58732d81569e77b10ba301d	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	4.25.1	\N	\N	0654969304
1.9.2	keycloak	META-INF/jpa-changelog-1.9.2.xml	2024-07-10 23:42:52.399749	26	EXECUTED	9:fc576660fc016ae53d2d4778d84d86d0	createIndex indexName=IDX_USER_EMAIL, tableName=USER_ENTITY; createIndex indexName=IDX_USER_ROLE_MAPPING, tableName=USER_ROLE_MAPPING; createIndex indexName=IDX_USER_GROUP_MAPPING, tableName=USER_GROUP_MEMBERSHIP; createIndex indexName=IDX_USER_CO...		\N	4.25.1	\N	\N	0654969304
authz-2.0.0	psilva@redhat.com	META-INF/jpa-changelog-authz-2.0.0.xml	2024-07-10 23:42:52.633093	27	EXECUTED	9:43ed6b0da89ff77206289e87eaa9c024	createTable tableName=RESOURCE_SERVER; addPrimaryKey constraintName=CONSTRAINT_FARS, tableName=RESOURCE_SERVER; addUniqueConstraint constraintName=UK_AU8TT6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER; createTable tableName=RESOURCE_SERVER_RESOU...		\N	4.25.1	\N	\N	0654969304
authz-2.5.1	psilva@redhat.com	META-INF/jpa-changelog-authz-2.5.1.xml	2024-07-10 23:42:52.645142	28	EXECUTED	9:44bae577f551b3738740281eceb4ea70	update tableName=RESOURCE_SERVER_POLICY		\N	4.25.1	\N	\N	0654969304
2.1.0-KEYCLOAK-5461	bburke@redhat.com	META-INF/jpa-changelog-2.1.0.xml	2024-07-10 23:42:52.795264	29	EXECUTED	9:bd88e1f833df0420b01e114533aee5e8	createTable tableName=BROKER_LINK; createTable tableName=FED_USER_ATTRIBUTE; createTable tableName=FED_USER_CONSENT; createTable tableName=FED_USER_CONSENT_ROLE; createTable tableName=FED_USER_CONSENT_PROT_MAPPER; createTable tableName=FED_USER_CR...		\N	4.25.1	\N	\N	0654969304
2.2.0	bburke@redhat.com	META-INF/jpa-changelog-2.2.0.xml	2024-07-10 23:42:52.826773	30	EXECUTED	9:a7022af5267f019d020edfe316ef4371	addColumn tableName=ADMIN_EVENT_ENTITY; createTable tableName=CREDENTIAL_ATTRIBUTE; createTable tableName=FED_CREDENTIAL_ATTRIBUTE; modifyDataType columnName=VALUE, tableName=CREDENTIAL; addForeignKeyConstraint baseTableName=FED_CREDENTIAL_ATTRIBU...		\N	4.25.1	\N	\N	0654969304
2.3.0	bburke@redhat.com	META-INF/jpa-changelog-2.3.0.xml	2024-07-10 23:42:52.85627	31	EXECUTED	9:fc155c394040654d6a79227e56f5e25a	createTable tableName=FEDERATED_USER; addPrimaryKey constraintName=CONSTR_FEDERATED_USER, tableName=FEDERATED_USER; dropDefaultValue columnName=TOTP, tableName=USER_ENTITY; dropColumn columnName=TOTP, tableName=USER_ENTITY; addColumn tableName=IDE...		\N	4.25.1	\N	\N	0654969304
2.4.0	bburke@redhat.com	META-INF/jpa-changelog-2.4.0.xml	2024-07-10 23:42:52.863165	32	EXECUTED	9:eac4ffb2a14795e5dc7b426063e54d88	customChange		\N	4.25.1	\N	\N	0654969304
2.5.0	bburke@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.870924	33	EXECUTED	9:54937c05672568c4c64fc9524c1e9462	customChange; modifyDataType columnName=USER_ID, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
2.5.0-unicode-oracle	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.873463	34	MARK_RAN	9:3a32bace77c84d7678d035a7f5a8084e	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	4.25.1	\N	\N	0654969304
2.5.0-unicode-other-dbs	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.922201	35	EXECUTED	9:33d72168746f81f98ae3a1e8e0ca3554	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	4.25.1	\N	\N	0654969304
2.5.0-duplicate-email-support	slawomir@dabek.name	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.928458	36	EXECUTED	9:61b6d3d7a4c0e0024b0c839da283da0c	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
2.5.0-unique-group-names	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.937732	37	EXECUTED	9:8dcac7bdf7378e7d823cdfddebf72fda	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
2.5.1	bburke@redhat.com	META-INF/jpa-changelog-2.5.1.xml	2024-07-10 23:42:52.943241	38	EXECUTED	9:a2b870802540cb3faa72098db5388af3	addColumn tableName=FED_USER_CONSENT		\N	4.25.1	\N	\N	0654969304
3.0.0	bburke@redhat.com	META-INF/jpa-changelog-3.0.0.xml	2024-07-10 23:42:52.948102	39	EXECUTED	9:132a67499ba24bcc54fb5cbdcfe7e4c0	addColumn tableName=IDENTITY_PROVIDER		\N	4.25.1	\N	\N	0654969304
3.2.0-fix	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.950108	40	MARK_RAN	9:938f894c032f5430f2b0fafb1a243462	addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS		\N	4.25.1	\N	\N	0654969304
3.2.0-fix-with-keycloak-5416	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.952434	41	MARK_RAN	9:845c332ff1874dc5d35974b0babf3006	dropIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS; addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS; createIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS		\N	4.25.1	\N	\N	0654969304
3.2.0-fix-offline-sessions	hmlnarik	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.959399	42	EXECUTED	9:fc86359c079781adc577c5a217e4d04c	customChange		\N	4.25.1	\N	\N	0654969304
3.2.0-fixed	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:53.244705	43	EXECUTED	9:59a64800e3c0d09b825f8a3b444fa8f4	addColumn tableName=REALM; dropPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_PK2, tableName=OFFLINE_CLIENT_SESSION; dropColumn columnName=CLIENT_SESSION_ID, tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_P...		\N	4.25.1	\N	\N	0654969304
3.3.0	keycloak	META-INF/jpa-changelog-3.3.0.xml	2024-07-10 23:42:53.252083	44	EXECUTED	9:d48d6da5c6ccf667807f633fe489ce88	addColumn tableName=USER_ENTITY		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part1	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.258022	45	EXECUTED	9:dde36f7973e80d71fceee683bc5d2951	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_RESOURCE; addColumn tableName=RESOURCE_SERVER_SCOPE		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part2-KEYCLOAK-6095	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.265429	46	EXECUTED	9:b855e9b0a406b34fa323235a0cf4f640	customChange		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.26752	47	MARK_RAN	9:51abbacd7b416c50c4421a8cabf7927e	dropIndex indexName=IDX_RES_SERV_POL_RES_SERV, tableName=RESOURCE_SERVER_POLICY; dropIndex indexName=IDX_RES_SRV_RES_RES_SRV, tableName=RESOURCE_SERVER_RESOURCE; dropIndex indexName=IDX_RES_SRV_SCOPE_RES_SRV, tableName=RESOURCE_SERVER_SCOPE		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed-nodropindex	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.353602	48	EXECUTED	9:bdc99e567b3398bac83263d375aad143	addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_POLICY; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_RESOURCE; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, ...		\N	4.25.1	\N	\N	0654969304
authn-3.4.0.CR1-refresh-token-max-reuse	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.361863	49	EXECUTED	9:d198654156881c46bfba39abd7769e69	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
3.4.0	keycloak	META-INF/jpa-changelog-3.4.0.xml	2024-07-10 23:42:53.477374	50	EXECUTED	9:cfdd8736332ccdd72c5256ccb42335db	addPrimaryKey constraintName=CONSTRAINT_REALM_DEFAULT_ROLES, tableName=REALM_DEFAULT_ROLES; addPrimaryKey constraintName=CONSTRAINT_COMPOSITE_ROLE, tableName=COMPOSITE_ROLE; addPrimaryKey constraintName=CONSTR_REALM_DEFAULT_GROUPS, tableName=REALM...		\N	4.25.1	\N	\N	0654969304
3.4.0-KEYCLOAK-5230	hmlnarik@redhat.com	META-INF/jpa-changelog-3.4.0.xml	2024-07-10 23:42:53.543976	51	EXECUTED	9:7c84de3d9bd84d7f077607c1a4dcb714	createIndex indexName=IDX_FU_ATTRIBUTE, tableName=FED_USER_ATTRIBUTE; createIndex indexName=IDX_FU_CONSENT, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CONSENT_RU, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CREDENTIAL, t...		\N	4.25.1	\N	\N	0654969304
3.4.1	psilva@redhat.com	META-INF/jpa-changelog-3.4.1.xml	2024-07-10 23:42:53.548333	52	EXECUTED	9:5a6bb36cbefb6a9d6928452c0852af2d	modifyDataType columnName=VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
3.4.2	keycloak	META-INF/jpa-changelog-3.4.2.xml	2024-07-10 23:42:53.551911	53	EXECUTED	9:8f23e334dbc59f82e0a328373ca6ced0	update tableName=REALM		\N	4.25.1	\N	\N	0654969304
3.4.2-KEYCLOAK-5172	mkanis@redhat.com	META-INF/jpa-changelog-3.4.2.xml	2024-07-10 23:42:53.556029	54	EXECUTED	9:9156214268f09d970cdf0e1564d866af	update tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-6335	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.567132	55	EXECUTED	9:db806613b1ed154826c02610b7dbdf74	createTable tableName=CLIENT_AUTH_FLOW_BINDINGS; addPrimaryKey constraintName=C_CLI_FLOW_BIND, tableName=CLIENT_AUTH_FLOW_BINDINGS		\N	4.25.1	\N	\N	0654969304
4.0.0-CLEANUP-UNUSED-TABLE	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.57746	56	EXECUTED	9:229a041fb72d5beac76bb94a5fa709de	dropTable tableName=CLIENT_IDENTITY_PROV_MAPPING		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-6228	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.614704	57	EXECUTED	9:079899dade9c1e683f26b2aa9ca6ff04	dropUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHOGM8UEWRT, tableName=USER_CONSENT; dropNotNullConstraint columnName=CLIENT_ID, tableName=USER_CONSENT; addColumn tableName=USER_CONSENT; addUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHO...		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-5579-fixed	mposolda@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:54.020307	58	EXECUTED	9:139b79bcbbfe903bb1c2d2a4dbf001d9	dropForeignKeyConstraint baseTableName=CLIENT_TEMPLATE_ATTRIBUTES, constraintName=FK_CL_TEMPL_ATTR_TEMPL; renameTable newTableName=CLIENT_SCOPE_ATTRIBUTES, oldTableName=CLIENT_TEMPLATE_ATTRIBUTES; renameColumn newColumnName=SCOPE_ID, oldColumnName...		\N	4.25.1	\N	\N	0654969304
authz-4.0.0.CR1	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.CR1.xml	2024-07-10 23:42:54.162355	59	EXECUTED	9:b55738ad889860c625ba2bf483495a04	createTable tableName=RESOURCE_SERVER_PERM_TICKET; addPrimaryKey constraintName=CONSTRAINT_FAPMT, tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRHO213XCX4WNKOG82SSPMT...		\N	4.25.1	\N	\N	0654969304
authz-4.0.0.Beta3	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.Beta3.xml	2024-07-10 23:42:54.179609	60	EXECUTED	9:e0057eac39aa8fc8e09ac6cfa4ae15fe	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRPO2128CX4WNKOG82SSRFY, referencedTableName=RESOURCE_SERVER_POLICY		\N	4.25.1	\N	\N	0654969304
authz-4.2.0.Final	mhajas@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2024-07-10 23:42:54.200768	61	EXECUTED	9:42a33806f3a0443fe0e7feeec821326c	createTable tableName=RESOURCE_URIS; addForeignKeyConstraint baseTableName=RESOURCE_URIS, constraintName=FK_RESOURCE_SERVER_URIS, referencedTableName=RESOURCE_SERVER_RESOURCE; customChange; dropColumn columnName=URI, tableName=RESOURCE_SERVER_RESO...		\N	4.25.1	\N	\N	0654969304
authz-4.2.0.Final-KEYCLOAK-9944	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2024-07-10 23:42:54.215337	62	EXECUTED	9:9968206fca46eecc1f51db9c024bfe56	addPrimaryKey constraintName=CONSTRAINT_RESOUR_URIS_PK, tableName=RESOURCE_URIS		\N	4.25.1	\N	\N	0654969304
4.2.0-KEYCLOAK-6313	wadahiro@gmail.com	META-INF/jpa-changelog-4.2.0.xml	2024-07-10 23:42:54.22302	63	EXECUTED	9:92143a6daea0a3f3b8f598c97ce55c3d	addColumn tableName=REQUIRED_ACTION_PROVIDER		\N	4.25.1	\N	\N	0654969304
4.3.0-KEYCLOAK-7984	wadahiro@gmail.com	META-INF/jpa-changelog-4.3.0.xml	2024-07-10 23:42:54.231767	64	EXECUTED	9:82bab26a27195d889fb0429003b18f40	update tableName=REQUIRED_ACTION_PROVIDER		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-7950	psilva@redhat.com	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.23731	65	EXECUTED	9:e590c88ddc0b38b0ae4249bbfcb5abc3	update tableName=RESOURCE_SERVER_RESOURCE		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-8377	keycloak	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.294076	66	EXECUTED	9:5c1f475536118dbdc38d5d7977950cc0	createTable tableName=ROLE_ATTRIBUTE; addPrimaryKey constraintName=CONSTRAINT_ROLE_ATTRIBUTE_PK, tableName=ROLE_ATTRIBUTE; addForeignKeyConstraint baseTableName=ROLE_ATTRIBUTE, constraintName=FK_ROLE_ATTRIBUTE_ID, referencedTableName=KEYCLOAK_ROLE...		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-8555	gideonray@gmail.com	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.309051	67	EXECUTED	9:e7c9f5f9c4d67ccbbcc215440c718a17	createIndex indexName=IDX_COMPONENT_PROVIDER_TYPE, tableName=COMPONENT		\N	4.25.1	\N	\N	0654969304
4.7.0-KEYCLOAK-1267	sguilhen@redhat.com	META-INF/jpa-changelog-4.7.0.xml	2024-07-10 23:42:54.317403	68	EXECUTED	9:88e0bfdda924690d6f4e430c53447dd5	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
4.7.0-KEYCLOAK-7275	keycloak	META-INF/jpa-changelog-4.7.0.xml	2024-07-10 23:42:54.343453	69	EXECUTED	9:f53177f137e1c46b6a88c59ec1cb5218	renameColumn newColumnName=CREATED_ON, oldColumnName=LAST_SESSION_REFRESH, tableName=OFFLINE_USER_SESSION; addNotNullConstraint columnName=CREATED_ON, tableName=OFFLINE_USER_SESSION; addColumn tableName=OFFLINE_USER_SESSION; customChange; createIn...		\N	4.25.1	\N	\N	0654969304
4.8.0-KEYCLOAK-8835	sguilhen@redhat.com	META-INF/jpa-changelog-4.8.0.xml	2024-07-10 23:42:54.354756	70	EXECUTED	9:a74d33da4dc42a37ec27121580d1459f	addNotNullConstraint columnName=SSO_MAX_LIFESPAN_REMEMBER_ME, tableName=REALM; addNotNullConstraint columnName=SSO_IDLE_TIMEOUT_REMEMBER_ME, tableName=REALM		\N	4.25.1	\N	\N	0654969304
authz-7.0.0-KEYCLOAK-10443	psilva@redhat.com	META-INF/jpa-changelog-authz-7.0.0.xml	2024-07-10 23:42:54.361354	71	EXECUTED	9:fd4ade7b90c3b67fae0bfcfcb42dfb5f	addColumn tableName=RESOURCE_SERVER		\N	4.25.1	\N	\N	0654969304
8.0.0-adding-credential-columns	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.37	72	EXECUTED	9:aa072ad090bbba210d8f18781b8cebf4	addColumn tableName=CREDENTIAL; addColumn tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-updating-credential-data-not-oracle-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.384233	73	EXECUTED	9:1ae6be29bab7c2aa376f6983b932be37	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-updating-credential-data-oracle-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.388733	74	MARK_RAN	9:14706f286953fc9a25286dbd8fb30d97	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-credential-cleanup-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.426784	75	EXECUTED	9:2b9cc12779be32c5b40e2e67711a218b	dropDefaultValue columnName=COUNTER, tableName=CREDENTIAL; dropDefaultValue columnName=DIGITS, tableName=CREDENTIAL; dropDefaultValue columnName=PERIOD, tableName=CREDENTIAL; dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; dropColumn ...		\N	4.25.1	\N	\N	0654969304
8.0.0-resource-tag-support	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.440353	76	EXECUTED	9:91fa186ce7a5af127a2d7a91ee083cc5	addColumn tableName=MIGRATION_MODEL; createIndex indexName=IDX_UPDATE_TIME, tableName=MIGRATION_MODEL		\N	4.25.1	\N	\N	0654969304
9.0.0-always-display-client	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.447429	77	EXECUTED	9:6335e5c94e83a2639ccd68dd24e2e5ad	addColumn tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
9.0.0-drop-constraints-for-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.451032	78	MARK_RAN	9:6bdb5658951e028bfe16fa0a8228b530	dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5PMT, tableName=RESOURCE_SERVER_PERM_TICKET; dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER_RESOURCE; dropPrimaryKey constraintName=CONSTRAINT_O...		\N	4.25.1	\N	\N	0654969304
9.0.0-increase-column-size-federated-fk	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.492482	79	EXECUTED	9:d5bc15a64117ccad481ce8792d4c608f	modifyDataType columnName=CLIENT_ID, tableName=FED_USER_CONSENT; modifyDataType columnName=CLIENT_REALM_CONSTRAINT, tableName=KEYCLOAK_ROLE; modifyDataType columnName=OWNER, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=CLIENT_ID, ta...		\N	4.25.1	\N	\N	0654969304
9.0.0-recreate-constraints-after-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.496219	80	MARK_RAN	9:077cba51999515f4d3e7ad5619ab592c	addNotNullConstraint columnName=CLIENT_ID, tableName=OFFLINE_CLIENT_SESSION; addNotNullConstraint columnName=OWNER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNullConstraint columnName=REQUESTER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNull...		\N	4.25.1	\N	\N	0654969304
9.0.1-add-index-to-client.client_id	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.509934	81	EXECUTED	9:be969f08a163bf47c6b9e9ead8ac2afb	createIndex indexName=IDX_CLIENT_ID, tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-drop-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.513367	82	MARK_RAN	9:6d3bb4408ba5a72f39bd8a0b301ec6e3	dropUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-add-not-null-constraint	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.523944	83	EXECUTED	9:966bda61e46bebf3cc39518fbed52fa7	addNotNullConstraint columnName=PARENT_GROUP, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-recreate-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.527109	84	MARK_RAN	9:8dcac7bdf7378e7d823cdfddebf72fda	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-add-index-to-events	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.539626	85	EXECUTED	9:7d93d602352a30c0c317e6a609b56599	createIndex indexName=IDX_EVENT_TIME, tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
map-remove-ri	keycloak	META-INF/jpa-changelog-11.0.0.xml	2024-07-10 23:42:54.546342	86	EXECUTED	9:71c5969e6cdd8d7b6f47cebc86d37627	dropForeignKeyConstraint baseTableName=REALM, constraintName=FK_TRAF444KK6QRKMS7N56AIWQ5Y; dropForeignKeyConstraint baseTableName=KEYCLOAK_ROLE, constraintName=FK_KJHO5LE2C0RAL09FL8CM9WFW9		\N	4.25.1	\N	\N	0654969304
map-remove-ri	keycloak	META-INF/jpa-changelog-12.0.0.xml	2024-07-10 23:42:54.555574	87	EXECUTED	9:a9ba7d47f065f041b7da856a81762021	dropForeignKeyConstraint baseTableName=REALM_DEFAULT_GROUPS, constraintName=FK_DEF_GROUPS_GROUP; dropForeignKeyConstraint baseTableName=REALM_DEFAULT_ROLES, constraintName=FK_H4WPD7W4HSOOLNI3H0SW7BTJE; dropForeignKeyConstraint baseTableName=CLIENT...		\N	4.25.1	\N	\N	0654969304
12.1.0-add-realm-localization-table	keycloak	META-INF/jpa-changelog-12.0.0.xml	2024-07-10 23:42:54.576967	88	EXECUTED	9:fffabce2bc01e1a8f5110d5278500065	createTable tableName=REALM_LOCALIZATIONS; addPrimaryKey tableName=REALM_LOCALIZATIONS		\N	4.25.1	\N	\N	0654969304
default-roles	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.61746	89	EXECUTED	9:fa8a5b5445e3857f4b010bafb5009957	addColumn tableName=REALM; customChange		\N	4.25.1	\N	\N	0654969304
default-roles-cleanup	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.692185	90	EXECUTED	9:67ac3241df9a8582d591c5ed87125f39	dropTable tableName=REALM_DEFAULT_ROLES; dropTable tableName=CLIENT_DEFAULT_ROLES		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-16844	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.71104	91	EXECUTED	9:ad1194d66c937e3ffc82386c050ba089	createIndex indexName=IDX_OFFLINE_USS_PRELOAD, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
map-remove-ri-13.0.0	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.719296	92	EXECUTED	9:d9be619d94af5a2f5d07b9f003543b91	dropForeignKeyConstraint baseTableName=DEFAULT_CLIENT_SCOPE, constraintName=FK_R_DEF_CLI_SCOPE_SCOPE; dropForeignKeyConstraint baseTableName=CLIENT_SCOPE_CLIENT, constraintName=FK_C_CLI_SCOPE_SCOPE; dropForeignKeyConstraint baseTableName=CLIENT_SC...		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-17992-drop-constraints	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.722417	93	MARK_RAN	9:544d201116a0fcc5a5da0925fbbc3bde	dropPrimaryKey constraintName=C_CLI_SCOPE_BIND, tableName=CLIENT_SCOPE_CLIENT; dropIndex indexName=IDX_CLSCOPE_CL, tableName=CLIENT_SCOPE_CLIENT; dropIndex indexName=IDX_CL_CLSCOPE, tableName=CLIENT_SCOPE_CLIENT		\N	4.25.1	\N	\N	0654969304
13.0.0-increase-column-size-federated	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.756742	94	EXECUTED	9:43c0c1055b6761b4b3e89de76d612ccf	modifyDataType columnName=CLIENT_ID, tableName=CLIENT_SCOPE_CLIENT; modifyDataType columnName=SCOPE_ID, tableName=CLIENT_SCOPE_CLIENT		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-17992-recreate-constraints	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.760308	95	MARK_RAN	9:8bd711fd0330f4fe980494ca43ab1139	addNotNullConstraint columnName=CLIENT_ID, tableName=CLIENT_SCOPE_CLIENT; addNotNullConstraint columnName=SCOPE_ID, tableName=CLIENT_SCOPE_CLIENT; addPrimaryKey constraintName=C_CLI_SCOPE_BIND, tableName=CLIENT_SCOPE_CLIENT; createIndex indexName=...		\N	4.25.1	\N	\N	0654969304
json-string-accomodation-fixed	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.838755	96	EXECUTED	9:e07d2bc0970c348bb06fb63b1f82ddbf	addColumn tableName=REALM_ATTRIBUTE; update tableName=REALM_ATTRIBUTE; dropColumn columnName=VALUE, tableName=REALM_ATTRIBUTE; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=REALM_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-11019	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.896035	97	EXECUTED	9:24fb8611e97f29989bea412aa38d12b7	createIndex indexName=IDX_OFFLINE_CSS_PRELOAD, tableName=OFFLINE_CLIENT_SESSION; createIndex indexName=IDX_OFFLINE_USS_BY_USER, tableName=OFFLINE_USER_SESSION; createIndex indexName=IDX_OFFLINE_USS_BY_USERSESS, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.898701	98	MARK_RAN	9:259f89014ce2506ee84740cbf7163aa7	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-revert	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.918684	99	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-supported-dbs	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.930654	100	EXECUTED	9:60ca84a0f8c94ec8c3504a5a3bc88ee8	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-unsupported-dbs	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.934431	101	MARK_RAN	9:d3d977031d431db16e2c181ce49d73e9	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
KEYCLOAK-17267-add-index-to-user-attributes	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.952961	102	EXECUTED	9:0b305d8d1277f3a89a0a53a659ad274c	createIndex indexName=IDX_USER_ATTRIBUTE_NAME, tableName=USER_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
KEYCLOAK-18146-add-saml-art-binding-identifier	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.960903	103	EXECUTED	9:2c374ad2cdfe20e2905a84c8fac48460	customChange		\N	4.25.1	\N	\N	0654969304
15.0.0-KEYCLOAK-18467	keycloak	META-INF/jpa-changelog-15.0.0.xml	2024-07-10 23:42:54.97174	104	EXECUTED	9:47a760639ac597360a8219f5b768b4de	addColumn tableName=REALM_LOCALIZATIONS; update tableName=REALM_LOCALIZATIONS; dropColumn columnName=TEXTS, tableName=REALM_LOCALIZATIONS; renameColumn newColumnName=TEXTS, oldColumnName=TEXTS_NEW, tableName=REALM_LOCALIZATIONS; addNotNullConstrai...		\N	4.25.1	\N	\N	0654969304
17.0.0-9562	keycloak	META-INF/jpa-changelog-17.0.0.xml	2024-07-10 23:42:54.98224	105	EXECUTED	9:a6272f0576727dd8cad2522335f5d99e	createIndex indexName=IDX_USER_SERVICE_ACCOUNT, tableName=USER_ENTITY		\N	4.25.1	\N	\N	0654969304
18.0.0-10625-IDX_ADMIN_EVENT_TIME	keycloak	META-INF/jpa-changelog-18.0.0.xml	2024-07-10 23:42:54.997442	106	EXECUTED	9:015479dbd691d9cc8669282f4828c41d	createIndex indexName=IDX_ADMIN_EVENT_TIME, tableName=ADMIN_EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
19.0.0-10135	keycloak	META-INF/jpa-changelog-19.0.0.xml	2024-07-10 23:42:55.005062	107	EXECUTED	9:9518e495fdd22f78ad6425cc30630221	customChange		\N	4.25.1	\N	\N	0654969304
20.0.0-12964-supported-dbs	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.017665	108	EXECUTED	9:e5f243877199fd96bcc842f27a1656ac	createIndex indexName=IDX_GROUP_ATT_BY_NAME_VALUE, tableName=GROUP_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
20.0.0-12964-unsupported-dbs	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.020086	109	MARK_RAN	9:1a6fcaa85e20bdeae0a9ce49b41946a5	createIndex indexName=IDX_GROUP_ATT_BY_NAME_VALUE, tableName=GROUP_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
client-attributes-string-accomodation-fixed	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.039726	110	EXECUTED	9:3f332e13e90739ed0c35b0b25b7822ca	addColumn tableName=CLIENT_ATTRIBUTES; update tableName=CLIENT_ATTRIBUTES; dropColumn columnName=VALUE, tableName=CLIENT_ATTRIBUTES; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
21.0.2-17277	keycloak	META-INF/jpa-changelog-21.0.2.xml	2024-07-10 23:42:55.045973	111	EXECUTED	9:7ee1f7a3fb8f5588f171fb9a6ab623c0	customChange		\N	4.25.1	\N	\N	0654969304
21.1.0-19404	keycloak	META-INF/jpa-changelog-21.1.0.xml	2024-07-10 23:42:55.270408	112	EXECUTED	9:3d7e830b52f33676b9d64f7f2b2ea634	modifyDataType columnName=DECISION_STRATEGY, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=LOGIC, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=POLICY_ENFORCE_MODE, tableName=RESOURCE_SERVER		\N	4.25.1	\N	\N	0654969304
21.1.0-19404-2	keycloak	META-INF/jpa-changelog-21.1.0.xml	2024-07-10 23:42:55.281114	113	MARK_RAN	9:627d032e3ef2c06c0e1f73d2ae25c26c	addColumn tableName=RESOURCE_SERVER_POLICY; update tableName=RESOURCE_SERVER_POLICY; dropColumn columnName=DECISION_STRATEGY, tableName=RESOURCE_SERVER_POLICY; renameColumn newColumnName=DECISION_STRATEGY, oldColumnName=DECISION_STRATEGY_NEW, tabl...		\N	4.25.1	\N	\N	0654969304
22.0.0-17484-updated	keycloak	META-INF/jpa-changelog-22.0.0.xml	2024-07-10 23:42:55.292055	114	EXECUTED	9:90af0bfd30cafc17b9f4d6eccd92b8b3	customChange		\N	4.25.1	\N	\N	0654969304
22.0.5-24031	keycloak	META-INF/jpa-changelog-22.0.0.xml	2024-07-10 23:42:55.297618	115	MARK_RAN	9:a60d2d7b315ec2d3eba9e2f145f9df28	customChange		\N	4.25.1	\N	\N	0654969304
23.0.0-12062	keycloak	META-INF/jpa-changelog-23.0.0.xml	2024-07-10 23:42:55.517095	116	EXECUTED	9:2168fbe728fec46ae9baf15bf80927b8	addColumn tableName=COMPONENT_CONFIG; update tableName=COMPONENT_CONFIG; dropColumn columnName=VALUE, tableName=COMPONENT_CONFIG; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=COMPONENT_CONFIG		\N	4.25.1	\N	\N	0654969304
23.0.0-17258	keycloak	META-INF/jpa-changelog-23.0.0.xml	2024-07-10 23:42:55.533685	117	EXECUTED	9:36506d679a83bbfda85a27ea1864dca8	addColumn tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
24.0.0-9758	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.650221	118	EXECUTED	9:502c557a5189f600f0f445a9b49ebbce	addColumn tableName=USER_ATTRIBUTE; addColumn tableName=FED_USER_ATTRIBUTE; createIndex indexName=USER_ATTR_LONG_VALUES, tableName=USER_ATTRIBUTE; createIndex indexName=FED_USER_ATTR_LONG_VALUES, tableName=FED_USER_ATTRIBUTE; createIndex indexName...		\N	4.25.1	\N	\N	0654969304
24.0.0-26618-drop-index-if-present	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.664885	120	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.0-26618-reindex	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.704023	121	EXECUTED	9:08707c0f0db1cef6b352db03a60edc7f	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.2-27228	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.713875	122	EXECUTED	9:eaee11f6b8aa25d2cc6a84fb86fc6238	customChange		\N	4.25.1	\N	\N	0654969304
24.0.2-27967-drop-index-if-present	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.720165	123	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.2-27967-reindex	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.724372	124	MARK_RAN	9:d3d977031d431db16e2c181ce49d73e9	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/jpa-changelog-1.0.0.Final.xml	2024-07-10 23:42:50.137495	1	EXECUTED	9:6f1016664e21e16d26517a4418f5e3df	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	4.25.1	\N	\N	0654969304
1.0.0.Final-KEYCLOAK-5461	sthorger@redhat.com	META-INF/db2-jpa-changelog-1.0.0.Final.xml	2024-07-10 23:42:50.156611	2	MARK_RAN	9:828775b1596a07d1200ba1d49e5e3941	createTable tableName=APPLICATION_DEFAULT_ROLES; createTable tableName=CLIENT; createTable tableName=CLIENT_SESSION; createTable tableName=CLIENT_SESSION_ROLE; createTable tableName=COMPOSITE_ROLE; createTable tableName=CREDENTIAL; createTable tab...		\N	4.25.1	\N	\N	0654969304
1.1.0.Beta1	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Beta1.xml	2024-07-10 23:42:50.416243	3	EXECUTED	9:5f090e44a7d595883c1fb61f4b41fd38	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=CLIENT_ATTRIBUTES; createTable tableName=CLIENT_SESSION_NOTE; createTable tableName=APP_NODE_REGISTRATIONS; addColumn table...		\N	4.25.1	\N	\N	0654969304
1.1.0.Final	sthorger@redhat.com	META-INF/jpa-changelog-1.1.0.Final.xml	2024-07-10 23:42:50.425611	4	EXECUTED	9:c07e577387a3d2c04d1adc9aaad8730e	renameColumn newColumnName=EVENT_TIME, oldColumnName=TIME, tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
1.2.0.Beta1	psilva@redhat.com	META-INF/jpa-changelog-1.2.0.Beta1.xml	2024-07-10 23:42:50.762414	5	EXECUTED	9:b68ce996c655922dbcd2fe6b6ae72686	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	4.25.1	\N	\N	0654969304
1.2.0.Beta1	psilva@redhat.com	META-INF/db2-jpa-changelog-1.2.0.Beta1.xml	2024-07-10 23:42:50.779551	6	MARK_RAN	9:543b5c9989f024fe35c6f6c5a97de88e	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION; createTable tableName=PROTOCOL_MAPPER; createTable tableName=PROTOCOL_MAPPER_CONFIG; createTable tableName=...		\N	4.25.1	\N	\N	0654969304
1.2.0.RC1	bburke@redhat.com	META-INF/jpa-changelog-1.2.0.CR1.xml	2024-07-10 23:42:51.205963	7	EXECUTED	9:765afebbe21cf5bbca048e632df38336	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	4.25.1	\N	\N	0654969304
1.2.0.RC1	bburke@redhat.com	META-INF/db2-jpa-changelog-1.2.0.CR1.xml	2024-07-10 23:42:51.217186	8	MARK_RAN	9:db4a145ba11a6fdaefb397f6dbf829a1	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=MIGRATION_MODEL; createTable tableName=IDENTITY_P...		\N	4.25.1	\N	\N	0654969304
1.2.0.Final	keycloak	META-INF/jpa-changelog-1.2.0.Final.xml	2024-07-10 23:42:51.22428	9	EXECUTED	9:9d05c7be10cdb873f8bcb41bc3a8ab23	update tableName=CLIENT; update tableName=CLIENT; update tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
1.3.0	bburke@redhat.com	META-INF/jpa-changelog-1.3.0.xml	2024-07-10 23:42:51.562331	10	EXECUTED	9:18593702353128d53111f9b1ff0b82b8	delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete tableName=USER_SESSION; createTable tableName=ADMI...		\N	4.25.1	\N	\N	0654969304
1.4.0	bburke@redhat.com	META-INF/jpa-changelog-1.4.0.xml	2024-07-10 23:42:51.722454	11	EXECUTED	9:6122efe5f090e41a85c0f1c9e52cbb62	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.4.0	bburke@redhat.com	META-INF/db2-jpa-changelog-1.4.0.xml	2024-07-10 23:42:51.733592	12	MARK_RAN	9:e1ff28bf7568451453f844c5d54bb0b5	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.5.0	bburke@redhat.com	META-INF/jpa-changelog-1.5.0.xml	2024-07-10 23:42:51.760427	13	EXECUTED	9:7af32cd8957fbc069f796b61217483fd	delete tableName=CLIENT_SESSION_AUTH_STATUS; delete tableName=CLIENT_SESSION_ROLE; delete tableName=CLIENT_SESSION_PROT_MAPPER; delete tableName=CLIENT_SESSION_NOTE; delete tableName=CLIENT_SESSION; delete tableName=USER_SESSION_NOTE; delete table...		\N	4.25.1	\N	\N	0654969304
1.6.1_from15	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.814505	14	EXECUTED	9:6005e15e84714cd83226bf7879f54190	addColumn tableName=REALM; addColumn tableName=KEYCLOAK_ROLE; addColumn tableName=CLIENT; createTable tableName=OFFLINE_USER_SESSION; createTable tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_US_SES_PK2, tableName=...		\N	4.25.1	\N	\N	0654969304
1.6.1_from16-pre	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.817727	15	MARK_RAN	9:bf656f5a2b055d07f314431cae76f06c	delete tableName=OFFLINE_CLIENT_SESSION; delete tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
1.6.1_from16	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.820926	16	MARK_RAN	9:f8dadc9284440469dcf71e25ca6ab99b	dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_US_SES_PK, tableName=OFFLINE_USER_SESSION; dropPrimaryKey constraintName=CONSTRAINT_OFFLINE_CL_SES_PK, tableName=OFFLINE_CLIENT_SESSION; addColumn tableName=OFFLINE_USER_SESSION; update tableName=OF...		\N	4.25.1	\N	\N	0654969304
1.6.1	mposolda@redhat.com	META-INF/jpa-changelog-1.6.1.xml	2024-07-10 23:42:51.831141	17	EXECUTED	9:d41d8cd98f00b204e9800998ecf8427e	empty		\N	4.25.1	\N	\N	0654969304
1.7.0	bburke@redhat.com	META-INF/jpa-changelog-1.7.0.xml	2024-07-10 23:42:52.007747	18	EXECUTED	9:3368ff0be4c2855ee2dd9ca813b38d8e	createTable tableName=KEYCLOAK_GROUP; createTable tableName=GROUP_ROLE_MAPPING; createTable tableName=GROUP_ATTRIBUTE; createTable tableName=USER_GROUP_MEMBERSHIP; createTable tableName=REALM_DEFAULT_GROUPS; addColumn tableName=IDENTITY_PROVIDER; ...		\N	4.25.1	\N	\N	0654969304
1.8.0	mposolda@redhat.com	META-INF/jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.243452	19	EXECUTED	9:8ac2fb5dd030b24c0570a763ed75ed20	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	4.25.1	\N	\N	0654969304
1.8.0-2	keycloak	META-INF/jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.269569	20	EXECUTED	9:f91ddca9b19743db60e3057679810e6c	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	4.25.1	\N	\N	0654969304
24.0.0-9758-2	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.658752	119	EXECUTED	9:bf0fdee10afdf597a987adbf291db7b2	customChange		\N	4.25.1	\N	\N	0654969304
1.8.0	mposolda@redhat.com	META-INF/db2-jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.273842	21	MARK_RAN	9:831e82914316dc8a57dc09d755f23c51	addColumn tableName=IDENTITY_PROVIDER; createTable tableName=CLIENT_TEMPLATE; createTable tableName=CLIENT_TEMPLATE_ATTRIBUTES; createTable tableName=TEMPLATE_SCOPE_MAPPING; dropNotNullConstraint columnName=CLIENT_ID, tableName=PROTOCOL_MAPPER; ad...		\N	4.25.1	\N	\N	0654969304
1.8.0-2	keycloak	META-INF/db2-jpa-changelog-1.8.0.xml	2024-07-10 23:42:52.280893	22	MARK_RAN	9:f91ddca9b19743db60e3057679810e6c	dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; update tableName=CREDENTIAL		\N	4.25.1	\N	\N	0654969304
1.9.0	mposolda@redhat.com	META-INF/jpa-changelog-1.9.0.xml	2024-07-10 23:42:52.317185	23	EXECUTED	9:bc3d0f9e823a69dc21e23e94c7a94bb1	update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=REALM; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=REALM; update tableName=REALM; customChange; dr...		\N	4.25.1	\N	\N	0654969304
1.9.1	keycloak	META-INF/jpa-changelog-1.9.1.xml	2024-07-10 23:42:52.325334	24	EXECUTED	9:c9999da42f543575ab790e76439a2679	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=PUBLIC_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	4.25.1	\N	\N	0654969304
1.9.1	keycloak	META-INF/db2-jpa-changelog-1.9.1.xml	2024-07-10 23:42:52.327705	25	MARK_RAN	9:0d6c65c6f58732d81569e77b10ba301d	modifyDataType columnName=PRIVATE_KEY, tableName=REALM; modifyDataType columnName=CERTIFICATE, tableName=REALM		\N	4.25.1	\N	\N	0654969304
1.9.2	keycloak	META-INF/jpa-changelog-1.9.2.xml	2024-07-10 23:42:52.399749	26	EXECUTED	9:fc576660fc016ae53d2d4778d84d86d0	createIndex indexName=IDX_USER_EMAIL, tableName=USER_ENTITY; createIndex indexName=IDX_USER_ROLE_MAPPING, tableName=USER_ROLE_MAPPING; createIndex indexName=IDX_USER_GROUP_MAPPING, tableName=USER_GROUP_MEMBERSHIP; createIndex indexName=IDX_USER_CO...		\N	4.25.1	\N	\N	0654969304
authz-2.0.0	psilva@redhat.com	META-INF/jpa-changelog-authz-2.0.0.xml	2024-07-10 23:42:52.633093	27	EXECUTED	9:43ed6b0da89ff77206289e87eaa9c024	createTable tableName=RESOURCE_SERVER; addPrimaryKey constraintName=CONSTRAINT_FARS, tableName=RESOURCE_SERVER; addUniqueConstraint constraintName=UK_AU8TT6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER; createTable tableName=RESOURCE_SERVER_RESOU...		\N	4.25.1	\N	\N	0654969304
authz-2.5.1	psilva@redhat.com	META-INF/jpa-changelog-authz-2.5.1.xml	2024-07-10 23:42:52.645142	28	EXECUTED	9:44bae577f551b3738740281eceb4ea70	update tableName=RESOURCE_SERVER_POLICY		\N	4.25.1	\N	\N	0654969304
2.1.0-KEYCLOAK-5461	bburke@redhat.com	META-INF/jpa-changelog-2.1.0.xml	2024-07-10 23:42:52.795264	29	EXECUTED	9:bd88e1f833df0420b01e114533aee5e8	createTable tableName=BROKER_LINK; createTable tableName=FED_USER_ATTRIBUTE; createTable tableName=FED_USER_CONSENT; createTable tableName=FED_USER_CONSENT_ROLE; createTable tableName=FED_USER_CONSENT_PROT_MAPPER; createTable tableName=FED_USER_CR...		\N	4.25.1	\N	\N	0654969304
2.2.0	bburke@redhat.com	META-INF/jpa-changelog-2.2.0.xml	2024-07-10 23:42:52.826773	30	EXECUTED	9:a7022af5267f019d020edfe316ef4371	addColumn tableName=ADMIN_EVENT_ENTITY; createTable tableName=CREDENTIAL_ATTRIBUTE; createTable tableName=FED_CREDENTIAL_ATTRIBUTE; modifyDataType columnName=VALUE, tableName=CREDENTIAL; addForeignKeyConstraint baseTableName=FED_CREDENTIAL_ATTRIBU...		\N	4.25.1	\N	\N	0654969304
2.3.0	bburke@redhat.com	META-INF/jpa-changelog-2.3.0.xml	2024-07-10 23:42:52.85627	31	EXECUTED	9:fc155c394040654d6a79227e56f5e25a	createTable tableName=FEDERATED_USER; addPrimaryKey constraintName=CONSTR_FEDERATED_USER, tableName=FEDERATED_USER; dropDefaultValue columnName=TOTP, tableName=USER_ENTITY; dropColumn columnName=TOTP, tableName=USER_ENTITY; addColumn tableName=IDE...		\N	4.25.1	\N	\N	0654969304
2.4.0	bburke@redhat.com	META-INF/jpa-changelog-2.4.0.xml	2024-07-10 23:42:52.863165	32	EXECUTED	9:eac4ffb2a14795e5dc7b426063e54d88	customChange		\N	4.25.1	\N	\N	0654969304
2.5.0	bburke@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.870924	33	EXECUTED	9:54937c05672568c4c64fc9524c1e9462	customChange; modifyDataType columnName=USER_ID, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
2.5.0-unicode-oracle	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.873463	34	MARK_RAN	9:3a32bace77c84d7678d035a7f5a8084e	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	4.25.1	\N	\N	0654969304
2.5.0-unicode-other-dbs	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.922201	35	EXECUTED	9:33d72168746f81f98ae3a1e8e0ca3554	modifyDataType columnName=DESCRIPTION, tableName=AUTHENTICATION_FLOW; modifyDataType columnName=DESCRIPTION, tableName=CLIENT_TEMPLATE; modifyDataType columnName=DESCRIPTION, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=DESCRIPTION,...		\N	4.25.1	\N	\N	0654969304
2.5.0-duplicate-email-support	slawomir@dabek.name	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.928458	36	EXECUTED	9:61b6d3d7a4c0e0024b0c839da283da0c	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
2.5.0-unique-group-names	hmlnarik@redhat.com	META-INF/jpa-changelog-2.5.0.xml	2024-07-10 23:42:52.937732	37	EXECUTED	9:8dcac7bdf7378e7d823cdfddebf72fda	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
2.5.1	bburke@redhat.com	META-INF/jpa-changelog-2.5.1.xml	2024-07-10 23:42:52.943241	38	EXECUTED	9:a2b870802540cb3faa72098db5388af3	addColumn tableName=FED_USER_CONSENT		\N	4.25.1	\N	\N	0654969304
3.0.0	bburke@redhat.com	META-INF/jpa-changelog-3.0.0.xml	2024-07-10 23:42:52.948102	39	EXECUTED	9:132a67499ba24bcc54fb5cbdcfe7e4c0	addColumn tableName=IDENTITY_PROVIDER		\N	4.25.1	\N	\N	0654969304
3.2.0-fix	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.950108	40	MARK_RAN	9:938f894c032f5430f2b0fafb1a243462	addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS		\N	4.25.1	\N	\N	0654969304
3.2.0-fix-with-keycloak-5416	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.952434	41	MARK_RAN	9:845c332ff1874dc5d35974b0babf3006	dropIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS; addNotNullConstraint columnName=REALM_ID, tableName=CLIENT_INITIAL_ACCESS; createIndex indexName=IDX_CLIENT_INIT_ACC_REALM, tableName=CLIENT_INITIAL_ACCESS		\N	4.25.1	\N	\N	0654969304
3.2.0-fix-offline-sessions	hmlnarik	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:52.959399	42	EXECUTED	9:fc86359c079781adc577c5a217e4d04c	customChange		\N	4.25.1	\N	\N	0654969304
3.2.0-fixed	keycloak	META-INF/jpa-changelog-3.2.0.xml	2024-07-10 23:42:53.244705	43	EXECUTED	9:59a64800e3c0d09b825f8a3b444fa8f4	addColumn tableName=REALM; dropPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_PK2, tableName=OFFLINE_CLIENT_SESSION; dropColumn columnName=CLIENT_SESSION_ID, tableName=OFFLINE_CLIENT_SESSION; addPrimaryKey constraintName=CONSTRAINT_OFFL_CL_SES_P...		\N	4.25.1	\N	\N	0654969304
3.3.0	keycloak	META-INF/jpa-changelog-3.3.0.xml	2024-07-10 23:42:53.252083	44	EXECUTED	9:d48d6da5c6ccf667807f633fe489ce88	addColumn tableName=USER_ENTITY		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part1	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.258022	45	EXECUTED	9:dde36f7973e80d71fceee683bc5d2951	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_RESOURCE; addColumn tableName=RESOURCE_SERVER_SCOPE		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part2-KEYCLOAK-6095	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.265429	46	EXECUTED	9:b855e9b0a406b34fa323235a0cf4f640	customChange		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.26752	47	MARK_RAN	9:51abbacd7b416c50c4421a8cabf7927e	dropIndex indexName=IDX_RES_SERV_POL_RES_SERV, tableName=RESOURCE_SERVER_POLICY; dropIndex indexName=IDX_RES_SRV_RES_RES_SRV, tableName=RESOURCE_SERVER_RESOURCE; dropIndex indexName=IDX_RES_SRV_SCOPE_RES_SRV, tableName=RESOURCE_SERVER_SCOPE		\N	4.25.1	\N	\N	0654969304
authz-3.4.0.CR1-resource-server-pk-change-part3-fixed-nodropindex	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.353602	48	EXECUTED	9:bdc99e567b3398bac83263d375aad143	addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_POLICY; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, tableName=RESOURCE_SERVER_RESOURCE; addNotNullConstraint columnName=RESOURCE_SERVER_CLIENT_ID, ...		\N	4.25.1	\N	\N	0654969304
authn-3.4.0.CR1-refresh-token-max-reuse	glavoie@gmail.com	META-INF/jpa-changelog-authz-3.4.0.CR1.xml	2024-07-10 23:42:53.361863	49	EXECUTED	9:d198654156881c46bfba39abd7769e69	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
3.4.0	keycloak	META-INF/jpa-changelog-3.4.0.xml	2024-07-10 23:42:53.477374	50	EXECUTED	9:cfdd8736332ccdd72c5256ccb42335db	addPrimaryKey constraintName=CONSTRAINT_REALM_DEFAULT_ROLES, tableName=REALM_DEFAULT_ROLES; addPrimaryKey constraintName=CONSTRAINT_COMPOSITE_ROLE, tableName=COMPOSITE_ROLE; addPrimaryKey constraintName=CONSTR_REALM_DEFAULT_GROUPS, tableName=REALM...		\N	4.25.1	\N	\N	0654969304
3.4.0-KEYCLOAK-5230	hmlnarik@redhat.com	META-INF/jpa-changelog-3.4.0.xml	2024-07-10 23:42:53.543976	51	EXECUTED	9:7c84de3d9bd84d7f077607c1a4dcb714	createIndex indexName=IDX_FU_ATTRIBUTE, tableName=FED_USER_ATTRIBUTE; createIndex indexName=IDX_FU_CONSENT, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CONSENT_RU, tableName=FED_USER_CONSENT; createIndex indexName=IDX_FU_CREDENTIAL, t...		\N	4.25.1	\N	\N	0654969304
3.4.1	psilva@redhat.com	META-INF/jpa-changelog-3.4.1.xml	2024-07-10 23:42:53.548333	52	EXECUTED	9:5a6bb36cbefb6a9d6928452c0852af2d	modifyDataType columnName=VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
3.4.2	keycloak	META-INF/jpa-changelog-3.4.2.xml	2024-07-10 23:42:53.551911	53	EXECUTED	9:8f23e334dbc59f82e0a328373ca6ced0	update tableName=REALM		\N	4.25.1	\N	\N	0654969304
3.4.2-KEYCLOAK-5172	mkanis@redhat.com	META-INF/jpa-changelog-3.4.2.xml	2024-07-10 23:42:53.556029	54	EXECUTED	9:9156214268f09d970cdf0e1564d866af	update tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-6335	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.567132	55	EXECUTED	9:db806613b1ed154826c02610b7dbdf74	createTable tableName=CLIENT_AUTH_FLOW_BINDINGS; addPrimaryKey constraintName=C_CLI_FLOW_BIND, tableName=CLIENT_AUTH_FLOW_BINDINGS		\N	4.25.1	\N	\N	0654969304
4.0.0-CLEANUP-UNUSED-TABLE	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.57746	56	EXECUTED	9:229a041fb72d5beac76bb94a5fa709de	dropTable tableName=CLIENT_IDENTITY_PROV_MAPPING		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-6228	bburke@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:53.614704	57	EXECUTED	9:079899dade9c1e683f26b2aa9ca6ff04	dropUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHOGM8UEWRT, tableName=USER_CONSENT; dropNotNullConstraint columnName=CLIENT_ID, tableName=USER_CONSENT; addColumn tableName=USER_CONSENT; addUniqueConstraint constraintName=UK_JKUWUVD56ONTGSUHO...		\N	4.25.1	\N	\N	0654969304
4.0.0-KEYCLOAK-5579-fixed	mposolda@redhat.com	META-INF/jpa-changelog-4.0.0.xml	2024-07-10 23:42:54.020307	58	EXECUTED	9:139b79bcbbfe903bb1c2d2a4dbf001d9	dropForeignKeyConstraint baseTableName=CLIENT_TEMPLATE_ATTRIBUTES, constraintName=FK_CL_TEMPL_ATTR_TEMPL; renameTable newTableName=CLIENT_SCOPE_ATTRIBUTES, oldTableName=CLIENT_TEMPLATE_ATTRIBUTES; renameColumn newColumnName=SCOPE_ID, oldColumnName...		\N	4.25.1	\N	\N	0654969304
authz-4.0.0.CR1	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.CR1.xml	2024-07-10 23:42:54.162355	59	EXECUTED	9:b55738ad889860c625ba2bf483495a04	createTable tableName=RESOURCE_SERVER_PERM_TICKET; addPrimaryKey constraintName=CONSTRAINT_FAPMT, tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRHO213XCX4WNKOG82SSPMT...		\N	4.25.1	\N	\N	0654969304
authz-4.0.0.Beta3	psilva@redhat.com	META-INF/jpa-changelog-authz-4.0.0.Beta3.xml	2024-07-10 23:42:54.179609	60	EXECUTED	9:e0057eac39aa8fc8e09ac6cfa4ae15fe	addColumn tableName=RESOURCE_SERVER_POLICY; addColumn tableName=RESOURCE_SERVER_PERM_TICKET; addForeignKeyConstraint baseTableName=RESOURCE_SERVER_PERM_TICKET, constraintName=FK_FRSRPO2128CX4WNKOG82SSRFY, referencedTableName=RESOURCE_SERVER_POLICY		\N	4.25.1	\N	\N	0654969304
authz-4.2.0.Final	mhajas@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2024-07-10 23:42:54.200768	61	EXECUTED	9:42a33806f3a0443fe0e7feeec821326c	createTable tableName=RESOURCE_URIS; addForeignKeyConstraint baseTableName=RESOURCE_URIS, constraintName=FK_RESOURCE_SERVER_URIS, referencedTableName=RESOURCE_SERVER_RESOURCE; customChange; dropColumn columnName=URI, tableName=RESOURCE_SERVER_RESO...		\N	4.25.1	\N	\N	0654969304
authz-4.2.0.Final-KEYCLOAK-9944	hmlnarik@redhat.com	META-INF/jpa-changelog-authz-4.2.0.Final.xml	2024-07-10 23:42:54.215337	62	EXECUTED	9:9968206fca46eecc1f51db9c024bfe56	addPrimaryKey constraintName=CONSTRAINT_RESOUR_URIS_PK, tableName=RESOURCE_URIS		\N	4.25.1	\N	\N	0654969304
4.2.0-KEYCLOAK-6313	wadahiro@gmail.com	META-INF/jpa-changelog-4.2.0.xml	2024-07-10 23:42:54.22302	63	EXECUTED	9:92143a6daea0a3f3b8f598c97ce55c3d	addColumn tableName=REQUIRED_ACTION_PROVIDER		\N	4.25.1	\N	\N	0654969304
4.3.0-KEYCLOAK-7984	wadahiro@gmail.com	META-INF/jpa-changelog-4.3.0.xml	2024-07-10 23:42:54.231767	64	EXECUTED	9:82bab26a27195d889fb0429003b18f40	update tableName=REQUIRED_ACTION_PROVIDER		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-7950	psilva@redhat.com	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.23731	65	EXECUTED	9:e590c88ddc0b38b0ae4249bbfcb5abc3	update tableName=RESOURCE_SERVER_RESOURCE		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-8377	keycloak	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.294076	66	EXECUTED	9:5c1f475536118dbdc38d5d7977950cc0	createTable tableName=ROLE_ATTRIBUTE; addPrimaryKey constraintName=CONSTRAINT_ROLE_ATTRIBUTE_PK, tableName=ROLE_ATTRIBUTE; addForeignKeyConstraint baseTableName=ROLE_ATTRIBUTE, constraintName=FK_ROLE_ATTRIBUTE_ID, referencedTableName=KEYCLOAK_ROLE...		\N	4.25.1	\N	\N	0654969304
4.6.0-KEYCLOAK-8555	gideonray@gmail.com	META-INF/jpa-changelog-4.6.0.xml	2024-07-10 23:42:54.309051	67	EXECUTED	9:e7c9f5f9c4d67ccbbcc215440c718a17	createIndex indexName=IDX_COMPONENT_PROVIDER_TYPE, tableName=COMPONENT		\N	4.25.1	\N	\N	0654969304
4.7.0-KEYCLOAK-1267	sguilhen@redhat.com	META-INF/jpa-changelog-4.7.0.xml	2024-07-10 23:42:54.317403	68	EXECUTED	9:88e0bfdda924690d6f4e430c53447dd5	addColumn tableName=REALM		\N	4.25.1	\N	\N	0654969304
4.7.0-KEYCLOAK-7275	keycloak	META-INF/jpa-changelog-4.7.0.xml	2024-07-10 23:42:54.343453	69	EXECUTED	9:f53177f137e1c46b6a88c59ec1cb5218	renameColumn newColumnName=CREATED_ON, oldColumnName=LAST_SESSION_REFRESH, tableName=OFFLINE_USER_SESSION; addNotNullConstraint columnName=CREATED_ON, tableName=OFFLINE_USER_SESSION; addColumn tableName=OFFLINE_USER_SESSION; customChange; createIn...		\N	4.25.1	\N	\N	0654969304
4.8.0-KEYCLOAK-8835	sguilhen@redhat.com	META-INF/jpa-changelog-4.8.0.xml	2024-07-10 23:42:54.354756	70	EXECUTED	9:a74d33da4dc42a37ec27121580d1459f	addNotNullConstraint columnName=SSO_MAX_LIFESPAN_REMEMBER_ME, tableName=REALM; addNotNullConstraint columnName=SSO_IDLE_TIMEOUT_REMEMBER_ME, tableName=REALM		\N	4.25.1	\N	\N	0654969304
authz-7.0.0-KEYCLOAK-10443	psilva@redhat.com	META-INF/jpa-changelog-authz-7.0.0.xml	2024-07-10 23:42:54.361354	71	EXECUTED	9:fd4ade7b90c3b67fae0bfcfcb42dfb5f	addColumn tableName=RESOURCE_SERVER		\N	4.25.1	\N	\N	0654969304
8.0.0-adding-credential-columns	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.37	72	EXECUTED	9:aa072ad090bbba210d8f18781b8cebf4	addColumn tableName=CREDENTIAL; addColumn tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-updating-credential-data-not-oracle-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.384233	73	EXECUTED	9:1ae6be29bab7c2aa376f6983b932be37	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-updating-credential-data-oracle-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.388733	74	MARK_RAN	9:14706f286953fc9a25286dbd8fb30d97	update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL; update tableName=FED_USER_CREDENTIAL		\N	4.25.1	\N	\N	0654969304
8.0.0-credential-cleanup-fixed	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.426784	75	EXECUTED	9:2b9cc12779be32c5b40e2e67711a218b	dropDefaultValue columnName=COUNTER, tableName=CREDENTIAL; dropDefaultValue columnName=DIGITS, tableName=CREDENTIAL; dropDefaultValue columnName=PERIOD, tableName=CREDENTIAL; dropDefaultValue columnName=ALGORITHM, tableName=CREDENTIAL; dropColumn ...		\N	4.25.1	\N	\N	0654969304
8.0.0-resource-tag-support	keycloak	META-INF/jpa-changelog-8.0.0.xml	2024-07-10 23:42:54.440353	76	EXECUTED	9:91fa186ce7a5af127a2d7a91ee083cc5	addColumn tableName=MIGRATION_MODEL; createIndex indexName=IDX_UPDATE_TIME, tableName=MIGRATION_MODEL		\N	4.25.1	\N	\N	0654969304
9.0.0-always-display-client	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.447429	77	EXECUTED	9:6335e5c94e83a2639ccd68dd24e2e5ad	addColumn tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
9.0.0-drop-constraints-for-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.451032	78	MARK_RAN	9:6bdb5658951e028bfe16fa0a8228b530	dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5PMT, tableName=RESOURCE_SERVER_PERM_TICKET; dropUniqueConstraint constraintName=UK_FRSR6T700S9V50BU18WS5HA6, tableName=RESOURCE_SERVER_RESOURCE; dropPrimaryKey constraintName=CONSTRAINT_O...		\N	4.25.1	\N	\N	0654969304
9.0.0-increase-column-size-federated-fk	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.492482	79	EXECUTED	9:d5bc15a64117ccad481ce8792d4c608f	modifyDataType columnName=CLIENT_ID, tableName=FED_USER_CONSENT; modifyDataType columnName=CLIENT_REALM_CONSTRAINT, tableName=KEYCLOAK_ROLE; modifyDataType columnName=OWNER, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=CLIENT_ID, ta...		\N	4.25.1	\N	\N	0654969304
9.0.0-recreate-constraints-after-column-increase	keycloak	META-INF/jpa-changelog-9.0.0.xml	2024-07-10 23:42:54.496219	80	MARK_RAN	9:077cba51999515f4d3e7ad5619ab592c	addNotNullConstraint columnName=CLIENT_ID, tableName=OFFLINE_CLIENT_SESSION; addNotNullConstraint columnName=OWNER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNullConstraint columnName=REQUESTER, tableName=RESOURCE_SERVER_PERM_TICKET; addNotNull...		\N	4.25.1	\N	\N	0654969304
9.0.1-add-index-to-client.client_id	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.509934	81	EXECUTED	9:be969f08a163bf47c6b9e9ead8ac2afb	createIndex indexName=IDX_CLIENT_ID, tableName=CLIENT		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-drop-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.513367	82	MARK_RAN	9:6d3bb4408ba5a72f39bd8a0b301ec6e3	dropUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-add-not-null-constraint	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.523944	83	EXECUTED	9:966bda61e46bebf3cc39518fbed52fa7	addNotNullConstraint columnName=PARENT_GROUP, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-KEYCLOAK-12579-recreate-constraints	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.527109	84	MARK_RAN	9:8dcac7bdf7378e7d823cdfddebf72fda	addUniqueConstraint constraintName=SIBLING_NAMES, tableName=KEYCLOAK_GROUP		\N	4.25.1	\N	\N	0654969304
9.0.1-add-index-to-events	keycloak	META-INF/jpa-changelog-9.0.1.xml	2024-07-10 23:42:54.539626	85	EXECUTED	9:7d93d602352a30c0c317e6a609b56599	createIndex indexName=IDX_EVENT_TIME, tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
map-remove-ri	keycloak	META-INF/jpa-changelog-11.0.0.xml	2024-07-10 23:42:54.546342	86	EXECUTED	9:71c5969e6cdd8d7b6f47cebc86d37627	dropForeignKeyConstraint baseTableName=REALM, constraintName=FK_TRAF444KK6QRKMS7N56AIWQ5Y; dropForeignKeyConstraint baseTableName=KEYCLOAK_ROLE, constraintName=FK_KJHO5LE2C0RAL09FL8CM9WFW9		\N	4.25.1	\N	\N	0654969304
map-remove-ri	keycloak	META-INF/jpa-changelog-12.0.0.xml	2024-07-10 23:42:54.555574	87	EXECUTED	9:a9ba7d47f065f041b7da856a81762021	dropForeignKeyConstraint baseTableName=REALM_DEFAULT_GROUPS, constraintName=FK_DEF_GROUPS_GROUP; dropForeignKeyConstraint baseTableName=REALM_DEFAULT_ROLES, constraintName=FK_H4WPD7W4HSOOLNI3H0SW7BTJE; dropForeignKeyConstraint baseTableName=CLIENT...		\N	4.25.1	\N	\N	0654969304
12.1.0-add-realm-localization-table	keycloak	META-INF/jpa-changelog-12.0.0.xml	2024-07-10 23:42:54.576967	88	EXECUTED	9:fffabce2bc01e1a8f5110d5278500065	createTable tableName=REALM_LOCALIZATIONS; addPrimaryKey tableName=REALM_LOCALIZATIONS		\N	4.25.1	\N	\N	0654969304
default-roles	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.61746	89	EXECUTED	9:fa8a5b5445e3857f4b010bafb5009957	addColumn tableName=REALM; customChange		\N	4.25.1	\N	\N	0654969304
default-roles-cleanup	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.692185	90	EXECUTED	9:67ac3241df9a8582d591c5ed87125f39	dropTable tableName=REALM_DEFAULT_ROLES; dropTable tableName=CLIENT_DEFAULT_ROLES		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-16844	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.71104	91	EXECUTED	9:ad1194d66c937e3ffc82386c050ba089	createIndex indexName=IDX_OFFLINE_USS_PRELOAD, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
map-remove-ri-13.0.0	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.719296	92	EXECUTED	9:d9be619d94af5a2f5d07b9f003543b91	dropForeignKeyConstraint baseTableName=DEFAULT_CLIENT_SCOPE, constraintName=FK_R_DEF_CLI_SCOPE_SCOPE; dropForeignKeyConstraint baseTableName=CLIENT_SCOPE_CLIENT, constraintName=FK_C_CLI_SCOPE_SCOPE; dropForeignKeyConstraint baseTableName=CLIENT_SC...		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-17992-drop-constraints	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.722417	93	MARK_RAN	9:544d201116a0fcc5a5da0925fbbc3bde	dropPrimaryKey constraintName=C_CLI_SCOPE_BIND, tableName=CLIENT_SCOPE_CLIENT; dropIndex indexName=IDX_CLSCOPE_CL, tableName=CLIENT_SCOPE_CLIENT; dropIndex indexName=IDX_CL_CLSCOPE, tableName=CLIENT_SCOPE_CLIENT		\N	4.25.1	\N	\N	0654969304
13.0.0-increase-column-size-federated	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.756742	94	EXECUTED	9:43c0c1055b6761b4b3e89de76d612ccf	modifyDataType columnName=CLIENT_ID, tableName=CLIENT_SCOPE_CLIENT; modifyDataType columnName=SCOPE_ID, tableName=CLIENT_SCOPE_CLIENT		\N	4.25.1	\N	\N	0654969304
13.0.0-KEYCLOAK-17992-recreate-constraints	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.760308	95	MARK_RAN	9:8bd711fd0330f4fe980494ca43ab1139	addNotNullConstraint columnName=CLIENT_ID, tableName=CLIENT_SCOPE_CLIENT; addNotNullConstraint columnName=SCOPE_ID, tableName=CLIENT_SCOPE_CLIENT; addPrimaryKey constraintName=C_CLI_SCOPE_BIND, tableName=CLIENT_SCOPE_CLIENT; createIndex indexName=...		\N	4.25.1	\N	\N	0654969304
json-string-accomodation-fixed	keycloak	META-INF/jpa-changelog-13.0.0.xml	2024-07-10 23:42:54.838755	96	EXECUTED	9:e07d2bc0970c348bb06fb63b1f82ddbf	addColumn tableName=REALM_ATTRIBUTE; update tableName=REALM_ATTRIBUTE; dropColumn columnName=VALUE, tableName=REALM_ATTRIBUTE; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=REALM_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-11019	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.896035	97	EXECUTED	9:24fb8611e97f29989bea412aa38d12b7	createIndex indexName=IDX_OFFLINE_CSS_PRELOAD, tableName=OFFLINE_CLIENT_SESSION; createIndex indexName=IDX_OFFLINE_USS_BY_USER, tableName=OFFLINE_USER_SESSION; createIndex indexName=IDX_OFFLINE_USS_BY_USERSESS, tableName=OFFLINE_USER_SESSION		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.898701	98	MARK_RAN	9:259f89014ce2506ee84740cbf7163aa7	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-revert	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.918684	99	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-supported-dbs	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.930654	100	EXECUTED	9:60ca84a0f8c94ec8c3504a5a3bc88ee8	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
14.0.0-KEYCLOAK-18286-unsupported-dbs	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.934431	101	MARK_RAN	9:d3d977031d431db16e2c181ce49d73e9	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
KEYCLOAK-17267-add-index-to-user-attributes	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.952961	102	EXECUTED	9:0b305d8d1277f3a89a0a53a659ad274c	createIndex indexName=IDX_USER_ATTRIBUTE_NAME, tableName=USER_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
KEYCLOAK-18146-add-saml-art-binding-identifier	keycloak	META-INF/jpa-changelog-14.0.0.xml	2024-07-10 23:42:54.960903	103	EXECUTED	9:2c374ad2cdfe20e2905a84c8fac48460	customChange		\N	4.25.1	\N	\N	0654969304
15.0.0-KEYCLOAK-18467	keycloak	META-INF/jpa-changelog-15.0.0.xml	2024-07-10 23:42:54.97174	104	EXECUTED	9:47a760639ac597360a8219f5b768b4de	addColumn tableName=REALM_LOCALIZATIONS; update tableName=REALM_LOCALIZATIONS; dropColumn columnName=TEXTS, tableName=REALM_LOCALIZATIONS; renameColumn newColumnName=TEXTS, oldColumnName=TEXTS_NEW, tableName=REALM_LOCALIZATIONS; addNotNullConstrai...		\N	4.25.1	\N	\N	0654969304
17.0.0-9562	keycloak	META-INF/jpa-changelog-17.0.0.xml	2024-07-10 23:42:54.98224	105	EXECUTED	9:a6272f0576727dd8cad2522335f5d99e	createIndex indexName=IDX_USER_SERVICE_ACCOUNT, tableName=USER_ENTITY		\N	4.25.1	\N	\N	0654969304
18.0.0-10625-IDX_ADMIN_EVENT_TIME	keycloak	META-INF/jpa-changelog-18.0.0.xml	2024-07-10 23:42:54.997442	106	EXECUTED	9:015479dbd691d9cc8669282f4828c41d	createIndex indexName=IDX_ADMIN_EVENT_TIME, tableName=ADMIN_EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
19.0.0-10135	keycloak	META-INF/jpa-changelog-19.0.0.xml	2024-07-10 23:42:55.005062	107	EXECUTED	9:9518e495fdd22f78ad6425cc30630221	customChange		\N	4.25.1	\N	\N	0654969304
20.0.0-12964-supported-dbs	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.017665	108	EXECUTED	9:e5f243877199fd96bcc842f27a1656ac	createIndex indexName=IDX_GROUP_ATT_BY_NAME_VALUE, tableName=GROUP_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
20.0.0-12964-unsupported-dbs	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.020086	109	MARK_RAN	9:1a6fcaa85e20bdeae0a9ce49b41946a5	createIndex indexName=IDX_GROUP_ATT_BY_NAME_VALUE, tableName=GROUP_ATTRIBUTE		\N	4.25.1	\N	\N	0654969304
client-attributes-string-accomodation-fixed	keycloak	META-INF/jpa-changelog-20.0.0.xml	2024-07-10 23:42:55.039726	110	EXECUTED	9:3f332e13e90739ed0c35b0b25b7822ca	addColumn tableName=CLIENT_ATTRIBUTES; update tableName=CLIENT_ATTRIBUTES; dropColumn columnName=VALUE, tableName=CLIENT_ATTRIBUTES; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
21.0.2-17277	keycloak	META-INF/jpa-changelog-21.0.2.xml	2024-07-10 23:42:55.045973	111	EXECUTED	9:7ee1f7a3fb8f5588f171fb9a6ab623c0	customChange		\N	4.25.1	\N	\N	0654969304
21.1.0-19404	keycloak	META-INF/jpa-changelog-21.1.0.xml	2024-07-10 23:42:55.270408	112	EXECUTED	9:3d7e830b52f33676b9d64f7f2b2ea634	modifyDataType columnName=DECISION_STRATEGY, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=LOGIC, tableName=RESOURCE_SERVER_POLICY; modifyDataType columnName=POLICY_ENFORCE_MODE, tableName=RESOURCE_SERVER		\N	4.25.1	\N	\N	0654969304
21.1.0-19404-2	keycloak	META-INF/jpa-changelog-21.1.0.xml	2024-07-10 23:42:55.281114	113	MARK_RAN	9:627d032e3ef2c06c0e1f73d2ae25c26c	addColumn tableName=RESOURCE_SERVER_POLICY; update tableName=RESOURCE_SERVER_POLICY; dropColumn columnName=DECISION_STRATEGY, tableName=RESOURCE_SERVER_POLICY; renameColumn newColumnName=DECISION_STRATEGY, oldColumnName=DECISION_STRATEGY_NEW, tabl...		\N	4.25.1	\N	\N	0654969304
22.0.0-17484-updated	keycloak	META-INF/jpa-changelog-22.0.0.xml	2024-07-10 23:42:55.292055	114	EXECUTED	9:90af0bfd30cafc17b9f4d6eccd92b8b3	customChange		\N	4.25.1	\N	\N	0654969304
22.0.5-24031	keycloak	META-INF/jpa-changelog-22.0.0.xml	2024-07-10 23:42:55.297618	115	MARK_RAN	9:a60d2d7b315ec2d3eba9e2f145f9df28	customChange		\N	4.25.1	\N	\N	0654969304
23.0.0-12062	keycloak	META-INF/jpa-changelog-23.0.0.xml	2024-07-10 23:42:55.517095	116	EXECUTED	9:2168fbe728fec46ae9baf15bf80927b8	addColumn tableName=COMPONENT_CONFIG; update tableName=COMPONENT_CONFIG; dropColumn columnName=VALUE, tableName=COMPONENT_CONFIG; renameColumn newColumnName=VALUE, oldColumnName=VALUE_NEW, tableName=COMPONENT_CONFIG		\N	4.25.1	\N	\N	0654969304
23.0.0-17258	keycloak	META-INF/jpa-changelog-23.0.0.xml	2024-07-10 23:42:55.533685	117	EXECUTED	9:36506d679a83bbfda85a27ea1864dca8	addColumn tableName=EVENT_ENTITY		\N	4.25.1	\N	\N	0654969304
24.0.0-9758	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.650221	118	EXECUTED	9:502c557a5189f600f0f445a9b49ebbce	addColumn tableName=USER_ATTRIBUTE; addColumn tableName=FED_USER_ATTRIBUTE; createIndex indexName=USER_ATTR_LONG_VALUES, tableName=USER_ATTRIBUTE; createIndex indexName=FED_USER_ATTR_LONG_VALUES, tableName=FED_USER_ATTRIBUTE; createIndex indexName...		\N	4.25.1	\N	\N	0654969304
24.0.0-26618-drop-index-if-present	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.664885	120	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.0-26618-reindex	keycloak	META-INF/jpa-changelog-24.0.0.xml	2024-07-10 23:42:55.704023	121	EXECUTED	9:08707c0f0db1cef6b352db03a60edc7f	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.2-27228	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.713875	122	EXECUTED	9:eaee11f6b8aa25d2cc6a84fb86fc6238	customChange		\N	4.25.1	\N	\N	0654969304
24.0.2-27967-drop-index-if-present	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.720165	123	MARK_RAN	9:04baaf56c116ed19951cbc2cca584022	dropIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
24.0.2-27967-reindex	keycloak	META-INF/jpa-changelog-24.0.2.xml	2024-07-10 23:42:55.724372	124	MARK_RAN	9:d3d977031d431db16e2c181ce49d73e9	createIndex indexName=IDX_CLIENT_ATT_BY_NAME_VALUE, tableName=CLIENT_ATTRIBUTES		\N	4.25.1	\N	\N	0654969304
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
1000	f	\N	\N
1001	f	\N	\N
\.


--
-- Data for Name: default_client_scope; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.default_client_scope (realm_id, scope_id, default_scope) FROM stdin;
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	9340b0b0-cdf3-4f1c-bdf4-b47f3c9a88a1	f
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f4edda41-8fc3-4995-89b0-1b67e5471969	t
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	efa99282-8b1b-45d3-83c2-92994b4b1f34	t
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	a27fab96-b997-482d-97b6-3feafd230f88	t
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	98c490bc-f91f-46d7-a43e-692ad6680fb4	f
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f4922d7f-6e65-45d2-8236-6d0b9373f9ec	f
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	c119aa7e-e6f4-44b2-9319-5ab700773d95	t
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f8ea87f2-5324-41ae-80b2-6211b7264dfb	t
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	2fa7959e-6a12-45bd-b7de-1d0d899ecea9	f
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f	t
43c36b1e-22ce-4293-8d6e-181a68f70b2a	868bf2e4-0846-432a-83f8-a3d1722d7b8b	t
43c36b1e-22ce-4293-8d6e-181a68f70b2a	7b70bab9-acbf-46fd-afd3-07a922e1e248	t
43c36b1e-22ce-4293-8d6e-181a68f70b2a	74e2fe9c-cf28-414b-9ac1-69b8341d5413	t
43c36b1e-22ce-4293-8d6e-181a68f70b2a	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656	t
43c36b1e-22ce-4293-8d6e-181a68f70b2a	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce	t
43c36b1e-22ce-4293-8d6e-181a68f70b2a	3cca6cb8-d31d-47c6-b906-6edc41b12173	t
43c36b1e-22ce-4293-8d6e-181a68f70b2a	fd9ab351-6886-426c-9911-c98ab6cd5e95	f
43c36b1e-22ce-4293-8d6e-181a68f70b2a	f54202d6-f14e-420f-b60c-64775b2b9132	f
43c36b1e-22ce-4293-8d6e-181a68f70b2a	df80f1b1-5b4e-4032-b0f1-d02f24a48d17	f
43c36b1e-22ce-4293-8d6e-181a68f70b2a	7fe8e74f-ddf7-4936-bd43-8280c0443525	f
\.


--
-- Data for Name: event_entity; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.event_entity (id, client_id, details_json, error, ip_address, realm_id, session_id, event_time, type, user_id, details_json_long_value) FROM stdin;
\.


--
-- Data for Name: fed_user_attribute; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.fed_user_attribute (id, name, user_id, realm_id, storage_provider_id, value, long_value_hash, long_value_hash_lower_case, long_value) FROM stdin;
\.


--
-- Data for Name: fed_user_consent; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.fed_user_consent (id, client_id, user_id, realm_id, storage_provider_id, created_date, last_updated_date, client_storage_provider, external_client_id) FROM stdin;
\.


--
-- Data for Name: fed_user_consent_cl_scope; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.fed_user_consent_cl_scope (user_consent_id, scope_id) FROM stdin;
\.


--
-- Data for Name: fed_user_credential; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.fed_user_credential (id, salt, type, created_date, user_id, realm_id, storage_provider_id, user_label, secret_data, credential_data, priority) FROM stdin;
\.


--
-- Data for Name: fed_user_group_membership; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.fed_user_group_membership (group_id, user_id, realm_id, storage_provider_id) FROM stdin;
\.


--
-- Data for Name: fed_user_required_action; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.fed_user_required_action (required_action, user_id, realm_id, storage_provider_id) FROM stdin;
\.


--
-- Data for Name: fed_user_role_mapping; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.fed_user_role_mapping (role_id, user_id, realm_id, storage_provider_id) FROM stdin;
\.


--
-- Data for Name: federated_identity; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.federated_identity (identity_provider, realm_id, federated_user_id, federated_username, token, user_id) FROM stdin;
\.


--
-- Data for Name: federated_user; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.federated_user (id, storage_provider_id, realm_id) FROM stdin;
\.


--
-- Data for Name: group_attribute; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.group_attribute (id, name, value, group_id) FROM stdin;
\.


--
-- Data for Name: group_role_mapping; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.group_role_mapping (role_id, group_id) FROM stdin;
\.


--
-- Data for Name: identity_provider; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.identity_provider (internal_id, enabled, provider_alias, provider_id, store_token, authenticate_by_default, realm_id, add_token_role, trust_email, first_broker_login_flow_id, post_broker_login_flow_id, provider_display_name, link_only) FROM stdin;
\.


--
-- Data for Name: identity_provider_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.identity_provider_config (identity_provider_id, value, name) FROM stdin;
\.


--
-- Data for Name: identity_provider_mapper; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.identity_provider_mapper (id, name, idp_alias, idp_mapper_name, realm_id) FROM stdin;
\.


--
-- Data for Name: idp_mapper_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.idp_mapper_config (idp_mapper_id, value, name) FROM stdin;
\.


--
-- Data for Name: keycloak_group; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.keycloak_group (id, name, parent_group, realm_id) FROM stdin;
\.


--
-- Data for Name: keycloak_role; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.keycloak_role (id, client_realm_constraint, client_role, description, name, realm_id, client, realm) FROM stdin;
1f0f7753-5ef0-4083-8641-173682edf64d	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f	${role_default-roles}	default-roles-master	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N	\N
6b04c0c3-31eb-4180-a753-2d67b4e540a5	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f	${role_admin}	admin	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N	\N
d00996f9-3ced-4b67-90dc-d87bf7651e79	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f	${role_create-realm}	create-realm	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N	\N
b0e7ba35-f8a8-497e-9952-58983b62f295	122557f0-b934-4094-a8f5-36579c416a87	t	${role_create-client}	create-client	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
6afdadc5-36ed-4281-b0e2-a001afa458b7	122557f0-b934-4094-a8f5-36579c416a87	t	${role_view-realm}	view-realm	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
6514cf70-3b5b-4c9b-ab40-0bc400dd5a28	122557f0-b934-4094-a8f5-36579c416a87	t	${role_view-users}	view-users	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
c950aa8a-1bf4-4da3-af04-153a0e742291	122557f0-b934-4094-a8f5-36579c416a87	t	${role_view-clients}	view-clients	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
64977b56-2d0a-426e-98f2-659b3fb78f25	122557f0-b934-4094-a8f5-36579c416a87	t	${role_view-events}	view-events	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
48653960-6b09-47b8-afb4-d3bfdadf9994	122557f0-b934-4094-a8f5-36579c416a87	t	${role_view-identity-providers}	view-identity-providers	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
9efd6572-16a7-4422-9268-8b99c12efc68	122557f0-b934-4094-a8f5-36579c416a87	t	${role_view-authorization}	view-authorization	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
d709e2c5-3ce1-4aa4-a371-dfc2623bbfdf	122557f0-b934-4094-a8f5-36579c416a87	t	${role_manage-realm}	manage-realm	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
ff9f52ed-2e3d-49dd-8f51-2e3b9542e35a	122557f0-b934-4094-a8f5-36579c416a87	t	${role_manage-users}	manage-users	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
60801c0e-15a7-49fd-a61c-105ea63360de	122557f0-b934-4094-a8f5-36579c416a87	t	${role_manage-clients}	manage-clients	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
c7e3af63-8147-4b34-ac12-463a219c75d1	122557f0-b934-4094-a8f5-36579c416a87	t	${role_manage-events}	manage-events	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
8c8600e2-a36e-47e6-8173-0fe10ae0f3b2	122557f0-b934-4094-a8f5-36579c416a87	t	${role_manage-identity-providers}	manage-identity-providers	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
20e99be8-9755-499f-b8f3-f80a7132e0e0	122557f0-b934-4094-a8f5-36579c416a87	t	${role_manage-authorization}	manage-authorization	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
d6c7b275-03de-43d7-abea-63490168d3d9	122557f0-b934-4094-a8f5-36579c416a87	t	${role_query-users}	query-users	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
a2bc9a43-ec83-43a5-b302-e5a719788fff	122557f0-b934-4094-a8f5-36579c416a87	t	${role_query-clients}	query-clients	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
ba74ce3f-9931-4334-ab6b-5f5ae65df4d3	122557f0-b934-4094-a8f5-36579c416a87	t	${role_query-realms}	query-realms	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
3c340347-77ab-465c-ad5b-01d95d231264	122557f0-b934-4094-a8f5-36579c416a87	t	${role_query-groups}	query-groups	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
ba976cf2-8c29-4513-a933-6b8c1148330d	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_view-profile}	view-profile	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
a1b72506-aa0a-4955-aee3-138855abe878	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_manage-account}	manage-account	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
4ecbb52c-9bfe-4d73-a7d7-6962290de3ad	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_manage-account-links}	manage-account-links	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
14307e44-aab2-4316-8d4e-532e2584c88a	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_view-applications}	view-applications	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
c7592195-a988-4074-a994-d08e5f62ab60	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_view-consent}	view-consent	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
d00f018f-f848-42c5-8221-c46227f6be19	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_manage-consent}	manage-consent	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
b8222eb3-2016-4ffe-9795-787925f6c1c9	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_view-groups}	view-groups	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
19a8a544-c2f4-4e82-b895-7906a2925c0e	3f63aef2-fb73-4e1a-884e-5cd61ef71406	t	${role_delete-account}	delete-account	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	3f63aef2-fb73-4e1a-884e-5cd61ef71406	\N
c06bfe11-ce61-4f6c-9e3b-7334eff81b26	14139360-cf57-4820-ba85-7e1479912b93	t	${role_read-token}	read-token	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	14139360-cf57-4820-ba85-7e1479912b93	\N
c49fcb47-86fb-4693-836e-ed1dc3318345	122557f0-b934-4094-a8f5-36579c416a87	t	${role_impersonation}	impersonation	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	122557f0-b934-4094-a8f5-36579c416a87	\N
3b9dced1-74c3-4f76-ab51-9a07df38585c	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f	${role_offline-access}	offline_access	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N	\N
d75717c5-50b3-487f-ad0f-8c514e213b5f	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f	${role_uma_authorization}	uma_authorization	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	\N	\N
9b39afcd-e04a-44e9-8074-6e42485a9a92	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_manage-realm}	manage-realm	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
74766a90-cae1-4b4d-84cc-969b06d3dbee	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_manage-users}	manage-users	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
9c10ddad-0f44-4ebd-993e-384b379c7b86	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_manage-clients}	manage-clients	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
f3d51c75-5f7e-4b36-a237-c155baf04e61	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_manage-events}	manage-events	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
889ae762-90c3-4660-a1ab-116b97c91387	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f	${role_default-roles}	default-roles-firmas-food-dev	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N	\N
857bf84e-fb3f-43ae-b05c-ebdc2259a352	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_create-client}	create-client	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
4507d89d-0b96-4b5d-86d3-69e61ea853e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_view-realm}	view-realm	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
76bf2360-9e8b-47c1-937a-dcc5b78ec0c1	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_view-users}	view-users	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
dd403b4c-068f-481f-8022-16aba8402363	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_view-clients}	view-clients	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
54b9cfda-1a0a-4804-9bb0-c51c014fabc0	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_view-events}	view-events	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
59efd6b6-b872-428d-9421-598cc3d37fd4	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_view-identity-providers}	view-identity-providers	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
1dc9d122-7749-4ead-ae84-a22497f4d675	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_view-authorization}	view-authorization	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
f4f660ba-5b65-42cd-8aab-4fc460ed586a	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_manage-identity-providers}	manage-identity-providers	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
a791dd88-dd20-4f37-a7b5-faf2bcf236e8	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_manage-authorization}	manage-authorization	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
6c9d8878-fef9-4125-9d63-399b331c737b	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_query-users}	query-users	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
a0ace9e3-7276-4734-9501-7f1213bfb65d	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_query-clients}	query-clients	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
00d13db5-59d0-46fe-8dfa-3473f82dc34e	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_query-realms}	query-realms	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
72670e98-92aa-440c-8a37-5765552ae6c1	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_query-groups}	query-groups	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
b24f38be-3778-4b07-87e1-2af29038a967	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f	${role_offline-access}	offline_access	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N	\N
d56ee8dc-3e1b-4963-8917-35b8934e352b	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f	${role_uma_authorization}	uma_authorization	43c36b1e-22ce-4293-8d6e-181a68f70b2a	\N	\N
9178d8a6-1dfe-45d9-bd93-b510a48397e9	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_manage-events}	manage-events	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
d151fa43-d5e8-4a33-a40b-9e743c6fcd74	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_query-clients}	query-clients	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
0f3aef0e-84d6-4c9c-ab98-c4796ecaf842	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_manage-clients}	manage-clients	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
9f113135-3b37-4cf1-af3f-e425ffb5e21d	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_query-groups}	query-groups	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
c1aa3daf-4831-4762-81d1-4b3ca015308a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_view-events}	view-events	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
7b8376ce-61ae-487a-95ea-5543b57e4cfb	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_query-users}	query-users	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
5703a051-85e8-4e23-b201-8e88b163ccb5	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_view-authorization}	view-authorization	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
2c3289bf-5864-48d7-8bae-53fdc7879c35	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_manage-realm}	manage-realm	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
38d7dd11-c64f-4786-a4cc-16802299f6b4	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_view-users}	view-users	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
5bb1acb3-0813-4fbb-a02b-0b62428977e0	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_query-realms}	query-realms	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
a2a5dbd1-194a-4363-a521-feea3f81a1db	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_impersonation}	impersonation	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
d87ebd33-9c7d-44bd-86e3-73b55f2b9281	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_manage-users}	manage-users	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
c6ca4f6d-2295-4a3b-9109-904534fc8c80	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_create-client}	create-client	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
5bdbbfdb-a86e-4161-be1d-eeb87f472128	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_realm-admin}	realm-admin	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
4af4e5c5-995d-479f-be5d-2de1eec9c44a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_manage-authorization}	manage-authorization	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
77fa02bf-af89-41d8-95b4-b0a6e3c09496	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_view-realm}	view-realm	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
4e409c41-94ea-4fa9-9c2f-fd7f5049a2f5	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_view-clients}	view-clients	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
172ecf05-e058-4f34-9d49-fe27633d80a3	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_manage-identity-providers}	manage-identity-providers	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
ac9d1596-c34b-4daf-8e8f-ac0b1ffce403	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	t	${role_view-identity-providers}	view-identity-providers	43c36b1e-22ce-4293-8d6e-181a68f70b2a	b7725f50-c6db-4a3f-a057-ea61ce3ed36c	\N
40bebe9a-aa28-4e92-89a6-9043ed0e8eda	1f97db26-efee-41e7-ac44-e6fa35fd1fa0	t	\N	uma_protection	43c36b1e-22ce-4293-8d6e-181a68f70b2a	1f97db26-efee-41e7-ac44-e6fa35fd1fa0	\N
082a9fa4-9f7b-4189-8b90-f47fd1fe0c9e	c91b6aba-8136-4311-b037-bcc7f590dc7e	t	\N	uma_protection	43c36b1e-22ce-4293-8d6e-181a68f70b2a	c91b6aba-8136-4311-b037-bcc7f590dc7e	\N
b86211bd-25fc-498d-a12c-2ad749c2c105	19f7fd43-a831-423f-b424-96312d17bfd5	t	${role_read-token}	read-token	43c36b1e-22ce-4293-8d6e-181a68f70b2a	19f7fd43-a831-423f-b424-96312d17bfd5	\N
af20813b-efa1-4862-8ed5-5bb4092a3112	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_manage-account-links}	manage-account-links	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
b80002c9-23f4-466b-bfb8-19bc45888640	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_view-profile}	view-profile	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
0954fe84-e6c1-4183-85ac-55d4ddcc7521	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_view-consent}	view-consent	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
f4e266f8-9f0f-4352-8a3b-3c05b91f9dc1	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_manage-account}	manage-account	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
9414f138-8ce8-4e88-927e-9ce0d7bcce88	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_delete-account}	delete-account	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
e9715274-3949-45ea-8156-6d5b9de32421	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_manage-consent}	manage-consent	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
464752e4-0bee-4b21-a8e8-707b50e2d20b	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_view-applications}	view-applications	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
d499a30b-0493-4565-a74e-602c50c5841e	e87b6b71-1178-409e-b4ff-5969eb94b377	t	${role_view-groups}	view-groups	43c36b1e-22ce-4293-8d6e-181a68f70b2a	e87b6b71-1178-409e-b4ff-5969eb94b377	\N
d854bde3-932b-45c6-ac3c-dbaaffe3d2ac	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	t	${role_impersonation}	impersonation	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	\N
\.


--
-- Data for Name: migration_model; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.migration_model (id, version, update_time) FROM stdin;
c92gl	24.0.3	1720654976
\.


--
-- Data for Name: offline_client_session; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.offline_client_session (user_session_id, client_id, offline_flag, "timestamp", data, client_storage_provider, external_client_id) FROM stdin;
\.


--
-- Data for Name: offline_user_session; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.offline_user_session (user_session_id, user_id, realm_id, created_on, offline_flag, data, last_session_refresh) FROM stdin;
\.


--
-- Data for Name: policy_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.policy_config (policy_id, name, value) FROM stdin;
\.


--
-- Data for Name: protocol_mapper; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.protocol_mapper (id, name, protocol, protocol_mapper_name, client_id, client_scope_id) FROM stdin;
77bb2807-466e-47f3-bcf5-608f335eee74	audience resolve	openid-connect	oidc-audience-resolve-mapper	795b4b60-c45a-4377-8da9-8471c55091d0	\N
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	locale	openid-connect	oidc-usermodel-attribute-mapper	787aea5c-fd15-4af6-b6a9-442b26950af5	\N
eef35f99-227e-4457-8ec3-79174ac7c9db	role list	saml	saml-role-list-mapper	\N	f4edda41-8fc3-4995-89b0-1b67e5471969
a8056061-9e89-451b-8fd2-5b788e62972a	full name	openid-connect	oidc-full-name-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
60f96efc-29e3-431f-9900-e22afd1b48df	family name	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	given name	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	middle name	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
bf32bc27-ea31-4c13-81b8-9f856f597485	nickname	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
ae972150-864b-43ac-b3e5-0bf2765e7280	username	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
58ea3515-62c6-4151-9b40-07fa5d5ec444	profile	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
50c696f1-e91c-4e6d-a37f-736dc92be1ec	picture	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
9daca76e-91e4-417f-97c4-cfd61e87f65c	website	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
d4f73ab8-d5d1-4705-a361-d9ce2f149778	gender	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	birthdate	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	zoneinfo	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
2690fdae-67e7-4e3d-b31c-443af5550a42	locale	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
a6c29b1b-4af7-4eb6-8240-11d2956a258d	updated at	openid-connect	oidc-usermodel-attribute-mapper	\N	efa99282-8b1b-45d3-83c2-92994b4b1f34
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	email	openid-connect	oidc-usermodel-attribute-mapper	\N	a27fab96-b997-482d-97b6-3feafd230f88
894ea610-f8f5-4a48-a0b6-0c8d21da7229	email verified	openid-connect	oidc-usermodel-property-mapper	\N	a27fab96-b997-482d-97b6-3feafd230f88
197ae84c-abbf-40b7-8b14-c88dcd565069	address	openid-connect	oidc-address-mapper	\N	98c490bc-f91f-46d7-a43e-692ad6680fb4
2837e749-d4a9-44f8-add4-6176304a032b	phone number	openid-connect	oidc-usermodel-attribute-mapper	\N	f4922d7f-6e65-45d2-8236-6d0b9373f9ec
8a870332-8776-481f-b1b7-14c9b6e38b05	phone number verified	openid-connect	oidc-usermodel-attribute-mapper	\N	f4922d7f-6e65-45d2-8236-6d0b9373f9ec
e286baac-70a3-4e40-a2a3-5c27d1a068af	realm roles	openid-connect	oidc-usermodel-realm-role-mapper	\N	c119aa7e-e6f4-44b2-9319-5ab700773d95
38efabcd-1969-46cc-bc3e-e2ea82bd96f8	client roles	openid-connect	oidc-usermodel-client-role-mapper	\N	c119aa7e-e6f4-44b2-9319-5ab700773d95
2ade9982-7faf-4aa0-a3a3-53672617f7a3	audience resolve	openid-connect	oidc-audience-resolve-mapper	\N	c119aa7e-e6f4-44b2-9319-5ab700773d95
23304ce9-a28f-4735-ac97-63bbfd07990c	allowed web origins	openid-connect	oidc-allowed-origins-mapper	\N	f8ea87f2-5324-41ae-80b2-6211b7264dfb
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	upn	openid-connect	oidc-usermodel-attribute-mapper	\N	2fa7959e-6a12-45bd-b7de-1d0d899ecea9
ce6d844f-5d20-4634-be77-eebc912b3106	groups	openid-connect	oidc-usermodel-realm-role-mapper	\N	2fa7959e-6a12-45bd-b7de-1d0d899ecea9
cd4e4bbb-6786-4272-858c-6efc2a65cf73	acr loa level	openid-connect	oidc-acr-mapper	\N	3b3c4e11-7897-4c6c-88c5-c6dc71d1313f
c199fb0e-f0a7-4ca6-80c7-07939515c185	middle name	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	given name	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
68243aae-4311-43f2-b00e-726a91649966	locale	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
12fa08ca-6a47-492f-9623-e68b061edc68	nickname	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	username	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
815ebaac-aa32-4838-b125-d442aa716ebe	picture	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	gender	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	birthdate	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
cdc251d7-3a67-4210-8048-20bed0d020c1	profile	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	updated at	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
8933fee0-806a-4216-a592-6da5d84a8426	full name	openid-connect	oidc-full-name-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
378157b6-56b9-4deb-b19a-b48d8dde26f9	zoneinfo	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	website	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
6f6f69e7-4abf-42a8-b093-343389fe2a10	family name	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
4e7f3478-715f-4102-9792-b5f7e0054b50	restaurante	openid-connect	oidc-usermodel-attribute-mapper	\N	7b70bab9-acbf-46fd-afd3-07a922e1e248
6c416f33-b528-4ef0-8d9e-b093c0f36e83	allowed web origins	openid-connect	oidc-allowed-origins-mapper	\N	53f54bb2-17d2-4c8d-badc-fa85cff6a4ce
6349f0bf-8ba4-4ab5-ad5d-fe9e47352a93	role list	saml	saml-role-list-mapper	\N	868bf2e4-0846-432a-83f8-a3d1722d7b8b
90e025c6-dd67-4d62-8989-cfb7ffff9369	client roles	openid-connect	oidc-usermodel-client-role-mapper	\N	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656
d3c632df-e625-4333-ba44-b6655edef278	audience resolve	openid-connect	oidc-audience-resolve-mapper	\N	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656
8e771959-c0e3-4ad2-b4e0-cab5e050a69a	realm roles	openid-connect	oidc-usermodel-realm-role-mapper	\N	cf5cc7d7-8fae-4aed-ad9f-f2bc9c339656
b12c8e90-17db-46a6-a310-b3e2290d4de4	address	openid-connect	oidc-address-mapper	\N	f54202d6-f14e-420f-b60c-64775b2b9132
797b1744-3c6b-4dca-86c1-1214e801b8c9	email	openid-connect	oidc-usermodel-attribute-mapper	\N	74e2fe9c-cf28-414b-9ac1-69b8341d5413
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	email verified	openid-connect	oidc-usermodel-property-mapper	\N	74e2fe9c-cf28-414b-9ac1-69b8341d5413
9f88c8b3-7e13-4767-842d-a5498fda3745	phone number	openid-connect	oidc-usermodel-attribute-mapper	\N	df80f1b1-5b4e-4032-b0f1-d02f24a48d17
3a63af56-6e69-456d-a6f4-173dfd7ce149	phone number verified	openid-connect	oidc-usermodel-attribute-mapper	\N	df80f1b1-5b4e-4032-b0f1-d02f24a48d17
4707d4f0-c368-41cd-9fcf-e7d003dead76	acr loa level	openid-connect	oidc-acr-mapper	\N	3cca6cb8-d31d-47c6-b906-6edc41b12173
778b52ee-dd5b-4c12-9d21-11645e54ae98	groups	openid-connect	oidc-usermodel-realm-role-mapper	\N	7fe8e74f-ddf7-4936-bd43-8280c0443525
b825391c-c41f-49df-9a0e-acc90b693cbe	upn	openid-connect	oidc-usermodel-attribute-mapper	\N	7fe8e74f-ddf7-4936-bd43-8280c0443525
395a9834-e1e5-4fdd-8c7f-286a9d65dfca	audience resolve	openid-connect	oidc-audience-resolve-mapper	450ad3d9-9b7c-4b7d-9044-d828e8a708af	\N
81d38b67-3a5a-4d71-8622-68c4f92930fe	Client ID	openid-connect	oidc-usersessionmodel-note-mapper	c91b6aba-8136-4311-b037-bcc7f590dc7e	\N
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	Client IP Address	openid-connect	oidc-usersessionmodel-note-mapper	c91b6aba-8136-4311-b037-bcc7f590dc7e	\N
7f37977b-ebfc-4b2b-bcb1-93746648627b	Client Host	openid-connect	oidc-usersessionmodel-note-mapper	c91b6aba-8136-4311-b037-bcc7f590dc7e	\N
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	Client IP Address	openid-connect	oidc-usersessionmodel-note-mapper	1f97db26-efee-41e7-ac44-e6fa35fd1fa0	\N
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	Client ID	openid-connect	oidc-usersessionmodel-note-mapper	1f97db26-efee-41e7-ac44-e6fa35fd1fa0	\N
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	Client Host	openid-connect	oidc-usersessionmodel-note-mapper	1f97db26-efee-41e7-ac44-e6fa35fd1fa0	\N
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	locale	openid-connect	oidc-usermodel-attribute-mapper	3ce679a2-0f55-4679-b3cb-c957e20e2ac0	\N
\.


--
-- Data for Name: protocol_mapper_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.protocol_mapper_config (protocol_mapper_id, value, name) FROM stdin;
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	true	introspection.token.claim
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	true	userinfo.token.claim
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	locale	user.attribute
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	true	id.token.claim
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	true	access.token.claim
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	locale	claim.name
b4e990ce-831e-4ec0-8dbf-5eddfb70f642	String	jsonType.label
eef35f99-227e-4457-8ec3-79174ac7c9db	false	single
eef35f99-227e-4457-8ec3-79174ac7c9db	Basic	attribute.nameformat
eef35f99-227e-4457-8ec3-79174ac7c9db	Role	attribute.name
2690fdae-67e7-4e3d-b31c-443af5550a42	true	introspection.token.claim
2690fdae-67e7-4e3d-b31c-443af5550a42	true	userinfo.token.claim
2690fdae-67e7-4e3d-b31c-443af5550a42	locale	user.attribute
2690fdae-67e7-4e3d-b31c-443af5550a42	true	id.token.claim
2690fdae-67e7-4e3d-b31c-443af5550a42	true	access.token.claim
2690fdae-67e7-4e3d-b31c-443af5550a42	locale	claim.name
2690fdae-67e7-4e3d-b31c-443af5550a42	String	jsonType.label
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	true	introspection.token.claim
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	true	userinfo.token.claim
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	firstName	user.attribute
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	true	id.token.claim
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	true	access.token.claim
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	given_name	claim.name
2a1a27be-fff0-4d2b-9786-22ff9a8fa9d3	String	jsonType.label
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	true	introspection.token.claim
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	true	userinfo.token.claim
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	birthdate	user.attribute
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	true	id.token.claim
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	true	access.token.claim
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	birthdate	claim.name
4fe88d05-b4b2-4f52-ae8f-3d695212dd80	String	jsonType.label
50c696f1-e91c-4e6d-a37f-736dc92be1ec	true	introspection.token.claim
50c696f1-e91c-4e6d-a37f-736dc92be1ec	true	userinfo.token.claim
50c696f1-e91c-4e6d-a37f-736dc92be1ec	picture	user.attribute
50c696f1-e91c-4e6d-a37f-736dc92be1ec	true	id.token.claim
50c696f1-e91c-4e6d-a37f-736dc92be1ec	true	access.token.claim
50c696f1-e91c-4e6d-a37f-736dc92be1ec	picture	claim.name
50c696f1-e91c-4e6d-a37f-736dc92be1ec	String	jsonType.label
58ea3515-62c6-4151-9b40-07fa5d5ec444	true	introspection.token.claim
58ea3515-62c6-4151-9b40-07fa5d5ec444	true	userinfo.token.claim
58ea3515-62c6-4151-9b40-07fa5d5ec444	profile	user.attribute
58ea3515-62c6-4151-9b40-07fa5d5ec444	true	id.token.claim
58ea3515-62c6-4151-9b40-07fa5d5ec444	true	access.token.claim
58ea3515-62c6-4151-9b40-07fa5d5ec444	profile	claim.name
58ea3515-62c6-4151-9b40-07fa5d5ec444	String	jsonType.label
60f96efc-29e3-431f-9900-e22afd1b48df	true	introspection.token.claim
60f96efc-29e3-431f-9900-e22afd1b48df	true	userinfo.token.claim
60f96efc-29e3-431f-9900-e22afd1b48df	lastName	user.attribute
60f96efc-29e3-431f-9900-e22afd1b48df	true	id.token.claim
60f96efc-29e3-431f-9900-e22afd1b48df	true	access.token.claim
60f96efc-29e3-431f-9900-e22afd1b48df	family_name	claim.name
60f96efc-29e3-431f-9900-e22afd1b48df	String	jsonType.label
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	true	introspection.token.claim
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	true	userinfo.token.claim
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	zoneinfo	user.attribute
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	true	id.token.claim
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	true	access.token.claim
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	zoneinfo	claim.name
85f0e3a5-5456-4f0e-a3b6-11efcb5a6b86	String	jsonType.label
9daca76e-91e4-417f-97c4-cfd61e87f65c	true	introspection.token.claim
9daca76e-91e4-417f-97c4-cfd61e87f65c	true	userinfo.token.claim
9daca76e-91e4-417f-97c4-cfd61e87f65c	website	user.attribute
9daca76e-91e4-417f-97c4-cfd61e87f65c	true	id.token.claim
9daca76e-91e4-417f-97c4-cfd61e87f65c	true	access.token.claim
9daca76e-91e4-417f-97c4-cfd61e87f65c	website	claim.name
9daca76e-91e4-417f-97c4-cfd61e87f65c	String	jsonType.label
a6c29b1b-4af7-4eb6-8240-11d2956a258d	true	introspection.token.claim
a6c29b1b-4af7-4eb6-8240-11d2956a258d	true	userinfo.token.claim
a6c29b1b-4af7-4eb6-8240-11d2956a258d	updatedAt	user.attribute
a6c29b1b-4af7-4eb6-8240-11d2956a258d	true	id.token.claim
a6c29b1b-4af7-4eb6-8240-11d2956a258d	true	access.token.claim
a6c29b1b-4af7-4eb6-8240-11d2956a258d	updated_at	claim.name
a6c29b1b-4af7-4eb6-8240-11d2956a258d	long	jsonType.label
a8056061-9e89-451b-8fd2-5b788e62972a	true	introspection.token.claim
a8056061-9e89-451b-8fd2-5b788e62972a	true	userinfo.token.claim
a8056061-9e89-451b-8fd2-5b788e62972a	true	id.token.claim
a8056061-9e89-451b-8fd2-5b788e62972a	true	access.token.claim
ae972150-864b-43ac-b3e5-0bf2765e7280	true	introspection.token.claim
ae972150-864b-43ac-b3e5-0bf2765e7280	true	userinfo.token.claim
ae972150-864b-43ac-b3e5-0bf2765e7280	username	user.attribute
ae972150-864b-43ac-b3e5-0bf2765e7280	true	id.token.claim
ae972150-864b-43ac-b3e5-0bf2765e7280	true	access.token.claim
ae972150-864b-43ac-b3e5-0bf2765e7280	preferred_username	claim.name
ae972150-864b-43ac-b3e5-0bf2765e7280	String	jsonType.label
bf32bc27-ea31-4c13-81b8-9f856f597485	true	introspection.token.claim
bf32bc27-ea31-4c13-81b8-9f856f597485	true	userinfo.token.claim
bf32bc27-ea31-4c13-81b8-9f856f597485	nickname	user.attribute
bf32bc27-ea31-4c13-81b8-9f856f597485	true	id.token.claim
bf32bc27-ea31-4c13-81b8-9f856f597485	true	access.token.claim
bf32bc27-ea31-4c13-81b8-9f856f597485	nickname	claim.name
bf32bc27-ea31-4c13-81b8-9f856f597485	String	jsonType.label
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	true	introspection.token.claim
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	true	userinfo.token.claim
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	middleName	user.attribute
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	true	id.token.claim
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	true	access.token.claim
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	middle_name	claim.name
d0c3575f-b9f2-490d-ba93-d1ae76a6d102	String	jsonType.label
d4f73ab8-d5d1-4705-a361-d9ce2f149778	true	introspection.token.claim
d4f73ab8-d5d1-4705-a361-d9ce2f149778	true	userinfo.token.claim
d4f73ab8-d5d1-4705-a361-d9ce2f149778	gender	user.attribute
d4f73ab8-d5d1-4705-a361-d9ce2f149778	true	id.token.claim
d4f73ab8-d5d1-4705-a361-d9ce2f149778	true	access.token.claim
d4f73ab8-d5d1-4705-a361-d9ce2f149778	gender	claim.name
d4f73ab8-d5d1-4705-a361-d9ce2f149778	String	jsonType.label
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	true	introspection.token.claim
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	true	userinfo.token.claim
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	email	user.attribute
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	true	id.token.claim
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	true	access.token.claim
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	email	claim.name
74ae2d86-0c7b-4c5e-8fdc-0a7314c2e604	String	jsonType.label
894ea610-f8f5-4a48-a0b6-0c8d21da7229	true	introspection.token.claim
894ea610-f8f5-4a48-a0b6-0c8d21da7229	true	userinfo.token.claim
894ea610-f8f5-4a48-a0b6-0c8d21da7229	emailVerified	user.attribute
894ea610-f8f5-4a48-a0b6-0c8d21da7229	true	id.token.claim
894ea610-f8f5-4a48-a0b6-0c8d21da7229	true	access.token.claim
894ea610-f8f5-4a48-a0b6-0c8d21da7229	email_verified	claim.name
894ea610-f8f5-4a48-a0b6-0c8d21da7229	boolean	jsonType.label
197ae84c-abbf-40b7-8b14-c88dcd565069	formatted	user.attribute.formatted
197ae84c-abbf-40b7-8b14-c88dcd565069	country	user.attribute.country
197ae84c-abbf-40b7-8b14-c88dcd565069	true	introspection.token.claim
197ae84c-abbf-40b7-8b14-c88dcd565069	postal_code	user.attribute.postal_code
197ae84c-abbf-40b7-8b14-c88dcd565069	true	userinfo.token.claim
197ae84c-abbf-40b7-8b14-c88dcd565069	street	user.attribute.street
197ae84c-abbf-40b7-8b14-c88dcd565069	true	id.token.claim
197ae84c-abbf-40b7-8b14-c88dcd565069	region	user.attribute.region
197ae84c-abbf-40b7-8b14-c88dcd565069	true	access.token.claim
197ae84c-abbf-40b7-8b14-c88dcd565069	locality	user.attribute.locality
2837e749-d4a9-44f8-add4-6176304a032b	true	introspection.token.claim
2837e749-d4a9-44f8-add4-6176304a032b	true	userinfo.token.claim
2837e749-d4a9-44f8-add4-6176304a032b	phoneNumber	user.attribute
2837e749-d4a9-44f8-add4-6176304a032b	true	id.token.claim
2837e749-d4a9-44f8-add4-6176304a032b	true	access.token.claim
2837e749-d4a9-44f8-add4-6176304a032b	phone_number	claim.name
2837e749-d4a9-44f8-add4-6176304a032b	String	jsonType.label
8a870332-8776-481f-b1b7-14c9b6e38b05	true	introspection.token.claim
8a870332-8776-481f-b1b7-14c9b6e38b05	true	userinfo.token.claim
8a870332-8776-481f-b1b7-14c9b6e38b05	phoneNumberVerified	user.attribute
8a870332-8776-481f-b1b7-14c9b6e38b05	true	id.token.claim
8a870332-8776-481f-b1b7-14c9b6e38b05	true	access.token.claim
8a870332-8776-481f-b1b7-14c9b6e38b05	phone_number_verified	claim.name
8a870332-8776-481f-b1b7-14c9b6e38b05	boolean	jsonType.label
2ade9982-7faf-4aa0-a3a3-53672617f7a3	true	introspection.token.claim
2ade9982-7faf-4aa0-a3a3-53672617f7a3	true	access.token.claim
38efabcd-1969-46cc-bc3e-e2ea82bd96f8	true	introspection.token.claim
38efabcd-1969-46cc-bc3e-e2ea82bd96f8	true	multivalued
38efabcd-1969-46cc-bc3e-e2ea82bd96f8	foo	user.attribute
38efabcd-1969-46cc-bc3e-e2ea82bd96f8	true	access.token.claim
38efabcd-1969-46cc-bc3e-e2ea82bd96f8	resource_access.${client_id}.roles	claim.name
38efabcd-1969-46cc-bc3e-e2ea82bd96f8	String	jsonType.label
e286baac-70a3-4e40-a2a3-5c27d1a068af	true	introspection.token.claim
e286baac-70a3-4e40-a2a3-5c27d1a068af	true	multivalued
e286baac-70a3-4e40-a2a3-5c27d1a068af	foo	user.attribute
e286baac-70a3-4e40-a2a3-5c27d1a068af	true	access.token.claim
e286baac-70a3-4e40-a2a3-5c27d1a068af	realm_access.roles	claim.name
e286baac-70a3-4e40-a2a3-5c27d1a068af	String	jsonType.label
23304ce9-a28f-4735-ac97-63bbfd07990c	true	introspection.token.claim
23304ce9-a28f-4735-ac97-63bbfd07990c	true	access.token.claim
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	true	introspection.token.claim
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	true	userinfo.token.claim
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	username	user.attribute
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	true	id.token.claim
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	true	access.token.claim
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	upn	claim.name
292cdca4-ed1e-4d7c-bc41-2bd8fb5b449b	String	jsonType.label
ce6d844f-5d20-4634-be77-eebc912b3106	true	introspection.token.claim
ce6d844f-5d20-4634-be77-eebc912b3106	true	multivalued
ce6d844f-5d20-4634-be77-eebc912b3106	foo	user.attribute
ce6d844f-5d20-4634-be77-eebc912b3106	true	id.token.claim
ce6d844f-5d20-4634-be77-eebc912b3106	true	access.token.claim
ce6d844f-5d20-4634-be77-eebc912b3106	groups	claim.name
ce6d844f-5d20-4634-be77-eebc912b3106	String	jsonType.label
cd4e4bbb-6786-4272-858c-6efc2a65cf73	true	introspection.token.claim
cd4e4bbb-6786-4272-858c-6efc2a65cf73	true	id.token.claim
cd4e4bbb-6786-4272-858c-6efc2a65cf73	true	access.token.claim
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	true	introspection.token.claim
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	true	userinfo.token.claim
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	username	user.attribute
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	true	id.token.claim
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	true	access.token.claim
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	preferred_username	claim.name
0f8967bb-4a2d-46a2-9c4d-23fc99fb5f94	String	jsonType.label
12fa08ca-6a47-492f-9623-e68b061edc68	true	introspection.token.claim
12fa08ca-6a47-492f-9623-e68b061edc68	true	userinfo.token.claim
12fa08ca-6a47-492f-9623-e68b061edc68	nickname	user.attribute
12fa08ca-6a47-492f-9623-e68b061edc68	true	id.token.claim
12fa08ca-6a47-492f-9623-e68b061edc68	true	access.token.claim
12fa08ca-6a47-492f-9623-e68b061edc68	nickname	claim.name
12fa08ca-6a47-492f-9623-e68b061edc68	String	jsonType.label
378157b6-56b9-4deb-b19a-b48d8dde26f9	true	introspection.token.claim
378157b6-56b9-4deb-b19a-b48d8dde26f9	true	userinfo.token.claim
378157b6-56b9-4deb-b19a-b48d8dde26f9	zoneinfo	user.attribute
378157b6-56b9-4deb-b19a-b48d8dde26f9	true	id.token.claim
378157b6-56b9-4deb-b19a-b48d8dde26f9	true	access.token.claim
378157b6-56b9-4deb-b19a-b48d8dde26f9	zoneinfo	claim.name
378157b6-56b9-4deb-b19a-b48d8dde26f9	String	jsonType.label
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	true	introspection.token.claim
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	true	userinfo.token.claim
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	updatedAt	user.attribute
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	true	id.token.claim
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	true	access.token.claim
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	updated_at	claim.name
4c7b9f36-ebfa-42bf-97ec-b79a3b6c6b25	long	jsonType.label
4e7f3478-715f-4102-9792-b5f7e0054b50	true	introspection.token.claim
4e7f3478-715f-4102-9792-b5f7e0054b50	true	userinfo.token.claim
4e7f3478-715f-4102-9792-b5f7e0054b50	restaurante	user.attribute
4e7f3478-715f-4102-9792-b5f7e0054b50	true	id.token.claim
4e7f3478-715f-4102-9792-b5f7e0054b50	false	lightweight.claim
4e7f3478-715f-4102-9792-b5f7e0054b50	true	access.token.claim
4e7f3478-715f-4102-9792-b5f7e0054b50	restaurante	claim.name
4e7f3478-715f-4102-9792-b5f7e0054b50	String	jsonType.label
68243aae-4311-43f2-b00e-726a91649966	true	introspection.token.claim
68243aae-4311-43f2-b00e-726a91649966	true	userinfo.token.claim
68243aae-4311-43f2-b00e-726a91649966	locale	user.attribute
68243aae-4311-43f2-b00e-726a91649966	true	id.token.claim
68243aae-4311-43f2-b00e-726a91649966	true	access.token.claim
68243aae-4311-43f2-b00e-726a91649966	locale	claim.name
68243aae-4311-43f2-b00e-726a91649966	String	jsonType.label
6f6f69e7-4abf-42a8-b093-343389fe2a10	true	introspection.token.claim
6f6f69e7-4abf-42a8-b093-343389fe2a10	true	userinfo.token.claim
6f6f69e7-4abf-42a8-b093-343389fe2a10	lastName	user.attribute
6f6f69e7-4abf-42a8-b093-343389fe2a10	true	id.token.claim
6f6f69e7-4abf-42a8-b093-343389fe2a10	true	access.token.claim
6f6f69e7-4abf-42a8-b093-343389fe2a10	family_name	claim.name
6f6f69e7-4abf-42a8-b093-343389fe2a10	String	jsonType.label
815ebaac-aa32-4838-b125-d442aa716ebe	true	introspection.token.claim
815ebaac-aa32-4838-b125-d442aa716ebe	true	userinfo.token.claim
815ebaac-aa32-4838-b125-d442aa716ebe	picture	user.attribute
815ebaac-aa32-4838-b125-d442aa716ebe	true	id.token.claim
815ebaac-aa32-4838-b125-d442aa716ebe	true	access.token.claim
815ebaac-aa32-4838-b125-d442aa716ebe	picture	claim.name
815ebaac-aa32-4838-b125-d442aa716ebe	String	jsonType.label
8933fee0-806a-4216-a592-6da5d84a8426	true	id.token.claim
8933fee0-806a-4216-a592-6da5d84a8426	true	introspection.token.claim
8933fee0-806a-4216-a592-6da5d84a8426	true	access.token.claim
8933fee0-806a-4216-a592-6da5d84a8426	true	userinfo.token.claim
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	true	introspection.token.claim
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	true	userinfo.token.claim
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	website	user.attribute
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	true	id.token.claim
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	true	access.token.claim
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	website	claim.name
a11c05a5-2b10-4d8e-a97c-bcc04bf956b7	String	jsonType.label
c199fb0e-f0a7-4ca6-80c7-07939515c185	true	introspection.token.claim
c199fb0e-f0a7-4ca6-80c7-07939515c185	true	userinfo.token.claim
c199fb0e-f0a7-4ca6-80c7-07939515c185	middleName	user.attribute
c199fb0e-f0a7-4ca6-80c7-07939515c185	true	id.token.claim
c199fb0e-f0a7-4ca6-80c7-07939515c185	true	access.token.claim
c199fb0e-f0a7-4ca6-80c7-07939515c185	middle_name	claim.name
c199fb0e-f0a7-4ca6-80c7-07939515c185	String	jsonType.label
cdc251d7-3a67-4210-8048-20bed0d020c1	true	introspection.token.claim
cdc251d7-3a67-4210-8048-20bed0d020c1	true	userinfo.token.claim
cdc251d7-3a67-4210-8048-20bed0d020c1	profile	user.attribute
cdc251d7-3a67-4210-8048-20bed0d020c1	true	id.token.claim
cdc251d7-3a67-4210-8048-20bed0d020c1	true	access.token.claim
cdc251d7-3a67-4210-8048-20bed0d020c1	profile	claim.name
cdc251d7-3a67-4210-8048-20bed0d020c1	String	jsonType.label
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	true	introspection.token.claim
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	true	userinfo.token.claim
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	firstName	user.attribute
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	true	id.token.claim
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	true	access.token.claim
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	given_name	claim.name
e43829f6-977b-4e1d-84f9-b5bca2f7e0be	String	jsonType.label
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	true	introspection.token.claim
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	true	userinfo.token.claim
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	birthdate	user.attribute
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	true	id.token.claim
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	true	access.token.claim
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	birthdate	claim.name
e7d4c4bf-0404-4bdd-b9aa-fba43fdcc29d	String	jsonType.label
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	true	introspection.token.claim
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	true	userinfo.token.claim
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	gender	user.attribute
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	true	id.token.claim
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	true	access.token.claim
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	gender	claim.name
f4389f2d-d4d8-4625-85d1-2dd889e3b57d	String	jsonType.label
6c416f33-b528-4ef0-8d9e-b093c0f36e83	true	introspection.token.claim
6c416f33-b528-4ef0-8d9e-b093c0f36e83	true	access.token.claim
6349f0bf-8ba4-4ab5-ad5d-fe9e47352a93	false	single
6349f0bf-8ba4-4ab5-ad5d-fe9e47352a93	Basic	attribute.nameformat
6349f0bf-8ba4-4ab5-ad5d-fe9e47352a93	Role	attribute.name
8e771959-c0e3-4ad2-b4e0-cab5e050a69a	true	introspection.token.claim
8e771959-c0e3-4ad2-b4e0-cab5e050a69a	true	multivalued
8e771959-c0e3-4ad2-b4e0-cab5e050a69a	foo	user.attribute
8e771959-c0e3-4ad2-b4e0-cab5e050a69a	true	access.token.claim
8e771959-c0e3-4ad2-b4e0-cab5e050a69a	realm_access.roles	claim.name
8e771959-c0e3-4ad2-b4e0-cab5e050a69a	String	jsonType.label
90e025c6-dd67-4d62-8989-cfb7ffff9369	true	introspection.token.claim
90e025c6-dd67-4d62-8989-cfb7ffff9369	true	multivalued
90e025c6-dd67-4d62-8989-cfb7ffff9369	foo	user.attribute
90e025c6-dd67-4d62-8989-cfb7ffff9369	true	access.token.claim
90e025c6-dd67-4d62-8989-cfb7ffff9369	resource_access.${client_id}.roles	claim.name
90e025c6-dd67-4d62-8989-cfb7ffff9369	String	jsonType.label
d3c632df-e625-4333-ba44-b6655edef278	true	introspection.token.claim
d3c632df-e625-4333-ba44-b6655edef278	true	access.token.claim
b12c8e90-17db-46a6-a310-b3e2290d4de4	formatted	user.attribute.formatted
b12c8e90-17db-46a6-a310-b3e2290d4de4	country	user.attribute.country
b12c8e90-17db-46a6-a310-b3e2290d4de4	true	introspection.token.claim
b12c8e90-17db-46a6-a310-b3e2290d4de4	postal_code	user.attribute.postal_code
b12c8e90-17db-46a6-a310-b3e2290d4de4	true	userinfo.token.claim
b12c8e90-17db-46a6-a310-b3e2290d4de4	street	user.attribute.street
b12c8e90-17db-46a6-a310-b3e2290d4de4	true	id.token.claim
b12c8e90-17db-46a6-a310-b3e2290d4de4	region	user.attribute.region
b12c8e90-17db-46a6-a310-b3e2290d4de4	true	access.token.claim
b12c8e90-17db-46a6-a310-b3e2290d4de4	locality	user.attribute.locality
797b1744-3c6b-4dca-86c1-1214e801b8c9	true	introspection.token.claim
797b1744-3c6b-4dca-86c1-1214e801b8c9	true	userinfo.token.claim
797b1744-3c6b-4dca-86c1-1214e801b8c9	email	user.attribute
797b1744-3c6b-4dca-86c1-1214e801b8c9	true	id.token.claim
797b1744-3c6b-4dca-86c1-1214e801b8c9	true	access.token.claim
797b1744-3c6b-4dca-86c1-1214e801b8c9	email	claim.name
797b1744-3c6b-4dca-86c1-1214e801b8c9	String	jsonType.label
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	true	introspection.token.claim
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	true	userinfo.token.claim
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	emailVerified	user.attribute
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	true	id.token.claim
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	true	access.token.claim
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	email_verified	claim.name
b7a7e2f9-200c-436c-bd74-fabe8aeb0a8c	boolean	jsonType.label
3a63af56-6e69-456d-a6f4-173dfd7ce149	true	introspection.token.claim
3a63af56-6e69-456d-a6f4-173dfd7ce149	true	userinfo.token.claim
3a63af56-6e69-456d-a6f4-173dfd7ce149	phoneNumberVerified	user.attribute
3a63af56-6e69-456d-a6f4-173dfd7ce149	true	id.token.claim
3a63af56-6e69-456d-a6f4-173dfd7ce149	true	access.token.claim
3a63af56-6e69-456d-a6f4-173dfd7ce149	phone_number_verified	claim.name
3a63af56-6e69-456d-a6f4-173dfd7ce149	boolean	jsonType.label
9f88c8b3-7e13-4767-842d-a5498fda3745	true	introspection.token.claim
9f88c8b3-7e13-4767-842d-a5498fda3745	true	userinfo.token.claim
9f88c8b3-7e13-4767-842d-a5498fda3745	phoneNumber	user.attribute
9f88c8b3-7e13-4767-842d-a5498fda3745	true	id.token.claim
9f88c8b3-7e13-4767-842d-a5498fda3745	true	access.token.claim
9f88c8b3-7e13-4767-842d-a5498fda3745	phone_number	claim.name
9f88c8b3-7e13-4767-842d-a5498fda3745	String	jsonType.label
4707d4f0-c368-41cd-9fcf-e7d003dead76	true	id.token.claim
4707d4f0-c368-41cd-9fcf-e7d003dead76	true	introspection.token.claim
4707d4f0-c368-41cd-9fcf-e7d003dead76	true	access.token.claim
4707d4f0-c368-41cd-9fcf-e7d003dead76	true	userinfo.token.claim
778b52ee-dd5b-4c12-9d21-11645e54ae98	true	introspection.token.claim
778b52ee-dd5b-4c12-9d21-11645e54ae98	true	multivalued
778b52ee-dd5b-4c12-9d21-11645e54ae98	true	userinfo.token.claim
778b52ee-dd5b-4c12-9d21-11645e54ae98	foo	user.attribute
778b52ee-dd5b-4c12-9d21-11645e54ae98	true	id.token.claim
778b52ee-dd5b-4c12-9d21-11645e54ae98	true	access.token.claim
778b52ee-dd5b-4c12-9d21-11645e54ae98	groups	claim.name
778b52ee-dd5b-4c12-9d21-11645e54ae98	String	jsonType.label
b825391c-c41f-49df-9a0e-acc90b693cbe	true	introspection.token.claim
b825391c-c41f-49df-9a0e-acc90b693cbe	true	userinfo.token.claim
b825391c-c41f-49df-9a0e-acc90b693cbe	username	user.attribute
b825391c-c41f-49df-9a0e-acc90b693cbe	true	id.token.claim
b825391c-c41f-49df-9a0e-acc90b693cbe	true	access.token.claim
b825391c-c41f-49df-9a0e-acc90b693cbe	upn	claim.name
b825391c-c41f-49df-9a0e-acc90b693cbe	String	jsonType.label
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	clientAddress	user.session.note
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	true	introspection.token.claim
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	true	id.token.claim
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	true	access.token.claim
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	clientAddress	claim.name
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	String	jsonType.label
7f37977b-ebfc-4b2b-bcb1-93746648627b	clientHost	user.session.note
7f37977b-ebfc-4b2b-bcb1-93746648627b	true	introspection.token.claim
7f37977b-ebfc-4b2b-bcb1-93746648627b	true	id.token.claim
7f37977b-ebfc-4b2b-bcb1-93746648627b	true	access.token.claim
7f37977b-ebfc-4b2b-bcb1-93746648627b	clientHost	claim.name
7f37977b-ebfc-4b2b-bcb1-93746648627b	String	jsonType.label
81d38b67-3a5a-4d71-8622-68c4f92930fe	client_id	user.session.note
81d38b67-3a5a-4d71-8622-68c4f92930fe	true	introspection.token.claim
81d38b67-3a5a-4d71-8622-68c4f92930fe	true	userinfo.token.claim
81d38b67-3a5a-4d71-8622-68c4f92930fe	true	id.token.claim
81d38b67-3a5a-4d71-8622-68c4f92930fe	true	access.token.claim
81d38b67-3a5a-4d71-8622-68c4f92930fe	client_id	claim.name
81d38b67-3a5a-4d71-8622-68c4f92930fe	String	jsonType.label
0249bc05-f0f4-4b36-ba2e-8bfbb3e9009f	true	userinfo.token.claim
7f37977b-ebfc-4b2b-bcb1-93746648627b	true	userinfo.token.claim
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	client_id	user.session.note
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	true	introspection.token.claim
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	true	id.token.claim
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	true	access.token.claim
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	client_id	claim.name
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	String	jsonType.label
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	clientAddress	user.session.note
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	true	introspection.token.claim
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	true	userinfo.token.claim
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	true	id.token.claim
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	true	access.token.claim
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	clientAddress	claim.name
cae6fe80-8b1c-46c2-8afb-6eef41b70c1d	String	jsonType.label
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	clientHost	user.session.note
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	true	introspection.token.claim
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	true	id.token.claim
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	true	access.token.claim
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	clientHost	claim.name
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	String	jsonType.label
7e9f87aa-4c30-4bdd-b9bb-b5dc356d1eef	true	userinfo.token.claim
cb44639b-1dae-496f-ba23-c6f83d0b1cd1	true	userinfo.token.claim
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	true	introspection.token.claim
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	true	userinfo.token.claim
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	locale	user.attribute
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	true	id.token.claim
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	true	access.token.claim
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	locale	claim.name
3e6ef0dd-c2b1-4fae-8c01-86ea21c1523a	String	jsonType.label
\.


--
-- Data for Name: realm; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm (id, access_code_lifespan, user_action_lifespan, access_token_lifespan, account_theme, admin_theme, email_theme, enabled, events_enabled, events_expiration, login_theme, name, not_before, password_policy, registration_allowed, remember_me, reset_password_allowed, social, ssl_required, sso_idle_timeout, sso_max_lifespan, update_profile_on_soc_login, verify_email, master_admin_client, login_lifespan, internationalization_enabled, default_locale, reg_email_as_username, admin_events_enabled, admin_events_details_enabled, edit_username_allowed, otp_policy_counter, otp_policy_window, otp_policy_period, otp_policy_digits, otp_policy_alg, otp_policy_type, browser_flow, registration_flow, direct_grant_flow, reset_credentials_flow, client_auth_flow, offline_session_idle_timeout, revoke_refresh_token, access_token_life_implicit, login_with_email_allowed, duplicate_emails_allowed, docker_auth_flow, refresh_token_max_reuse, allow_user_managed_access, sso_max_lifespan_remember_me, sso_idle_timeout_remember_me, default_role) FROM stdin;
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	60	300	60	\N	\N	\N	t	f	0	\N	master	0	\N	f	f	f	f	EXTERNAL	1800	36000	f	f	122557f0-b934-4094-a8f5-36579c416a87	1800	f	\N	f	f	f	f	0	1	30	6	HmacSHA1	totp	d8811f56-e82f-41ce-988b-c2fda3bf34f2	5d0bc900-ffc9-4784-96a5-7c9702a3aecd	9bbd3553-f605-4afe-bb24-a5e48875ef43	63066cc3-55aa-4fdc-a6e2-b2d9ba0ac862	72f31022-3844-49bc-ae87-48bd47e58680	2592000	f	900	t	f	f313d64f-617c-4f1f-9ef6-c4043335ce0c	0	f	0	0	1f0f7753-5ef0-4083-8641-173682edf64d
43c36b1e-22ce-4293-8d6e-181a68f70b2a	60	300	300	\N	\N	\N	t	f	0	\N	firmas-food-dev	0	\N	f	f	f	f	EXTERNAL	1800	36000	f	f	7cdf5822-e39c-4b10-a5da-bc906b38f4ba	1800	f	\N	f	f	f	f	0	1	30	6	HmacSHA1	totp	d2297650-b0c7-4e35-99a6-3b8864aca1f5	9b3d29f8-295f-43c5-b6d2-e5cc34e402c3	b9a5f815-36aa-45bf-a5a5-702ce4ab093d	f4f059a1-55a8-4469-b047-ef0df376de0f	841698fe-1b21-43e0-9182-f5f552558cfa	2592000	f	900	t	f	97c9b442-8a95-4510-9477-25ad65d795d6	0	f	0	0	889ae762-90c3-4660-a1ab-116b97c91387
\.


--
-- Data for Name: realm_attribute; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_attribute (name, realm_id, value) FROM stdin;
_browser_header.contentSecurityPolicyReportOnly	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	
_browser_header.xContentTypeOptions	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	nosniff
_browser_header.referrerPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	no-referrer
_browser_header.xRobotsTag	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	none
_browser_header.xFrameOptions	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	SAMEORIGIN
_browser_header.contentSecurityPolicy	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	frame-src 'self'; frame-ancestors 'self'; object-src 'none';
_browser_header.xXSSProtection	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	1; mode=block
_browser_header.strictTransportSecurity	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	max-age=31536000; includeSubDomains
bruteForceProtected	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	false
permanentLockout	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	false
maxTemporaryLockouts	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	0
maxFailureWaitSeconds	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	900
minimumQuickLoginWaitSeconds	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	60
waitIncrementSeconds	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	60
quickLoginCheckMilliSeconds	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	1000
maxDeltaTimeSeconds	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	43200
failureFactor	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	30
realmReusableOtpCode	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	false
firstBrokerLoginFlowId	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	5d10ca24-692b-466e-973e-3db49db59a1a
displayName	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	Keycloak
displayNameHtml	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	<div class="kc-logo-text"><span>Keycloak</span></div>
defaultSignatureAlgorithm	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	RS256
offlineSessionMaxLifespanEnabled	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	false
offlineSessionMaxLifespan	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	5184000
_browser_header.contentSecurityPolicyReportOnly	43c36b1e-22ce-4293-8d6e-181a68f70b2a	
_browser_header.xContentTypeOptions	43c36b1e-22ce-4293-8d6e-181a68f70b2a	nosniff
_browser_header.referrerPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	no-referrer
_browser_header.xRobotsTag	43c36b1e-22ce-4293-8d6e-181a68f70b2a	none
_browser_header.xFrameOptions	43c36b1e-22ce-4293-8d6e-181a68f70b2a	SAMEORIGIN
_browser_header.contentSecurityPolicy	43c36b1e-22ce-4293-8d6e-181a68f70b2a	frame-src 'self'; frame-ancestors 'self'; object-src 'none';
_browser_header.xXSSProtection	43c36b1e-22ce-4293-8d6e-181a68f70b2a	1; mode=block
_browser_header.strictTransportSecurity	43c36b1e-22ce-4293-8d6e-181a68f70b2a	max-age=31536000; includeSubDomains
bruteForceProtected	43c36b1e-22ce-4293-8d6e-181a68f70b2a	false
permanentLockout	43c36b1e-22ce-4293-8d6e-181a68f70b2a	false
maxTemporaryLockouts	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0
maxFailureWaitSeconds	43c36b1e-22ce-4293-8d6e-181a68f70b2a	900
minimumQuickLoginWaitSeconds	43c36b1e-22ce-4293-8d6e-181a68f70b2a	60
waitIncrementSeconds	43c36b1e-22ce-4293-8d6e-181a68f70b2a	60
quickLoginCheckMilliSeconds	43c36b1e-22ce-4293-8d6e-181a68f70b2a	1000
maxDeltaTimeSeconds	43c36b1e-22ce-4293-8d6e-181a68f70b2a	43200
failureFactor	43c36b1e-22ce-4293-8d6e-181a68f70b2a	30
realmReusableOtpCode	43c36b1e-22ce-4293-8d6e-181a68f70b2a	false
defaultSignatureAlgorithm	43c36b1e-22ce-4293-8d6e-181a68f70b2a	RS256
offlineSessionMaxLifespanEnabled	43c36b1e-22ce-4293-8d6e-181a68f70b2a	false
offlineSessionMaxLifespan	43c36b1e-22ce-4293-8d6e-181a68f70b2a	5184000
clientSessionIdleTimeout	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0
clientSessionMaxLifespan	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0
clientOfflineSessionIdleTimeout	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0
clientOfflineSessionMaxLifespan	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0
actionTokenGeneratedByAdminLifespan	43c36b1e-22ce-4293-8d6e-181a68f70b2a	43200
actionTokenGeneratedByUserLifespan	43c36b1e-22ce-4293-8d6e-181a68f70b2a	300
oauth2DeviceCodeLifespan	43c36b1e-22ce-4293-8d6e-181a68f70b2a	600
oauth2DevicePollingInterval	43c36b1e-22ce-4293-8d6e-181a68f70b2a	5
webAuthnPolicyRpEntityName	43c36b1e-22ce-4293-8d6e-181a68f70b2a	keycloak
webAuthnPolicySignatureAlgorithms	43c36b1e-22ce-4293-8d6e-181a68f70b2a	ES256
webAuthnPolicyRpId	43c36b1e-22ce-4293-8d6e-181a68f70b2a	
webAuthnPolicyAttestationConveyancePreference	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyAuthenticatorAttachment	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyRequireResidentKey	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyUserVerificationRequirement	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyCreateTimeout	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0
webAuthnPolicyAvoidSameAuthenticatorRegister	43c36b1e-22ce-4293-8d6e-181a68f70b2a	false
webAuthnPolicyRpEntityNamePasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	keycloak
webAuthnPolicySignatureAlgorithmsPasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	ES256
webAuthnPolicyRpIdPasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	
webAuthnPolicyAttestationConveyancePreferencePasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyAuthenticatorAttachmentPasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyRequireResidentKeyPasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyUserVerificationRequirementPasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	not specified
webAuthnPolicyCreateTimeoutPasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	0
webAuthnPolicyAvoidSameAuthenticatorRegisterPasswordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	false
cibaBackchannelTokenDeliveryMode	43c36b1e-22ce-4293-8d6e-181a68f70b2a	poll
cibaExpiresIn	43c36b1e-22ce-4293-8d6e-181a68f70b2a	120
cibaInterval	43c36b1e-22ce-4293-8d6e-181a68f70b2a	5
cibaAuthRequestedUserHint	43c36b1e-22ce-4293-8d6e-181a68f70b2a	login_hint
parRequestUriLifespan	43c36b1e-22ce-4293-8d6e-181a68f70b2a	60
firstBrokerLoginFlowId	43c36b1e-22ce-4293-8d6e-181a68f70b2a	8b5d0c5f-21c0-4084-b118-9f86ef06fadb
client-policies.profiles	43c36b1e-22ce-4293-8d6e-181a68f70b2a	{"profiles":[]}
client-policies.policies	43c36b1e-22ce-4293-8d6e-181a68f70b2a	{"policies":[]}
\.


--
-- Data for Name: realm_default_groups; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_default_groups (realm_id, group_id) FROM stdin;
\.


--
-- Data for Name: realm_enabled_event_types; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_enabled_event_types (realm_id, value) FROM stdin;
\.


--
-- Data for Name: realm_events_listeners; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_events_listeners (realm_id, value) FROM stdin;
d5be0b10-5b50-455d-a9e0-a7c62d0334e5	jboss-logging
43c36b1e-22ce-4293-8d6e-181a68f70b2a	jboss-logging
\.


--
-- Data for Name: realm_localizations; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_localizations (realm_id, locale, texts) FROM stdin;
\.


--
-- Data for Name: realm_required_credential; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_required_credential (type, form_label, input, secret, realm_id) FROM stdin;
password	password	t	t	d5be0b10-5b50-455d-a9e0-a7c62d0334e5
password	password	t	t	43c36b1e-22ce-4293-8d6e-181a68f70b2a
\.


--
-- Data for Name: realm_smtp_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_smtp_config (realm_id, value, name) FROM stdin;
\.


--
-- Data for Name: realm_supported_locales; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.realm_supported_locales (realm_id, value) FROM stdin;
\.


--
-- Data for Name: redirect_uris; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.redirect_uris (client_id, value) FROM stdin;
3f63aef2-fb73-4e1a-884e-5cd61ef71406	/realms/master/account/*
795b4b60-c45a-4377-8da9-8471c55091d0	/realms/master/account/*
787aea5c-fd15-4af6-b6a9-442b26950af5	/admin/master/console/*
e87b6b71-1178-409e-b4ff-5969eb94b377	/realms/firmas-food-dev/account/*
450ad3d9-9b7c-4b7d-9044-d828e8a708af	/realms/firmas-food-dev/account/*
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	http://localhost:8081/*
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	/admin/firmas-food-dev/console/*
\.


--
-- Data for Name: required_action_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.required_action_config (required_action_id, value, name) FROM stdin;
\.


--
-- Data for Name: required_action_provider; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.required_action_provider (id, alias, name, realm_id, enabled, default_action, provider_id, priority) FROM stdin;
df3af69b-0b3d-494b-a154-0dea5765f722	VERIFY_EMAIL	Verify Email	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	VERIFY_EMAIL	50
029c241b-dfc1-41c5-85bb-bf877187053b	UPDATE_PROFILE	Update Profile	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	UPDATE_PROFILE	40
98a15689-c701-4473-b450-1e11aca0aa9f	CONFIGURE_TOTP	Configure OTP	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	CONFIGURE_TOTP	10
3b615f79-46c7-44c9-8cf4-0c1ee55ace53	UPDATE_PASSWORD	Update Password	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	UPDATE_PASSWORD	30
ded90b7d-7caf-42ec-8317-1cf5b2cbe7ab	TERMS_AND_CONDITIONS	Terms and Conditions	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f	f	TERMS_AND_CONDITIONS	20
22ab0022-e213-4023-98df-121ff1e7e6b0	delete_account	Delete Account	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	f	f	delete_account	60
6aa0e6a2-422c-49f5-851c-0b01abf17a58	delete_credential	Delete Credential	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	delete_credential	100
7e63b937-0cb1-45db-a4ff-929e5e278626	update_user_locale	Update User Locale	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	update_user_locale	1000
e59f7991-ccb3-4946-94e3-3729561eafd7	webauthn-register	Webauthn Register	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	webauthn-register	70
904f4afd-4b1c-488f-a0af-b24cebe97809	webauthn-register-passwordless	Webauthn Register Passwordless	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	webauthn-register-passwordless	80
dd5b3828-ddbf-455f-a70b-f5c8c15f3920	VERIFY_PROFILE	Verify Profile	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	t	f	VERIFY_PROFILE	90
904e1d9b-72ba-4ebf-8b56-5b5df9cc5886	CONFIGURE_TOTP	Configure OTP	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	CONFIGURE_TOTP	10
2a8bfa30-f800-4642-9cef-05f028c65f36	TERMS_AND_CONDITIONS	Terms and Conditions	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f	f	TERMS_AND_CONDITIONS	20
074c4037-4836-41b4-8088-aa4fea58b47c	UPDATE_PASSWORD	Update Password	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	UPDATE_PASSWORD	30
a36aa0d8-7614-497d-84d5-c33f6a77afde	UPDATE_PROFILE	Update Profile	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	UPDATE_PROFILE	40
3ddab7cd-9fb9-4f68-a160-f47d3e2c149f	VERIFY_EMAIL	Verify Email	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	VERIFY_EMAIL	50
8ddd4a24-6b84-43f9-9f54-6f2eb1d42eb5	delete_account	Delete Account	43c36b1e-22ce-4293-8d6e-181a68f70b2a	f	f	delete_account	60
d0bd3da3-d37b-4d91-90a5-e2c5394199c8	webauthn-register	Webauthn Register	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	webauthn-register	70
8514b38b-d8d3-490a-87c3-666e6971f3ff	webauthn-register-passwordless	Webauthn Register Passwordless	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	webauthn-register-passwordless	80
2f2eaba0-7052-44d7-9ce7-b32afab4c18f	VERIFY_PROFILE	Verify Profile	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	VERIFY_PROFILE	90
e7b597a7-0894-4341-851b-e4ec6007f1cb	delete_credential	Delete Credential	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	delete_credential	100
a8106d69-0fd3-47bc-bb9e-88d8ec6874cc	update_user_locale	Update User Locale	43c36b1e-22ce-4293-8d6e-181a68f70b2a	t	f	update_user_locale	1000
\.


--
-- Data for Name: resource_attribute; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_attribute (id, name, value, resource_id) FROM stdin;
\.


--
-- Data for Name: resource_policy; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_policy (resource_id, policy_id) FROM stdin;
\.


--
-- Data for Name: resource_scope; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_scope (resource_id, scope_id) FROM stdin;
\.


--
-- Data for Name: resource_server; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_server (id, allow_rs_remote_mgmt, policy_enforce_mode, decision_strategy) FROM stdin;
c91b6aba-8136-4311-b037-bcc7f590dc7e	t	0	1
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	t	0	1
\.


--
-- Data for Name: resource_server_perm_ticket; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_server_perm_ticket (id, owner, requester, created_timestamp, granted_timestamp, resource_id, scope_id, resource_server_id, policy_id) FROM stdin;
\.


--
-- Data for Name: resource_server_policy; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_server_policy (id, name, description, type, decision_strategy, logic, resource_server_id, owner) FROM stdin;
\.


--
-- Data for Name: resource_server_resource; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_server_resource (id, name, type, icon_uri, owner, resource_server_id, owner_managed_access, display_name) FROM stdin;
de701ba6-2fb9-4344-ad25-202c4ab13f96	Default Resource	urn:admin-cli:resources:default	\N	c91b6aba-8136-4311-b037-bcc7f590dc7e	c91b6aba-8136-4311-b037-bcc7f590dc7e	f	\N
\.


--
-- Data for Name: resource_server_scope; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_server_scope (id, name, icon_uri, resource_server_id, display_name) FROM stdin;
\.


--
-- Data for Name: resource_uris; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.resource_uris (resource_id, value) FROM stdin;
de701ba6-2fb9-4344-ad25-202c4ab13f96	/*
\.


--
-- Data for Name: role_attribute; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.role_attribute (id, role_id, name, value) FROM stdin;
\.


--
-- Data for Name: scope_mapping; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.scope_mapping (client_id, role_id) FROM stdin;
795b4b60-c45a-4377-8da9-8471c55091d0	a1b72506-aa0a-4955-aee3-138855abe878
795b4b60-c45a-4377-8da9-8471c55091d0	b8222eb3-2016-4ffe-9795-787925f6c1c9
450ad3d9-9b7c-4b7d-9044-d828e8a708af	f4e266f8-9f0f-4352-8a3b-3c05b91f9dc1
450ad3d9-9b7c-4b7d-9044-d828e8a708af	d499a30b-0493-4565-a74e-602c50c5841e
\.


--
-- Data for Name: scope_policy; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.scope_policy (scope_id, policy_id) FROM stdin;
\.


--
-- Data for Name: user_attribute; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_attribute (name, value, user_id, id, long_value_hash, long_value_hash_lower_case, long_value) FROM stdin;
restaurante	laaa	a48915d6-8cda-42bf-bbc9-5f8a2d2d2137	af42e592-0e4b-4d97-8e0c-bd10d6e01074	\N	\N	\N
restaurante	laaa	7f3d7400-dc5e-42ab-9278-f902389d55c2	31978f30-fce6-4a9b-98b0-bbbd535aab01	\N	\N	\N
restaurante	lalala	76290f27-6ada-4f08-9ea6-82558836fce8	7a06963a-da9c-4e5b-a042-36191cbceba6	\N	\N	\N
\.


--
-- Data for Name: user_consent; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_consent (id, client_id, user_id, created_date, last_updated_date, client_storage_provider, external_client_id) FROM stdin;
\.


--
-- Data for Name: user_consent_client_scope; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_consent_client_scope (user_consent_id, scope_id) FROM stdin;
\.


--
-- Data for Name: user_entity; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_entity (id, email, email_constraint, email_verified, enabled, federation_link, first_name, last_name, realm_id, username, created_timestamp, service_account_client_link, not_before) FROM stdin;
ecf7cc4b-da42-4c7d-95b0-2b272378e3a4	\N	82d38e99-5947-4db5-842a-4282aaeefd2f	f	t	\N	\N	\N	d5be0b10-5b50-455d-a9e0-a7c62d0334e5	admin	1720654979263	\N	0
b3c6c468-bc37-44e7-ab87-c97d3e25069f	\N	66a9c4e3-8b85-4f99-8ed0-78dd4c2ed8ab	f	t	\N	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	service-account-admin-cli	1720657609603	c91b6aba-8136-4311-b037-bcc7f590dc7e	0
840361cd-d110-4a57-b321-252a0682bffe	\N	3e20acf0-4560-4b53-91c4-04e87fea726f	f	t	\N	\N	\N	43c36b1e-22ce-4293-8d6e-181a68f70b2a	service-account-firmas-food	1720657385671	1f97db26-efee-41e7-ac44-e6fa35fd1fa0	0
a48915d6-8cda-42bf-bbc9-5f8a2d2d2137	daniel2.djgomes2@outlook.com	daniel2.djgomes2@outlook.com	f	t	\N	use1r2	us1er	43c36b1e-22ce-4293-8d6e-181a68f70b2a	laaa_daniel2.djgomes2@outlook.com	1720659368790	\N	0
7f3d7400-dc5e-42ab-9278-f902389d55c2	daniel12.djgomes2@outlook.com	daniel12.djgomes2@outlook.com	f	t	\N	use1r2	us1er	43c36b1e-22ce-4293-8d6e-181a68f70b2a	laaa_daniel12.djgomes2@outlook.com	1720659680774	\N	0
76290f27-6ada-4f08-9ea6-82558836fce8	test@email.com	test@email.com	t	t	\N	a	v	43c36b1e-22ce-4293-8d6e-181a68f70b2a	guest	1721143641576	\N	0
\.


--
-- Data for Name: user_federation_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_federation_config (user_federation_provider_id, value, name) FROM stdin;
\.


--
-- Data for Name: user_federation_mapper; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_federation_mapper (id, name, federation_provider_id, federation_mapper_type, realm_id) FROM stdin;
\.


--
-- Data for Name: user_federation_mapper_config; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_federation_mapper_config (user_federation_mapper_id, value, name) FROM stdin;
\.


--
-- Data for Name: user_federation_provider; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_federation_provider (id, changed_sync_period, display_name, full_sync_period, last_sync, priority, provider_name, realm_id) FROM stdin;
\.


--
-- Data for Name: user_group_membership; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_group_membership (group_id, user_id) FROM stdin;
\.


--
-- Data for Name: user_required_action; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_required_action (user_id, required_action) FROM stdin;
\.


--
-- Data for Name: user_role_mapping; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_role_mapping (role_id, user_id) FROM stdin;
1f0f7753-5ef0-4083-8641-173682edf64d	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
6b04c0c3-31eb-4180-a753-2d67b4e540a5	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
889ae762-90c3-4660-a1ab-116b97c91387	b3c6c468-bc37-44e7-ab87-c97d3e25069f
082a9fa4-9f7b-4189-8b90-f47fd1fe0c9e	b3c6c468-bc37-44e7-ab87-c97d3e25069f
889ae762-90c3-4660-a1ab-116b97c91387	840361cd-d110-4a57-b321-252a0682bffe
40bebe9a-aa28-4e92-89a6-9043ed0e8eda	840361cd-d110-4a57-b321-252a0682bffe
857bf84e-fb3f-43ae-b05c-ebdc2259a352	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
4507d89d-0b96-4b5d-86d3-69e61ea853e5	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
76bf2360-9e8b-47c1-937a-dcc5b78ec0c1	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
dd403b4c-068f-481f-8022-16aba8402363	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
54b9cfda-1a0a-4804-9bb0-c51c014fabc0	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
59efd6b6-b872-428d-9421-598cc3d37fd4	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
1dc9d122-7749-4ead-ae84-a22497f4d675	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
9b39afcd-e04a-44e9-8074-6e42485a9a92	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
74766a90-cae1-4b4d-84cc-969b06d3dbee	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
9c10ddad-0f44-4ebd-993e-384b379c7b86	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
f3d51c75-5f7e-4b36-a237-c155baf04e61	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
f4f660ba-5b65-42cd-8aab-4fc460ed586a	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
a791dd88-dd20-4f37-a7b5-faf2bcf236e8	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
6c9d8878-fef9-4125-9d63-399b331c737b	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
a0ace9e3-7276-4734-9501-7f1213bfb65d	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
00d13db5-59d0-46fe-8dfa-3473f82dc34e	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
72670e98-92aa-440c-8a37-5765552ae6c1	ecf7cc4b-da42-4c7d-95b0-2b272378e3a4
889ae762-90c3-4660-a1ab-116b97c91387	a48915d6-8cda-42bf-bbc9-5f8a2d2d2137
889ae762-90c3-4660-a1ab-116b97c91387	7f3d7400-dc5e-42ab-9278-f902389d55c2
889ae762-90c3-4660-a1ab-116b97c91387	76290f27-6ada-4f08-9ea6-82558836fce8
\.


--
-- Data for Name: user_session; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_session (id, auth_method, ip_address, last_session_refresh, login_username, realm_id, remember_me, started, user_id, user_session_state, broker_session_id, broker_user_id) FROM stdin;
\.


--
-- Data for Name: user_session_note; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.user_session_note (user_session, name, value) FROM stdin;
\.


--
-- Data for Name: username_login_failure; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.username_login_failure (realm_id, username, failed_login_not_before, last_failure, last_ip_failure, num_failures) FROM stdin;
\.


--
-- Data for Name: web_origins; Type: TABLE DATA; Schema: public; Owner: dbo
--

COPY public.web_origins (client_id, value) FROM stdin;
787aea5c-fd15-4af6-b6a9-442b26950af5	+
1f97db26-efee-41e7-ac44-e6fa35fd1fa0	http://localhost:8081
3ce679a2-0f55-4679-b3cb-c957e20e2ac0	+
\.


--
-- Name: username_login_failure CONSTRAINT_17-2; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.username_login_failure
    ADD CONSTRAINT "CONSTRAINT_17-2" PRIMARY KEY (realm_id, username);


--
-- Name: keycloak_role UK_J3RWUVD56ONTGSUHOGM184WW2-2; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.keycloak_role
    ADD CONSTRAINT "UK_J3RWUVD56ONTGSUHOGM184WW2-2" UNIQUE (name, client_realm_constraint);


--
-- Name: client_auth_flow_bindings c_cli_flow_bind; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_auth_flow_bindings
    ADD CONSTRAINT c_cli_flow_bind PRIMARY KEY (client_id, binding_name);


--
-- Name: client_scope_client c_cli_scope_bind; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_scope_client
    ADD CONSTRAINT c_cli_scope_bind PRIMARY KEY (client_id, scope_id);


--
-- Name: client_initial_access cnstr_client_init_acc_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_initial_access
    ADD CONSTRAINT cnstr_client_init_acc_pk PRIMARY KEY (id);


--
-- Name: realm_default_groups con_group_id_def_groups; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_default_groups
    ADD CONSTRAINT con_group_id_def_groups UNIQUE (group_id);


--
-- Name: broker_link constr_broker_link_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.broker_link
    ADD CONSTRAINT constr_broker_link_pk PRIMARY KEY (identity_provider, user_id);


--
-- Name: client_user_session_note constr_cl_usr_ses_note; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_user_session_note
    ADD CONSTRAINT constr_cl_usr_ses_note PRIMARY KEY (client_session, name);


--
-- Name: component_config constr_component_config_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.component_config
    ADD CONSTRAINT constr_component_config_pk PRIMARY KEY (id);


--
-- Name: component constr_component_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.component
    ADD CONSTRAINT constr_component_pk PRIMARY KEY (id);


--
-- Name: fed_user_required_action constr_fed_required_action; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.fed_user_required_action
    ADD CONSTRAINT constr_fed_required_action PRIMARY KEY (required_action, user_id);


--
-- Name: fed_user_attribute constr_fed_user_attr_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.fed_user_attribute
    ADD CONSTRAINT constr_fed_user_attr_pk PRIMARY KEY (id);


--
-- Name: fed_user_consent constr_fed_user_consent_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.fed_user_consent
    ADD CONSTRAINT constr_fed_user_consent_pk PRIMARY KEY (id);


--
-- Name: fed_user_credential constr_fed_user_cred_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.fed_user_credential
    ADD CONSTRAINT constr_fed_user_cred_pk PRIMARY KEY (id);


--
-- Name: fed_user_group_membership constr_fed_user_group; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.fed_user_group_membership
    ADD CONSTRAINT constr_fed_user_group PRIMARY KEY (group_id, user_id);


--
-- Name: fed_user_role_mapping constr_fed_user_role; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.fed_user_role_mapping
    ADD CONSTRAINT constr_fed_user_role PRIMARY KEY (role_id, user_id);


--
-- Name: federated_user constr_federated_user; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.federated_user
    ADD CONSTRAINT constr_federated_user PRIMARY KEY (id);


--
-- Name: realm_default_groups constr_realm_default_groups; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_default_groups
    ADD CONSTRAINT constr_realm_default_groups PRIMARY KEY (realm_id, group_id);


--
-- Name: realm_enabled_event_types constr_realm_enabl_event_types; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_enabled_event_types
    ADD CONSTRAINT constr_realm_enabl_event_types PRIMARY KEY (realm_id, value);


--
-- Name: realm_events_listeners constr_realm_events_listeners; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_events_listeners
    ADD CONSTRAINT constr_realm_events_listeners PRIMARY KEY (realm_id, value);


--
-- Name: realm_supported_locales constr_realm_supported_locales; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_supported_locales
    ADD CONSTRAINT constr_realm_supported_locales PRIMARY KEY (realm_id, value);


--
-- Name: identity_provider constraint_2b; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.identity_provider
    ADD CONSTRAINT constraint_2b PRIMARY KEY (internal_id);


--
-- Name: client_attributes constraint_3c; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_attributes
    ADD CONSTRAINT constraint_3c PRIMARY KEY (client_id, name);


--
-- Name: event_entity constraint_4; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.event_entity
    ADD CONSTRAINT constraint_4 PRIMARY KEY (id);


--
-- Name: federated_identity constraint_40; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.federated_identity
    ADD CONSTRAINT constraint_40 PRIMARY KEY (identity_provider, user_id);


--
-- Name: realm constraint_4a; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm
    ADD CONSTRAINT constraint_4a PRIMARY KEY (id);


--
-- Name: client_session_role constraint_5; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_role
    ADD CONSTRAINT constraint_5 PRIMARY KEY (client_session, role_id);


--
-- Name: user_session constraint_57; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_session
    ADD CONSTRAINT constraint_57 PRIMARY KEY (id);


--
-- Name: user_federation_provider constraint_5c; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_provider
    ADD CONSTRAINT constraint_5c PRIMARY KEY (id);


--
-- Name: client_session_note constraint_5e; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_note
    ADD CONSTRAINT constraint_5e PRIMARY KEY (client_session, name);


--
-- Name: client constraint_7; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT constraint_7 PRIMARY KEY (id);


--
-- Name: client_session constraint_8; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session
    ADD CONSTRAINT constraint_8 PRIMARY KEY (id);


--
-- Name: scope_mapping constraint_81; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.scope_mapping
    ADD CONSTRAINT constraint_81 PRIMARY KEY (client_id, role_id);


--
-- Name: client_node_registrations constraint_84; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_node_registrations
    ADD CONSTRAINT constraint_84 PRIMARY KEY (client_id, name);


--
-- Name: realm_attribute constraint_9; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_attribute
    ADD CONSTRAINT constraint_9 PRIMARY KEY (name, realm_id);


--
-- Name: realm_required_credential constraint_92; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_required_credential
    ADD CONSTRAINT constraint_92 PRIMARY KEY (realm_id, type);


--
-- Name: keycloak_role constraint_a; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.keycloak_role
    ADD CONSTRAINT constraint_a PRIMARY KEY (id);


--
-- Name: admin_event_entity constraint_admin_event_entity; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.admin_event_entity
    ADD CONSTRAINT constraint_admin_event_entity PRIMARY KEY (id);


--
-- Name: authenticator_config_entry constraint_auth_cfg_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authenticator_config_entry
    ADD CONSTRAINT constraint_auth_cfg_pk PRIMARY KEY (authenticator_id, name);


--
-- Name: authentication_execution constraint_auth_exec_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authentication_execution
    ADD CONSTRAINT constraint_auth_exec_pk PRIMARY KEY (id);


--
-- Name: authentication_flow constraint_auth_flow_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authentication_flow
    ADD CONSTRAINT constraint_auth_flow_pk PRIMARY KEY (id);


--
-- Name: authenticator_config constraint_auth_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authenticator_config
    ADD CONSTRAINT constraint_auth_pk PRIMARY KEY (id);


--
-- Name: client_session_auth_status constraint_auth_status_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_auth_status
    ADD CONSTRAINT constraint_auth_status_pk PRIMARY KEY (client_session, authenticator);


--
-- Name: user_role_mapping constraint_c; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_role_mapping
    ADD CONSTRAINT constraint_c PRIMARY KEY (role_id, user_id);


--
-- Name: composite_role constraint_composite_role; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.composite_role
    ADD CONSTRAINT constraint_composite_role PRIMARY KEY (composite, child_role);


--
-- Name: client_session_prot_mapper constraint_cs_pmp_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_prot_mapper
    ADD CONSTRAINT constraint_cs_pmp_pk PRIMARY KEY (client_session, protocol_mapper_id);


--
-- Name: identity_provider_config constraint_d; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.identity_provider_config
    ADD CONSTRAINT constraint_d PRIMARY KEY (identity_provider_id, name);


--
-- Name: policy_config constraint_dpc; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.policy_config
    ADD CONSTRAINT constraint_dpc PRIMARY KEY (policy_id, name);


--
-- Name: realm_smtp_config constraint_e; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_smtp_config
    ADD CONSTRAINT constraint_e PRIMARY KEY (realm_id, name);


--
-- Name: credential constraint_f; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.credential
    ADD CONSTRAINT constraint_f PRIMARY KEY (id);


--
-- Name: user_federation_config constraint_f9; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_config
    ADD CONSTRAINT constraint_f9 PRIMARY KEY (user_federation_provider_id, name);


--
-- Name: resource_server_perm_ticket constraint_fapmt; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT constraint_fapmt PRIMARY KEY (id);


--
-- Name: resource_server_resource constraint_farsr; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_resource
    ADD CONSTRAINT constraint_farsr PRIMARY KEY (id);


--
-- Name: resource_server_policy constraint_farsrp; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_policy
    ADD CONSTRAINT constraint_farsrp PRIMARY KEY (id);


--
-- Name: associated_policy constraint_farsrpap; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.associated_policy
    ADD CONSTRAINT constraint_farsrpap PRIMARY KEY (policy_id, associated_policy_id);


--
-- Name: resource_policy constraint_farsrpp; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_policy
    ADD CONSTRAINT constraint_farsrpp PRIMARY KEY (resource_id, policy_id);


--
-- Name: resource_server_scope constraint_farsrs; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_scope
    ADD CONSTRAINT constraint_farsrs PRIMARY KEY (id);


--
-- Name: resource_scope constraint_farsrsp; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_scope
    ADD CONSTRAINT constraint_farsrsp PRIMARY KEY (resource_id, scope_id);


--
-- Name: scope_policy constraint_farsrsps; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.scope_policy
    ADD CONSTRAINT constraint_farsrsps PRIMARY KEY (scope_id, policy_id);


--
-- Name: user_entity constraint_fb; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT constraint_fb PRIMARY KEY (id);


--
-- Name: user_federation_mapper_config constraint_fedmapper_cfg_pm; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_mapper_config
    ADD CONSTRAINT constraint_fedmapper_cfg_pm PRIMARY KEY (user_federation_mapper_id, name);


--
-- Name: user_federation_mapper constraint_fedmapperpm; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_mapper
    ADD CONSTRAINT constraint_fedmapperpm PRIMARY KEY (id);


--
-- Name: fed_user_consent_cl_scope constraint_fgrntcsnt_clsc_pm; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.fed_user_consent_cl_scope
    ADD CONSTRAINT constraint_fgrntcsnt_clsc_pm PRIMARY KEY (user_consent_id, scope_id);


--
-- Name: user_consent_client_scope constraint_grntcsnt_clsc_pm; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_consent_client_scope
    ADD CONSTRAINT constraint_grntcsnt_clsc_pm PRIMARY KEY (user_consent_id, scope_id);


--
-- Name: user_consent constraint_grntcsnt_pm; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_consent
    ADD CONSTRAINT constraint_grntcsnt_pm PRIMARY KEY (id);


--
-- Name: keycloak_group constraint_group; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.keycloak_group
    ADD CONSTRAINT constraint_group PRIMARY KEY (id);


--
-- Name: group_attribute constraint_group_attribute_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.group_attribute
    ADD CONSTRAINT constraint_group_attribute_pk PRIMARY KEY (id);


--
-- Name: group_role_mapping constraint_group_role; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.group_role_mapping
    ADD CONSTRAINT constraint_group_role PRIMARY KEY (role_id, group_id);


--
-- Name: identity_provider_mapper constraint_idpm; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.identity_provider_mapper
    ADD CONSTRAINT constraint_idpm PRIMARY KEY (id);


--
-- Name: idp_mapper_config constraint_idpmconfig; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.idp_mapper_config
    ADD CONSTRAINT constraint_idpmconfig PRIMARY KEY (idp_mapper_id, name);


--
-- Name: migration_model constraint_migmod; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.migration_model
    ADD CONSTRAINT constraint_migmod PRIMARY KEY (id);


--
-- Name: offline_client_session constraint_offl_cl_ses_pk3; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.offline_client_session
    ADD CONSTRAINT constraint_offl_cl_ses_pk3 PRIMARY KEY (user_session_id, client_id, client_storage_provider, external_client_id, offline_flag);


--
-- Name: offline_user_session constraint_offl_us_ses_pk2; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.offline_user_session
    ADD CONSTRAINT constraint_offl_us_ses_pk2 PRIMARY KEY (user_session_id, offline_flag);


--
-- Name: protocol_mapper constraint_pcm; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.protocol_mapper
    ADD CONSTRAINT constraint_pcm PRIMARY KEY (id);


--
-- Name: protocol_mapper_config constraint_pmconfig; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.protocol_mapper_config
    ADD CONSTRAINT constraint_pmconfig PRIMARY KEY (protocol_mapper_id, name);


--
-- Name: redirect_uris constraint_redirect_uris; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.redirect_uris
    ADD CONSTRAINT constraint_redirect_uris PRIMARY KEY (client_id, value);


--
-- Name: required_action_config constraint_req_act_cfg_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.required_action_config
    ADD CONSTRAINT constraint_req_act_cfg_pk PRIMARY KEY (required_action_id, name);


--
-- Name: required_action_provider constraint_req_act_prv_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.required_action_provider
    ADD CONSTRAINT constraint_req_act_prv_pk PRIMARY KEY (id);


--
-- Name: user_required_action constraint_required_action; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_required_action
    ADD CONSTRAINT constraint_required_action PRIMARY KEY (required_action, user_id);


--
-- Name: resource_uris constraint_resour_uris_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_uris
    ADD CONSTRAINT constraint_resour_uris_pk PRIMARY KEY (resource_id, value);


--
-- Name: role_attribute constraint_role_attribute_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.role_attribute
    ADD CONSTRAINT constraint_role_attribute_pk PRIMARY KEY (id);


--
-- Name: user_attribute constraint_user_attribute_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_attribute
    ADD CONSTRAINT constraint_user_attribute_pk PRIMARY KEY (id);


--
-- Name: user_group_membership constraint_user_group; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_group_membership
    ADD CONSTRAINT constraint_user_group PRIMARY KEY (group_id, user_id);


--
-- Name: user_session_note constraint_usn_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_session_note
    ADD CONSTRAINT constraint_usn_pk PRIMARY KEY (user_session, name);


--
-- Name: web_origins constraint_web_origins; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.web_origins
    ADD CONSTRAINT constraint_web_origins PRIMARY KEY (client_id, value);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: client_scope_attributes pk_cl_tmpl_attr; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_scope_attributes
    ADD CONSTRAINT pk_cl_tmpl_attr PRIMARY KEY (scope_id, name);


--
-- Name: client_scope pk_cli_template; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_scope
    ADD CONSTRAINT pk_cli_template PRIMARY KEY (id);


--
-- Name: resource_server pk_resource_server; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server
    ADD CONSTRAINT pk_resource_server PRIMARY KEY (id);


--
-- Name: client_scope_role_mapping pk_template_scope; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_scope_role_mapping
    ADD CONSTRAINT pk_template_scope PRIMARY KEY (scope_id, role_id);


--
-- Name: default_client_scope r_def_cli_scope_bind; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.default_client_scope
    ADD CONSTRAINT r_def_cli_scope_bind PRIMARY KEY (realm_id, scope_id);


--
-- Name: realm_localizations realm_localizations_pkey; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_localizations
    ADD CONSTRAINT realm_localizations_pkey PRIMARY KEY (realm_id, locale);


--
-- Name: resource_attribute res_attr_pk; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_attribute
    ADD CONSTRAINT res_attr_pk PRIMARY KEY (id);


--
-- Name: keycloak_group sibling_names; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.keycloak_group
    ADD CONSTRAINT sibling_names UNIQUE (realm_id, parent_group, name);


--
-- Name: identity_provider uk_2daelwnibji49avxsrtuf6xj33; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.identity_provider
    ADD CONSTRAINT uk_2daelwnibji49avxsrtuf6xj33 UNIQUE (provider_alias, realm_id);


--
-- Name: client uk_b71cjlbenv945rb6gcon438at; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT uk_b71cjlbenv945rb6gcon438at UNIQUE (realm_id, client_id);


--
-- Name: client_scope uk_cli_scope; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_scope
    ADD CONSTRAINT uk_cli_scope UNIQUE (realm_id, name);


--
-- Name: user_entity uk_dykn684sl8up1crfei6eckhd7; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT uk_dykn684sl8up1crfei6eckhd7 UNIQUE (realm_id, email_constraint);


--
-- Name: resource_server_resource uk_frsr6t700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_resource
    ADD CONSTRAINT uk_frsr6t700s9v50bu18ws5ha6 UNIQUE (name, owner, resource_server_id);


--
-- Name: resource_server_perm_ticket uk_frsr6t700s9v50bu18ws5pmt; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT uk_frsr6t700s9v50bu18ws5pmt UNIQUE (owner, requester, resource_server_id, resource_id, scope_id);


--
-- Name: resource_server_policy uk_frsrpt700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_policy
    ADD CONSTRAINT uk_frsrpt700s9v50bu18ws5ha6 UNIQUE (name, resource_server_id);


--
-- Name: resource_server_scope uk_frsrst700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_scope
    ADD CONSTRAINT uk_frsrst700s9v50bu18ws5ha6 UNIQUE (name, resource_server_id);


--
-- Name: user_consent uk_jkuwuvd56ontgsuhogm8uewrt; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_consent
    ADD CONSTRAINT uk_jkuwuvd56ontgsuhogm8uewrt UNIQUE (client_id, client_storage_provider, external_client_id, user_id);


--
-- Name: realm uk_orvsdmla56612eaefiq6wl5oi; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm
    ADD CONSTRAINT uk_orvsdmla56612eaefiq6wl5oi UNIQUE (name);


--
-- Name: user_entity uk_ru8tt6t700s9v50bu18ws5ha6; Type: CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_entity
    ADD CONSTRAINT uk_ru8tt6t700s9v50bu18ws5ha6 UNIQUE (realm_id, username);


--
-- Name: fed_user_attr_long_values; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX fed_user_attr_long_values ON public.fed_user_attribute USING btree (long_value_hash, name);


--
-- Name: fed_user_attr_long_values_lower_case; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX fed_user_attr_long_values_lower_case ON public.fed_user_attribute USING btree (long_value_hash_lower_case, name);


--
-- Name: idx_admin_event_time; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_admin_event_time ON public.admin_event_entity USING btree (realm_id, admin_event_time);


--
-- Name: idx_assoc_pol_assoc_pol_id; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_assoc_pol_assoc_pol_id ON public.associated_policy USING btree (associated_policy_id);


--
-- Name: idx_auth_config_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_auth_config_realm ON public.authenticator_config USING btree (realm_id);


--
-- Name: idx_auth_exec_flow; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_auth_exec_flow ON public.authentication_execution USING btree (flow_id);


--
-- Name: idx_auth_exec_realm_flow; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_auth_exec_realm_flow ON public.authentication_execution USING btree (realm_id, flow_id);


--
-- Name: idx_auth_flow_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_auth_flow_realm ON public.authentication_flow USING btree (realm_id);


--
-- Name: idx_cl_clscope; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_cl_clscope ON public.client_scope_client USING btree (scope_id);


--
-- Name: idx_client_att_by_name_value; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_client_att_by_name_value ON public.client_attributes USING btree (name, substr(value, 1, 255));


--
-- Name: idx_client_id; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_client_id ON public.client USING btree (client_id);


--
-- Name: idx_client_init_acc_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_client_init_acc_realm ON public.client_initial_access USING btree (realm_id);


--
-- Name: idx_client_session_session; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_client_session_session ON public.client_session USING btree (session_id);


--
-- Name: idx_clscope_attrs; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_clscope_attrs ON public.client_scope_attributes USING btree (scope_id);


--
-- Name: idx_clscope_cl; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_clscope_cl ON public.client_scope_client USING btree (client_id);


--
-- Name: idx_clscope_protmap; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_clscope_protmap ON public.protocol_mapper USING btree (client_scope_id);


--
-- Name: idx_clscope_role; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_clscope_role ON public.client_scope_role_mapping USING btree (scope_id);


--
-- Name: idx_compo_config_compo; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_compo_config_compo ON public.component_config USING btree (component_id);


--
-- Name: idx_component_provider_type; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_component_provider_type ON public.component USING btree (provider_type);


--
-- Name: idx_component_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_component_realm ON public.component USING btree (realm_id);


--
-- Name: idx_composite; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_composite ON public.composite_role USING btree (composite);


--
-- Name: idx_composite_child; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_composite_child ON public.composite_role USING btree (child_role);


--
-- Name: idx_defcls_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_defcls_realm ON public.default_client_scope USING btree (realm_id);


--
-- Name: idx_defcls_scope; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_defcls_scope ON public.default_client_scope USING btree (scope_id);


--
-- Name: idx_event_time; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_event_time ON public.event_entity USING btree (realm_id, event_time);


--
-- Name: idx_fedidentity_feduser; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fedidentity_feduser ON public.federated_identity USING btree (federated_user_id);


--
-- Name: idx_fedidentity_user; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fedidentity_user ON public.federated_identity USING btree (user_id);


--
-- Name: idx_fu_attribute; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_attribute ON public.fed_user_attribute USING btree (user_id, realm_id, name);


--
-- Name: idx_fu_cnsnt_ext; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_cnsnt_ext ON public.fed_user_consent USING btree (user_id, client_storage_provider, external_client_id);


--
-- Name: idx_fu_consent; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_consent ON public.fed_user_consent USING btree (user_id, client_id);


--
-- Name: idx_fu_consent_ru; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_consent_ru ON public.fed_user_consent USING btree (realm_id, user_id);


--
-- Name: idx_fu_credential; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_credential ON public.fed_user_credential USING btree (user_id, type);


--
-- Name: idx_fu_credential_ru; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_credential_ru ON public.fed_user_credential USING btree (realm_id, user_id);


--
-- Name: idx_fu_group_membership; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_group_membership ON public.fed_user_group_membership USING btree (user_id, group_id);


--
-- Name: idx_fu_group_membership_ru; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_group_membership_ru ON public.fed_user_group_membership USING btree (realm_id, user_id);


--
-- Name: idx_fu_required_action; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_required_action ON public.fed_user_required_action USING btree (user_id, required_action);


--
-- Name: idx_fu_required_action_ru; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_required_action_ru ON public.fed_user_required_action USING btree (realm_id, user_id);


--
-- Name: idx_fu_role_mapping; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_role_mapping ON public.fed_user_role_mapping USING btree (user_id, role_id);


--
-- Name: idx_fu_role_mapping_ru; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_fu_role_mapping_ru ON public.fed_user_role_mapping USING btree (realm_id, user_id);


--
-- Name: idx_group_att_by_name_value; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_group_att_by_name_value ON public.group_attribute USING btree (name, ((value)::character varying(250)));


--
-- Name: idx_group_attr_group; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_group_attr_group ON public.group_attribute USING btree (group_id);


--
-- Name: idx_group_role_mapp_group; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_group_role_mapp_group ON public.group_role_mapping USING btree (group_id);


--
-- Name: idx_id_prov_mapp_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_id_prov_mapp_realm ON public.identity_provider_mapper USING btree (realm_id);


--
-- Name: idx_ident_prov_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_ident_prov_realm ON public.identity_provider USING btree (realm_id);


--
-- Name: idx_keycloak_role_client; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_keycloak_role_client ON public.keycloak_role USING btree (client);


--
-- Name: idx_keycloak_role_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_keycloak_role_realm ON public.keycloak_role USING btree (realm);


--
-- Name: idx_offline_css_preload; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_offline_css_preload ON public.offline_client_session USING btree (client_id, offline_flag);


--
-- Name: idx_offline_uss_by_user; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_offline_uss_by_user ON public.offline_user_session USING btree (user_id, realm_id, offline_flag);


--
-- Name: idx_offline_uss_by_usersess; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_offline_uss_by_usersess ON public.offline_user_session USING btree (realm_id, offline_flag, user_session_id);


--
-- Name: idx_offline_uss_createdon; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_offline_uss_createdon ON public.offline_user_session USING btree (created_on);


--
-- Name: idx_offline_uss_preload; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_offline_uss_preload ON public.offline_user_session USING btree (offline_flag, created_on, user_session_id);


--
-- Name: idx_protocol_mapper_client; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_protocol_mapper_client ON public.protocol_mapper USING btree (client_id);


--
-- Name: idx_realm_attr_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_realm_attr_realm ON public.realm_attribute USING btree (realm_id);


--
-- Name: idx_realm_clscope; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_realm_clscope ON public.client_scope USING btree (realm_id);


--
-- Name: idx_realm_def_grp_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_realm_def_grp_realm ON public.realm_default_groups USING btree (realm_id);


--
-- Name: idx_realm_evt_list_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_realm_evt_list_realm ON public.realm_events_listeners USING btree (realm_id);


--
-- Name: idx_realm_evt_types_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_realm_evt_types_realm ON public.realm_enabled_event_types USING btree (realm_id);


--
-- Name: idx_realm_master_adm_cli; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_realm_master_adm_cli ON public.realm USING btree (master_admin_client);


--
-- Name: idx_realm_supp_local_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_realm_supp_local_realm ON public.realm_supported_locales USING btree (realm_id);


--
-- Name: idx_redir_uri_client; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_redir_uri_client ON public.redirect_uris USING btree (client_id);


--
-- Name: idx_req_act_prov_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_req_act_prov_realm ON public.required_action_provider USING btree (realm_id);


--
-- Name: idx_res_policy_policy; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_res_policy_policy ON public.resource_policy USING btree (policy_id);


--
-- Name: idx_res_scope_scope; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_res_scope_scope ON public.resource_scope USING btree (scope_id);


--
-- Name: idx_res_serv_pol_res_serv; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_res_serv_pol_res_serv ON public.resource_server_policy USING btree (resource_server_id);


--
-- Name: idx_res_srv_res_res_srv; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_res_srv_res_res_srv ON public.resource_server_resource USING btree (resource_server_id);


--
-- Name: idx_res_srv_scope_res_srv; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_res_srv_scope_res_srv ON public.resource_server_scope USING btree (resource_server_id);


--
-- Name: idx_role_attribute; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_role_attribute ON public.role_attribute USING btree (role_id);


--
-- Name: idx_role_clscope; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_role_clscope ON public.client_scope_role_mapping USING btree (role_id);


--
-- Name: idx_scope_mapping_role; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_scope_mapping_role ON public.scope_mapping USING btree (role_id);


--
-- Name: idx_scope_policy_policy; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_scope_policy_policy ON public.scope_policy USING btree (policy_id);


--
-- Name: idx_update_time; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_update_time ON public.migration_model USING btree (update_time);


--
-- Name: idx_us_sess_id_on_cl_sess; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_us_sess_id_on_cl_sess ON public.offline_client_session USING btree (user_session_id);


--
-- Name: idx_usconsent_clscope; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_usconsent_clscope ON public.user_consent_client_scope USING btree (user_consent_id);


--
-- Name: idx_user_attribute; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_attribute ON public.user_attribute USING btree (user_id);


--
-- Name: idx_user_attribute_name; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_attribute_name ON public.user_attribute USING btree (name, value);


--
-- Name: idx_user_consent; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_consent ON public.user_consent USING btree (user_id);


--
-- Name: idx_user_credential; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_credential ON public.credential USING btree (user_id);


--
-- Name: idx_user_email; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_email ON public.user_entity USING btree (email);


--
-- Name: idx_user_group_mapping; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_group_mapping ON public.user_group_membership USING btree (user_id);


--
-- Name: idx_user_reqactions; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_reqactions ON public.user_required_action USING btree (user_id);


--
-- Name: idx_user_role_mapping; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_role_mapping ON public.user_role_mapping USING btree (user_id);


--
-- Name: idx_user_service_account; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_user_service_account ON public.user_entity USING btree (realm_id, service_account_client_link);


--
-- Name: idx_usr_fed_map_fed_prv; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_usr_fed_map_fed_prv ON public.user_federation_mapper USING btree (federation_provider_id);


--
-- Name: idx_usr_fed_map_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_usr_fed_map_realm ON public.user_federation_mapper USING btree (realm_id);


--
-- Name: idx_usr_fed_prv_realm; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_usr_fed_prv_realm ON public.user_federation_provider USING btree (realm_id);


--
-- Name: idx_web_orig_client; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX idx_web_orig_client ON public.web_origins USING btree (client_id);


--
-- Name: user_attr_long_values; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX user_attr_long_values ON public.user_attribute USING btree (long_value_hash, name);


--
-- Name: user_attr_long_values_lower_case; Type: INDEX; Schema: public; Owner: dbo
--

CREATE INDEX user_attr_long_values_lower_case ON public.user_attribute USING btree (long_value_hash_lower_case, name);


--
-- Name: client_session_auth_status auth_status_constraint; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_auth_status
    ADD CONSTRAINT auth_status_constraint FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: identity_provider fk2b4ebc52ae5c3b34; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.identity_provider
    ADD CONSTRAINT fk2b4ebc52ae5c3b34 FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: client_attributes fk3c47c64beacca966; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_attributes
    ADD CONSTRAINT fk3c47c64beacca966 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: federated_identity fk404288b92ef007a6; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.federated_identity
    ADD CONSTRAINT fk404288b92ef007a6 FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: client_node_registrations fk4129723ba992f594; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_node_registrations
    ADD CONSTRAINT fk4129723ba992f594 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: client_session_note fk5edfb00ff51c2736; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_note
    ADD CONSTRAINT fk5edfb00ff51c2736 FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: user_session_note fk5edfb00ff51d3472; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_session_note
    ADD CONSTRAINT fk5edfb00ff51d3472 FOREIGN KEY (user_session) REFERENCES public.user_session(id);


--
-- Name: client_session_role fk_11b7sgqw18i532811v7o2dv76; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_role
    ADD CONSTRAINT fk_11b7sgqw18i532811v7o2dv76 FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: redirect_uris fk_1burs8pb4ouj97h5wuppahv9f; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.redirect_uris
    ADD CONSTRAINT fk_1burs8pb4ouj97h5wuppahv9f FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: user_federation_provider fk_1fj32f6ptolw2qy60cd8n01e8; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_provider
    ADD CONSTRAINT fk_1fj32f6ptolw2qy60cd8n01e8 FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: client_session_prot_mapper fk_33a8sgqw18i532811v7o2dk89; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session_prot_mapper
    ADD CONSTRAINT fk_33a8sgqw18i532811v7o2dk89 FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: realm_required_credential fk_5hg65lybevavkqfki3kponh9v; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_required_credential
    ADD CONSTRAINT fk_5hg65lybevavkqfki3kponh9v FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: resource_attribute fk_5hrm2vlf9ql5fu022kqepovbr; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_attribute
    ADD CONSTRAINT fk_5hrm2vlf9ql5fu022kqepovbr FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: user_attribute fk_5hrm2vlf9ql5fu043kqepovbr; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_attribute
    ADD CONSTRAINT fk_5hrm2vlf9ql5fu043kqepovbr FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: user_required_action fk_6qj3w1jw9cvafhe19bwsiuvmd; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_required_action
    ADD CONSTRAINT fk_6qj3w1jw9cvafhe19bwsiuvmd FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: keycloak_role fk_6vyqfe4cn4wlq8r6kt5vdsj5c; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.keycloak_role
    ADD CONSTRAINT fk_6vyqfe4cn4wlq8r6kt5vdsj5c FOREIGN KEY (realm) REFERENCES public.realm(id);


--
-- Name: realm_smtp_config fk_70ej8xdxgxd0b9hh6180irr0o; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_smtp_config
    ADD CONSTRAINT fk_70ej8xdxgxd0b9hh6180irr0o FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: realm_attribute fk_8shxd6l3e9atqukacxgpffptw; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_attribute
    ADD CONSTRAINT fk_8shxd6l3e9atqukacxgpffptw FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: composite_role fk_a63wvekftu8jo1pnj81e7mce2; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.composite_role
    ADD CONSTRAINT fk_a63wvekftu8jo1pnj81e7mce2 FOREIGN KEY (composite) REFERENCES public.keycloak_role(id);


--
-- Name: authentication_execution fk_auth_exec_flow; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authentication_execution
    ADD CONSTRAINT fk_auth_exec_flow FOREIGN KEY (flow_id) REFERENCES public.authentication_flow(id);


--
-- Name: authentication_execution fk_auth_exec_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authentication_execution
    ADD CONSTRAINT fk_auth_exec_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: authentication_flow fk_auth_flow_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authentication_flow
    ADD CONSTRAINT fk_auth_flow_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: authenticator_config fk_auth_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.authenticator_config
    ADD CONSTRAINT fk_auth_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: client_session fk_b4ao2vcvat6ukau74wbwtfqo1; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_session
    ADD CONSTRAINT fk_b4ao2vcvat6ukau74wbwtfqo1 FOREIGN KEY (session_id) REFERENCES public.user_session(id);


--
-- Name: user_role_mapping fk_c4fqv34p1mbylloxang7b1q3l; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_role_mapping
    ADD CONSTRAINT fk_c4fqv34p1mbylloxang7b1q3l FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: client_scope_attributes fk_cl_scope_attr_scope; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_scope_attributes
    ADD CONSTRAINT fk_cl_scope_attr_scope FOREIGN KEY (scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_scope_role_mapping fk_cl_scope_rm_scope; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_scope_role_mapping
    ADD CONSTRAINT fk_cl_scope_rm_scope FOREIGN KEY (scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_user_session_note fk_cl_usr_ses_note; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_user_session_note
    ADD CONSTRAINT fk_cl_usr_ses_note FOREIGN KEY (client_session) REFERENCES public.client_session(id);


--
-- Name: protocol_mapper fk_cli_scope_mapper; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.protocol_mapper
    ADD CONSTRAINT fk_cli_scope_mapper FOREIGN KEY (client_scope_id) REFERENCES public.client_scope(id);


--
-- Name: client_initial_access fk_client_init_acc_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.client_initial_access
    ADD CONSTRAINT fk_client_init_acc_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: component_config fk_component_config; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.component_config
    ADD CONSTRAINT fk_component_config FOREIGN KEY (component_id) REFERENCES public.component(id);


--
-- Name: component fk_component_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.component
    ADD CONSTRAINT fk_component_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: realm_default_groups fk_def_groups_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_default_groups
    ADD CONSTRAINT fk_def_groups_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: user_federation_mapper_config fk_fedmapper_cfg; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_mapper_config
    ADD CONSTRAINT fk_fedmapper_cfg FOREIGN KEY (user_federation_mapper_id) REFERENCES public.user_federation_mapper(id);


--
-- Name: user_federation_mapper fk_fedmapperpm_fedprv; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_mapper
    ADD CONSTRAINT fk_fedmapperpm_fedprv FOREIGN KEY (federation_provider_id) REFERENCES public.user_federation_provider(id);


--
-- Name: user_federation_mapper fk_fedmapperpm_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_mapper
    ADD CONSTRAINT fk_fedmapperpm_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: associated_policy fk_frsr5s213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.associated_policy
    ADD CONSTRAINT fk_frsr5s213xcx4wnkog82ssrfy FOREIGN KEY (associated_policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: scope_policy fk_frsrasp13xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.scope_policy
    ADD CONSTRAINT fk_frsrasp13xcx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: resource_server_perm_ticket fk_frsrho213xcx4wnkog82sspmt; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrho213xcx4wnkog82sspmt FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: resource_server_resource fk_frsrho213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_resource
    ADD CONSTRAINT fk_frsrho213xcx4wnkog82ssrfy FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: resource_server_perm_ticket fk_frsrho213xcx4wnkog83sspmt; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrho213xcx4wnkog83sspmt FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: resource_server_perm_ticket fk_frsrho213xcx4wnkog84sspmt; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrho213xcx4wnkog84sspmt FOREIGN KEY (scope_id) REFERENCES public.resource_server_scope(id);


--
-- Name: associated_policy fk_frsrpas14xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.associated_policy
    ADD CONSTRAINT fk_frsrpas14xcx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: scope_policy fk_frsrpass3xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.scope_policy
    ADD CONSTRAINT fk_frsrpass3xcx4wnkog82ssrfy FOREIGN KEY (scope_id) REFERENCES public.resource_server_scope(id);


--
-- Name: resource_server_perm_ticket fk_frsrpo2128cx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_perm_ticket
    ADD CONSTRAINT fk_frsrpo2128cx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: resource_server_policy fk_frsrpo213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_policy
    ADD CONSTRAINT fk_frsrpo213xcx4wnkog82ssrfy FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: resource_scope fk_frsrpos13xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_scope
    ADD CONSTRAINT fk_frsrpos13xcx4wnkog82ssrfy FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: resource_policy fk_frsrpos53xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_policy
    ADD CONSTRAINT fk_frsrpos53xcx4wnkog82ssrfy FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: resource_policy fk_frsrpp213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_policy
    ADD CONSTRAINT fk_frsrpp213xcx4wnkog82ssrfy FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: resource_scope fk_frsrps213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_scope
    ADD CONSTRAINT fk_frsrps213xcx4wnkog82ssrfy FOREIGN KEY (scope_id) REFERENCES public.resource_server_scope(id);


--
-- Name: resource_server_scope fk_frsrso213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_server_scope
    ADD CONSTRAINT fk_frsrso213xcx4wnkog82ssrfy FOREIGN KEY (resource_server_id) REFERENCES public.resource_server(id);


--
-- Name: composite_role fk_gr7thllb9lu8q4vqa4524jjy8; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.composite_role
    ADD CONSTRAINT fk_gr7thllb9lu8q4vqa4524jjy8 FOREIGN KEY (child_role) REFERENCES public.keycloak_role(id);


--
-- Name: user_consent_client_scope fk_grntcsnt_clsc_usc; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_consent_client_scope
    ADD CONSTRAINT fk_grntcsnt_clsc_usc FOREIGN KEY (user_consent_id) REFERENCES public.user_consent(id);


--
-- Name: user_consent fk_grntcsnt_user; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_consent
    ADD CONSTRAINT fk_grntcsnt_user FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: group_attribute fk_group_attribute_group; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.group_attribute
    ADD CONSTRAINT fk_group_attribute_group FOREIGN KEY (group_id) REFERENCES public.keycloak_group(id);


--
-- Name: group_role_mapping fk_group_role_group; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.group_role_mapping
    ADD CONSTRAINT fk_group_role_group FOREIGN KEY (group_id) REFERENCES public.keycloak_group(id);


--
-- Name: realm_enabled_event_types fk_h846o4h0w8epx5nwedrf5y69j; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_enabled_event_types
    ADD CONSTRAINT fk_h846o4h0w8epx5nwedrf5y69j FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: realm_events_listeners fk_h846o4h0w8epx5nxev9f5y69j; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_events_listeners
    ADD CONSTRAINT fk_h846o4h0w8epx5nxev9f5y69j FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: identity_provider_mapper fk_idpm_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.identity_provider_mapper
    ADD CONSTRAINT fk_idpm_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: idp_mapper_config fk_idpmconfig; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.idp_mapper_config
    ADD CONSTRAINT fk_idpmconfig FOREIGN KEY (idp_mapper_id) REFERENCES public.identity_provider_mapper(id);


--
-- Name: web_origins fk_lojpho213xcx4wnkog82ssrfy; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.web_origins
    ADD CONSTRAINT fk_lojpho213xcx4wnkog82ssrfy FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: scope_mapping fk_ouse064plmlr732lxjcn1q5f1; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.scope_mapping
    ADD CONSTRAINT fk_ouse064plmlr732lxjcn1q5f1 FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: protocol_mapper fk_pcm_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.protocol_mapper
    ADD CONSTRAINT fk_pcm_realm FOREIGN KEY (client_id) REFERENCES public.client(id);


--
-- Name: credential fk_pfyr0glasqyl0dei3kl69r6v0; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.credential
    ADD CONSTRAINT fk_pfyr0glasqyl0dei3kl69r6v0 FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: protocol_mapper_config fk_pmconfig; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.protocol_mapper_config
    ADD CONSTRAINT fk_pmconfig FOREIGN KEY (protocol_mapper_id) REFERENCES public.protocol_mapper(id);


--
-- Name: default_client_scope fk_r_def_cli_scope_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.default_client_scope
    ADD CONSTRAINT fk_r_def_cli_scope_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: required_action_provider fk_req_act_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.required_action_provider
    ADD CONSTRAINT fk_req_act_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: resource_uris fk_resource_server_uris; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.resource_uris
    ADD CONSTRAINT fk_resource_server_uris FOREIGN KEY (resource_id) REFERENCES public.resource_server_resource(id);


--
-- Name: role_attribute fk_role_attribute_id; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.role_attribute
    ADD CONSTRAINT fk_role_attribute_id FOREIGN KEY (role_id) REFERENCES public.keycloak_role(id);


--
-- Name: realm_supported_locales fk_supported_locales_realm; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.realm_supported_locales
    ADD CONSTRAINT fk_supported_locales_realm FOREIGN KEY (realm_id) REFERENCES public.realm(id);


--
-- Name: user_federation_config fk_t13hpu1j94r2ebpekr39x5eu5; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_federation_config
    ADD CONSTRAINT fk_t13hpu1j94r2ebpekr39x5eu5 FOREIGN KEY (user_federation_provider_id) REFERENCES public.user_federation_provider(id);


--
-- Name: user_group_membership fk_user_group_user; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.user_group_membership
    ADD CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES public.user_entity(id);


--
-- Name: policy_config fkdc34197cf864c4e43; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.policy_config
    ADD CONSTRAINT fkdc34197cf864c4e43 FOREIGN KEY (policy_id) REFERENCES public.resource_server_policy(id);


--
-- Name: identity_provider_config fkdc4897cf864c4e43; Type: FK CONSTRAINT; Schema: public; Owner: dbo
--

ALTER TABLE ONLY public.identity_provider_config
    ADD CONSTRAINT fkdc4897cf864c4e43 FOREIGN KEY (identity_provider_id) REFERENCES public.identity_provider(internal_id);


--
-- Name: DATABASE keycloak; Type: ACL; Schema: -; Owner: dbo
--

GRANT CONNECT ON DATABASE keycloak TO keycloak_user;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

GRANT USAGE ON SCHEMA public TO keycloak_user;


--
-- Name: TABLE admin_event_entity; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.admin_event_entity TO keycloak_user;


--
-- Name: TABLE associated_policy; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.associated_policy TO keycloak_user;


--
-- Name: TABLE authentication_execution; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.authentication_execution TO keycloak_user;


--
-- Name: TABLE authentication_flow; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.authentication_flow TO keycloak_user;


--
-- Name: TABLE authenticator_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.authenticator_config TO keycloak_user;


--
-- Name: TABLE authenticator_config_entry; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.authenticator_config_entry TO keycloak_user;


--
-- Name: TABLE broker_link; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.broker_link TO keycloak_user;


--
-- Name: TABLE client; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client TO keycloak_user;


--
-- Name: TABLE client_attributes; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_attributes TO keycloak_user;


--
-- Name: TABLE client_auth_flow_bindings; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_auth_flow_bindings TO keycloak_user;


--
-- Name: TABLE client_initial_access; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_initial_access TO keycloak_user;


--
-- Name: TABLE client_node_registrations; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_node_registrations TO keycloak_user;


--
-- Name: TABLE client_scope; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_scope TO keycloak_user;


--
-- Name: TABLE client_scope_attributes; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_scope_attributes TO keycloak_user;


--
-- Name: TABLE client_scope_client; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_scope_client TO keycloak_user;


--
-- Name: TABLE client_scope_role_mapping; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_scope_role_mapping TO keycloak_user;


--
-- Name: TABLE client_session; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_session TO keycloak_user;


--
-- Name: TABLE client_session_auth_status; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_session_auth_status TO keycloak_user;


--
-- Name: TABLE client_session_note; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_session_note TO keycloak_user;


--
-- Name: TABLE client_session_prot_mapper; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_session_prot_mapper TO keycloak_user;


--
-- Name: TABLE client_session_role; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_session_role TO keycloak_user;


--
-- Name: TABLE client_user_session_note; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.client_user_session_note TO keycloak_user;


--
-- Name: TABLE component; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.component TO keycloak_user;


--
-- Name: TABLE component_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.component_config TO keycloak_user;


--
-- Name: TABLE composite_role; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.composite_role TO keycloak_user;


--
-- Name: TABLE credential; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.credential TO keycloak_user;


--
-- Name: TABLE databasechangelog; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.databasechangelog TO keycloak_user;


--
-- Name: TABLE databasechangeloglock; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.databasechangeloglock TO keycloak_user;


--
-- Name: TABLE default_client_scope; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.default_client_scope TO keycloak_user;


--
-- Name: TABLE event_entity; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.event_entity TO keycloak_user;


--
-- Name: TABLE fed_user_attribute; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.fed_user_attribute TO keycloak_user;


--
-- Name: TABLE fed_user_consent; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.fed_user_consent TO keycloak_user;


--
-- Name: TABLE fed_user_consent_cl_scope; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.fed_user_consent_cl_scope TO keycloak_user;


--
-- Name: TABLE fed_user_credential; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.fed_user_credential TO keycloak_user;


--
-- Name: TABLE fed_user_group_membership; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.fed_user_group_membership TO keycloak_user;


--
-- Name: TABLE fed_user_required_action; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.fed_user_required_action TO keycloak_user;


--
-- Name: TABLE fed_user_role_mapping; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.fed_user_role_mapping TO keycloak_user;


--
-- Name: TABLE federated_identity; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.federated_identity TO keycloak_user;


--
-- Name: TABLE federated_user; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.federated_user TO keycloak_user;


--
-- Name: TABLE group_attribute; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.group_attribute TO keycloak_user;


--
-- Name: TABLE group_role_mapping; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.group_role_mapping TO keycloak_user;


--
-- Name: TABLE identity_provider; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.identity_provider TO keycloak_user;


--
-- Name: TABLE identity_provider_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.identity_provider_config TO keycloak_user;


--
-- Name: TABLE identity_provider_mapper; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.identity_provider_mapper TO keycloak_user;


--
-- Name: TABLE idp_mapper_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.idp_mapper_config TO keycloak_user;


--
-- Name: TABLE keycloak_group; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.keycloak_group TO keycloak_user;


--
-- Name: TABLE keycloak_role; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.keycloak_role TO keycloak_user;


--
-- Name: TABLE migration_model; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.migration_model TO keycloak_user;


--
-- Name: TABLE offline_client_session; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.offline_client_session TO keycloak_user;


--
-- Name: TABLE offline_user_session; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.offline_user_session TO keycloak_user;


--
-- Name: TABLE policy_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.policy_config TO keycloak_user;


--
-- Name: TABLE protocol_mapper; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.protocol_mapper TO keycloak_user;


--
-- Name: TABLE protocol_mapper_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.protocol_mapper_config TO keycloak_user;


--
-- Name: TABLE realm; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm TO keycloak_user;


--
-- Name: TABLE realm_attribute; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_attribute TO keycloak_user;


--
-- Name: TABLE realm_default_groups; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_default_groups TO keycloak_user;


--
-- Name: TABLE realm_enabled_event_types; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_enabled_event_types TO keycloak_user;


--
-- Name: TABLE realm_events_listeners; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_events_listeners TO keycloak_user;


--
-- Name: TABLE realm_localizations; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_localizations TO keycloak_user;


--
-- Name: TABLE realm_required_credential; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_required_credential TO keycloak_user;


--
-- Name: TABLE realm_smtp_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_smtp_config TO keycloak_user;


--
-- Name: TABLE realm_supported_locales; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.realm_supported_locales TO keycloak_user;


--
-- Name: TABLE redirect_uris; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.redirect_uris TO keycloak_user;


--
-- Name: TABLE required_action_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.required_action_config TO keycloak_user;


--
-- Name: TABLE required_action_provider; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.required_action_provider TO keycloak_user;


--
-- Name: TABLE resource_attribute; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_attribute TO keycloak_user;


--
-- Name: TABLE resource_policy; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_policy TO keycloak_user;


--
-- Name: TABLE resource_scope; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_scope TO keycloak_user;


--
-- Name: TABLE resource_server; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_server TO keycloak_user;


--
-- Name: TABLE resource_server_perm_ticket; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_server_perm_ticket TO keycloak_user;


--
-- Name: TABLE resource_server_policy; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_server_policy TO keycloak_user;


--
-- Name: TABLE resource_server_resource; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_server_resource TO keycloak_user;


--
-- Name: TABLE resource_server_scope; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_server_scope TO keycloak_user;


--
-- Name: TABLE resource_uris; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.resource_uris TO keycloak_user;


--
-- Name: TABLE role_attribute; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.role_attribute TO keycloak_user;


--
-- Name: TABLE scope_mapping; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.scope_mapping TO keycloak_user;


--
-- Name: TABLE scope_policy; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.scope_policy TO keycloak_user;


--
-- Name: TABLE user_attribute; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_attribute TO keycloak_user;


--
-- Name: TABLE user_consent; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_consent TO keycloak_user;


--
-- Name: TABLE user_consent_client_scope; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_consent_client_scope TO keycloak_user;


--
-- Name: TABLE user_entity; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_entity TO keycloak_user;


--
-- Name: TABLE user_federation_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_federation_config TO keycloak_user;


--
-- Name: TABLE user_federation_mapper; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_federation_mapper TO keycloak_user;


--
-- Name: TABLE user_federation_mapper_config; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_federation_mapper_config TO keycloak_user;


--
-- Name: TABLE user_federation_provider; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_federation_provider TO keycloak_user;


--
-- Name: TABLE user_group_membership; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_group_membership TO keycloak_user;


--
-- Name: TABLE user_required_action; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_required_action TO keycloak_user;


--
-- Name: TABLE user_role_mapping; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_role_mapping TO keycloak_user;


--
-- Name: TABLE user_session; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_session TO keycloak_user;


--
-- Name: TABLE user_session_note; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.user_session_note TO keycloak_user;


--
-- Name: TABLE username_login_failure; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.username_login_failure TO keycloak_user;


--
-- Name: TABLE web_origins; Type: ACL; Schema: public; Owner: dbo
--

GRANT ALL ON TABLE public.web_origins TO keycloak_user;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: dbo
--

ALTER DEFAULT PRIVILEGES FOR ROLE dbo IN SCHEMA public GRANT ALL ON SEQUENCES TO keycloak_user;


--
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: public; Owner: dbo
--

ALTER DEFAULT PRIVILEGES FOR ROLE dbo IN SCHEMA public GRANT ALL ON FUNCTIONS TO keycloak_user;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: dbo
--

ALTER DEFAULT PRIVILEGES FOR ROLE dbo IN SCHEMA public GRANT ALL ON TABLES TO keycloak_user;


--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Debian 16.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

DROP DATABASE postgres;
--
-- Name: postgres; Type: DATABASE; Schema: -; Owner: dbo
--

CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.utf8';


ALTER DATABASE postgres OWNER TO dbo;

\connect postgres

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: DATABASE postgres; Type: COMMENT; Schema: -; Owner: dbo
--

COMMENT ON DATABASE postgres IS 'default administrative connection database';


--
-- Name: DATABASE postgres; Type: ACL; Schema: -; Owner: dbo
--

REVOKE ALL ON DATABASE postgres FROM dbo;


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: pg_database_owner
--

GRANT USAGE ON SCHEMA public TO keycloak_user;
GRANT USAGE ON SCHEMA public TO restaurante_user;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: public; Owner: dbo
--

ALTER DEFAULT PRIVILEGES FOR ROLE dbo IN SCHEMA public GRANT ALL ON SEQUENCES TO keycloak_user;


--
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: public; Owner: dbo
--

ALTER DEFAULT PRIVILEGES FOR ROLE dbo IN SCHEMA public GRANT ALL ON FUNCTIONS TO keycloak_user;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: public; Owner: dbo
--

ALTER DEFAULT PRIVILEGES FOR ROLE dbo IN SCHEMA public GRANT ALL ON TABLES TO keycloak_user;
ALTER DEFAULT PRIVILEGES FOR ROLE dbo IN SCHEMA public GRANT SELECT,INSERT,DELETE,UPDATE ON TABLES TO restaurante_user;


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

