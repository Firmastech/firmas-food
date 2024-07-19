docker exec -t postgres_firma pg_dumpall -c -U dbo > dump_$(date "+%Y.%m.%d-%H.%M.%S").sql

if [ $? -eq 0 ]; then
    echo "Backup was successful."
else
    echo "Backup failed."
fi