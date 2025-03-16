# Desafio: Pontos de Interesse por GPS
Este projeto é uma solução para o desafio "Pontos de Interesse por GPS", proposto pela comunidade Back-End Brasil. O objetivo é implementar um serviço que auxilie na localização de pontos de interesse (POIs) utilizando coordenadas GPS.

## Tecnologias Utilizadas
-	Java 23
-	Spring Boot 3
-	Maven 
-	MapStruct
-	Database MySQL
-	Flyway (Migrations)


## Requisitos

- Cadastrar pontos de interesse, com 03 atributos: nome do POI, coordenada X (inteiro não negativo)
  e coordenada Y (inteiro não negativo).
- Os POIs devem ser armazenados em uma base de dados.
- Listar todos os POIs cadastrados.
- Listar os POIs por proximidade. Este serviço receberá uma coordenada X e uma coordenada Y, especificando um ponto de
  referência, bem como uma distância máxima (d-max) em metros. O serviço deverá retornar todos os POIs da base de dados
  que estejam a uma distância menor ou igual a d-max a partir do ponto de referência.


## Funcionalidades

-	Cadastro de novos pontos de interesse.
-	Listagem de todos os pontos de interesse cadastrados.
-	Consulta de pontos de interesse próximos a uma coordenada específica, considerando uma distância máxima.

## Estrutura do Projeto

- src/main/java/br/com/felmanc/pointsofinterest: Contém as classes principais da aplicação. 
  - controller: Classes responsáveis por expor os endpoints REST.
  - service: Implementação da lógica de negócio.
  - repository: Interfaces para acesso ao banco de dados.
  - model: Definição das entidades e DTOs.
  - mapper: Interfaces do MapStruct para conversão entre entidades e DTOs.


## Exemplo

Considere a seguinte base de dados de POIs:

- 'Lanchonete' (x=27, y=12)
- 'Posto' (x=31, y=18)
- 'Joalheria' (x=15, y=12)
- 'Floricultura' (x=19, y=21)
- 'Pub' (x=12, y=8)
- 'Supermercado' (x=23, y=6)
- 'Churrascaria' (x=28, y=2)

Dado o ponto de referência (x=20, y=10) indicado pelo receptor GPS, e uma distância máxima de 10 metros, o serviço deve
retornar os seguintes POIs:

- Lanchonete
- Joalheria
- Pub
- Supermercado

## Referências

- Comunidade Back-End Brasil - Pontos de Interesse por GPS
  https://github.com/backend-br/desafios/blob/master/points-of-interest/PROBLEM.md

- Buildrun - DESAFIO BACKEND com Java e Spring Boot | RECEPTOR GPS
  https://www.youtube.com/watch?v=Vc-V310gY5I
  
- Buildrun - desafio-backend-points-of-interest-solution
  https://github.com/buildrun-tech/buildrun-desafio-backend-points-of-interest-solution
