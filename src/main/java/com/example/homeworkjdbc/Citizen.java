package com.example.homeworkjdbc;

public class Citizen {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private int addressId;

    public Citizen() {
    }

    public Citizen(String name, String surname, String phone, int addressId) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.addressId = addressId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phone='" + phone + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
