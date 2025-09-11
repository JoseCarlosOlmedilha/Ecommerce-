🛍️ E-Commerce de Roupas com Spring Boot e JWT

Este é um projeto de API RESTful para um e-commerce de roupas, desenvolvido com Java e Spring Boot, utilizando JWT (JSON Web Tokens) para autenticação. A aplicação permite cadastro e login de usuários, gerenciamento de produtos (roupas), controle de pedidos e muito mais.

🚀 Tecnologias Utilizadas

Java 17+

Spring Boot 3+

Spring Security (Autenticação e Autorização com JWT)

Spring Data JPA

Hibernate

Banco de Dados H2 / PostgreSQL / MySQL (configurável)

Maven

Lombok

Swagger/OpenAPI (documentação da API)

📦 Funcionalidades Principais
👤 Autenticação & Autorização

Cadastro de novos usuários

Login com geração de token JWT

Controle de acesso baseado em roles (ADMIN, USER)

👕 Gerenciamento de Produtos

Cadastro, edição, remoção e listagem de roupas

Filtros por categoria, tamanho, preço, etc.

🛒 Carrinho & Pedidos

Adição e remoção de produtos no carrinho

Criação e listagem de pedidos por usuário

Atualização de status de pedidos (ex: PENDENTE, ENVIADO, ENTREGUE)

🗂️ Estrutura do Projeto
ecommerce/
├── src/
│   ├── main/
│   │   ├── java/com/seuusuario/ecommerce/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── security/
│   │   │   └── config/
│   └── resources/
│       ├── application.properties
│       └── schema.sql

🔐 Autenticação JWT

A autenticação é baseada em JWT, onde:

O usuário faz login com email e senha

Recebe um token JWT válido

Esse token é usado para acessar endpoints protegidos

Exemplo de header:

Authorization: Bearer seu_token_jwt

📄 Documentação da API

A API é documentada com Swagger. Após iniciar o projeto, acesse:

http://localhost:8080/swagger-ui/index.html

⚙️ Como Rodar o Projeto Localmente

Clone o repositório:

git clone https://github.com/seuusuario/ecommerce-roupas.git
cd ecommerce-roupas


Configure o banco de dados em application.properties (ou use o H2 em memória)

Compile o projeto:

./mvnw clean install


Execute a aplicação:

./mvnw spring-boot:run


Acesse:

Swagger: http://localhost:8080/swagger-ui/index.html

H2 Console (se estiver usando H2): http://localhost:8080/h2-console

🧪 Testes

O projeto pode incluir testes unitários e de integração utilizando:

JUnit 5

Mockito

Execute os testes com:

./mvnw test

📌 Possíveis Melhorias Futuras

Integração com sistemas de pagamento (ex: Stripe, PayPal)

Upload de imagens para produtos (Amazon S3, Cloudinary)

Sistema de avaliações e comentários

Painel administrativo com dashboards

🤝 Contribuição

Contribuições são bem-vindas! Sinta-se livre para abrir uma issue ou pull request.

📄 Licença

Este projeto está sob a licença MIT. Consulte o arquivo LICENSE para mais informações.

Se quiser, posso também gerar a versão em inglês ou adaptar para um formato mais técnico/comercial. Deseja isso?
