package com.store.dal.entities;

public class LostDrugs   {


     private String drugName;
     private String drugType;
     private int quantityDrug;

    public LostDrugs() {
    }

    public LostDrugs(String drugName, String drugType, int quantityDrug) {
       this.drugName = drugName;
       this.drugType = drugType;
       this.quantityDrug = quantityDrug;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public int getQuantityDrug() {
        return quantityDrug;
    }

    public void setQuantityDrug(int quantityDrug) {
        this.quantityDrug = quantityDrug;
    }
   
    



}


