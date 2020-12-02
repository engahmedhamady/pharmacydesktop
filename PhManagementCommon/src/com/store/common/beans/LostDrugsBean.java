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
public class LostDrugsBean {

    private String drugname;
    private String drugtype;
    private int quantitydrug;

    public LostDrugsBean(String drugname, String drugtype, int quantitydrug) {
        this.drugname = drugname;
        this.drugtype = drugtype;
        this.quantitydrug = quantitydrug;
    }

    public LostDrugsBean() {
    }

    /**
     * @return the drugname
     */
    public String getDrugname() {
        return drugname;
    }

    /**
     * @param drugname the drugname to set
     */
    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    /**
     * @return the drugtype
     */
    public String getDrugtype() {
        return drugtype;
    }

    /**
     * @param drugtype the drugtype to set
     */
    public void setDrugtype(String drugtype) {
        this.drugtype = drugtype;
    }

    /**
     * @return the quantitydrug
     */
    public int getQuantitydrug() {
        return quantitydrug;
    }

    /**
     * @param quantitydrug the quantitydrug to set
     */
    public void setQuantitydrug(int quantitydrug) {
        this.quantitydrug = quantitydrug;
    }

}
