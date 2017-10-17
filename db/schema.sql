CREATE DATABASE descontae;
CREATE USER descontaeusr WITH PASSWORD 'descontaeusr';
GRANT CONNECT ON DATABASE descontae TO descontaeusr;
ALTER USER descontaeusr WITH SUPERUSER;

CREATE DATABASE keycloak;
CREATE USER keycloakusr WITH PASSWORD 'keycloakusr';
GRANT CONNECT ON DATABASE keycloak TO keycloakusr;
ALTER USER keycloakusr WITH SUPERUSER;

