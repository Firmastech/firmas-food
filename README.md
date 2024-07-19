## Roles
- RestauranteAdmin
- RestauranteFuncionario
- RestauranteUsuario

## Use Case
- Configuracao de security (Daniel)
- Fluxo de cardapio - Adicionar e atualizar cardapio (Martins)
- Fluxo do pedido - Usuario do restaurante X criar um pedido com N quantidade de M itens (Henrique)
- Adicionar e atualizar dados do restaurante (Renan)
- Sistema de desconto de pedidos


# Projeto Firmas Food

Este é um guia para configurar e executar o projeto XYZ usando Docker Compose.

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

- Docker: [Instalação do Docker](https://docs.docker.com/get-docker/)
- Docker Compose: [Instalação do Docker Compose](https://docs.docker.com/compose/install/)

## Configuração

1. **Clone o repositório**

   ```bash
   git clone https://github.com/Firmastech/firmas-food.git
   cd firmas-food
   ```
2. **Execute o projeto**
```
 docker-compose up    
```


## Persistindo as mudancas de banco
 - Salve suas mudancas no arquivo `resources/sql/firmas_food_db.sql`
 - Execute o arquivo `backup_db.sh` e commite com os novos arquivos gerados.
