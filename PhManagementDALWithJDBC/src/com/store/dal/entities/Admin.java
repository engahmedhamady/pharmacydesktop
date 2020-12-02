package com.store.dal.entities;
// Generated Jul 8, 2020 8:01:12 AM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;



public class Admin   {


     private String name;
     private String password;
     private Set<Screens> screenses = new HashSet<Screens>(0);

    public Admin() {
    }

	
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public Admin(String name, String password, HashSet<Screens> screenses) {
       this.name = name;
       this.password = password;
       this.screenses = screenses;
    }

    public Admin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Screens> getScreenses() {
        return screenses;
    }

    public void setScreenses(Set<Screens> screenses) {
        this.screenses = screenses;
    }
   
    
    
    
    

}


