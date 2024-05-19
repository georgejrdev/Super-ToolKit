@echo off

setx FILE_TKD "C:\Program Files\toolkit-dev"

if not exist "%FILE_TKD%" (
    mkdir "%FILE_TKD%"
)

move ".\tkd.bat" "%FILE_TKD%"
move ".\ToolKit.jar" "%FILE_TKD%"

setx /M PATH "%PATH%;%FILE_TKD%"

echo "Installed successfully, restart the terminal"