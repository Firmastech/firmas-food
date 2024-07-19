#!/bin/bash
docker exec -t postgres_firma pg_dumpall -c -U dbo > dump_$(date "+%Y.%m.%d-%H.%M.%S").sql
