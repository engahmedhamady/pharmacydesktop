/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.common.beans;

import java.util.Date;

/**
 *
 * @author ahmed
 */
public class DrugsBean {

    private String barcode;
    private String name;
   
    private String type;
    private String company;
    private int purchasingPrice;
    private int sellingPrice;
    private int discount;
    private int quantity;
    private int profit;

    public DrugsBean() {
    }

    public DrugsBean(String name, int purchasingPrice, int sellingPrice, int discount, int quantity, int profit) {
        this.name = name;
        this.purchasingPrice = purchasingPrice;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.profit = profit;
       
    }

    public DrugsBean(String barcode, String name, String type, String company, int purchasingPrice, int sellingPrice, int discount, int quantity, int profit, Date expiry) {
        this.barcode = barcode;
        this.name = name;
        this.type = type;
        this.company = company;
        this.purchasingPrice = purchasingPrice;
        this.sellingPrice = sellingPrice;
        this.discount = discount;
        this.quantity = quantity;
        this.profit = profit;
     
    }

    public DrugsBean(String name,  int quantity) {
        this.name = name;
        
        this.quantity = quantity;
    }

    /**
     * @return the barcode
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * @param barcode the barcode to set
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the purchasingPrice
     */
    public int getPurchasingPrice() {
        return purchasingPrice;
    }

    /**
     * @param purchasingPrice the purchasingPrice to set
     */
    public void setPurchasingPrice(int purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    /**
     * @return the sellingPrice
     */
    public int getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @return the discount
     */
    public int getDiscount() {
        return discount;
    }

    /**
     * @param discount the discount to set
     */
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the profit
     */
    public int getProfit() {
        return profit;
    }

    /**
     * @param profit the profit to set
     */
    public void setProfit(int profit) {
        this.profit = profit;
    }

   

}
