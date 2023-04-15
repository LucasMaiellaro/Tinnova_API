# Tinnova API

Este repositório contém a API RESTful proposta no documento.

## Requisitos
- JDK 17
- MySQL
- Postman

## Instalação

Instalação das dependências do projeto

```bash
mvn clean install
```

## Run

Criar uma conexão no MySQL para rodar o banco de dados. Após isso os parâmetros **username** e **password** da sua conexão devem ser informados no arquivo application.properties
```bash
spring.datasource.username=root
spring.datasource.password=
```
Por padrão o **username** está configurado como **root**

Após isso, criar um schema chamado **"veiculos"**.

A criação da tabela e inserções de dados estão listadas abaixo e também salvas no diretório **/mysql** na raíz do projeto

#### Criação da tabela

```bash
create table veiculo
	(id bigint not null auto_increment,
    nome varchar(255),
    marca varchar(255),
    ano int,
    descricao text,
    vendido boolean,
    created datetime,
    updated datetime, primary key (id));
```

#### Inserções de dados

```bash
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (1, 'Spin', 'Chevrolet', 2015, 'Chevrolet Spin LT Marrom', 0, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (2, 'Onix', 'Chevrolet', 2017, 'Chevrolet Onix Preto', 1, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (3, 'Cruze', 'Chevrolet', 2020, 'Chevrolet Cruze Marrom', 0, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (4, 'Punto', 'FIAT', 2014, 'FIAT Punto Cinza', 1, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (5, 'Tucson', 'Hyundai', 2018, 'Hyundai Tucson Preto', 0, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (6, 'Renegade', 'Jeep', 2020, 'Jeep Renegade Marrom', 1, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (7, 'S10', 'Chevrolet', 2005, 'Chevrolet S10 Verde', 0, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (8, 'Sandero', 'Renault', 2014, 'Renault Sandero Vermelho', 1, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (9, 'Compass', 'Jeep', 2020, 'Jeep Compass Preto', 0, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
INSERT INTO veiculo (id, nome, marca, ano, descricao, vendido, created, updated) VALUES (10, 'Gol', 'Volkswagen ', 2019, 'Volkswagen Gol Vermelho', 1, '2023-04-14 18:30:00', '2023-04-14 18:30:00');
```

#### Executar o projeto
**Classe VeiculosApplication -> main()**

## Documentação da API

#### Url Base: http://localhost:8080/api/v1/veiculos

#### Lista todos os veículos

```http
  GET /
```

#### Retorna um veículos

```http
  GET /${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do veiculo que você quer |

#### Lista veículos por marca

```http
  GET /marca/${marca}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `marca`      | `String` | **Obrigatório**. A marca dos veiculos que você quer |

#### Lista veículos por status de vendido

```http
  GET /vendidos/{vendido}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `vendido`      | `boolean` | **Obrigatório**. Retorna veículos vendidos ou não-vendidos dependendo do valor passado |

#### Cadastra um veículo

```http
  POST /
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `veiculo`      | `Object` | **Obrigatório**. O objeto que você quer cadastrar |

#### Atualiza um veículo

```http
  PUT /${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do veículo que você quer atualizar |
| `veiculo`      | `Object` | **Obrigatório**. O novo veículo para cadastrar |

#### Deleta um veículo

```http
  DELETE /${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do veículo que você quer deletar |

#### Atualiza o atributo ano

```http
  PATCH /${id}/ano/${ano}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do veículo que você quer atualizar |
| `ano`      | `Integer` | **Obrigatório**. O novo atributo para atualizar |
