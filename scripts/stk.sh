#!/bin/bash

if [ "$#" -eq 0 ]; then
    args=(help)
else
    args=("$@")
fi

java -jar "$FILE_TKD" "${args[@]}"