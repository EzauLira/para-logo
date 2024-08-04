-- Exclui cliente com base na data de registro

DELETE FROM ClienteBase WHERE dataRegistro = CAST('data dd-yy-yyyy' AS DATE);
