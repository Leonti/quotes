#!/bin/bash

version=$(date +"%y.%m.%d.%H.%M")
mvn clean package
cp target/quotes-0.0.1-SNAPSHOT.war docker/quotes.war
sudo docker build -t leonti/quotes:$version docker
sudo docker push leonti/quotes
git tag -a v$version -m 'new version $version'
