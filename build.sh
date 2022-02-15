#!/bin/bash

docker build . --no-cache -t sudoku-verifier
docker network create sudokuNetwork
docker run -net=sudokuNetwork -p 5000:5000 sudoku-verifier