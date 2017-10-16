# CONFIGURANDO O AMBIENTE DO SISTEMA

## BANCO DE DADOS

### PRE-REQUISITOS

* Docker (em VM ou local) ou 
* Instância do PostgresSQL 9.4 ou superior. 

### CONFIGURAÇÃO DO DOCKER

Após a instalação do docker, execute a seguinte linha de comando para iniciar uma instância do docker:

```
sudo docker run --name postgres -e POSTGRES_PASSWORD=mysecretpassword -v=/var/lib/postgresql/data:/opt/docker/psql-data -p 5432:5432 -d postgres:9.4
```

Essa instância é acessível pelo endereço "localhost:5432", usuário: "postgres" e senha "mysecretpassword".

### PREPARAÇÃO DO BANCO

Executar o script **db/schema.sql** para criação do schema **descontae** e usuário **descontaeusr**, senha **descontaeusr**.

Conectar no banco com o usuário **descontaeusr** e executar o script **db/ddl.sql**.

Executar o script **db/carga.sql** para carregar as tabelas com os dados básicos.  

## FRONTEND

### PRE-REQUISITOS

* NodeJS 6.11 ou superior.

### EXECUTANDO

Para preparar o ambiente, execute (apenas 1 vez):

```
npm install
```

Para executar o frontend, execute:

```
npm start
```

O servidor será iniciado no endereço **http://localhost:3000**.


### BUILD

Execute:

```
npm run build
```

O projeto será construído na pasta **build**.

### DEPLOY

Como o servidor é externo, vamos empacotar o projeto para transferência:

```
cd build
zip descontae-admin.zip -r .
scp /c/Desenv/projetos/descontae-admin/build/descontae-admin.zip root@213.136.69.6:/opt/bin/descontae-admin.zip
SERÁ SOLICITADA A SENHA DO ROOT
```

Em seguida, acesse o servidor e execute os comandos:

```
ssh root@213.136.69.6
SERÁ SOLICITADA A SENHA DO ROOT

rm -R /opt/www/html/*
cp /opt/bin/descontae-admin.zip /opt/www/html
cd /opt/www/html
unzip descontae-admin.zip
```

O sistema exige um arquivo de configuração chamado keycloak.json:

```
mkdir /opt/www/html/descontae-admin
cd /opt/www/html/descontae-admin
sudo vi keycloak.json
PODE SER SOLICITADA A SENHA DO ROOT
```

Cole o seguinte:

```
{
"realm": "descontaeRealm-dev",
"auth-server-url": "http://213.136.69.6:9000/auth",
"ssl-required": "none",
"resource": "descontae-admin",
"public-client": true,
"use-resource-role-mappings": true
}
```

## BACKEND
scp /c/Desenv/projetos/descontae-backend/target/descontae-backend.jar root@213.136.69.6:/opt/bin/descontae-backend.jar
digite: ZA5dc5pBJba2GLPk



### PRE-REQUISITOS

* JAVA JDK 8 ou superior.
* APACHE MAVEN 3.5 ou superior.

### BUILD

Execute:

```
cd /c/Desenv/projetos/springboot-utils
mvn clean install
cd /c/Desenv/projetos/descontae-backend
mvn clean package -DskipTests
```

Hora de enviar o projeto para o servidor:

```
scp /c/Desenv/projetos/descontae-backend/target/descontae-backend.jar root@213.136.69.6:/opt/bin/descontae-backend.jar
SERÁ SOLICITADA A SENHA DO ROOT
```

### DEPLOY

Acesse o servidor e execute os comandos:

```
ssh root@213.136.69.6
SERÁ SOLICITADA A SENHA DO ROOT

/etc/init.d/descontae stop
/etc/init.d/descontae start
```

### ACESSO AO LOG

Acesse o servidor e execute os comandos:

```
ssh root@213.136.69.6
SERÁ SOLICITADA A SENHA DO ROOT
tail -f /var/log/descontae/descontae-backend.log
```