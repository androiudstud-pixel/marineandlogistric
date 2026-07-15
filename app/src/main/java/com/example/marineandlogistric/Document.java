package com.example.marineandlogistric;

public class Document {
    private int id;
    private String name;
    private String type;
    private String date;

    public Document(int id, String name, String type, String date) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getDate() { return date; }
}
