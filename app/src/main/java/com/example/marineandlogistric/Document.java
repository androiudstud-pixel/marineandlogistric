package com.example.marineandlogistric;

public class Document {
    private String name;
    private String type;
    private String date;

    public Document(String name, String type, String date) {
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getDate() { return date; }
}
