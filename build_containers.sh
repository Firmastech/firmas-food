#!/bin/bash

# Run docker-compose to start the services in detached mode
docker-compose -f compose.yaml up -d

# Function to check if PostgreSQL is ready
wait_for_postgres() {
    until docker exec -t postgres_firma pg_isready -U dbo; do
        echo "Waiting for PostgreSQL to be ready..."
        sleep 1
    done
}

# Wait for PostgreSQL to be ready
wait_for_postgres

# Run the pg_dumpall command once PostgreSQL is ready
cat dump.sql | docker exec -i postgres_firma psql -U postgres


