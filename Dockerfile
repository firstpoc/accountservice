
FROM java


WORKDIR /opt

COPY /accountservice-1.0.0.jar /opt/accountservice-1.0.0.jar

EXPOSE 8080

ENTRYPOINT java -jar /opt/accountservice-1.0.0.jar
