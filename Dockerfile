
FROM java


WORKDIR /usr/local

COPY /accountservice-1.0.0.jar /usr/local/accountservice-1.0.0.jar

EXPOSE 8080

ENTRYPOINT java -cp /usr/local/accountservice-1.0.0.jar:/usr/local/config org.msf4jpoc.service.Application
