#!/bin/bash

docker image rm tntconcept
docker build -t tntconcept -f tomcat-docker/Dockerfile .
