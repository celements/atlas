#!/bin/bash
set -euo pipefail # strict mode

! command -v docker > /dev/null && echo 'docker command missing' && exit 1
[ ! -f .env ] && echo 'no .env file `cp sample.env .env`' && exit 1
[ ! -f mongo/.env ] && echo 'no mongo/.env file `cp mongo/sample.env mongo/.env`' && exit 1
chmod o-rwx .env mongo/.env

compose='docker compose --env-file .env --env-file mongo/.env'
$compose up -d

set -a # enable auto export
source .env
set +a # disable auto export

./mvnw clean
./mvnw spring-boot:run
