#!/bin/bash

jardone=$(ls target | grep jar | wc -l)

if [[ ${jardone} == '0' ]]; then
    mvn clean package
fi

java -jar target/*.jar
