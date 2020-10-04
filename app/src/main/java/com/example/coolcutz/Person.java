package com.example.coolcutz;

public class Person {
    private String ID;
    private String Name;
    private String Email;
    private String Password;
    private Integer PhoneNumber;


public Person(){
}


    public Person(String id,String name, String email, String password, Integer phoneNumber) {
        ID = id;
        Name = name;
        Email = email;
        Password = password;
        PhoneNumber = phoneNumber;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public Person(String name, String email, String password, Integer phoneNumber) {



}}


