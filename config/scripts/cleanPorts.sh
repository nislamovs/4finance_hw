#!/usr/bin/env bash

#Cleaning ports of BE

sudo kill `sudo lsof -t -i:8080 -i:8081`