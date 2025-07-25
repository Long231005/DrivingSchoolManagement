package com.driving.school.model;

import java.util.Date;

public class Course {
    private int courseId;
    private String courseName;
    private int teacherId; // Liên kết với UserID của giảng viên
    private Date startDate;
    private Date endDate;

    // Constructor
    public Course() {}

    public Course(int courseId, String courseName, int teacherId, Date startDate, Date endDate) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public int getTeacherId() { return teacherId; }
    public void setTeacherId(int teacherId) { this.teacherId = teacherId; }
    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
}