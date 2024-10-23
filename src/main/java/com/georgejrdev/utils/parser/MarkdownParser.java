package com.georgejrdev.utils.parser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import com.georgejrdev.lib.reload.Parser;

public class MarkdownParser implements Parser {

    private Path srcPathFile;
    private Path destPathFile;

    public MarkdownParser(Path srcPathFile, Path destPathFile) {
        this.srcPathFile = srcPathFile;
        this.destPathFile = destPathFile;
    }

    @Override
    public void parse() {
        try {
            String content = new String(Files.readAllBytes(this.srcPathFile));
            content = convertHeaders(content);
            content = convertBold(content);
            content = convertItalic(content);
            content = convertLists(content);
            content = convertLinks(content);

            Files.write(this.destPathFile, content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } 
        
        catch (IOException e) {
            throw new RuntimeException("Failed to parse markdown file", e);
        }
    }

    private String convertHeaders(String markdown) {
        markdown = markdown.replaceAll("(?m)^# (.+)$", "<h1>$1</h1>");
        markdown = markdown.replaceAll("(?m)^## (.+)$", "<h2>$1</h2>");
        markdown = markdown.replaceAll("(?m)^### (.+)$", "<h3>$1</h3>");
        return markdown;
    }

    private String convertBold(String markdown) {
        markdown = markdown.replaceAll("\\*\\*(.+?)\\*\\*", "<strong>$1</strong>");
        markdown = markdown.replaceAll("__(.+?)__", "<strong>$1</strong>");
        return markdown;
    }

    private String convertItalic(String markdown) {
        markdown = markdown.replaceAll("\\*(.+?)\\*", "<em>$1</em>");
        markdown = markdown.replaceAll("_(.+?)_", "<em>$1</em>");
        return markdown;
    }

    private String convertLists(String markdown) {
        markdown = markdown.replaceAll("(?m)^(\\*|\\-) (.+)$", "<li>$2</li>");
        markdown = markdown.replaceAll("(?s)(<li>.+?</li>)", "<ul>$1</ul>");
        markdown = markdown.replaceAll("</ul><ul>", "");
        return markdown;
    }

    private String convertLinks(String markdown) {
        markdown = markdown.replaceAll("\\[(.+?)\\]\\((.+?)\\)", "<a href=\"$2\">$1</a>");
        return markdown;
    }
}
