package com.example.marineandlogistric;

public class Equipment {
    private int id;
    private String name;
    private String type;
    private String serial;

    public Equipment(int id, String name, String type, String serial) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.serial = serial;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getSerial() { return serial; }
}
