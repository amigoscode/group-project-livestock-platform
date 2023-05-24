package com.amigoscode.livestockplatform.response;

public class MessageResponse extends com.amigoscodelivestock_platform.model.MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}