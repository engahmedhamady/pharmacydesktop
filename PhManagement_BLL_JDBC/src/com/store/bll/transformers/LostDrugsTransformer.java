package com.store.bll.transformers;

import com.store.common.beans.LostDrugsBean;
import com.store.dal.entities.LostDrugs;

/**
 *
 * @author ahmed
 */
public class LostDrugsTransformer {

    
    public LostDrugsBean transformEntityToBean(LostDrugs entity) {
        if (entity == null) {
            return null;
        }
        LostDrugsBean bean = new LostDrugsBean();
        // transform
        bean.setDrugname(entity.getDrugName());
        bean.setDrugtype(entity.getDrugType());
         bean.setQuantitydrug(entity.getQuantityDrug());
        return bean;
    }

    public LostDrugs transformBeanToEntity(LostDrugsBean bean) {
        if (bean == null) {
            return null;
        }
       LostDrugs entity = new LostDrugs();
        // transform
        entity.setDrugName(bean.getDrugname());
        entity.setDrugType(bean.getDrugtype());
        entity.setQuantityDrug(bean.getQuantitydrug());
       
        return entity;
    }
}
