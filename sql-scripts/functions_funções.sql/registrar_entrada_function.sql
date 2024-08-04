-- FUNCTION: public.registrar_entrada(character varying, character varying, character varying)

-- DROP FUNCTION IF EXISTS public.registrar_entrada(character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION public.registrar_entrada(
	p_placa character varying,
	p_dataentrada character varying,
	p_horaentrada character varying)
    RETURNS void
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
    INSERT INTO registro (placa, dataEntrada, horaEntrada) 
    VALUES (p_placa, p_dataEntrada, p_horaEntrada);
END;
$BODY$;

ALTER FUNCTION public.registrar_entrada(character varying, character varying, character varying)
    OWNER TO postgres;
