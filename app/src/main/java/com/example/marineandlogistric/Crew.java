package com.example.marineandlogistric;

public class Crew {
    private int id;
    private String name;
    private String rank;
    private String nationality;
    private String passport;

    public Crew(int id, String name, String rank, String nationality, String passport) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.nationality = nationality;
        this.passport = passport;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRank() { return rank; }
    public String getNationality() { return nationality; }
    public String getPassport() { return passport; }
}
