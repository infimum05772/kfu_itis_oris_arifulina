package ru.kpfu.itis.arifulina.net.dto;

public class MasterDto {
    private String name;
    private String surname;
    private String phone;
    public MasterDto(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
