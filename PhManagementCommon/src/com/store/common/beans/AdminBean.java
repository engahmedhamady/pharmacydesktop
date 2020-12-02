/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.common.beans;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author ahmed
 */
public class AdminBean {
      private String name ;
       private String password;
    private String  isLegalLogin  ;
    private Set screenses= new HashSet();

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

    public String getIsLegalLogin() {
        return isLegalLogin;
    }

    public void setIsLegalLogin(String isLegalLogin) {
        this.isLegalLogin = isLegalLogin;
    }

    public Set getScreenses() {
        return screenses;
    }

    public void setScreenses(Set screenses) {
        this.screenses =  screenses;
    }
      
}
