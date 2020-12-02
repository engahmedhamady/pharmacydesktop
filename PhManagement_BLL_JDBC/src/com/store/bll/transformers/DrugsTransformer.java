package com.store.bll.transformers;

import com.store.dal.entities.Admin;
import com.store.common.beans.AdminBean;
import com.store.common.beans.DrugsBean;
import com.store.dal.entities.Drugs;


/**
 *
 * @author ahmed
 */
public class DrugsTransformer {

    public DrugsBean transformEntityToBean(Drugs entity) {
        if (entity == null) {
            return null;
        }
        DrugsBean bean = new DrugsBean();
        // transform
        bean.setBarcode(entity.getBarcode());
         bean.setName(entity.getName());
        bean.setType(entity.getType());
        bean.setCompany(entity.getCompany());
        bean.setDiscount(entity.getDiscount());
        bean.setPurchasingPrice(entity.getPurchasingPrice());
        bean.setSellingPrice(entity.getSellingPrice());
        bean.setQuantity(entity.getQuantity());
        bean.setProfit(entity.getProfit());

        return bean;
    }

    public Drugs transformBeanToEntity(DrugsBean bean) {
        if (bean == null) {
            return null;
        }
        Drugs entity = new Drugs();
        
       
        // transform
        entity.setBarcode(bean.getBarcode());
        entity.setName(bean.getName());
        entity.setType(bean.getType());
        entity.setPurchasingPrice(bean.getPurchasingPrice());
        entity.setSellingPrice(bean.getSellingPrice());
        entity.setDiscount(bean.getDiscount());
        entity.setQuantity(bean.getQuantity());
        entity.setProfit(bean.getProfit());
        entity.setCompany(bean.getCompany());

        return entity;
    }
}
