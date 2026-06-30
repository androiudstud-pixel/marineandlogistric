package com.example.marineandlogistric;

public class Crew {

    private String name;
    private String rank;
    private String nationality;
    private String passport;

    public Crew(String name, String rank, String nationality, String passport) {
        this.name = name;
        this.rank = rank;
        this.nationality = nationality;
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getRank() {
        return rank;
    }

    public String getNationality() {
        return nationality;
    }

    public String getPassport() {
        return passport;
    }
}
