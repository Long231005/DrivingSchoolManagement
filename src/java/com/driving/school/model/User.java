package com.driving.school.model;

public class User {
    private int userId;
    private String fullName;
    private String email;
    private String password;
    private String role; // ENUM: 'Student', 'Teacher', 'TrafficPolice'
    private String className; // Chỉ dành cho học sinh
    private String school; // Chỉ dành cho học sinh
    private String phone;

    // Constructor
    public User() {}

    public User(int userId, String fullName, String email, String password, String role, 
                String className, String school, String phone) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.className = className;
        this.school = school;
        this.phone = phone;
    }

    // Getters and Setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}