package com.store.bll.transformers;

import com.store.dal.entities.Admin;
import com.store.common.beans.AdminBean;
import com.store.common.beans.ScreensBean;
import com.store.dal.entities.Screens;

/**
 *
 * @author ahmed
 */
public class ScreensTransformer {

   
    public ScreensBean transformEntityToBean(Screens entity) {
        if (entity == null) {
            return null;
        }
        ScreensBean bean = new ScreensBean();
        // transform
        bean.setName(entity.getAdmin().getName());
        bean.setPage(entity.getPage());
        bean.setSeq(entity.getSeq());
        return bean;
    }

    public Screens transformBeanToEntity(ScreensBean bean) {
        if (bean == null) {
            return null;
        }
        Screens entity = new Screens();
        // transform
        Admin admin = new  Admin();
        admin.setName(bean.getName());
        entity.setAdmin(admin);
        entity.setPage(bean.getPage());
        entity.setSeq(entity.getSeq());
        return entity;
    }
}
