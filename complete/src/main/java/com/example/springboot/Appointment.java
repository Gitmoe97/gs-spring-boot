package com.example.springboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class Appointment {

    int id;
    String name;
    String date;
    int service;

    public Appointment() {}

    public Appointment(int id, String name, String date, int service) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getService() {
        return service;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setService(int service) {
        this.service = service;
    }
}
