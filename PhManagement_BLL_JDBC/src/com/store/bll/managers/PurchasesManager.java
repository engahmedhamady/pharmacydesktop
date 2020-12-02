/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.managers;

import com.store.common.beans.CustomerBean;
import com.store.common.beans.DrugsBean;
import com.store.common.beans.LostDrugsBean;
import com.store.common.beans.PurchasesBillsBean;
import com.store.dal.entities.BillCodeStore;
import java.util.List;

/**
 *
 * @author Sroor For Laptop
 */
public interface PurchasesManager {

  
    int createBill();

    PurchasesBillsBean saveBill(PurchasesBillsBean bean);

    List<DrugsBean> listAllFounded();

    List<DrugsBean> listByNamefounded(String name);

    List<DrugsBean> listByTypefounded(String type);

    List<DrugsBean> listByQuantityfounded(int from, int to);

    List<DrugsBean> listByPricefounded(int froom, int to);

    List<DrugsBean> listByCompanyfounded(String name);
    DrugsBean updateDrug (DrugsBean drugsBean);
    DrugsBean deleteDrug(DrugsBean bean);
    //---------------------------------------------------------------

    LostDrugsBean addRequested(LostDrugsBean bean);

    LostDrugsBean updateDrugRequested(LostDrugsBean bean);

    LostDrugsBean deleteLostDrug(LostDrugsBean bean);

    List<LostDrugsBean> listAllRequested();

    List<LostDrugsBean> listByNameRequested(String name);

    List<LostDrugsBean> listByTypeRequested(String type);
//-----------------------------------------------------------------------

    CustomerBean addCustomer(CustomerBean bean);

    CustomerBean updateCustomer(CustomerBean bean);

    CustomerBean deleteCustomer(CustomerBean bean);

    List<CustomerBean> listAllCustomer();

    List<CustomerBean> listByNameCustomer(String name);

}
