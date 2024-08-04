-- Table: public.estacionamento_vagas

-- DROP TABLE IF EXISTS public.estacionamento_vagas;

CREATE TABLE IF NOT EXISTS public.estacionamento_vagas
(
    id integer NOT NULL DEFAULT nextval('estacionamento_vagas_id_seq'::regclass),
    vagas_disponiveis integer NOT NULL,
    CONSTRAINT estacionamento_vagas_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.estacionamento_vagas
    OWNER to postgres;