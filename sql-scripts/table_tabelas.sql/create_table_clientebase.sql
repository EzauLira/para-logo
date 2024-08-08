-- Table: public.clientebase

-- DROP TABLE IF EXISTS public.clientebase;

CREATE TABLE IF NOT EXISTS public.clientebase
(
    idcliente integer NOT NULL DEFAULT nextval('clientebase_idcliente_seq'::regclass),
    nome character varying(255) COLLATE pg_catalog."default",
    rg character varying(255) COLLATE pg_catalog."default",
    telefone character varying(255) COLLATE pg_catalog."default",
    placaveiculo character varying(255) COLLATE pg_catalog."default",
    dataregistro date,
    CONSTRAINT clientebase_pkey PRIMARY KEY (idcliente)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.clientebase
    OWNER to postgres;