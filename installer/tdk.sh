#!/bin/bash

args=()

if [ -n "$1" ]; then
    args+=("$1")
fi

if [ -n "$2" ]; then
    args+=("$2")
fi

if [ -n "$3" ]; then
    args+=("$3")
fi

if [ "${#args[@]}" -gt 0 ]; then
    java -classpath "$FILE_TDK" ToolKit "${args[@]}"
fi
