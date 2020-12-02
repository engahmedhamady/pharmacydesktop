
package com.store.bll.managers;

import com.store.common.beans.AdminBean;

public interface LogManager {
   public abstract boolean login (AdminBean adminBean);
   public abstract AdminBean find (AdminBean adminBean);
    public abstract boolean  logout ();
  
}
