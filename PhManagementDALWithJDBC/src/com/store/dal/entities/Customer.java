package com.store.dal.entities;

public class Customer {

    private int ID;
    private String name;
    private String email;
    private String phone;

    public Customer() {
    }

    public Customer(int ID,String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   
}
