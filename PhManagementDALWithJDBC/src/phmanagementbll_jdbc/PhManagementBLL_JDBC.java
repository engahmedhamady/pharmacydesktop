
package phmanagementbll_jdbc;

import com.store.dal.entities.Admin;
import com.store.dal.entities.BillCodeStore;
import com.store.dal.entities.Drugs;
import com.store.dal.entities.LostDrugs;
import com.store.dal.entities.SalesBills;
import com.store.dal.repos.AdminDAO;
import com.store.dal.repos.BillCodeStoreDAO;
import com.store.dal.repos.DrugsDAO;
import com.store.dal.repos.LostDrugsDAO;
import com.store.dal.repos.SalesBillsDAO;

public class PhManagementBLL_JDBC {
static Integer x=10;
 int xx= 'c';
    public static void main(String[] args) {
         
     LostDrugsDAO dAO = new LostDrugsDAO();
          LostDrugs s = new LostDrugs();
            s.setDrugName("ankk");
           s.setDrugType("pill");
           s.setQuantityDrug(10);
    LostDrugs add = dAO.add(s);
        System.out.println(add);  
      
    }
    
}
