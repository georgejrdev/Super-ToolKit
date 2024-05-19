# Install in just 3 steps 

## Download the files

LINK

<br>

Extract the files and move them to a folder of your choice
  
<br> 

### Add the folder to PATH

You can do this by opening the configuration file in your terminal and putting at the end:

    export PATH="/path/to/your/folder:$PATH"
    export FILE_TKD="/path/to/your/folder/ToolKit.jar"
    alias tkd="/path/to/your/folder/tkd.sh"
  
After that, run in your terminal, example:

    source ~/.zshrc

<br>

## Giving execution permission

Finally, use the following command in your terminal to give permission:


    chmod +x /path/to/your/folder/tkd.sh
    chmod +x /path/to/your/folder/ToolKit.jar

## Running

To execute, use the command with the following syntax:

    tkd -t "text" pt

-t : Call the translation function

"text": Is the text to be translated

pt: Is the target language of the translation
