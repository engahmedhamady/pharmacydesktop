
package com.store.bll.impl;

import com.store.bll.managers.PurchasesManager;
import com.store.bll.transformers.BillCosdeStoreTransformer;
import com.store.bll.transformers.CustomerTransformer;
import com.store.bll.transformers.DrugsTransformer;
import com.store.bll.transformers.LostDrugsTransformer;
import com.store.bll.transformers.PurchasesBillsTransformer;
import com.store.common.beans.BillCodeStoreBean;
import com.store.common.beans.CustomerBean;
import com.store.common.beans.DrugsBean;
import com.store.common.beans.LostDrugsBean;
import com.store.common.beans.PurchasesBillsBean;
import com.store.dal.entities.BillCodeStore;
import com.store.dal.entities.Customer;
import com.store.dal.entities.Drugs;
import com.store.dal.entities.LostDrugs;
import com.store.dal.repos.BillCodeStoreDAO;
import com.store.dal.repos.CustomerDAO;
import com.store.dal.repos.DrugsDAO;
import com.store.dal.repos.LostDrugsDAO;
import com.store.dal.repos.PurchasesBillsDAO;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sroor For Laptop
 */
public class PurchasesManagerImpl implements PurchasesManager {
// c

    @Override
    public int createBill() {
        try {
          
            BillCodeStoreDAO DAO = new BillCodeStoreDAO();
            BillCosdeStoreTransformer transformer = new BillCosdeStoreTransformer();
            List<BillCodeStore> findList = DAO.findList();
            List<BillCodeStoreBean> list = new ArrayList<>();
            for (BillCodeStore billCodeStore : findList) {
                list.add(transformer.transformEntityToBean(billCodeStore));
            }
      
            if (list.size() > 0) {

                return list.get(0).getCode();
            } else {
                return 0;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return 0;
        }

    }
//c

    @Override
    public PurchasesBillsBean saveBill(PurchasesBillsBean bean) {
        PurchasesBillsBean add = null;
        try {
       
            PurchasesBillsDAO DAO = new PurchasesBillsDAO();
            PurchasesBillsTransformer transformer = new PurchasesBillsTransformer();
            add = transformer.transformEntityToBean(DAO.add(transformer.transformBeanToEntity(bean)));
            
            DrugsBean bean1 = new DrugsBean();
            if (add != null) {

                int profit = bean.getSellPrice() - bean.getPurchasePrice() - bean.getDiscount();
                bean1.setBarcode(bean.getBarCode());
                bean1.setName(bean.getDrugName());
                bean1.setType(bean.getDrugType());
                bean1.setPurchasingPrice(bean.getPurchasePrice());
                bean1.setSellingPrice(bean.getSellPrice());
                bean1.setDiscount(bean.getDiscount());
                bean1.setQuantity(bean.getQuantityDrug());
                bean1.setProfit(profit);
                bean1.setCompany(bean.getCompany());
                DrugsTransformer transformer1 = new DrugsTransformer();
                DrugsDAO ddao = new DrugsDAO();

                Drugs findById = ddao.findById(transformer1.transformBeanToEntity(bean1));

                if (findById == null) {

                    ddao.add(transformer1.transformBeanToEntity(bean1));

                } else {
                    findById.setQuantity(findById.getQuantity() + bean1.getQuantity());
                    ddao.update(findById);
                }
                BillCodeStoreBean s = new BillCodeStoreBean();
                s.setCode(bean.getBillCode());

                BillCosdeStoreTransformer transformer2 = new BillCosdeStoreTransformer();

                BillCodeStoreDAO bcsdao = new BillCodeStoreDAO();
                bcsdao.remove(transformer2.transformBeanToEntity(s));
                s.setCode(bean.getBillCode() + 1);
                bcsdao.add(transformer2.transformBeanToEntity(s));
            }

            return add;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public LostDrugsBean addRequested(LostDrugsBean bean) {
        try {

            LostDrugsDAO dAO = new LostDrugsDAO();
            LostDrugsTransformer transformer = new LostDrugsTransformer();
            LostDrugs findById = dAO.findById(bean.getDrugname());
         
            if (findById == null) {
                LostDrugs add = dAO.add(transformer.transformBeanToEntity(bean));
                  System.out.println(add);

                return transformer.transformEntityToBean(add);
            } else {

                findById.setQuantityDrug(bean.getQuantitydrug() + findById.getQuantityDrug());
                LostDrugsBean transformEntityToBean = transformer.transformEntityToBean(dAO.update(findById));

                return transformEntityToBean;

            }
        } catch (Exception ex) {

            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<LostDrugsBean> listAllRequested() {
        List<LostDrugsBean> find = null;
        try {
            LostDrugsDAO dAO = new LostDrugsDAO();

            LostDrugsTransformer drugsTransformer = new LostDrugsTransformer();
            List<LostDrugs> findList = dAO.findList();
            if (findList != null) {
                find = new ArrayList();
                for (LostDrugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }

          
        } catch (Exception ex) {
            return null;
        }
        return find;

    }

    @Override
    public List<LostDrugsBean> listByNameRequested(String name) {
        List<LostDrugsBean> find = null;
        try {
          
            LostDrugsDAO dAO = new LostDrugsDAO();

            LostDrugsTransformer drugsTransformer = new LostDrugsTransformer();
            List<LostDrugs> findList = dAO.findByName(name);
            if (findList != null) {
                find = new ArrayList();
                for (LostDrugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }

    
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public List<LostDrugsBean> listByTypeRequested(String type) {
        List<LostDrugsBean> find = null;
        try {
            
            LostDrugsDAO dAO = new LostDrugsDAO();

            LostDrugsTransformer drugsTransformer = new LostDrugsTransformer();
            List<LostDrugs> findList = dAO.findByType(type);
            if (findList != null) {
                find = new ArrayList();
                for (LostDrugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public CustomerBean addCustomer(CustomerBean bean) {
        Customer find = null;
        try {
           
            CustomerDAO dAO = new CustomerDAO();

            CustomerTransformer drugsTransformer = new CustomerTransformer();
            find = dAO.add(drugsTransformer.transformBeanToEntity(bean));

          
            return drugsTransformer.transformEntityToBean(find);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

    }

    @Override
    public List<CustomerBean> listAllCustomer() {
        List<CustomerBean> find = null;
        try {
            
            CustomerDAO dAO = new CustomerDAO();

            CustomerTransformer drugsTransformer = new CustomerTransformer();
            List<Customer> findList = dAO.findList();
            if (findList != null) {
                find = new ArrayList();
                for (Customer drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public List<CustomerBean> listByNameCustomer(String name) {
        List<CustomerBean> find = null;
        try {
           
            CustomerDAO dAO = new CustomerDAO();

            CustomerTransformer drugsTransformer = new CustomerTransformer();
            List<Customer> findList = dAO.findByName(name);
            if (findList != null) {
                find = new ArrayList();
                for (Customer drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }

        
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public CustomerBean deleteCustomer(CustomerBean bean) {
        Customer find = null;
        try {

            CustomerDAO dAO = new CustomerDAO();

            CustomerTransformer drugsTransformer = new CustomerTransformer();
            dAO.remove(drugsTransformer.transformBeanToEntity(bean));
            return drugsTransformer.transformEntityToBean(find);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public CustomerBean updateCustomer(CustomerBean bean) {
        Customer find = null;
        try {
      
            CustomerDAO dAO = new CustomerDAO();

            CustomerTransformer drugsTransformer = new CustomerTransformer();
            find = dAO.update(drugsTransformer.transformBeanToEntity(bean));


            return drugsTransformer.transformEntityToBean(find);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public LostDrugsBean updateDrugRequested(LostDrugsBean bean) {
        LostDrugs find = null;
        try {
           
            LostDrugsDAO dAO = new LostDrugsDAO();

            LostDrugsTransformer drugsTransformer = new LostDrugsTransformer();
            find = dAO.update(drugsTransformer.transformBeanToEntity(bean));
            return drugsTransformer.transformEntityToBean(find);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public DrugsBean deleteDrug(DrugsBean bean) {
        try {

            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            dAO.remove(drugsTransformer.transformBeanToEntity(bean));
            Drugs findById = dAO.findById(drugsTransformer.transformBeanToEntity(bean));

            if (findById == null) {
                return null;
            } else {
                return bean;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public LostDrugsBean deleteLostDrug(LostDrugsBean bean) {
        try {
              
            LostDrugsDAO dAO = new LostDrugsDAO();

            LostDrugsTransformer drugsTransformer = new LostDrugsTransformer();
            dAO.remove(drugsTransformer.transformBeanToEntity(bean));
            LostDrugs findById = dAO.findById(bean.getDrugname());
            if (findById == null) {
                return bean;
            } else {
                return null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DrugsBean> listAllFounded() {
        List<DrugsBean> find = null;
        try {
            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findList();
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public List<DrugsBean> listByNamefounded(String name) {
        List<DrugsBean> find = null;
        try {

            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByName(name);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public List<DrugsBean> listByTypefounded(String type) {
        List<DrugsBean> find = null;
        try {

            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByType(type);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public List<DrugsBean> listByQuantityfounded(int from, int to) {
        List<DrugsBean> find = null;
        try {

            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByQuantity(from, to);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public List<DrugsBean> listByPricefounded(int from, int to) {
        List<DrugsBean> find = null;
        try {

            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByPrice(from, to);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public List<DrugsBean> listByCompanyfounded(String name) {
        List<DrugsBean> find = null;
        try {
            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            List<Drugs> findList = dAO.findByCompanye(name);
            if (findList != null) {
                find = new ArrayList();
                for (Drugs drugs : findList) {

                    find.add(drugsTransformer.transformEntityToBean(drugs));
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return find;
    }

    @Override
    public DrugsBean updateDrug(DrugsBean drugsBean) {
        Drugs find = null;
        try {
            DrugsDAO dAO = new DrugsDAO();

            DrugsTransformer drugsTransformer = new DrugsTransformer();
            find = dAO.update(drugsTransformer.transformBeanToEntity(drugsBean));
            return drugsTransformer.transformEntityToBean(find);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
