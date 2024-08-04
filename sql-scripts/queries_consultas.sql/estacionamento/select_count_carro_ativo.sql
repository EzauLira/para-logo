-- Conta carros ativos na tabela registro

SELECT COUNT(*) FROM registro WHERE placa = 'ABC-1234' AND datasaida IS NULL AND horasaida IS NULL;
