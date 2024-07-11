@echo off

set DATA_DIR=postgres_data+

REM List of directories to create
set DIRS=pg_snapshots pg_commit_ts pg_twophase pg_tblspc pg_notify pg_replslot

REM Loop through each directory and create it if it doesn't exist
for %%D in (%DIRS%) do (
    if not exist %DATA_DIR%\%%D (
        echo Creating directory %DATA_DIR%\%%D
        mkdir %DATA_DIR%\%%D
    ) else (
        echo Directory %DATA_DIR%\%%D already exists
    )
)

echo All directories have been processed.
pause
