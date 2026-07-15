package com.example.marineandlogistric;

public class Certificate {
    private int id;
    private String name;
    private String issueDate;
    private String expiryDate;

    public Certificate(int id, String name, String issueDate, String expiryDate) {
        this.id = id;
        this.name = name;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getIssueDate() { return issueDate; }
    public String getExpiryDate() { return expiryDate; }
}
