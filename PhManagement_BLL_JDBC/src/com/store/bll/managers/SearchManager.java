/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.managers;

import com.store.bll.managers.*;
import com.store.common.beans.DrugsBean;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sroor For Laptop
 */
public interface SearchManager {

     List<DrugsBean> listAllSearch();

     List<DrugsBean> findByNameSearch(String name);

     List<DrugsBean> findByTypeSearch(String type);

   
    List<DrugsBean> findByPriceSearch(int from ,int to);

}
