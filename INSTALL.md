# Install in just 3 steps 

## Download the files

Download the two files present in the repository's "installer" directory

<br>

Move them to a folder of your choice
  
<br>

## Add the folder to PATH

You can do this by opening the configuration file in your terminal and putting at the end:

    export PATH="/path/to/your/folder:$PATH"
    alias tdk="/path/to/your/folder/tdk.sh"
  
After that, run in your terminal, example:

    source ~/.zshrc

<br>

## Giving execution permission

Finally, use the following command in your terminal to give permission:


    chmod +x /path/to/your/folder/tdk.sh

## Running

To execute, use the command with the following syntax:

    tdk -t "text" pt

-t : Call the translation function

"text": Is the text to be translated

pt: Is the target language of the translation