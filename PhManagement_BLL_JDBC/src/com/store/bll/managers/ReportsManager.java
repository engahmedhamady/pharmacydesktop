/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.managers;

import com.store.common.beans.PurchasesBillsBean;
import com.store.common.beans.SalesBillsBean;
import com.store.dal.entities.PurchasesBills;
import com.store.dal.entities.SalesBills;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sroor For Laptop
 */
public interface ReportsManager {

    List<SalesBillsBean> listAllSales();

    List<SalesBillsBean> findByCodeSales(int billCode);

    List<SalesBillsBean> findByDateSales(Date from , Date to);

    List<SalesBillsBean> findByTotalSales(int from , int to);

    List<SalesBillsBean> findByProfitSales(int from , int to);

    List<PurchasesBillsBean> listAllPurchases();

    List<PurchasesBillsBean> findByCodePurchases(int billCode);

    List<PurchasesBillsBean> findByDatePurchases(Date from , Date to);

    List<PurchasesBillsBean> findByTotalPurchases(int from , int to);

    int incom(Date from, Date to);

    int payment(Date from, Date to);

    int profits(Date from, Date to);

}
