--
-- PostgreSQL database dump
--

-- Dumped from database version 15.10 (Debian 15.10-0+deb12u1)
-- Dumped by pg_dump version 15.10 (Debian 15.10-0+deb12u1)

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
-- Name: candidato; Type: TABLE; Schema: public; Owner: geek
--

CREATE TABLE public.candidato (
    id bigint NOT NULL,
    nome character varying(100) NOT NULL,
    email character varying(150) NOT NULL,
    cpf character varying(14) NOT NULL,
    idade integer NOT NULL,
    estado character varying(50) NOT NULL,
    cep character varying(10)
);


ALTER TABLE public.candidato OWNER TO geek;

--
-- Name: candidatos_competencias; Type: TABLE; Schema: public; Owner: geek
--

CREATE TABLE public.candidatos_competencias (
    id_candidato bigint NOT NULL,
    id_competencia bigint NOT NULL
);


ALTER TABLE public.candidatos_competencias OWNER TO geek;

--
-- Name: candidatos_id_seq; Type: SEQUENCE; Schema: public; Owner: geek
--

ALTER TABLE public.candidato ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.candidatos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: competencias; Type: TABLE; Schema: public; Owner: geek
--

CREATE TABLE public.competencias (
    id bigint NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE public.competencias OWNER TO geek;

--
-- Name: competencias_id_seq; Type: SEQUENCE; Schema: public; Owner: geek
--

ALTER TABLE public.competencias ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.competencias_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: empresa; Type: TABLE; Schema: public; Owner: geek
--

CREATE TABLE public.empresa (
    id bigint NOT NULL,
    nome character varying(150) NOT NULL,
    email character varying(150) NOT NULL,
    cnpj character varying(18) NOT NULL,
    pais character varying(50) NOT NULL,
    cep character varying(10) NOT NULL
);


ALTER TABLE public.empresa OWNER TO geek;

--
-- Name: empresa_competencia; Type: TABLE; Schema: public; Owner: geek
--

CREATE TABLE public.empresa_competencia (
    id integer NOT NULL,
    empresa_id integer NOT NULL,
    nome character varying(255) NOT NULL
);


ALTER TABLE public.empresa_competencia OWNER TO geek;

--
-- Name: empresa_competencia_id_seq; Type: SEQUENCE; Schema: public; Owner: geek
--

CREATE SEQUENCE public.empresa_competencia_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empresa_competencia_id_seq OWNER TO geek;

--
-- Name: empresa_competencia_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: geek
--

ALTER SEQUENCE public.empresa_competencia_id_seq OWNED BY public.empresa_competencia.id;


--
-- Name: empresas_id_seq; Type: SEQUENCE; Schema: public; Owner: geek
--

ALTER TABLE public.empresa ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.empresas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: vagas; Type: TABLE; Schema: public; Owner: geek
--

CREATE TABLE public.vagas (
    id bigint NOT NULL,
    nome character varying(100) NOT NULL,
    descricao text NOT NULL,
    id_empresa bigint DEFAULT 0
);


ALTER TABLE public.vagas OWNER TO geek;

--
-- Name: vagas_competencias; Type: TABLE; Schema: public; Owner: geek
--

CREATE TABLE public.vagas_competencias (
    id_vaga bigint NOT NULL,
    id_competencia bigint NOT NULL
);


ALTER TABLE public.vagas_competencias OWNER TO geek;

--
-- Name: vagas_id_seq; Type: SEQUENCE; Schema: public; Owner: geek
--

ALTER TABLE public.vagas ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.vagas_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: empresa_competencia id; Type: DEFAULT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.empresa_competencia ALTER COLUMN id SET DEFAULT nextval('public.empresa_competencia_id_seq'::regclass);


--
-- Name: candidatos_competencias candidatos_competencias_pkey; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.candidatos_competencias
    ADD CONSTRAINT candidatos_competencias_pkey PRIMARY KEY (id_candidato, id_competencia);


--
-- Name: candidato candidatos_cpf_key; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.candidato
    ADD CONSTRAINT candidatos_cpf_key UNIQUE (cpf);


--
-- Name: candidato candidatos_email_key; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.candidato
    ADD CONSTRAINT candidatos_email_key UNIQUE (email);


--
-- Name: candidato candidatos_pkey; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.candidato
    ADD CONSTRAINT candidatos_pkey PRIMARY KEY (id);


--
-- Name: competencias competencias_nome_key; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.competencias
    ADD CONSTRAINT competencias_nome_key UNIQUE (nome);


--
-- Name: competencias competencias_pkey; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.competencias
    ADD CONSTRAINT competencias_pkey PRIMARY KEY (id);


--
-- Name: empresa_competencia empresa_competencia_pkey; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.empresa_competencia
    ADD CONSTRAINT empresa_competencia_pkey PRIMARY KEY (id);


--
-- Name: empresa empresas_cnpj_key; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresas_cnpj_key UNIQUE (cnpj);


--
-- Name: empresa empresas_email_key; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresas_email_key UNIQUE (email);


--
-- Name: empresa empresas_pkey; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.empresa
    ADD CONSTRAINT empresas_pkey PRIMARY KEY (id);


--
-- Name: vagas_competencias vagas_competencias_pkey; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.vagas_competencias
    ADD CONSTRAINT vagas_competencias_pkey PRIMARY KEY (id_vaga, id_competencia);


--
-- Name: vagas vagas_pkey; Type: CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.vagas
    ADD CONSTRAINT vagas_pkey PRIMARY KEY (id);


--
-- Name: candidatos_competencias candidatos_competencias_id_candidato_fkey; Type: FK CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.candidatos_competencias
    ADD CONSTRAINT candidatos_competencias_id_candidato_fkey FOREIGN KEY (id_candidato) REFERENCES public.candidato(id) ON DELETE CASCADE;


--
-- Name: candidatos_competencias candidatos_competencias_id_competencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.candidatos_competencias
    ADD CONSTRAINT candidatos_competencias_id_competencia_fkey FOREIGN KEY (id_competencia) REFERENCES public.competencias(id) ON DELETE CASCADE;


--
-- Name: empresa_competencia empresa_competencia_empresa_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.empresa_competencia
    ADD CONSTRAINT empresa_competencia_empresa_id_fkey FOREIGN KEY (empresa_id) REFERENCES public.empresa(id) ON DELETE CASCADE;


--
-- Name: vagas_competencias vagas_competencias_id_competencia_fkey; Type: FK CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.vagas_competencias
    ADD CONSTRAINT vagas_competencias_id_competencia_fkey FOREIGN KEY (id_competencia) REFERENCES public.competencias(id) ON DELETE CASCADE;


--
-- Name: vagas_competencias vagas_competencias_id_vaga_fkey; Type: FK CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.vagas_competencias
    ADD CONSTRAINT vagas_competencias_id_vaga_fkey FOREIGN KEY (id_vaga) REFERENCES public.vagas(id) ON DELETE CASCADE;


--
-- Name: vagas vagas_id_empresa_fkey; Type: FK CONSTRAINT; Schema: public; Owner: geek
--

ALTER TABLE ONLY public.vagas
    ADD CONSTRAINT vagas_id_empresa_fkey FOREIGN KEY (id_empresa) REFERENCES public.empresa(id) ON DELETE SET NULL;


--
-- PostgreSQL database dump complete
--

