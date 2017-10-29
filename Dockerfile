FROM ubuntu
MAINTAINER Anderson Junqueira (anderson.junqueira@gmail.com)

ARG BINARY_FILE
ARG ENVIRONMENT

RUN apt-get update 
RUN apt-get install -y openjdk-8-jdk curl

# CRIAR VOLUME PARA LOG 
RUN mkdir /var/log/java
VOLUME ["/var/log/java"]

# PASSAR O PARAMETRO DO ARQUIVO BIN DO JAVA
RUN mkdir /opt/bin
COPY ${BINARY_FILE} /opt/bin/java.jar

# EXPOR A PORTA
EXPOSE 8000

# INICIAR SERVICO 
ENTRYPOINT java -jar -Dspring.profiles.active=${ENVIRONMENT} /opt/bin/java.jar >> /dev/null