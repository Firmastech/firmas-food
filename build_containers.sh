#!/bin/bash

# Remove a stack do Docker
echo "Removendo a stack 'firmas-food'..."
docker container rm postgres_firma firmas-food_initdb_1 firmas -f

# Espera para garantir que a stack foi completamente removida
echo "Esperando 5 segundos para garantir que a stack foi removida..."
sleep 5

# Remove os volumes
echo "Removendo os volumes 'firmas-food_postgres_data' e 'firmas-food_keycloak_data'..."
docker volume rm firmas-food_postgres_data firmas-food_keycloak_data

# Executa o docker-compose para iniciar os serviços em modo destacado
echo "Iniciando os serviços com docker-compose..."
docker-compose -f compose.yaml up -d

# Função para verificar se o PostgreSQL está pronto
wait_for_postgres() {
    until docker exec -t postgres_firma pg_isready -U dbo; do
        echo "Aguardando o PostgreSQL estar pronto..."
        sleep 1
    done
}

# Espera até que o PostgreSQL esteja pronto
echo "Verificando se o PostgreSQL está pronto..."
wait_for_postgres

# Executa o comando pg_dumpall quando o PostgreSQL estiver pronto
echo "Restaurando o dump.sql para o PostgreSQL..."
cat dump.sql | docker exec -i postgres_firma psql -U postgres

echo "Processo concluído."
