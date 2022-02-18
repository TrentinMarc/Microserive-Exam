#!/bin/sh

cd EurekaServer
mvn clean install -DskipTests

cd ../meteo
mvn clean install -DskipTests

cd ../showWeather
mvn clean install -DskipTests

cd ..

#docker-compose up --build --force-recreate