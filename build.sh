#!/bin/sh
cd /c/Desenv/projetos/springboot-utils
mvn clean install
cd /c/Desenv/projetos/descontae-backend
mvn clean package -DskipTests
scp /c/Desenv/projetos/descontae-backend/target/descontae-backend.jar root@213.136.69.6:/opt/bin/descontae-backend.jar
