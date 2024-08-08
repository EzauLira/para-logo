# SGE - Sistema para gerenciamento de estacionamento 🅿


Gerenciador de Estacionamento: Projeto Acadêmico Desenvolvido em **Java** com **Spring Boot**, **PostgreSQL**, **Postman** e **Swagger** para a Disciplina de Desenvolvimento de Sistemas da *[Foursys](https://br.linkedin.com/company/foursys)*.

## Créditos


✍ Projeto proposto pela ***Fourcamp***, disciplinado e orientado pelos mestres: *[Bruno Martin](https://www.linkedin.com/in/brunoermacora/)* e *[Denilson Elias](https://www.linkedin.com/in/denilsonbitit/)*

## Descrição


Este projeto é uma **API** em **Java** para a gestão de um **estacionamento**. Ele oferece funcionalidades como **Cadastro de clientes** para um controle mensal da quantidade de clientes registrados no sistema, **controle de entrada** e **saída** de veículos e controle de vagas.

## Tecnologias usadas 💻

- **JAVA**: linguagem de programação principal.


- **SPRING-BOOT**: Framework para desenvolvimento de aplicações Java.


- **POSTGRESQL**: Banco de dados relacional.


- **MAVEN**: Ferramenta principal utilizada para gerenciar essas aplicações em Java.


- **POSTMAN**: Ferramenta para testar e manipular os endpoints da API.


- **SWAGGER**: Documentação interativa dos endpoints.


- **INTELLIJ**: Ambiente de desenvolvimento.


<a href="https://www.oracle.com/java/technologies/downloads/#jdk22-windows"><img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="50" height="50" /></a>
<a href="https://spring.io/projects/spring-boot"><img src="https://github.com/user-attachments/assets/2b843542-1437-44f2-b2f2-f33bae03b342" width="50" height="50" /></a>
<a href="https://www.postgresql.org/download/"><img src="https://github.com/user-attachments/assets/92f810dd-7297-4a86-b25f-3905fd976892" width="45" height="45" /></a>
<a href="https://maven.apache.org/download.cgi"><img src="https://github.com/user-attachments/assets/20c281e0-7784-46c2-b9c3-827979aa3391" width="45" height="45" /></a>
<a href="https://www.postman.com/downloads/"><img src="https://github.com/user-attachments/assets/3ead52df-8744-47a6-b932-e50ef1f86400" width="75" height="50" /></a>
<a href="https://swagger.io/tools/swagger-ui/"><img src="https://github.com/user-attachments/assets/d1358131-cdcc-4812-a72d-27426ef48bc6" width="120" height="50" /></a>
<a href="https://www.jetbrains.com/idea/download/"><img src="https://github.com/user-attachments/assets/7949db83-7fcb-4611-ba0e-ec37a4af28e3" width="40" height="40" /></a>


##  Requisitos para o desenvolvimento 📑


 - **JDK 17**


 - **PostgreSQL 15**


 - **Maven**

## Configuração do banco de dados 🗄️


A configuração do banco de dados é feita no arquivo ***application.yaml***. Certifique-se de fornecer as credenciais corretas para o PostgreSQL.

## Funcionalidades principais ⚙️


1. **Cadastro de Clientes**: Registra informações dos clientes, permitindo o controle de quantos clientes estão registrados no mês.


2. **Registro de Entrada e Saída**: Captura a data e hora de entrada e saída dos veículos, calculando o tempo de permanência.


3. **Controlador de Vagas**: A Classe ***EstacionamentoVagas*** é usada para associar vagas às entradas, diminuindo a quantidade de vagas disponíveis no estacionamento.


## Executando o projeto ▶️


1. Clone este repositório ```git clone https://github.com/EzauLira/genrenciamento-para-sistema-estacionamento.git```


2. Instale as dependências localizada na ***pom.xml***.


3. Crie as tabelas SQL localizada na ***sql-sripts***.


4. Use o ***Postman*** ou o ***Swagger*** para acessar as rotas.


5. Configure o arquivo ***application.yaml*** com as informações do seu banco de dados, localizado na pasta ***resources***.


## Configurando o Banco de Dados ⚙️


1. Instale o ***[PostgreSQL clicando aqui](https://www.postgresql.org/download/)***. Caso não saiba como baixar e instalar ***[clique aqui](https://www.hashtagtreinamentos.com/instalacao-do-postgresql-sql?gad_source=1&gclid=CjwKCAjwk8e1BhALEiwAc8MHiEtLRun0xkGK3yckbc9VmjS1znQKeM5JlKHC6cCgtkjaCk1DXcMH4RoCopQQAvD_BwE).***


2. 2. Após ter clonado o projeto e configurado o ***application.yaml***, vá até a pasta ***sql-scripts***. Nessa pasta estão todos os códigos necessários para criar as tabelas e funções no banco de dados.
         <div align="">
         <img src="https://github.com/user-attachments/assets/4f7460fe-33e9-4fc2-b320-ad1daf8bf00a" height="300" />
         </div>
      
   
3.  Após abrir a pasta ***sql-scripts***, não há regras, pode começar a construir pelas funções ou tabelas.
         <div align="">
         <img src="https://github.com/user-attachments/assets/2c76b3f4-ede6-48a0-b08a-1d69b504aa9a" height="250" />
         </div>


4. Terminado de criar as tabelas e funções no ***PostgreSQL***, você terá algo assim:


| <img src="https://github.com/user-attachments/assets/ae7a10f2-6243-4c00-8f01-791beabc0286" height="89" />| <img src="https://github.com/user-attachments/assets/66db59b1-0c41-41d2-9506-c9dc4bc61947" height="89" /> |
|:---:|:---:|


5. Em seguida, você pode voltar à pasta ***sql-scripts***, pegar as ***queries*** que estão no arquivo ***queries_consultas.sql*** e fazer testes diretamente no PostgreSQL para verificar se está tudo funcionando como esperado.
 
| <img src="https://github.com/user-attachments/assets/3f08a843-89cf-44ef-a344-17befd12618f" width="270" height="470" /> | <img src="https://github.com/user-attachments/assets/e2d52915-18e3-4e6e-b4fc-d16286194465" width="700" height="300" /> |
|:---:|:---:|



6. Feito os testes, você terá um resultado assim, exemplo para cadastro de cliente:


<div align="">
<img src="https://github.com/user-attachments/assets/38111116-0a4d-42e5-88e7-e6f745418bc0" height="70" /> 
</div>


## Configurando o Postman ⚙️


1. Instale o ***[Postman clicando aqui](https://www.postman.com/downloads/)***.  Caso não saiba como baixar e instalar ***[clique aqui](https://www.alura.com.br/artigos/postman-como-instalar-dar-seus-primeiros-passos).***


2. Crie as Collections.



| <img src="https://github.com/user-attachments/assets/391d3270-2bc1-4064-bcec-8b26088e21aa" height="89" />| <img src="https://github.com/user-attachments/assets/aa3e1ebc-488e-4812-b234-0bf0ad2fedce" height="150" /> |
|:---:|:----------------------------------------------------------------------------------------------------------:|


3. Adicione as requests, de um nome a request, vá em body -> raw, em seguida preencha o corpo da requisição.



| <img src="https://github.com/user-attachments/assets/2ffe3807-7e03-4e2b-989d-2d8f0caf2f47" height="250" /> | <img src="https://github.com/user-attachments/assets/4a55b77d-17ff-45dd-a537-9b1fcc12744d" height="300" /> |
|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|


4. 4. Feito tudo corretamente, você terá algo assim:



<div align="">
<img src="https://github.com/user-attachments/assets/20c5309f-37fb-4137-80ec-3fc9d2f3d4b8" height="400" /> 
</div>


## Configurando o Swagger ⚙️

Para acessar a documentação interativa da API usando Swagger:

1. Acesse a URL `http://localhost:8080/swagger-ui.html` no seu navegador após iniciar a aplicação.


2. Utilize a interface do Swagger para explorar e testar os endpoints disponíveis.


3. Acesse a pasta: ***Documentação Swagger*** para pegar todo o scrip formatada para facilitar o manuseio da requisição

| <img src="https://github.com/user-attachments/assets/279989ba-a724-45f6-ad9f-458f04d52527" height="200" /> | <img src="https://github.com/user-attachments/assets/8c3f54f8-851d-4243-ae1b-e61f0a58aea1" height="300" /> |
|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|


## Rotas 🧭

-  **CLIENTE**
1. [POST] - /v1/clientes/cadastrar
1. [POST] - /v1/clientes/buscar
1. [GET] - /v1/clientes/todos
1. [DELETE] - /v1/clientes/deletar
1. [DELETE] - /v1/clientes/deletar-por-data

- **ESTACIONAMENTO**
1. [POST] - /v1/estacionamento/entrada
1. [POST] - /v1/estacionamento/saida
1. [POST] - /v1/estacionamento/buscar
1. [GET] - /v1/estacionamento/todos-registros
1. [GET] - /v1/estacionamento/veiculos-ativos
1. [GET] - /v1/estacionamento/veiculos-inativos
1. [GET] - /v1/estacionamento/vagas-disponiveis
1. [POST] - /v1/estacionamento/calcular-custo
1. [DELETE] - /v1/estacionamento/deletar-por-data


## Instrução final 📌
- Execute a classe principal ***EstacionamentoApplication.java*** localizada na pasta ***estacionamento***.


<div align="">
<img src="https://github.com/user-attachments/assets/66c8193b-5f51-4a24-ab71-79f12a9dd6e0" height="400" /> 
</div>

## Contribuições 🤝
- Sinta-se à vontade para contribuir com melhorias ou correções neste projeto. Abra uma issue ou envie um pull request!


## 🤝 Agradecimentos:
Obrigado [Foursys](https://br.linkedin.com/company/foursys) e aos professores: [Bruno Martin](https://www.linkedin.com/in/brunoermacora/) e [Denilson Elias](https://www.linkedin.com/in/denilsonbitit/), por me proporcionar essa experiência incrível de aprendizado e evolução 😎🤝
