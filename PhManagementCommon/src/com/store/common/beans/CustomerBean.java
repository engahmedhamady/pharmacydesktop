/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.common.beans;

/**
 *
 * @author ahmed
 */
public class CustomerBean {
    private String   name ;
    private String  phone ;
    private String email ;
private int ID ;
     

      /**
       * @return the name
       */
      public String getName() {
            return name;
      }

      /**
       * @param name the name to set
       */
      public void setName(String name) {
            this.name = name;
      }

      /**
       * @return the phone
       */
      public String getPhone() {
            return phone;
      }

      /**
       * @param phone the phone to set
       */
      public void setPhone(String phone) {
            this.phone = phone;
      }

      /**
       * @return the email
       */
      public String getEmail() {
            return email;
      }

      /**
       * @param email the email to set
       */
      public void setEmail(String email) {
            this.email = email;
      }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
      
}
