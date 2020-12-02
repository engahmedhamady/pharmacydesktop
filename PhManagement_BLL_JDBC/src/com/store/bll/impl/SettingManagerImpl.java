
package com.store.bll.impl;

import com.store.bll.managers.SettingManager;
import com.store.bll.transformers.AdminTransformer;
import com.store.common.beans.AdminBean;
import com.store.dal.entities.Admin;
import com.store.dal.repos.AdminDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Sroor For Laptop
 */
public class SettingManagerImpl implements SettingManager {

    AdminTransformer adminTransformer = new AdminTransformer();

    @Override
    public void addAccount(AdminBean adminBean) {
        try {
         
            AdminDAO adminDAO = new AdminDAO();

            adminDAO.add(adminTransformer.transformBeanToEntity(adminBean));
        } catch (Exception ex) {
            Logger.getLogger(LogManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteAccount(AdminBean adminBean) {
        try {
           
            AdminDAO adminDAO = new AdminDAO();

            adminDAO.remove(adminTransformer.transformBeanToEntity(adminBean));
          
        } catch (Exception ex) {
            Logger.getLogger(LogManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updatePassword(AdminBean adminBean) {
        try {
           
            AdminDAO adminDAO = new AdminDAO();

            adminDAO.update(adminTransformer.transformBeanToEntity(adminBean));
           
        } catch (Exception ex) {
            Logger.getLogger(LogManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<AdminBean> listAll() {
        List<AdminBean> l = null;
        try {


            AdminDAO adminDAO = new AdminDAO();
            AdminTransformer adminTransformer = new AdminTransformer();
            List<Admin> list = adminDAO.findList();

            if (list!=null){
                 l= new ArrayList<>();
            for (Admin admin : list) {
              
              l.add(adminTransformer.transformEntityToBean(admin));  
            }
            
            }
            
        } catch (Exception ex) {
            Logger.getLogger(LogManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

}
