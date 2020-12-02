/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.impl;

import com.store.bll.managers.SalesManager;
import com.store.bll.transformers.BillCosdeStoreTransformer;
import com.store.bll.transformers.DeliveryBillsTransformer;
import com.store.bll.transformers.DrugsTransformer;
import com.store.bll.transformers.SalesBillsTransformer;
import com.store.common.beans.BillCodeStoreBean;
import com.store.common.beans.DeliveryBillsBean;
import com.store.common.beans.DrugsBean;
import com.store.common.beans.SalesBillsBean;
import com.store.dal.entities.DeliveryBills;
import com.store.dal.entities.Drugs;
import com.store.dal.entities.SalesBills;

import com.store.dal.repos.BillCodeStoreDAO;
import com.store.dal.repos.DeliveryBillsDAO;
import com.store.dal.repos.DrugsDAO;
import com.store.dal.repos.SalesBillsDAO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sroor For Laptop
 */
public class SalesManagerImpl implements SalesManager {

    @Override
    public List<DeliveryBillsBean> listAllDelivery() {
        List<DeliveryBillsBean> find = null;
        try {
 
            DeliveryBillsDAO dAO = new DeliveryBillsDAO();

            DeliveryBillsTransformer drugsTransformer = new DeliveryBillsTransformer();
            List<DeliveryBills> findList = dAO.findList();
            if (findList != null) {
                find = new ArrayList();
                for (DeliveryBills drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;

    }

    @Override
    public List<DeliveryBillsBean> listByCodeDelivery(int code) {
        List<DeliveryBillsBean> find = null;
        try {

            DeliveryBillsDAO dAO = new DeliveryBillsDAO();

            DeliveryBillsTransformer drugsTransformer = new DeliveryBillsTransformer();
            DeliveryBills findList = dAO.findById(code);
            if (findList != null) {
                find = new ArrayList();
                find.add(drugsTransformer.transformEntityToBean(findList));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public SalesBillsBean AddBillSales(SalesBills salesBills) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<DrugsBean> findDrug(String name) {
        List<DrugsBean> list = new ArrayList<>();
        try {

            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            Drugs d= new Drugs();
            d.setName(name);
            Drugs find = dAO.findById(d);
            list.add(drugsTransformer.transformEntityToBean(find));

            if (find == null) {
                return null;
            } else {
                return list;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public SalesBillsBean saveSaleBill(SalesBillsBean bean) {
        SalesBillsBean add = null;
        try {

            SalesBillsDAO DAO = new SalesBillsDAO();
            SalesBillsTransformer transformer = new SalesBillsTransformer();
            add = transformer.transformEntityToBean(DAO.add(transformer.transformBeanToEntity(bean)));
            if (add != null) {
                int[] quantity = {add.getQuantity1(), add.getQuantity2(), add.getQuantity3(), add.getQuantity4(), add.getQuantity5()};
                int i = 0;
                String[] names = {add.getDrug1(), add.getDrug2(), add.getDrug3(), add.getDrug4(), add.getDrug5()};
                DrugsDAO dAO = new DrugsDAO();
                DrugsTransformer drugsTransformer = new DrugsTransformer();
                for (String name : names) {
                    if (name != null) {
                        Drugs find = dAO.findById(name);
                        find.setQuantity(find.getQuantity() - quantity[i]);
                        dAO.update(find);

                    }
                    i++;
                }

            
            BillCodeStoreBean s = new BillCodeStoreBean();
            s.setCode(bean.getBillcode());

            BillCosdeStoreTransformer transformer2 = new BillCosdeStoreTransformer();

            BillCodeStoreDAO bcsdao = new BillCodeStoreDAO();
            bcsdao.remove(transformer2.transformBeanToEntity(s));
            s.setCode(bean.getBillcode() + 1);
            bcsdao.add(transformer2.transformBeanToEntity(s));
            }
            return add;
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public SalesBillsBean saveDeliveryBill(DeliveryBillsBean bean) {
        DeliveryBillsBean add = null;
        try {
  
            DeliveryBillsDAO DAO = new DeliveryBillsDAO();
            DeliveryBillsTransformer transformer = new DeliveryBillsTransformer();
            add = transformer.transformEntityToBean(DAO.findById(bean.getBillcode()));

            if (add != null) 
            {
                // java.util.Date date = new java.util.Date(new java.util.Date().getTime());

                SalesBillsBean salesBillsBean = new SalesBillsBean();

                salesBillsBean.setBillcode(add.getBillcode());
                salesBillsBean.setBilldate(add.getBilldate());
                salesBillsBean.setDrug1(add.getDrug1());
                salesBillsBean.setDrug2(add.getDrug2());
                salesBillsBean.setDrug3(add.getDrug3());
                salesBillsBean.setDrug4(add.getDrug4());
                salesBillsBean.setDrug5(add.getDrug5());

                salesBillsBean.setDiscount1(add.getDiscount1());
                salesBillsBean.setDiscount2(add.getDiscount2());
                salesBillsBean.setDiscount3(add.getDiscount3());
                salesBillsBean.setDiscount4(add.getDiscount4());
                salesBillsBean.setDiscount5(add.getDiscount5());

                salesBillsBean.setNet1(add.getNet1());
                salesBillsBean.setNet2(add.getNet2());
                salesBillsBean.setNet3(add.getNet3());
                salesBillsBean.setNet4(add.getNet4());
                salesBillsBean.setNet5(add.getNet5());

                salesBillsBean.setProfit1(add.getProfit1());
                salesBillsBean.setProfit2(add.getProfit2());
                salesBillsBean.setProfit3(add.getProfit3());
                salesBillsBean.setProfit4(add.getProfit4());
                salesBillsBean.setProfit5(add.getProfit5());

                salesBillsBean.setQuantity1(add.getQuantity1());
                salesBillsBean.setQuantity2(add.getQuantity2());
                salesBillsBean.setQuantity3(add.getQuantity3());
                salesBillsBean.setQuantity4(add.getQuantity4());
                salesBillsBean.setQuantity5(add.getQuantity5());

                salesBillsBean.setTotal1(add.getTotal1());
                salesBillsBean.setTotal2(add.getTotal2());
                salesBillsBean.setTotal3(add.getTotal3());
                salesBillsBean.setTotal4(add.getTotal4());
                salesBillsBean.setTotal5(add.getTotal5());

                salesBillsBean.setUnitprice1(add.getUnitprice1());
                salesBillsBean.setUnitprice2(add.getUnitprice2());
                salesBillsBean.setUnitprice3(add.getUnitprice3());
                salesBillsBean.setUnitprice4(add.getUnitprice4());
                salesBillsBean.setUnitprice5(add.getUnitprice5());

                salesBillsBean.setTotalnet(add.getTotalnet());
                salesBillsBean.setTotalprofits(add.getTotalprofits());

                SalesBillsDAO dAO1 = new SalesBillsDAO();
                SalesBillsTransformer transformer2 = new SalesBillsTransformer();
                SalesBillsBean transformEntityToBean = transformer2.transformEntityToBean(dAO1.add(transformer2.transformBeanToEntity(salesBillsBean)));

                DrugsBean bean1 = new DrugsBean();
                if ( transformEntityToBean != null)
                {
                    int[] quantity = {add.getQuantity1(), add.getQuantity2(), add.getQuantity3(), add.getQuantity4(), add.getQuantity5()};
                    int i = 0;
                    String[] names = {add.getDrug1(), add.getDrug2(), add.getDrug3(), add.getDrug4(), add.getDrug5()};
                    DrugsDAO dAO = new DrugsDAO();
                    DrugsTransformer drugsTransformer = new DrugsTransformer();
                    for (String name : names) {
                        if (name != null) {
                            Drugs find = dAO.findById(name);
                            find.setQuantity(find.getQuantity() - quantity[i]);
                            dAO.update(find);

                        }
                        i++;
                    }
                    //SalesBillsBean saveSaleBill = saveSaleBill(salesBillsBean);
                   
                    
                        // delete delivery
                        DeliveryBillsBean deletedeliveryBill = deletedeliveryBill(bean);
                        if (deletedeliveryBill != null) {
                            return salesBillsBean;
                        } else {
                            return null;
                        }

                   

                } else {
                    return null;
                }

            }
            else
            { 
                return null;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public DeliveryBillsBean deliveryBill(DeliveryBillsBean bean) {
        DeliveryBillsBean add = null;
        try {
            
            DeliveryBillsDAO DAO = new DeliveryBillsDAO();
            DeliveryBillsTransformer transformer = new DeliveryBillsTransformer();
            add = transformer.transformEntityToBean(DAO.add(transformer.transformBeanToEntity(bean)));
            if (add!=null){
                BillCodeStoreBean s = new BillCodeStoreBean();
            s.setCode(bean.getBillcode());

            BillCosdeStoreTransformer transformer2 = new BillCosdeStoreTransformer();

            BillCodeStoreDAO bcsdao = new BillCodeStoreDAO();
            bcsdao.remove(transformer2.transformBeanToEntity(s));
            s.setCode(bean.getBillcode() + 1);
           
            bcsdao.add(transformer2.transformBeanToEntity(s));

            }
            

            return add;
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public DeliveryBillsBean deletedeliveryBill(DeliveryBillsBean bean) {
        DeliveryBillsBean add = null;
        try {
          
            DeliveryBillsDAO DAO = new DeliveryBillsDAO();
            DeliveryBillsTransformer transformer = new DeliveryBillsTransformer();
            DeliveryBills findById = DAO.findById(bean.getBillcode());
            DAO.remove(findById);
  
            return bean;
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }

}
