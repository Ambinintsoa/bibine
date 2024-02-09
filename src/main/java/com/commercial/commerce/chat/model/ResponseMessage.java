package com.commercial.commerce.chat.model;

public class ResponseMessage {

    private String content;
    private String senderId;
    private String senderName;
    private String senderPicturePath;

    public ResponseMessage() {
    }

    public ResponseMessage(String content) {
        this.content = content;
    }

    public ResponseMessage(String content, String senderId, String senderName, String picturePath) {
        this.content = content;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderPicturePath = picturePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getsenderId() {
        return senderId;
    }

    public void setsenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getsenderName() {
        return senderName;
    }

    public void setsenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getPicturePath() {
        return senderPicturePath;
    }

    public void setPicturePath(String picturePath) {
        this.senderPicturePath = picturePath;
    }
}
