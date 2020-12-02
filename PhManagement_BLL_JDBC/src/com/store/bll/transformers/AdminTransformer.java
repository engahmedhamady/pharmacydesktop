package com.store.bll.transformers;

import com.store.dal.entities.Admin;
import com.store.common.beans.AdminBean;
import com.store.dal.entities.Screens;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author ahmed
 */
public class AdminTransformer {

    public AdminBean transformEntityToBean(Admin entity) {
        if (entity == null) {
            return null;
        }
        Set set = new HashSet();
        Set<Screens> screenses = entity.getScreenses();
        for (Screens screense : screenses) {
           set.add(screense);  
        }
        
       
      
        AdminBean bean = new AdminBean();
     
        // transform
        bean.setName(entity.getName());
        bean.setPassword(entity.getPassword());
        bean.setScreenses(set);
        return bean;
    }

    public Admin transformBeanToEntity(AdminBean bean) {
        if (bean == null) {
            return null;
        }
        Admin entity = new Admin();
        // transform
        entity.setName(bean.getName());
        entity.setPassword(bean.getPassword());
        entity.setScreenses(bean.getScreenses());
        return entity;
    }
}
