package com.store.bll.transformers;

import com.store.dal.entities.Admin;
import com.store.common.beans.AdminBean;
import com.store.common.beans.CustomerBean;
import com.store.dal.entities.Customer;

/**
 *
 * @author ahmed
 */
public class CustomerTransformer {

   
    public CustomerBean transformEntityToBean(Customer entity) {
        if (entity == null) {
            return null;
        }
       CustomerBean bean = new CustomerBean();
        // transform
        bean.setEmail(entity.getEmail());
        bean.setName(entity.getName());
        bean.setPhone(entity.getPhone());
        bean.setID(entity.getID());
        return bean;
    }

    public Customer transformBeanToEntity(CustomerBean bean) {
        if (bean == null) {
            return null;
        }
        Customer entity = new Customer();
        // transform
        entity.setName(bean.getName());
        entity.setEmail(bean.getEmail());
         entity.setPhone(bean.getPhone());
          entity.setID(bean.getID());
        return entity;
    }
}
