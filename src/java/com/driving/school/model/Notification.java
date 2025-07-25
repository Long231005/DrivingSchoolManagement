package com.driving.school.model;

import java.util.Date;

public class Notification {
    private int notificationId;
    private int userId; // Liên kết với UserID
    private String message;
    private Date sentDate;
    private boolean isRead;

    // Constructor
    public Notification() {}

    public Notification(int notificationId, int userId, String message, Date sentDate, boolean isRead) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.sentDate = sentDate;
        this.isRead = isRead;
    }

    // Getters and Setters
    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Date getSentDate() { return sentDate; }
    public void setSentDate(Date sentDate) { this.sentDate = sentDate; }
    public boolean isRead() { return isRead; }
    public void setRead(boolean isRead) { this.isRead = isRead; }
}