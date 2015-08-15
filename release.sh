#!/bin/bash

version=$(date +"%y.%m.%d.%H.%M")

cd src/main/ui
bower install
cd ../../../
cp src/main/resources/log4j.xml /tmp/log4j.xml
cp docker/log4j.xml src/main/resources/log4j.xml
mvn clean package -DskipTests
cp /tmp/log4j.xml src/main/resources/log4j.xml
cp target/quotes-0.0.1-SNAPSHOT.war docker/quotes.war
sudo docker build -t leonti/quotes:$version docker
sudo docker push leonti/quotes
git tag -a v$version -m 'new version $version'

echo "Released version $version"
