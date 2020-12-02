package com.store.bll.impl;

import com.store.bll.managers.DiscountManager;
import com.store.bll.transformers.DrugsTransformer;
import com.store.common.beans.DrugsBean;
import com.store.dal.entities.Drugs;
import com.store.dal.repos.DrugsDAO;
import java.util.ArrayList;
import java.util.List;


public class DiscountManagerImpl implements DiscountManager {

    @Override
    public List<DrugsBean> listAllDiscount() {
        List<DrugsBean> l = null;
        try {

          
            DrugsDAO drugsDAO = new DrugsDAO();
            DrugsTransformer adminTransformer = new DrugsTransformer();
            List<Drugs> list = drugsDAO.findList();

            if (list != null) {
                l = new ArrayList<>();
                for (Drugs d : list) {

                    l.add(adminTransformer.transformEntityToBean(d));
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<DrugsBean> findByNameDiscount(String name) {
        List<DrugsBean> find = null;
        try {
                DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByName(name);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

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
    public List<DrugsBean> findByTypeDiscount(String type) {
        List<DrugsBean> find = null;
        try {
           
            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByType(type);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

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
    public List<DrugsBean> findByDiscount(int from, int to) {
        List<DrugsBean> find = null;
        try {
           
            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByDiscount(from, to);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {
                    System.out.println(drugs.getName());
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
    public List<DrugsBean> findByPriceDiscount(int from, int to) {
        List<DrugsBean> find = null;
        try {
          
            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByPrice(from, to);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {
                    System.out.println(drugs.getName());
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
    public DrugsBean updateDiscount(String name , int value) {
         Drugs find = null;
          DrugsBean bean = new DrugsBean();
          bean.setName(name);
          bean.setDiscount(value);
        try {
             
            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
             Drugs findById = dAO.findById(drugsTransformer.transformBeanToEntity(bean));
             findById.setDiscount(value);
            find = dAO.update(findById);

         
            return drugsTransformer.transformEntityToBean(find);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    }

