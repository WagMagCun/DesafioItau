# Microserviço de Transferência Bancária

## Descrição
Este serviço é responsável por processar transferências bancárias entre contas.

## Requisitos
- Java 17
- Spring Boot 3.2.9
- Docker
- Gradle 8.10.2

## Tecnologias utilizadas
- Resilience4j (Circuit Breaker e retries)
- Feign Client (para chamadas HTTP)
- Docker
- Swagger (para documentação)

## Preparação do ambiente

## Dependencia do projeto DesafioItau Wiremock
1. Clone o repositório do projeto para sua máquina local usando o Git:
   ```bash
   git clone https://github.com/mllcarvalho/DesafioItau.git
    cd DesafioItau

## Clonagem do Projeto de transferências Bancárias
2. Clone o repositório do projeto para sua máquina local usando o Git:
   ```bash
   https://github.com/WagMagCun/DesafioItau.git
    cd DesafioItau-main

## Docker configuracao de Rede
3. Para que os dois projetos compartilhem a mesma rede foi necessário criação de uma rede.
   ```bash
   docker network create api-network

4. docker-compose.yml do projeto Wiremock deverá ter referencia para a rede
    ```bash
                networks:
                - api-network
    networks:
       api-network:
          external: true

5. Construção dos Containers com Docker Compose
   Na raiz dos projetos Wiremock e Tranferencia acessar a pasta onde o arquivo docker-compose.yml está localizado, execute o comando abaixo para construir e iniciar todo o container  definido no Docker Compose:
    ```bash
   cd docker
   docker-compose up --build -d

## Documentação Swagger
1.  A documentação swagger pode ser acessada a partir da URL abaixo:
    http://localhost:8091/swagger-ui/index.html

## Estrutura do Projeto
A estrutura dos diretórios e pacotes é organizada de acordo com padrões de arquitetura como Arquitetura Hexagonal:

* `application/`: Contém controladores para gerenciar requisições, handler para tratamento de exceções, e entidades para requisições e respostas.
* `domain/`: Contém as entidades de domínio e regras de negócio, incluindo contratos de serviços e gateways, definições de exceções personalizadas e implementações de serviços que seguem esses contratos.
* `resource/`: Inclui adaptadores para integração com serviços externos, clientes para comunicação com APIs, e objetos de transferência de dados (DTOs) organizados em request e response, utilizando records para facilitar a serialização.

## Observabilidade
Micrometer e Prometheus: Como as métricas são expostas.
http://localhost:8091/actuator/prometheus

## Resiliência
### Retry
O sistema implementa um mecanismo de retry para chamadas à API BACEN, visando aumentar a resiliência e garantir que as operações de transferência sejam concluídas mesmo em caso de falhas temporárias. As regras de retry são configuradas da seguinte forma:
Se todas as tentativas falharem, o sistema acionará o método de fallback, garantindo que uma resposta adequada seja retornada ao usuário.
Número Máximo de Tentativas: 5
Tempo de Espera Entre Tentativas: 2 segundos

### Circuit Breaker
O sistema implementa um Circuit Breaker para chamadas à API BACEN, com o objetivo de melhorar a resiliência e prevenir falhas em cascata.
As regras serão baseadas em variáveis de ambiente, ou seja, parametros iniciais que poderão ser reajustados de acordo com o desempenho observado e as necessidades do sistema sem a necessidade de recompilar o código,
favorecendo a flexibilidade na adaptação do comportamento do sistema em resposta a mudanças nas cargas de trabalho ou requisitos de desempenho sendo não nescessário a recom
Configurações:
* Limite de falhas: se 60% das chamadas falharem, o Circuit Breaker se abre.
* Duração aberta: o Circuit Breaker permanece aberto por 30 segundos antes de tentar fechar novamente.

##  Endpoints da API
URL: http://localhost:8091/transferencia
Método: HTTP (POST)

### Exemplo de requisição
```bash
curl --location 'http://localhost:8091/transferencia' \
--header 'Content-Type: application/json' \
--data '{
    "idCliente": "2ceb26e9-7b5c-417e-bf75-ffaa66e3a76f", 
    "valor": 100,
    "conta": {
        "idOrigem": "d0d32142-74b7-4aca-9c68-838aeacef96b", 
        "idDestino": "41313d7b-bd75-4c75-9dea-1f4be434007f" 
    }
  }'
  ```
Códigos de status retornados (ex.: 200, 400, 500)

### Exemplo de respostas
HTTP 200 OK
```bash
{
    "idTransferencia": "5af058d4-5afe-47e4-afb1-c4ad543c4d85"
}
```
HTTP 400 Bad Request
```bash
{
"message": "Validação para transfêrencia: Campo idCliente é obrigatório"
}
```
```bash
{
    "message": "Validação para transfêrencia: Campo valor é obrigatório    "
}
```
```bash
{
    "message": "Validação para transfêrencia: Campo Conta de origem é obrigatório"
}
```
```bash
{
    "message": "Validação para transfêrencia: Campo Conta de destino é obrigatório"
}
```
```bash
{
    "message": "Saldo insuficiente: Saldo insuficiente para realizar a transferência."
}
```
```bash
{
    "message": "Cliente não localizado: [idClient]"
}
```
```bash
{
    "message": "Conta não está ativa: [idConta]"
}
```
```bash
{
"message": "Valor de transferência excede o limite diário.""
}
```
```bash
{
"message": "Valor de transferência excede o limite diário.""
}
```
```bash

HTTP 500 Bad Request
{
"message": "Falha na tentativa de efetuar transferencia no acesso conta e ou cliente: [descricao erro] "
}
```




