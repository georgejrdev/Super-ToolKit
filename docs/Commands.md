# Explanation of Commands  

#### Convert Files  

- **Image:**  
    - **Syntax:** `stk convert image path/to/file newFormat`  
    - **Example:** `stk convert image ./images/myImage.jpg png`  
    - **Explanation:** Convert an image file to a new format.  

- **Video:**  
    - **Syntax:** `stk convert video path/to/file newFormat`  
    - **Example:** `stk convert video ./videos/myVideo.mp4 mov`  
    - **Explanation:** Convert a video file to a new format.  

- **Audio:**  
    - **Syntax:** `stk convert audio path/to/file newFormat`  
    - **Example:** `stk convert audio ./audio/myAudio.mp3 wav`  
    - **Explanation:** Convert an audio file to a new format.  

---

#### Parse Markdown to HTML  

- **Parse a File:**  
    - **Syntax:** `stk parse path/to/file`  
    - **Example:** `stk parse ./docs/myMarkdown.md`  
    - **Explanation:** Parse a Markdown file and convert it to HTML.  

- **Parse with Live Reload:**  
    - **Syntax:** `stk parse watch path/to/file`  
    - **Example:** `stk parse watch ./docs/myMarkdown.md`  
    - **Explanation:** Parse a Markdown file and activate live reload for changes.  

---

#### ToDo  

- **Add Task:**  
    - **Syntax:** `stk todo add "text"`  
    - **Example:** `stk todo add "Finish project documentation"`  
    - **Explanation:** Add a new task to the ToDo list.  

- **Remove Task:**  
    - **Syntax:** `stk todo remove id`  
    - **Example:** `stk todo remove 3`  
    - **Explanation:** Remove a task from the ToDo list by its ID.  

- **List Tasks:**  
    - **Syntax:** `stk todo list`  
    - **Example:** `stk todo list`  
    - **Explanation:** Display all tasks in the ToDo list.  

- **Check Task:**  
    - **Syntax:** `stk todo check id`  
    - **Example:** `stk todo check 2`  
    - **Explanation:** Mark a task as completed by its ID.  

- **Uncheck Task:**  
    - **Syntax:** `stk todo uncheck id`  
    - **Example:** `stk todo uncheck 2`  
    - **Explanation:** Mark a task as incomplete by its ID.  

---

#### Translate  

- **Syntax:** `stk translate text target_language`  
- **Example:** `stk translate "Hello, world!" es`  
- **Explanation:** Translate a text string into the specified target language.  

---

#### Get Commit Message  

- **Syntax:** `stk commit commit_description`  
- **Example:** `stk commit "Added new user authentication feature"`  
- **Explanation:** Generate a commit message based on the description provided.  

---

#### Generate QRCode  

- **Syntax:** `stk qrcode url path/to/save`  
- **Example:** `stk qrcode https://example.com ./images`  
- **Explanation:** Generate a QR code for a URL and save it to the specified path.  

---

#### Ram Viewer  

- **Syntax:** `stk ramviewer`  
- **Example:** `stk ramviewer`  
- **Explanation:** Launch a RAM usage viewer.  

---

#### Utilities  

- **Help:**  
    - **Syntax:** `stk help`  
    - **Example:** `stk help`  
    - **Explanation:** Display a list of all available commands and their syntax. 

    <br>

    - **Syntax:** `stk help [command]`  
    - **Example:** `stk help ramviewer`  
    - **Explanation:** Display a list of available options and their syntax for a specific command.   

- **Version:**  
    - **Syntax:** `stk version`  
    - **Example:** `stk version`  
    - **Explanation:** Display the current version of the software.  

- **Update:**  
    - **Syntax:** `stk update`  
    - **Example:** `stk update`  
    - **Explanation:** Update the software to the latest version.  

- **Config:**  
    - **Syntax:** `stk config`  
    - **Example:** `stk config`  
    - **Explanation:** Open the configuration settings.  


- **Change Log:**  
    - **Syntax:** `stk changelog`  
    - **Example:** `stk changelog`  
    - **Explanation:** Shows the change log of the last installed version.  

    <br>

    - **Syntax:** `stk changelog all`  
    - **Example:** `stk changelog all`  
    - **Explanation:** Shows the change log of all versions.  