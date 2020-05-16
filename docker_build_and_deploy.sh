#!/bin/bash

docker build -t balancing-act-api -f Dockerfile target
docker tag balancing-act-api server.lan:5000/balancing-act-api
docker push server.lan:5000/balancing-act-api

