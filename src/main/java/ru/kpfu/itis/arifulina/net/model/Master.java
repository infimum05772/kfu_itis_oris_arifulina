package ru.kpfu.itis.arifulina.net.model;

public class Master {
    private int masterId;
    private String name;
    private String surname;
    private String phone;

    public Master(int masterId, String name, String surname, String phone) {
        this.masterId = masterId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
