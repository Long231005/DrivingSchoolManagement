package com.driving.school.model;

import java.util.Date;

public class Exam {
    private int examId;
    private int courseId; // Liên kết với CourseID
    private Date date;
    private String room;

    // Constructor
    public Exam() {}

    public Exam(int examId, int courseId, Date date, String room) {
        this.examId = examId;
        this.courseId = courseId;
        this.date = date;
        this.room = room;
    }

    // Getters and Setters
    public int getExamId() { return examId; }
    public void setExamId(int examId) { this.examId = examId; }
    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public String getRoom() { return room; }
    public void setRoom(String room) { this.room = room; }
}