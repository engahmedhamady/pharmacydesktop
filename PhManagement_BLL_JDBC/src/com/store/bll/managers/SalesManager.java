/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.managers;

import com.store.common.beans.DeliveryBillsBean;
import com.store.common.beans.DrugsBean;
import com.store.common.beans.SalesBillsBean;
import com.store.dal.entities.DeliveryBills;
import com.store.dal.entities.Drugs;
import com.store.dal.entities.SalesBills;
import java.util.List;

/**
 *
 * @author Sroor For Laptop
 */
public interface SalesManager {
   
   SalesBillsBean saveSaleBill(SalesBillsBean bean);
   
  SalesBillsBean saveDeliveryBill(DeliveryBillsBean bean);
   DeliveryBillsBean deliveryBill(DeliveryBillsBean bean);
   DeliveryBillsBean deletedeliveryBill(DeliveryBillsBean bean);
   List < DeliveryBillsBean> listAllDelivery();
   List<DeliveryBillsBean> listByCodeDelivery(int code);
    SalesBillsBean AddBillSales (SalesBills salesBills);
   List <DrugsBean> findDrug (String name);
}
