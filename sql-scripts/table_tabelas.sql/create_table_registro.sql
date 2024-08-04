-- Table: public.registro

-- DROP TABLE IF EXISTS public.registro;

CREATE TABLE IF NOT EXISTS public.registro
(
    placa character varying(255) COLLATE pg_catalog."default",
    dataentrada character varying(255) COLLATE pg_catalog."default",
    horaentrada character varying(255) COLLATE pg_catalog."default",
    datasaida character varying(255) COLLATE pg_catalog."default",
    horasaida character varying(255) COLLATE pg_catalog."default"
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.registro
    OWNER to postgres;