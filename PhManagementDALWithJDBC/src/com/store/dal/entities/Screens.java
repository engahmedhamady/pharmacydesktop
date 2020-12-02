package com.store.dal.entities;

public class Screens   {


     private int seq;
     private Admin admin;
     private String page;

    public Screens() {
    }

    public Screens(int seq, Admin admin, String page) {
       this.seq = seq;
       this.admin = admin;
       this.page = page;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
   
    



}


