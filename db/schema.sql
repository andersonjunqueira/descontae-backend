CREATE DATABASE descontae;
CREATE USER descontaeusr WITH PASSWORD 'descontaeusr';
GRANT CONNECT ON DATABASE descontae TO descontaeusr;
ALTER USER descontaeusr WITH SUPERUSER;
