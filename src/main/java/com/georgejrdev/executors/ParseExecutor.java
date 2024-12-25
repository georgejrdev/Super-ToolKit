package com.georgejrdev.executors;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import com.georgejrdev.lib.reload.HotReload;
import com.georgejrdev.lib.reload.Parser;
import com.georgejrdev.utils.parser.MarkdownParser;
import com.georgejrdev.utils.helper.AppLogger;

public class ParseExecutor {

    private final Parser parser;
    private final int HTTP_PORT;
    private final int WEB_SOCKET_PORT;
    private Path directoryPath;
    private Path srcFilePath;
    private Path destFilePath;
    private String htmlFileName;
    private static final Logger logger = AppLogger.getLogger();

    public ParseExecutor(String srcFilePath) {
        this.HTTP_PORT = 8080;
        this.WEB_SOCKET_PORT = 8081;
        this.htmlFileName = "index.html";
        this.srcFilePath = Paths.get(srcFilePath);
        this.directoryPath = this.srcFilePath.getParent();
        this.destFilePath = directoryPath.resolve(this.htmlFileName);
        this.parser = new MarkdownParser(this.srcFilePath, this.destFilePath);
    }

    public void parseToHtml() {
        logger.info("Converting markdown to html" + this.srcFilePath + " to " + this.destFilePath);

        if (!Files.exists(this.destFilePath)) {
            createFileIfNotExist(false);
        }

        this.parser.parse();
    }

    public void parseToHtmlWithHotReload() {
        logger.info("Converting markdown to html with hot reload" + this.srcFilePath + " to " + this.destFilePath);

        if (!Files.exists(this.destFilePath)) {
            createFileIfNotExist(true);
        }

        HotReload hotReload = new HotReload(this.srcFilePath.toString(), this.destFilePath.toString(), this.HTTP_PORT, this.WEB_SOCKET_PORT, this.parser);
        hotReload.start();
    }

    private void createFileIfNotExist(boolean reloadScript) {
        String initialContent = "<h1>Init Reload</h1> \n <p>update the file to load its content</p> \n <script> document.addEventListener('DOMContentLoaded', (event) => { var ws = new WebSocket('ws://localhost:" + this.WEB_SOCKET_PORT + "/reload'); ws.onmessage = function(event) { if (event.data === 'reload') { window.location.reload(); } }; }); </script>";

        try {
            if (!Files.exists(this.directoryPath)) {
                Files.createDirectories(this.directoryPath);
            }

            Files.createFile(this.destFilePath);

            if (reloadScript) {
                Files.write(this.destFilePath, initialContent.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            }

            logger.info("Created destination file " + this.destFilePath + ". Reload script: " + reloadScript);
        } 
        
        catch (IOException e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Failed to create destination file");
            logger.fine("Stack trace for the error:\n" + sw.toString());

            throw new RuntimeException("Failed to create destination file", e);
        }
    }
}