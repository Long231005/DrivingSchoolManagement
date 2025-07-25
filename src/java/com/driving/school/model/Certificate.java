package com.driving.school.model;

import java.util.Date;

public class Certificate {
    private int certificateId;
    private int userId; // Liên kết với UserID
    private Date issuedDate;
    private Date expirationDate;
    private String certificateCode;

    // Constructor
    public Certificate() {}

    public Certificate(int certificateId, int userId, Date issuedDate, Date expirationDate, String certificateCode) {
        this.certificateId = certificateId;
        this.userId = userId;
        this.issuedDate = issuedDate;
        this.expirationDate = expirationDate;
        this.certificateCode = certificateCode;
    }

    // Getters and Setters
    public int getCertificateId() { return certificateId; }
    public void setCertificateId(int certificateId) { this.certificateId = certificateId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getIssuedDate() { return issuedDate; }
    public void setIssuedDate(Date issuedDate) { this.issuedDate = issuedDate; }
    public Date getExpirationDate() { return expirationDate; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
    public String getCertificateCode() { return certificateCode; }
    public void setCertificateCode(String certificateCode) { this.certificateCode = certificateCode; }
}