#!/bin/bash

docker compose down
docker image rm tntconcept
docker build -t tntconcept -f tomcat-docker/Dockerfile .
docker compose up
