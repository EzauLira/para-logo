# SGE - Sistema para gerenciamento de estacionamento 🅿


Gerenciador de Estacionamento: Projeto Acadêmico Desenvolvido em **Java** com **Spring Boot**, **PostgreSQL**, **Postman** e **Swagger** para a Disciplina de Desenvolvimento de Sistemas da *[Foursys](https://br.linkedin.com/company/foursys)*.

## Créditos


✍ Projeto proposto pela ***Fourcamp***. Sendo disciplinado e orientado pelos mestres: *[Bruno Martin](https://www.linkedin.com/in/brunoermacora/)*
   e  *[Denilson Elias](https://www.linkedin.com/in/denilsonbitit/)*

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


<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original-wordmark.svg" width="50" height="50" />
<img src="https://github.com/user-attachments/assets/f83409ca-b336-453a-a45c-84c743fdd781" width="50" height="50" />
<img src="https://github.com/user-attachments/assets/0304ff16-0be3-426a-9ae2-57846e217db8" width="45" height="45" />
<img src="https://github.com/user-attachments/assets/01708cb5-c31d-409f-b73c-9de7d941c72c" width="45" height="45" />
<img src="https://github.com/user-attachments/assets/205b41ac-971f-4871-92dc-468d2676a846" width="75" height="50" />
<img src="https://github.com/user-attachments/assets/eb215758-11c4-49ad-8ab0-73624c23f44f" width="120" height="50" />
<img src="https://github.com/user-attachments/assets/de8d1369-0da3-49a8-b892-5c11c0509987" width="40" height="40" />


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


4. Use o ***Postman*** ou o ***Swaager*** para acessar as rotas.


5. Configure o arquivo ***application.yaml*** com as informações do seu banco de dados, localizado na pasta ***resources***.


## Configurando o Banco de Dados ⚙️


1. Instale o ***[PostgreSQL clicando aqui](https://www.postgresql.org/download/)***.


2. Após ter clonado o projeto, configurado o ***application.yaml***, vá até a pasta ***sql-scripts***, Nessa pasta contém todos os códigos necessários para poder criar as tabelas e funções no banco de dados.
         <div align="">
         <img src="https://github.com/user-attachments/assets/bcee4368-1bb6-490e-a1b3-f8e6b824b2d3" height="300" />
         </div>
      
   
3. Após abrir a pasta ***sql-scrits***, não há regras, pode começar a construir pelas funções ou tabelas.
         <div align="">
         <img src="https://github.com/user-attachments/assets/2b651df7-d545-4952-946d-30bcd596dd3d" height="89" />
         </div>


4. Terminado de criar as tabelas e funções no ***postgreSQL*** você terá algo assim


| <img src="https://github.com/user-attachments/assets/5c648281-fb9b-4449-a030-8069fd7ce6ca" height="89" />| <img src="https://github.com/user-attachments/assets/d9ed1b75-1bc5-432b-818d-0c94b5c2cd56" height="89" /> |
|:---:|:---:|


5. Em seguida, você pode voltar à pasta ***sql-scrips*** pegar as ***queries*** que estão no arquivo ***queries_consultas.sql*** e fazer testes diretamente no PostgreSQL para verificar se está tudo funcionando como esperado.
 
| <img src="https://github.com/user-attachments/assets/f0b6afb3-ae11-4a60-9ea6-594e2a90bba0" width="270" height="470" /> | <img src="https://github.com/user-attachments/assets/0063018c-c32b-464e-8fa9-cffd76f85c5d" width="700" height="300" /> |
|:---:|:---:|



6. Feito os testes você terá um resultado assim, exemplo para cadastro de cliente:


<div align="">
<img src="https://github.com/user-attachments/assets/38111116-0a4d-42e5-88e7-e6f745418bc0" height="70" /> 
</div>


## Configurando o Postman ⚙️


1. Instale o ***[Postman clicando aqui](https://www.postman.com/downloads/)***.


2. Crie as Collections.



| <img src="https://github.com/user-attachments/assets/2c598c4b-af90-459f-b5d5-31d2f2b4ddb4" height="89" />| <img src="https://github.com/user-attachments/assets/0b8d0826-73bf-4e63-887c-1da4cc323049" height="100" /> |
|:---:|:----------------------------------------------------------------------------------------------------------:|


3. Adicione as requests.



| <img src="https://github.com/user-attachments/assets/29cfe36f-479d-4271-96af-ce343bc594b7" height="250" /> | <img src="https://github.com/user-attachments/assets/296e9fe7-03d7-4882-bf4c-0160ee885324" height="300" /> |
|:----------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------:|


4. Feito tudo corretamente vc terá algo assim:


<div align="">
<img src="https://github.com/user-attachments/assets/90993b5a-969a-4997-b56f-abfaf8cbb72b" height="400" /> 
</div>


## Rotas 🧭

  - CLIENTE
1. [POST] - /v1/clientes/cadastrar
1. [POST] - /v1/clientes/buscar
1. [GET] - /v1/clientes/todos
1. [DELETE] - /v1/clientes/deletar
1. [DELETE] - /v1/clientes/deletar-por-data

  - ESTACIONAMENTO
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
<img src="https://github.com/user-attachments/assets/668e60f7-e4fc-4867-984f-bfa38ba86fba" height="400" /> 
</div>

## Contribuições 🤝
Sinta-se à vontade para contribuir com melhorias ou correções neste projeto. Abra uma issue ou envie um pull request!


## 🤝 Agradecimentos:
Obrigado [Foursys](https://br.linkedin.com/company/foursys). e professores: [Bruno Martin](https://www.linkedin.com/in/brunoermacora/) e [Denilson Elias](https://www.linkedin.com/in/denilsonbitit/), por me proporcionar essa experiência incrível de aprendizado e evolução 😎🤝