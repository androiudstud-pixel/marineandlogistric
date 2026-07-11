package com.example.marineandlogistric;

public class Equipment {
    private String name;
    private String type;
    private String serial;

    public Equipment(String name, String type, String serial) {
        this.name = name;
        this.type = type;
        this.serial = serial;
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getSerial() { return serial; }
}
