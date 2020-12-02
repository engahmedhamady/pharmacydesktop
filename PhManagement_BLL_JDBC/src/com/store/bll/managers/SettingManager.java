/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.bll.managers;

import com.store.common.beans.AdminBean;
import com.store.dal.entities.Admin;
import java.util.List;

/**
 *
 * @author Sroor For Laptop
 */
public interface SettingManager {
    void addAccount(AdminBean adminBean);

    void deleteAccount(AdminBean adminBean);

    void updatePassword(AdminBean adminBean);
   List <AdminBean> listAll();
 
}
