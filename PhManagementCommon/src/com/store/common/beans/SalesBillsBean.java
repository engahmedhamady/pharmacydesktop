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
public class SalesBillsBean {

    private int billcode;
    private Date billdate;

    private String drug1;
    private String drug2;
    private String drug3;
    private String drug4;
    private String drug5;
    private int unitprice1;
    private int unitprice2;
    private int unitprice3;
    private int unitprice4;
    private int unitprice5;
    private int quantity1;
    private int quantity2;
    private int quantity3;
    private int quantity4;
    private int quantity5;
    private int total1;
    private int total2;
    private int total3;
    private int total4;
    private int total5;
    private int discount1;
    private int discount2;
    private int discount3;
    private int discount4;
    private int discount5;
    private int net1;
    private int net2;
    private int net3;
    private int net4;
    private int net5;
    private int profit1;
    private int profit2;
    private int profit3;
    private int profit4;
    private int profit5;
    private int totalnet;
    private int totalprofits;

    public SalesBillsBean(int billcode, Date billdate, String drug1, String drug2, String drug3, String drug4, String drug5, int unitprice1, int unitprice2, int unitprice3, int unitprice4, int unitprice5, int quantity1, int quantity2, int quantity3, int quantity4, int quantity5, int total1, int total2, int total3, int total4, int total5, int discount1, int discount2, int discount3, int discount4, int discount5, int net1, int net2, int net3, int net4, int net5, int profit1, int profit2, int profit3, int profit4, int profit5, int totalnet, int totalprofits) {
        this.billcode = billcode;
        this.billdate = billdate;
        this.drug1 = drug1;
        this.drug2 = drug2;
        this.drug3 = drug3;
        this.drug4 = drug4;
        this.drug5 = drug5;
        this.unitprice1 = unitprice1;
        this.unitprice2 = unitprice2;
        this.unitprice3 = unitprice3;
        this.unitprice4 = unitprice4;
        this.unitprice5 = unitprice5;
        this.quantity1 = quantity1;
        this.quantity2 = quantity2;
        this.quantity3 = quantity3;
        this.quantity4 = quantity4;
        this.quantity5 = quantity5;
        this.total1 = total1;
        this.total2 = total2;
        this.total3 = total3;
        this.total4 = total4;
        this.total5 = total5;
        this.discount1 = discount1;
        this.discount2 = discount2;
        this.discount3 = discount3;
        this.discount4 = discount4;
        this.discount5 = discount5;
        this.net1 = net1;
        this.net2 = net2;
        this.net3 = net3;
        this.net4 = net4;
        this.net5 = net5;
        this.profit1 = profit1;
        this.profit2 = profit2;
        this.profit3 = profit3;
        this.profit4 = profit4;
        this.profit5 = profit5;
        this.totalnet = totalnet;
        this.totalprofits = totalprofits;
    }

    public SalesBillsBean(int billcode, String drug1, String drug2, String drug3, String drug4, String drug5, int unitprice1, int unitprice2, int unitprice3, int unitprice4, int unitprice5, int quantity1, int quantity2, int quantity3, int quantity4, int quantity5, int total1, int total2, int total3, int total4, int total5, int discount1, int discount2, int discount3, int discount4, int discount5, int net1, int net2, int net3, int net4, int net5, int profit1, int profit2, int profit3, int profit4, int profit5, int totalnet, int totalprofits) {
        this.billcode = billcode;
        this.drug1 = drug1;
        this.drug2 = drug2;
        this.drug3 = drug3;
        this.drug4 = drug4;
        this.drug5 = drug5;
        this.unitprice1 = unitprice1;
        this.unitprice2 = unitprice2;
        this.unitprice3 = unitprice3;
        this.unitprice4 = unitprice4;
        this.unitprice5 = unitprice5;
        this.quantity1 = quantity1;
        this.quantity2 = quantity2;
        this.quantity3 = quantity3;
        this.quantity4 = quantity4;
        this.quantity5 = quantity5;
        this.total1 = total1;
        this.total2 = total2;
        this.total3 = total3;
        this.total4 = total4;
        this.total5 = total5;
        this.discount1 = discount1;
        this.discount2 = discount2;
        this.discount3 = discount3;
        this.discount4 = discount4;
        this.discount5 = discount5;
        this.net1 = net1;
        this.net2 = net2;
        this.net3 = net3;
        this.net4 = net4;
        this.net5 = net5;
        this.profit1 = profit1;
        this.profit2 = profit2;
        this.profit3 = profit3;
        this.profit4 = profit4;
        this.profit5 = profit5;
        this.totalnet = totalnet;
        this.totalprofits = totalprofits;
    }

    public SalesBillsBean(int billcode, Date billdate) {
        this.billcode = billcode;
        this.billdate = billdate;
    }

    public SalesBillsBean(int billcode, int totalnet, int totalprofits, Date billdate) {
        this.billcode = billcode;
        this.totalnet = totalnet;
        this.totalprofits = totalprofits;
        this.billdate = billdate;
    }

    public SalesBillsBean() {
    }

    public int getBillcode() {
        return billcode;
    }

    public void setBillcode(int billcode) {
        this.billcode = billcode;
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public String getDrug1() {
        return drug1;
    }

    public void setDrug1(String drug1) {
        this.drug1 = drug1;
    }

    public String getDrug2() {
        return drug2;
    }

    public void setDrug2(String drug2) {
        this.drug2 = drug2;
    }

    public String getDrug3() {
        return drug3;
    }

    public void setDrug3(String drug3) {
        this.drug3 = drug3;
    }

    public String getDrug4() {
        return drug4;
    }

    public void setDrug4(String drug4) {
        this.drug4 = drug4;
    }

    public String getDrug5() {
        return drug5;
    }

    public void setDrug5(String drug5) {
        this.drug5 = drug5;
    }

    public int getUnitprice1() {
        return unitprice1;
    }

    public void setUnitprice1(int unitprice1) {
        this.unitprice1 = unitprice1;
    }

    public int getUnitprice2() {
        return unitprice2;
    }

    public void setUnitprice2(int unitprice2) {
        this.unitprice2 = unitprice2;
    }

    public int getUnitprice3() {
        return unitprice3;
    }

    public void setUnitprice3(int unitprice3) {
        this.unitprice3 = unitprice3;
    }

    public int getUnitprice4() {
        return unitprice4;
    }

    public void setUnitprice4(int unitprice4) {
        this.unitprice4 = unitprice4;
    }

    public int getUnitprice5() {
        return unitprice5;
    }

    public void setUnitprice5(int unitprice5) {
        this.unitprice5 = unitprice5;
    }

    public int getQuantity1() {
        return quantity1;
    }

    public void setQuantity1(int quantity1) {
        this.quantity1 = quantity1;
    }

    public int getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(int quantity2) {
        this.quantity2 = quantity2;
    }

    public int getQuantity3() {
        return quantity3;
    }

    public void setQuantity3(int quantity3) {
        this.quantity3 = quantity3;
    }

    public int getQuantity4() {
        return quantity4;
    }

    public void setQuantity4(int quantity4) {
        this.quantity4 = quantity4;
    }

    public int getQuantity5() {
        return quantity5;
    }

    public void setQuantity5(int quantity5) {
        this.quantity5 = quantity5;
    }

    public int getTotal1() {
        return total1;
    }

    public void setTotal1(int total1) {
        this.total1 = total1;
    }

    public int getTotal2() {
        return total2;
    }

    public void setTotal2(int total2) {
        this.total2 = total2;
    }

    public int getTotal3() {
        return total3;
    }

    public void setTotal3(int total3) {
        this.total3 = total3;
    }

    public int getTotal4() {
        return total4;
    }

    public void setTotal4(int total4) {
        this.total4 = total4;
    }

    public int getTotal5() {
        return total5;
    }

    public void setTotal5(int total5) {
        this.total5 = total5;
    }

    public int getDiscount1() {
        return discount1;
    }

    public void setDiscount1(int discount1) {
        this.discount1 = discount1;
    }

    public int getDiscount2() {
        return discount2;
    }

    public void setDiscount2(int discount2) {
        this.discount2 = discount2;
    }

    public int getDiscount3() {
        return discount3;
    }

    public void setDiscount3(int discount3) {
        this.discount3 = discount3;
    }

    public int getDiscount4() {
        return discount4;
    }

    public void setDiscount4(int discount4) {
        this.discount4 = discount4;
    }

    public int getDiscount5() {
        return discount5;
    }

    public void setDiscount5(int discount5) {
        this.discount5 = discount5;
    }

    public int getNet1() {
        return net1;
    }

    public void setNet1(int net1) {
        this.net1 = net1;
    }

    public int getNet2() {
        return net2;
    }

    public void setNet2(int net2) {
        this.net2 = net2;
    }

    public int getNet3() {
        return net3;
    }

    public void setNet3(int net3) {
        this.net3 = net3;
    }

    public int getNet4() {
        return net4;
    }

    public void setNet4(int net4) {
        this.net4 = net4;
    }

    public int getNet5() {
        return net5;
    }

    public void setNet5(int net5) {
        this.net5 = net5;
    }

    public int getProfit1() {
        return profit1;
    }

    public void setProfit1(int profit1) {
        this.profit1 = profit1;
    }

    public int getProfit2() {
        return profit2;
    }

    public void setProfit2(int profit2) {
        this.profit2 = profit2;
    }

    public int getProfit3() {
        return profit3;
    }

    public void setProfit3(int profit3) {
        this.profit3 = profit3;
    }

    public int getProfit4() {
        return profit4;
    }

    public void setProfit4(int profit4) {
        this.profit4 = profit4;
    }

    public int getProfit5() {
        return profit5;
    }

    public void setProfit5(int profit5) {
        this.profit5 = profit5;
    }

    public int getTotalnet() {
        return totalnet;
    }

    public void setTotalnet(int totalnet) {
        this.totalnet = totalnet;
    }

    public int getTotalprofits() {
        return totalprofits;
    }

    public void setTotalprofits(int totalprofits) {
        this.totalprofits = totalprofits;
    }

}
