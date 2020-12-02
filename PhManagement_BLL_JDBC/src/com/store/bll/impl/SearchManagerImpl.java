/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.impl;

import com.store.bll.managers.SearchManager;
import com.store.bll.transformers.DrugsTransformer;
import com.store.common.beans.DrugsBean;
import com.store.dal.entities.Drugs;

import com.store.dal.repos.DrugsDAO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sroor For Laptop
 */
public class SearchManagerImpl implements SearchManager {

    @Override
    public List<DrugsBean> listAllSearch() {
        List<DrugsBean> find = null;
        try {

            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findList();
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
    public List<DrugsBean> findByNameSearch(String name) {
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
    public List<DrugsBean> findByTypeSearch(String type) {
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
    public List<DrugsBean> findByPriceSearch(int from, int to) {
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

}
