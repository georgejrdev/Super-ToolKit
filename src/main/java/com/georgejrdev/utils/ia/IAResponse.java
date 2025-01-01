package com.georgejrdev.utils.ia;

public class IAResponse {
    
    private boolean success;
    private String content;

    public IAResponse(boolean success, String content) {
        this.success = success;
        this.content = content;
    }

    public boolean success() {
        return success;
    }

    public String getContent() {
        return content;
    }
}
