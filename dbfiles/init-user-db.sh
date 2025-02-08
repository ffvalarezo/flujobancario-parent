 #!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    CREATE DATABASE cliente;
    GRANT ALL PRIVILEGES ON DATABASE cliente TO "$POSTGRES_USER";
    
    CREATE DATABASE transaccion;
    GRANT ALL PRIVILEGES ON DATABASE transaccion TO "$POSTGRES_USER";
EOSQL


