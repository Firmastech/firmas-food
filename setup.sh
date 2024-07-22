#!/bin/bash

DATA_DIR="postgres_data"

# List of directories to create
DIRS="pg_snapshots pg_commit_ts pg_twophase pg_tblspc pg_notify pg_replslot"

# Loop through each directory and create it if it doesn't exist
for D in $DIRS; do
    if [ ! -d "$DATA_DIR/$D" ]; then
        echo "Creating directory $DATA_DIR/$D"
        mkdir -p "$DATA_DIR/$D"
    else
        echo "Directory $DATA_DIR/$D already exists"
    fi
done

echo "All directories have been processed."
read -p "Press Enter to exit."

