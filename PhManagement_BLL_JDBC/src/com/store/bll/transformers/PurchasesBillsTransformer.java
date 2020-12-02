package com.store.bll.transformers;

import com.store.dal.entities.Admin;
import com.store.common.beans.AdminBean;
import com.store.common.beans.PurchasesBillsBean;
import com.store.dal.entities.PurchasesBills;

/**
 *
 * @author ahmed
 */
public class PurchasesBillsTransformer {

    public PurchasesBillsBean transformEntityToBean(PurchasesBills entity) {
        if (entity == null) {
            return null;
        }
        PurchasesBillsBean bean = new PurchasesBillsBean();
        // transform
        bean.setBillCode(entity.getBillcode());
        bean.setBarCode(entity.getBarcode());
        bean.setCompany(entity.getCompany());
        bean.setDateBill(entity.getDateBill());
        bean.setDiscount(entity.getDiscount());
        bean.setDrugName(entity.getName());
        bean.setDrugType(entity.getType());
        bean.setPurchasePrice(entity.getPurchasingPrice());
        bean.setQuantityDrug(entity.getQuantity());
        bean.setSellPrice(entity.getSellingPrice());
        bean.setTotal(entity.getTotal());
        return bean;
    }

    public PurchasesBills transformBeanToEntity(PurchasesBillsBean bean) {
        if (bean == null) {
            return null;
        }
        PurchasesBills entity = new PurchasesBills();
        // transform
        entity.setBillcode(bean.getBillCode());
        entity.setBarcode(bean.getBarCode());
        entity.setCompany(bean.getCompany());
        entity.setDateBill(bean.getDateBill());
        entity.setDiscount(bean.getDiscount());
        entity.setName(bean.getDrugName());
        entity.setType(bean.getDrugType());
        entity.setPurchasingPrice(bean.getPurchasePrice());
        entity.setQuantity(bean.getQuantityDrug());
        entity.setSellingPrice(bean.getSellPrice());
        entity.setTotal(bean.getTotal());
        return entity;
    }
}
