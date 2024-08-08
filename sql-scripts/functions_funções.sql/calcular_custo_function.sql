-- FUNCTION: public.calcular_custo(character varying)

-- DROP FUNCTION IF EXISTS public.calcular_custo(character varying);

CREATE OR REPLACE FUNCTION public.calcular_custo(
	p_placa character varying)
    RETURNS double precision
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE
    v_dataHoraEntrada timestamp;
    v_dataHoraSaida timestamp;
    v_horasEstacionado double precision;
    v_taxaPorHora double precision := 10.0; -- valor de calção
    v_custo double precision := 0.0;
BEGIN
    SELECT INTO v_dataHoraEntrada TO_TIMESTAMP(dataentrada || ' ' || horaentrada, 'YYYY-MM-DD HH24:MI') FROM registro WHERE placa = p_placa AND datasaida IS NULL;
    SELECT INTO v_dataHoraSaida TO_TIMESTAMP(datasaida || ' ' || horasaida, 'YYYY-MM-DD HH24:MI') FROM registro WHERE placa = p_placa AND datasaida IS NOT NULL;

    v_horasEstacionado = EXTRACT(EPOCH FROM v_dataHoraSaida - v_dataHoraEntrada) / 3600;

    IF v_horasEstacionado <= 1 THEN
        v_custo := v_taxaPorHora;
    ELSIF v_horasEstacionado <= 3 THEN
        v_custo := v_taxaPorHora * 2 * (v_horasEstacionado - 1); -- 100% a mais do valor calção para as horas após a primeira
    ELSIF v_horasEstacionado <= 4 THEN
        v_custo := v_taxaPorHora * 2 * 2 + v_taxaPorHora * 1.25 * (v_horasEstacionado - 3); -- 25% a mais do valor calção para as horas após a terceira
    ELSIF v_horasEstacionado <= 5 THEN
        v_custo := v_taxaPorHora * 2 * 2 + v_taxaPorHora * 1.25 + v_taxaPorHora * 0.8 * (v_horasEstacionado - 4); -- 20% a menos do valor calção para as horas após a quarta
    ELSE
        v_custo := v_taxaPorHora * 2 * 2 + v_taxaPorHora * 1.25 + v_taxaPorHora * 0.8 + v_taxaPorHora * 0.75 * (v_horasEstacionado - 5); -- 25% a menos do valor calção para as horas após a quinta
    END IF;

    RETURN v_custo;
END;
$BODY$;

ALTER FUNCTION public.calcular_custo(character varying)
    OWNER TO postgres;
