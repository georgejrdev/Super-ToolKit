#!/bin/bash

path_file="$HOME/Super-ToolKit"
file_tkd="$path_file/SuperToolKit.jar"
path_save="$HOME/Super-ToolKit/save"

if [ ! -d "$path_file" ]; then
    mkdir -p "$path_file"
    echo "Created directory: $path_file"
else
    echo "Directory already exists: $path_file"
fi

if [ ! -d "$path_save" ]; then
    mkdir -p "$path_save"
    echo "Created directory: $path_save"
else
    echo "Directory already exists: $path_save"
fi

if [ -f "./SuperToolKit.jar" ]; then
    mv "./SuperToolKit.jar" "$path_file/"
    echo "Moved SuperToolKit.jar to $path_file/"
else
    echo "File SuperToolKit.jar not found."
fi

if [ -f "./stk.sh" ]; then
    mv "./stk.sh" "$path_file/"
    echo "Moved stk.sh to $path_file/"
else
    echo "File stk.sh not found."
fi

case "$SHELL" in
    *bash)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.bashrc
            echo "Updated PATH in .bashrc"
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.bashrc
        echo "alias stk='$path_file/stk.sh'" >> ~/.bashrc
        source ~/.bashrc
        echo "Bash configuration updated."
        ;;
    *zsh)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.zshrc
            echo "Updated PATH in .zshrc"
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.zshrc
        echo "alias stk='$path_file/stk.sh'" >> ~/.zshrc
        source ~/.zshrc
        echo "Zsh configuration updated."
        ;;
    *)
        if [[ ":$PATH:" != *":$path_file:"* ]]; then
            echo 'export PATH="$PATH:'"$path_file"'"' >> ~/.profile
            echo "Updated PATH in .profile"
        fi
        echo "export FILE_TKD=\"$file_tkd\"" >> ~/.profile
        echo "alias stk='$path_file/stk.sh'" >> ~/.profile
        source ~/.profile
        echo "Profile configuration updated."
        ;;
esac

echo "Installed successfully"