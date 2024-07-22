#!/bin/bash

# Remove a stack do Docker
echo "Removendo a stack 'firmas-food'..."
docker container rm firmas-food-keycloak-1 -f

# Espera para garantir que a stack foi completamente removida
echo "Esperando 5 segundos para garantir que a stack foi removida..."
sleep 5

# Remove os volumes
echo "Removendo os volumes 'firmas-food_postgres_data' e 'firmas-food_keycloak_data'..."
docker volume rm firmas-food_keycloak_data

# Executa o docker-compose para iniciar os serviços em modo destacado
echo "Iniciando os serviços com docker-compose..."
docker-compose -f compose-dev.yaml up -d

echo "Processo concluído."
