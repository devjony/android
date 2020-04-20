package br.com.devjony.jsonapp;

import java.io.Serializable;

public class Schedule implements Serializable {
    private String name;
    private String phone;

    public Schedule(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Schedule(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
