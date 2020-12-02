package com.store.dal.entities;




public class Drugs  {


     private String name;
     private String barcode;
     private String type;
     private int purchasingPrice;
     private int sellingPrice;
     private int discount;
     private int quantity;
     private int profit;
     private String company;

    public Drugs(String name, String company) {
        this.name = name;
        this.company = company;
    }

    public Drugs() {
    }

    public Drugs(String name, String barcode, String type, int purchasingPrice, int sellingPrice, int discount, int quantity, int profit, String company) {
       this.name = name;
       this.barcode = barcode;
       this.type = type;
       this.purchasingPrice = purchasingPrice;
       this.sellingPrice = sellingPrice;
       this.discount = discount;
       this.quantity = quantity;
       this.profit = profit;
       this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPurchasingPrice() {
        return purchasingPrice;
    }

    public void setPurchasingPrice(int purchasingPrice) {
        this.purchasingPrice = purchasingPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
   
     
}


