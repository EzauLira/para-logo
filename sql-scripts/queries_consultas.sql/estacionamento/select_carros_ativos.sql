-- Consulta carros que ainda estão ativos (não saíram)

SELECT * FROM registro WHERE dataSaida IS NULL;
