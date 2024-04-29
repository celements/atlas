#!/bin/bash
set -euo pipefail # strict mode

[ ! -f .env ] && echo 'no .env file `cp sample.env .env`' && exit 1
[ ! -f mongo/.env ] && echo 'no mongo/.env file `cp mongo/sample.env mongo/.env`' && exit 1

compose='docker compose --env-file .env --env-file mongo/.env'
$compose pull
$compose up -d

set -a # enable auto export
source .env
set +a # disable auto export

./mvnw clean
./mvnw spring-boot:run