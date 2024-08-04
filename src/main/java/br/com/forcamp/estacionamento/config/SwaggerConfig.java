package br.com.forcamp.estacionamento.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API do Sistema de Estacionamento (SDE)")
                        .version("1.0")
                        .description("Documentação da API para o Sistema de Estacionamento (SDE). " +
                                "Este sistema permite o gerenciamento eficiente de vagas de estacionamento, " +
                                "incluindo reserva, check-in/check-out de veículos e cálculo de tarifas."));
    }
    //http://localhost:8080/swagger-ui/index.html
}