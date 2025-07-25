package com.driving.school.model;

public class Registration {
    private int registrationId;
    private int userId; // Liên kết với UserID của học sinh
    private int courseId; // Liên kết với CourseID
    private String status; // ENUM: 'Pending', 'Approved', 'Rejected'
    private String comments;

    // Constructor
    public Registration() {}

    public Registration(int registrationId, int userId, int courseId, String status, String comments) {
        this.registrationId = registrationId;
        this.userId = userId;
        this.courseId = courseId;
        this.status = status;
        this.comments = comments;
    }

    // Getters and Setters
    public int getRegistrationId() { return registrationId; }
    public void setRegistrationId(int registrationId) { this.registrationId = registrationId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }
}