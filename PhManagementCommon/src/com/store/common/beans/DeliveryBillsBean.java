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
public class DeliveryBillsBean {
      	
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

      public DeliveryBillsBean(int billcode) {
            this.billcode = billcode;
      }

    public DeliveryBillsBean() {
    }
     
      public DeliveryBillsBean(int billcode,  String drug1, String drug2, String drug3, String drug4, String drug5, int unitprice1, int unitprice2, int unitprice3, int unitprice4, int unitprice5, int quantity1, int quantity2, int quantity3, int quantity4, int quantity5, int total1, int total2, int total3, int total4, int total5, int discount1, int discount2, int discount3, int discount4, int discount5, int net1, int net2, int net3, int net4, int net5, int profit1, int profit2, int profit3, int profit4, int profit5, int totalnet, int totalprofits) {
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

    
    

      /**
       * @return the billcode
       */
      public int getBillcode() {
            return billcode;
      }

      /**
       * @param billcode the billcode to set
       */
      public void setBillcode(int billcode) {
            this.billcode = billcode;
      }

      /**
       * @return the billdate
       */
      public Date getBilldate() {
            return billdate;
      }

      /**
       * @param billdate the billdate to set
       */
      public void setBilldate(Date billdate) {
            this.billdate = billdate;
      }

      /**
       * @return the expiry1
       */
      
      /**
       * @return the drug1
       */
      public String getDrug1() {
            return drug1;
      }

      /**
       * @param drug1 the drug1 to set
       */
      public void setDrug1(String drug1) {
            this.drug1 = drug1;
      }

      /**
       * @return the drug2
       */
      public String getDrug2() {
            return drug2;
      }

      /**
       * @param drug2 the drug2 to set
       */
      public void setDrug2(String drug2) {
            this.drug2 = drug2;
      }

      /**
       * @return the drug3
       */
      public String getDrug3() {
            return drug3;
      }

      /**
       * @param drug3 the drug3 to set
       */
      public void setDrug3(String drug3) {
            this.drug3 = drug3;
      }

      /**
       * @return the drug4
       */
      public String getDrug4() {
            return drug4;
      }

      /**
       * @param drug4 the drug4 to set
       */
      public void setDrug4(String drug4) {
            this.drug4 = drug4;
      }

      /**
       * @return the drug5
       */
      public String getDrug5() {
            return drug5;
      }

      /**
       * @param drug5 the drug5 to set
       */
      public void setDrug5(String drug5) {
            this.drug5 = drug5;
      }

      /**
       * @return the unitprice1
       */
      public int getUnitprice1() {
            return unitprice1;
      }

      /**
       * @param unitprice1 the unitprice1 to set
       */
      public void setUnitprice1(int unitprice1) {
            this.unitprice1 = unitprice1;
      }

      /**
       * @return the unitprice2
       */
      public int getUnitprice2() {
            return unitprice2;
      }

      /**
       * @param unitprice2 the unitprice2 to set
       */
      public void setUnitprice2(int unitprice2) {
            this.unitprice2 = unitprice2;
      }

      /**
       * @return the unitprice3
       */
      public int getUnitprice3() {
            return unitprice3;
      }

      /**
       * @param unitprice3 the unitprice3 to set
       */
      public void setUnitprice3(int unitprice3) {
            this.unitprice3 = unitprice3;
      }

      /**
       * @return the unitprice4
       */
      public int getUnitprice4() {
            return unitprice4;
      }

      /**
       * @param unitprice4 the unitprice4 to set
       */
      public void setUnitprice4(int unitprice4) {
            this.unitprice4 = unitprice4;
      }

      /**
       * @return the unitprice5
       */
      public int getUnitprice5() {
            return unitprice5;
      }

      /**
       * @param unitprice5 the unitprice5 to set
       */
      public void setUnitprice5(int unitprice5) {
            this.unitprice5 = unitprice5;
      }

      /**
       * @return the quantity1
       */
      public int getQuantity1() {
            return quantity1;
      }

      /**
       * @param quantity1 the quantity1 to set
       */
      public void setQuantity1(int quantity1) {
            this.quantity1 = quantity1;
      }

      /**
       * @return the quantity2
       */
      public int getQuantity2() {
            return quantity2;
      }

      /**
       * @param quantity2 the quantity2 to set
       */
      public void setQuantity2(int quantity2) {
            this.quantity2 = quantity2;
      }

      /**
       * @return the quantity3
       */
      public int getQuantity3() {
            return quantity3;
      }

      /**
       * @param quantity3 the quantity3 to set
       */
      public void setQuantity3(int quantity3) {
            this.quantity3 = quantity3;
      }

      /**
       * @return the quantity4
       */
      public int getQuantity4() {
            return quantity4;
      }

      /**
       * @param quantity4 the quantity4 to set
       */
      public void setQuantity4(int quantity4) {
            this.quantity4 = quantity4;
      }

      /**
       * @return the quantity5
       */
      public int getQuantity5() {
            return quantity5;
      }

      /**
       * @param quantity5 the quantity5 to set
       */
      public void setQuantity5(int quantity5) {
            this.quantity5 = quantity5;
      }

      /**
       * @return the total1
       */
      public int getTotal1() {
            return total1;
      }

      /**
       * @param total1 the total1 to set
       */
      public void setTotal1(int total1) {
            this.total1 = total1;
      }

      /**
       * @return the total2
       */
      public int getTotal2() {
            return total2;
      }

      /**
       * @param total2 the total2 to set
       */
      public void setTotal2(int total2) {
            this.total2 = total2;
      }

      /**
       * @return the total3
       */
      public int getTotal3() {
            return total3;
      }

      /**
       * @param total3 the total3 to set
       */
      public void setTotal3(int total3) {
            this.total3 = total3;
      }

      /**
       * @return the total4
       */
      public int getTotal4() {
            return total4;
      }

      /**
       * @param total4 the total4 to set
       */
      public void setTotal4(int total4) {
            this.total4 = total4;
      }

      /**
       * @return the total5
       */
      public int getTotal5() {
            return total5;
      }

      /**
       * @param total5 the total5 to set
       */
      public void setTotal5(int total5) {
            this.total5 = total5;
      }

      /**
       * @return the discount1
       */
      public int getDiscount1() {
            return discount1;
      }

      /**
       * @param discount1 the discount1 to set
       */
      public void setDiscount1(int discount1) {
            this.discount1 = discount1;
      }

      /**
       * @return the discount2
       */
      public int getDiscount2() {
            return discount2;
      }

      /**
       * @param discount2 the discount2 to set
       */
      public void setDiscount2(int discount2) {
            this.discount2 = discount2;
      }

      /**
       * @return the discount3
       */
      public int getDiscount3() {
            return discount3;
      }

      /**
       * @param discount3 the discount3 to set
       */
      public void setDiscount3(int discount3) {
            this.discount3 = discount3;
      }

      /**
       * @return the discount4
       */
      public int getDiscount4() {
            return discount4;
      }

      /**
       * @param discount4 the discount4 to set
       */
      public void setDiscount4(int discount4) {
            this.discount4 = discount4;
      }

      /**
       * @return the discount5
       */
      public int getDiscount5() {
            return discount5;
      }

      /**
       * @param discount5 the discount5 to set
       */
      public void setDiscount5(int discount5) {
            this.discount5 = discount5;
      }

      /**
       * @return the net1
       */
      public int getNet1() {
            return net1;
      }

      /**
       * @param net1 the net1 to set
       */
      public void setNet1(int net1) {
            this.net1 = net1;
      }

      /**
       * @return the net2
       */
      public int getNet2() {
            return net2;
      }

      /**
       * @param net2 the net2 to set
       */
      public void setNet2(int net2) {
            this.net2 = net2;
      }

      /**
       * @return the net3
       */
      public int getNet3() {
            return net3;
      }

      /**
       * @param net3 the net3 to set
       */
      public void setNet3(int net3) {
            this.net3 = net3;
      }

      /**
       * @return the net4
       */
      public int getNet4() {
            return net4;
      }

      /**
       * @param net4 the net4 to set
       */
      public void setNet4(int net4) {
            this.net4 = net4;
      }

      /**
       * @return the net5
       */
      public int getNet5() {
            return net5;
      }

      /**
       * @param net5 the net5 to set
       */
      public void setNet5(int net5) {
            this.net5 = net5;
      }

      /**
       * @return the profit1
       */
      public int getProfit1() {
            return profit1;
      }

      /**
       * @param profit1 the profit1 to set
       */
      public void setProfit1(int profit1) {
            this.profit1 = profit1;
      }

      /**
       * @return the profit2
       */
      public int getProfit2() {
            return profit2;
      }

      /**
       * @param profit2 the profit2 to set
       */
      public void setProfit2(int profit2) {
            this.profit2 = profit2;
      }

      /**
       * @return the profit3
       */
      public int getProfit3() {
            return profit3;
      }

      /**
       * @param profit3 the profit3 to set
       */
      public void setProfit3(int profit3) {
            this.profit3 = profit3;
      }

      /**
       * @return the profit4
       */
      public int getProfit4() {
            return profit4;
      }

      /**
       * @param profit4 the profit4 to set
       */
      public void setProfit4(int profit4) {
            this.profit4 = profit4;
      }

      /**
       * @return the profit5
       */
      public int getProfit5() {
            return profit5;
      }

      /**
       * @param profit5 the profit5 to set
       */
      public void setProfit5(int profit5) {
            this.profit5 = profit5;
      }

      /**
       * @return the totalnet
       */
      public int getTotalnet() {
            return totalnet;
      }

      /**
       * @param totalnet the totalnet to set
       */
      public void setTotalnet(int totalnet) {
            this.totalnet = totalnet;
      }

      /**
       * @return the totalprofits
       */
      public int getTotalprofits() {
            return totalprofits;
      }

      /**
       * @param totalprofits the totalprofits to set
       */
      public void setTotalprofits(int totalprofits) {
            this.totalprofits = totalprofits;
      }

    
   
}
