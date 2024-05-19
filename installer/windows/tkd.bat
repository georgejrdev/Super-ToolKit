@echo off
setlocal enabledelayedexpansion

if "%~1"=="" (
    set args=-h
) else (
    set args=
    :loop
    if "%~1"=="" goto after_loop
    set args=!args! %1
    shift
    goto loop
    :after_loop
)

java -jar "%FILE_TKD%\ToolKit.jar" !args!