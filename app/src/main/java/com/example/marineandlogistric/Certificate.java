package com.example.marineandlogistric;

public class Certificate {
    private String name;
    private String issueDate;
    private String expiryDate;

    public Certificate(String name, String issueDate, String expiryDate) {
        this.name = name;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public String getName() { return name; }
    public String getIssueDate() { return issueDate; }
    public String getExpiryDate() { return expiryDate; }
}
