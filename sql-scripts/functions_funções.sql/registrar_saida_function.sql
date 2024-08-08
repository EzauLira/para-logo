-- FUNCTION: public.registrar_saida(character varying, character varying, character varying)

-- DROP FUNCTION IF EXISTS public.registrar_saida(character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION public.registrar_saida(
	p_placa character varying,
	p_datasaida character varying,
	p_horasaida character varying)
    RETURNS void
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
BEGIN
    UPDATE registro 
    SET dataSaida = p_dataSaida, horaSaida = p_horaSaida
    WHERE placa = p_placa AND dataSaida IS NULL;
END;
$BODY$;

ALTER FUNCTION public.registrar_saida(character varying, character varying, character varying)
    OWNER TO postgres;
