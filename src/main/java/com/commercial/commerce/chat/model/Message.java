package com.commercial.commerce.chat.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Message {

    @Id
    private String id;
    private String senderId;
    private String senderName;
    private String senderEmail;
    private String senderPicturePath;
    private String receiverId;
    private String receiverEmail;
    private String receiverPicturePath;
    private String content;
    private Date date;


    public Message(String id, String senderId, String content, String receiverEmail, Date date) {
        this.id = id;
        this.senderId = senderId;
        this.content = content;
        this.receiverEmail = receiverEmail;
        this.date = date;
    }

    public Message(String senderId, String senderName, String senderEmail, String senderPicturePath, String receiverId, String receiverEmail, String receiverPicturePath, String content, Date date) {

        this.senderId = senderId;
        this.senderName = senderName;
        this.senderEmail = senderEmail;
        this.senderPicturePath = senderPicturePath;
        this.receiverId = receiverId;
        this.receiverEmail = receiverEmail;
        this.receiverPicturePath = receiverPicturePath;
        this.content = content;
        this.date = date;
    }

    public Message(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSenderPicturePath() {
        return senderPicturePath;
    }

    public void setSenderPicturePath(String senderPicturePath) {
        this.senderPicturePath = senderPicturePath;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverPicturePath() {
        return receiverPicturePath;
    }

    public void setReceiverPicturePath(String receiverPicturePath) {
        this.receiverPicturePath = receiverPicturePath;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }


    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", senderId='" + senderId + '\'' +
                ", content='" + content + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                '}';
    }
}
