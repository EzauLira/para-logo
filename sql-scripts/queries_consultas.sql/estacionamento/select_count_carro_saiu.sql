-- Conta carros que saíram do registro no dia atual

SELECT COUNT(*) FROM registro WHERE placa = ? AND TO_DATE(dataentrada, 'YYYY-MM-DD') = CURRENT_DATE AND datasaida IS NOT NULL AND horasaida IS NOT NULL;
