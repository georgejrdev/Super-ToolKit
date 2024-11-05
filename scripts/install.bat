@echo off

setx FILE_STK "C:\Program Files\Super-ToolKit"

set CURRENT_DIR=%~dp0
set CURRENT_DIR=%CURRENT_DIR:~0,-1%

if not exist "%FILE_STK%" (
    mkdir "%FILE_STK%"
)

move "%CURRENT_DIR%\stk.bat" "%FILE_STK%"
move "%CURRENT_DIR%\SuperToolKit.jar" "%FILE_STK%"

setx /M PATH "%PATH%;%FILE_STK%"

echo "Installed successfully, restart the terminal"
pause