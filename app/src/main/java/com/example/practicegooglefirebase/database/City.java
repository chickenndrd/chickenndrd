package com.example.practicegooglefirebase.database;

public class City {

    String id, name, state;

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public City() {    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
