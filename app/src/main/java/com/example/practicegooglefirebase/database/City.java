package com.example.practicegooglefirebase.database;

public class City {

    String id, name, state;

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    public City() {    }

    public City(String id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    @Override
    public String toString() {
        return "City{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
