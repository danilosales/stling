# Stling

A arquitetura do projeto foi montado utilizando as seguintes tecnologias:

* Spring Boot - para construção do backend
* MySQL - Para persistência dos dados
* Angular 8 com bootstrap - Para criação da interface visual
* Swagger - Usado para docuemntar os serviços


## Instruções para executar o projeto


Após realizar o download do projeto, executar os seguintes comandos:

```sh
 mvn clean package
```
Este comando acima irá compilar os pacotes da aplicação java

Após o término da execução executar o comando:

```sh
docker-compose up
```

Caso deseje que se execute em background adicionar o parametro -d ao final:

```sh
docker-compose up -d
```

Caso seje necesário refazer o build utilizar o seguinte comando:

```sh
docker-compose up --build
```

Este comando irá subir todas as imagens dos projetos em containers dockers.


Caso deseje pode-se subir cada projeto separadamente também, no caso do projeto frontend será necessário acessar a pasta e eecutar o comando:

```sh
npm install
```

A Aplicação está configurada para que sempre que subir recriar o banco de dados para que se possa realizar os devidos testes, no momento exitem dois usuário cadastrados:

Usuário: pedro@stling.com.br
Senha: 123456

e

Usuário: larissa@stling.com.br
Senha: 123456

O usuário Pedro tem perfil de vendedor então somente ele conseguirá logar na aplicação.


Endereços disponíveis:

* [Documentação serviços para o frontend](http://localhost:8080/swagger-ui.html)

* [Aplicação frontend](http://localhost:4200)


#### Considerações Finais

* Foi utilizado o conceito JWT junto ao Spring security para acesso aos recursos de clientes.

* Em relação ao controle de acesso, o ideal é se utilizar um gateway para gerenciar a autenticação junto com um service discovery.

* Existem outras validações a serem feitas na aplicação.

