#!/bin/bash

if [ "$EUID" -ne 0 ]; then
  echo "Please, run as root"
  exit
fi

path_file="$HOME/Super-ToolKit"
file_tkd="$path_file/SuperToolKit.jar"
path_save="$HOME/Super-ToolKit/save"

if [ ! -d "$path_file" ]; then
    mkdir -p "$path_file"
fi

if [ ! -d "$path_save" ]; then
    mkdir -p "$path_save"
fi


mv "./SuperToolKit.jar" "$path_file/"
mv "./skt.sh" "$path_file/"

case "$SHELL" in
    *bash)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.bashrc
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.bashrc
        echo "alias skt='$path_file/skt.sh'" >> ~/.bashrc
        source ~/.bashrc
        ;;
    *zsh)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.zshrc
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.zshrc
        echo "alias skt='$path_file/skt.sh'" >> ~/.zshrc
        source ~/.zshrc
        ;;
    *)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.profile
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.profile
        echo "alias skt='$path_file/skt.sh'" >> ~/.profile
        source ~/.profile
        ;;
esac

echo "Installed successfully"