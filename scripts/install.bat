@echo off

set "FILE_STK=C:\Program Files\Super-ToolKit"

set "CURRENT_DIR=%~dp0"
set "CURRENT_DIR=%CURRENT_DIR:~0,-1%"

if not exist "%FILE_STK%" (
    echo Creating directory "%FILE_STK%"
    mkdir "%FILE_STK%"
    if errorlevel 1 (
        echo Failed to create directory "%FILE_STK%". Check permissions.
        pause
        exit /b
    )
)

if not exist "%CURRENT_DIR%\stk.bat" (
    echo "stk.bat not found in %CURRENT_DIR%"
    pause
    exit /b
)

if not exist "%CURRENT_DIR%\SuperToolKit.jar" (
    echo "SuperToolKit.jar not found in %CURRENT_DIR%"
    pause
    exit /b
)

echo Moving files to "%FILE_STK%"
move "%CURRENT_DIR%\stk.bat" "%FILE_STK%" || echo Failed to move stk.bat
move "%CURRENT_DIR%\SuperToolKit.jar" "%FILE_STK%" || echo Failed to move SuperToolKit.jar

echo Updating PATH environment variable
setx /M PATH "%PATH%;%FILE_STK%"
if errorlevel 1 (
    echo Failed to update PATH. Check permissions.
    pause
    exit /b
)

setx FILE_STK "C:\Program Files\Super-ToolKit"

echo "Installed successfully, restart the terminal"
pause