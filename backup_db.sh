#!/bin/bash

# Create the backups directory if it doesn't exist
mkdir -p backups

# Check if dump.sql exists
if [ -f "dump.sql" ]; then
    # Rename the existing dump.sql file with a timestamp
    mv "dump.sql" "backups/dump_bkp_$(date "+%Y.%m.%d-%H.%M.%S").sql"
fi

# Execute the pg_dumpall command
docker exec -t postgres_firma pg_dumpall -c -U dbo > "dump.sql"


if [ $? -eq 0 ]; then
    echo "Backup was successful."
else
    echo "Backup failed."
fi