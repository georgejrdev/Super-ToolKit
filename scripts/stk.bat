@echo off
setlocal enabledelayedexpansion

set PARAMS=
for %%a in (%*) do (
    set PARAMS=!PARAMS! %%a
)

java -jar "%FILE_STK%\SuperToolKit.jar" !PARAMS!