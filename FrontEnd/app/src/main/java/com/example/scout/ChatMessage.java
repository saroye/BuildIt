package com.example.scout;

public class ChatMessage {
    private String message;
    private boolean incomingMessage;

    public ChatMessage(String message, boolean incomingMessage){
        this.message = message;
        this.incomingMessage = incomingMessage;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIncomingMessage() {
        return incomingMessage;
    }

    public void setIncomingMessage(boolean incomingMessage) {
        this.incomingMessage = incomingMessage;
    }
}
