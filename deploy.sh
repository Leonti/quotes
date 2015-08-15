#!/bin/bash
version=$1
docker stop quotes
docker rm quotes
docker run -p 8080:8080 -v /root/.quotes:/home/jetty/.quotes --name quotes -d leonti/quotes:$version
