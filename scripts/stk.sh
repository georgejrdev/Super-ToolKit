#!/bin/bash

if [ "$#" -eq 0 ]; then
    args=(-h)
else
    args=("$@")
fi

java -jar "$FILE_TKD" "${args[@]}"