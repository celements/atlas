#!/bin/bash
set -euo pipefail # strict mode

docker compose pull
docker compose up -d

set -a # automatically export variables
source .env # load environment variables
set +a # stop automatically exporting
./mvnw clean
./mvnw spring-boot:run
