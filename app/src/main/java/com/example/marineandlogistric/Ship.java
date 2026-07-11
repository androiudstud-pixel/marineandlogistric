package com.example.marineandlogistric;

public class Ship {

    private int id;
    private String name;
    private String imo;
    private String type;
    private String flag;
    private String status;

    public Ship(int id, String name, String imo, String type, String flag, String status) {
        this.id = id;
        this.name = name;
        this.imo = imo;
        this.type = type;
        this.flag = flag;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImo() {
        return imo;
    }

    public String getType() {
        return type;
    }

    public String getFlag() {
        return flag;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImo(String imo) {
        this.imo = imo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}