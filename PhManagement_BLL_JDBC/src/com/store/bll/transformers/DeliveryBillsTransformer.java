package com.store.bll.transformers;

import com.store.dal.entities.Admin;
import com.store.common.beans.AdminBean;
import com.store.common.beans.DeliveryBillsBean;
import com.store.dal.entities.DeliveryBills;

/**
 *
 * @author ahmed
 */
public class DeliveryBillsTransformer {

    public DeliveryBillsBean transformEntityToBean(DeliveryBills entity) {
        if (entity == null) {
            return null;
        }
        DeliveryBillsBean bean = new DeliveryBillsBean();
        // transform
        bean.setBillcode(entity.getBillCode());
        bean.setBilldate(entity.getBillDate());
        
        bean.setDrug1(entity.getDrug1());
        
        bean.setUnitprice1(entity.getUnitPrice1());
        bean.setQuantity1(entity.getQuantity1());
        bean.setTotal1(entity.getTotal1());
        bean.setDiscount1(entity.getDiscount1());
        bean.setNet1(entity.getNet1());
        bean.setProfit1(entity.getProfit1());

        bean.setDrug2(entity.getDrug2());
    
        bean.setUnitprice2(entity.getUnitPrice2());
        bean.setQuantity2(entity.getQuantity2());
        bean.setTotal2(entity.getTotal2());
        bean.setDiscount2(entity.getDiscount2());
        bean.setNet2(entity.getNet2());
        bean.setProfit2(entity.getProfit2());

        bean.setDrug3(entity.getDrug3());
       
        bean.setUnitprice3(entity.getUnitPrice3());
        bean.setQuantity3(entity.getQuantity3());
        bean.setTotal3(entity.getTotal3());
        bean.setDiscount3(entity.getDiscount3());
        bean.setNet3(entity.getNet3());
        bean.setProfit3(entity.getProfit3());

        bean.setDrug4(entity.getDrug4());
       
        bean.setUnitprice4(entity.getUnitPrice4());
        bean.setQuantity4(entity.getQuantity4());
        bean.setTotal4(entity.getTotal4());
        bean.setDiscount4(entity.getDiscount4());
        bean.setNet4(entity.getNet4());
        bean.setProfit4(entity.getProfit4());

        bean.setDrug5(entity.getDrug5());
     
        bean.setUnitprice5(entity.getUnitPrice5());
        bean.setQuantity5(entity.getQuantity5());
        bean.setTotal5(entity.getTotal5());
        bean.setDiscount5(entity.getDiscount5());
        bean.setNet5(entity.getNet5());
        bean.setProfit5(entity.getProfit5());

        bean.setTotalnet(entity.getTotalnet());
        bean.setTotalprofits(entity.getTotalProfits());

        return bean;
    }

    public DeliveryBills transformBeanToEntity(DeliveryBillsBean bean) {
        if (bean == null) {
            return null;
        }
        DeliveryBills entity = new DeliveryBills();
        // transform
          entity.setBillCode(bean.getBillcode());
       entity.setBillDate(bean.getBilldate());
        
        entity.setDrug1(bean.getDrug1());
 
        entity.setUnitPrice1(bean.getUnitprice1());
        entity.setQuantity1(bean.getQuantity1());
        entity.setTotal1(bean.getTotal1());
        entity.setDiscount1(bean.getDiscount1());
        entity.setNet1(bean.getNet1());
        entity.setProfit1(bean.getProfit1());

        entity.setDrug2(bean.getDrug2());
    
       entity.setUnitPrice2(bean.getUnitprice2());
        entity.setQuantity2(bean.getQuantity2());
       entity.setTotal2(bean.getTotal2());
       entity.setDiscount2(bean.getDiscount2());
       entity.setNet2(bean.getNet2());
      entity.setProfit2(bean.getProfit2());

       entity.setDrug3(bean.getDrug3());
      
       entity.setUnitPrice3(bean.getUnitprice3());
       entity.setQuantity3(bean.getQuantity3());
        entity.setTotal3(bean.getTotal3());
        entity.setDiscount3(bean.getDiscount3());
       entity.setNet3(bean.getNet3());
       entity.setProfit3(bean.getProfit3());

       entity.setDrug4(bean.getDrug4());
        
        entity.setUnitPrice4(bean.getUnitprice4());
        entity.setQuantity4(bean.getQuantity4());
        entity.setTotal4(bean.getTotal4());
        entity.setDiscount4(bean.getDiscount4());
        entity.setNet4(bean.getNet4());
        entity.setProfit4(bean.getProfit4());

        entity.setDrug5(bean.getDrug5());
        
        entity.setUnitPrice5(bean.getUnitprice5());
        entity.setQuantity5(bean.getQuantity5());
        entity.setTotal5(bean.getTotal5());
       entity.setDiscount5(bean.getDiscount5());
        entity.setNet5(bean.getNet5());
        entity.setProfit5(bean.getProfit5());

        entity.setTotalnet(bean.getTotalnet());
        entity.setTotalProfits(bean.getTotalprofits()); 
        return entity;
    }
}
