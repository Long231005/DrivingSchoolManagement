package com.driving.school.model;

public class Result {
    private int resultId;
    private int examId; // Liên kết với ExamID
    private int userId; // Liên kết với UserID của học sinh
    private double score;
    private boolean passStatus;

    // Constructor
    public Result() {}

    public Result(int resultId, int examId, int userId, double score, boolean passStatus) {
        this.resultId = resultId;
        this.examId = examId;
        this.userId = userId;
        this.score = score;
        this.passStatus = passStatus;
    }

    // Getters and Setters
    public int getResultId() { return resultId; }
    public void setResultId(int resultId) { this.resultId = resultId; }
    public int getExamId() { return examId; }
    public void setExamId(int examId) { this.examId = examId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
    public boolean isPassStatus() { return passStatus; }
    public void setPassStatus(boolean passStatus) { this.passStatus = passStatus; }
}