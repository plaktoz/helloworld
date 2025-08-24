FROM alpine/openssl:latest

RUN mkdir /certs
RUN chmod 755 /certs
WORKDIR /certs

RUN touch /certs/test.txt
RUN ls /certs/

RUN openssl genrsa -out server.key 2048
RUN openssl req -new -key server.key -out server.csr -subj "/C=US/ST=State/L=City/O=Org/CN=localhost"
RUN openssl x509 -req -days 365 -in server.csr -signkey server.key -out /certs/server.crt

#    docker run -it --rm -v "./infra/certs" alpine/openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:2048 -keyout /export/private.key -out /export/certificate.crt