package com.store.dal.entities;

import java.util.Date;

public class PurchasesBills   {


     private int billcode;
     private String barcode;
     private String name;
     private String type;
     private int purchasingPrice;
     private int sellingPrice;
     private int discount;
     private int quantity;
     private int total;
     private String company;
     private Date dateBill;

    public PurchasesBills() {
    }

    public PurchasesBills(int billcode, String barcode, String name, String type, int purchasingPrice, int sellingPrice, int discount, int quantity, int total, String company, Date dateBill) {
       this.billcode = billcode;
       this.barcode = barcode;
       this.name = name;
       this.type = type;
       this.purchasingPrice = purchasingPrice;
       this.sellingPrice = sellingPrice;
       this.discount = discount;
       this.quantity = quantity;
       this.total = total;
       this.company = company;
       this.dateBill = dateBill;
    }

    public int getBillcode() {
        return billcode;
    }

    public void setBillcode(int billcode) {
        this.billcode = billcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getDateBill() {
        return dateBill;
    }

    public void setDateBill(Date dateBill) {
        this.dateBill = dateBill;
    }
   
   

}


