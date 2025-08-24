#!/bin/sh
# DEPRECATED and included within docker compose
apk add --no-cache openssl
mkdir -p /certs


## Generate Root CA
openssl genrsa -out /certs/ca.key 2048
openssl req -new -x509 -days 3650 -key /certs/ca.key -out /certs/ca.crt \
  -subj '/C=US/ST=State/L=City/O=Organization/CN=Root CA'

# Generate Server Certificate
openssl genrsa -out /certs/server.key 2048
openssl req -new -key /certs/server.key -out /certs/server.csr \
  -subj '/C=US/ST=State/L=City/O=Organization/CN=localhost'
openssl x509 -req -days 3650 -in /certs/server.csr -CA /certs/ca.crt \
  -CAkey /certs/ca.key -CAcreateserial -out /certs/server.crt

## Generate Client Certificate
#openssl genrsa -out /certs/client.key 2048
#openssl req -new -key /certs/client.key -out /certs/client.csr \
#  -subj '/C=US/ST=State/L=City/O=Organization/CN=client'
#openssl x509 -req -days 3650 -in /certs/client.csr -CA /certs/ca.crt \
#  -CAkey /certs/ca.key -CAcreateserial -out /certs/client.crt

# Clean up CSR files
rm /certs/*.csr /certs/*.srl

echo 'Certificates generated successfully!'
ls -la /certs/