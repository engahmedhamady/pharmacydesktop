/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.managers;

import com.store.common.beans.DrugsBean;
import java.util.List;

/**
 *
 * @author Sroor For Laptop
 */
public interface DiscountManager {

     List<DrugsBean> listAllDiscount();

    List<DrugsBean> findByNameDiscount(String name);

    List<DrugsBean> findByTypeDiscount(String type);

    List<DrugsBean> findByDiscount(int from , int to);

   List<DrugsBean> findByPriceDiscount(int from , int to);

   DrugsBean updateDiscount(String name , int value);

    
}
