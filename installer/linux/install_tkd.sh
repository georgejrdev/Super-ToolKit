#!/bin/bash

path_file="$HOME/ToolKit-dev"
file_tkd="$path_file/ToolKit.jar"

if [ ! -d "$path_file" ]; then
    mkdir -p "$path_file"
fi

mv "./ToolKit.jar" "$path_file/"
mv "./tkd.sh" "$path_file/"

case "$SHELL" in
    *bash)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.bashrc
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.bashrc
        echo "alias tkd='$path_file/tkd.sh'" >> ~/.bashrc
        source ~/.bashrc
        ;;
    *zsh)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.zshrc
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.zshrc
        echo "alias tkd='$path_file/tkd.sh'" >> ~/.zshrc
        source ~/.zshrc
        ;;
    *)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.profile
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.profile
        echo "alias tkd='$path_file/tkd.sh'" >> ~/.profile
        source ~/.profile
        ;;
esac

echo "Installed successfully"