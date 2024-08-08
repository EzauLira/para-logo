-- Diminui o número de vagas disponíveis

UPDATE estacionamento_vagas SET vagas_disponiveis = vagas_disponiveis - 1 WHERE id = 1;
