# Manual installation 
***~ for users without administrator permission***

## To begin with

- **Download the files present on the home page. [Click here to go.](../README.md)**

- **Delete install.sh (or install.bat on Windows), you won't need it.**

- **Move SuperToolKit.jar and stk.sh (or stk.bat on Windows) to a folder**

## Linux

**On Linux, create an environment variable called FILE_STK in your shell configuration file (such as .bashrc or .zshrc) with the path to SuperToolKit.jar.**

*-Example: export FILE_STK="/home/george/Super-ToolKit/SuperToolKit.jar"*

**Also, in your shell file, add the path to the folder where the files are located to the PATH.**

*Example: export PATH="$PATH:/home/user/Super-ToolKit"*

**Finally, still in your shell file, add an alias called stk to your stk.sh file**

*Example: alias stk='/home/george/Super-ToolKit/stk.sh'*

**Now just restart your terminal or use the command source ~/.bashrc (or any other shell file).**

<br>

## Windows

**On Windows, create a new environment variable called FILE_STK, pointing to the folder where the files were moved (the path should point to the folder, but not to any file directly).**

*Example path: C:\Users\User\Documents\Super-ToolKit-Folder*

**Then, you should add this same path to your user's PATH.**

**Now, just restart the terminal and everything will work normally.**