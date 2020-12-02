
package phmanagement_bll_jdbc;

import com.store.bll.impl.LogManagerImpl;
import com.store.common.beans.AdminBean;

public class PhManagement_BLL_JDBC {
    public static void main(String[] args) {
       
        LogManagerImpl impl = new LogManagerImpl();
        AdminBean adminBean = new AdminBean();
        adminBean.setName("admin");    
        adminBean.setPassword("admin");
        AdminBean find = impl.find(adminBean);
        System.out.println(find.getName());
    }
    
}
