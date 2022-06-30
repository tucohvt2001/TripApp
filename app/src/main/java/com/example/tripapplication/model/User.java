package com.example.tripapplication.model;

public class User {
    private int Id;
    private String Name;
    private String Password;
    private String Address;
    private String Phone;

    public User(){

    }

    public User(int id, String name, String password, String address, String phone) {
        Id = id;
        Name = name;
        Password = password;
        Address = address;
        Phone = phone;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

}
