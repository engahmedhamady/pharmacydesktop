package com.store.bll.impl;

import com.store.bll.managers.ReportsManager;
import com.store.bll.transformers.PurchasesBillsTransformer;
import com.store.bll.transformers.SalesBillsTransformer;
import com.store.common.beans.PurchasesBillsBean;
import com.store.common.beans.SalesBillsBean;
import com.store.dal.entities.PurchasesBills;
import com.store.dal.entities.SalesBills;
import com.store.dal.repos.PurchasesBillsDAO;
import com.store.dal.repos.SalesBillsDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sroor For Laptop
 */
public class ReportsManagerImpl implements ReportsManager {

    @Override
    public List<SalesBillsBean> listAllSales() {
        List<SalesBillsBean> l = null;
        try {

            SalesBillsDAO salesBillsDAO = new SalesBillsDAO();
            SalesBillsTransformer transformer = new SalesBillsTransformer();
            List<SalesBills> list = salesBillsDAO.findList();
            if (list != null) {
                l = new ArrayList<>();
                for (SalesBills d : list) {

                    l.add(transformer.transformEntityToBean(d));
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;

    }

    @Override
    public List<SalesBillsBean> findByCodeSales(int billCode) {
        List<SalesBillsBean> l = null;
        try {

            SalesBillsDAO salesBillsDAO = new SalesBillsDAO();
            SalesBillsTransformer transformer = new SalesBillsTransformer();
            SalesBills findById = salesBillsDAO.findById(billCode);

            if (findById != null) {
                l = new ArrayList<>();
                l.add(transformer.transformEntityToBean(findById));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<SalesBillsBean> findByDateSales(Date from, Date to) {
        List<SalesBillsBean> l = null;
        try {

            SalesBillsDAO salesBillsDAO = new SalesBillsDAO();
            SalesBillsTransformer transformer = new SalesBillsTransformer();
            List<SalesBills> list = salesBillsDAO.findByDate(from, to);

            if (list != null) {
                l = new ArrayList<>();
                for (SalesBills d : list) {

                    SalesBillsBean bean = new SalesBillsBean();
                    bean.setBillcode(d.getBillCode());
                    bean.setBilldate(d.getBillDate());
                    bean.setTotalnet(d.getTotalnet());
                    bean.setTotalprofits(d.getTotalProfits());

                    l.add(bean);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<SalesBillsBean> findByTotalSales(int from, int to) {
        List<SalesBillsBean> l = null;
        try {
            ;
            SalesBillsDAO salesBillsDAO = new SalesBillsDAO();
            SalesBillsTransformer transformer = new SalesBillsTransformer();
            List<SalesBills> list = salesBillsDAO.findByTotal(from, to);
            if (list != null) {
                l = new ArrayList<>();
                for (SalesBills d : list) {
                    SalesBillsBean bean = new SalesBillsBean();
                    bean.setBillcode(d.getBillCode());
                    bean.setBilldate(d.getBillDate());
                    bean.setTotalnet(d.getTotalnet());
                    bean.setTotalprofits(d.getTotalProfits());

                    l.add(bean);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<SalesBillsBean> findByProfitSales(int from, int to) {
        List<SalesBillsBean> l = null;
        try {

            SalesBillsDAO salesBillsDAO = new SalesBillsDAO();
            SalesBillsTransformer transformer = new SalesBillsTransformer();
            List<SalesBills> list = salesBillsDAO.findByProfit(from, to);

            if (list != null) {
                l = new ArrayList<>();
                for (SalesBills d : list) {

                    SalesBillsBean bean = new SalesBillsBean();
                    bean.setBillcode(d.getBillCode());
                    bean.setBilldate(d.getBillDate());
                    bean.setTotalnet(d.getTotalnet());
                    bean.setTotalprofits(d.getTotalProfits());

                    l.add(bean);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }
//=====================================================================================

    @Override
    public List<PurchasesBillsBean> listAllPurchases() {
        List<PurchasesBillsBean> l = null;
        try {

            PurchasesBillsDAO purchasesBillsDAO = new PurchasesBillsDAO();
            PurchasesBillsTransformer transformer = new PurchasesBillsTransformer();
            List<PurchasesBills> list = purchasesBillsDAO.findList();

            if (list != null) {
                l = new ArrayList<>();
                for (PurchasesBills d : list) {

                    l.add(transformer.transformEntityToBean(d));
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<PurchasesBillsBean> findByCodePurchases(int billCode) {
        List<PurchasesBillsBean> l = null;
        try {

            PurchasesBillsDAO purchasesBillsDAO = new PurchasesBillsDAO();
            PurchasesBillsTransformer transformer = new PurchasesBillsTransformer();
            PurchasesBills findById = purchasesBillsDAO.findById(billCode);
            List<PurchasesBills> list = null;
            if (findById != null) {
                list = new ArrayList<>();
                list.add(findById);
            }

            if (list != null) {
                l = new ArrayList<>();
                for (PurchasesBills d : list) {

                    l.add(transformer.transformEntityToBean(d));
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<PurchasesBillsBean> findByDatePurchases(Date from, Date to) {
        List<PurchasesBillsBean> l = null;
        try {

            PurchasesBillsDAO purchasesBillsDAO = new PurchasesBillsDAO();
            PurchasesBillsTransformer transformer = new PurchasesBillsTransformer();
            List<PurchasesBills> list = purchasesBillsDAO.findByDate(from, to);

            if (list != null) {
                l = new ArrayList<>();
                for (PurchasesBills d : list) {

                    l.add(transformer.transformEntityToBean(d));
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }

    @Override
    public List<PurchasesBillsBean> findByTotalPurchases(int from, int to) {
        List<PurchasesBillsBean> l = null;
        try {

            PurchasesBillsDAO purchasesBillsDAO = new PurchasesBillsDAO();
            PurchasesBillsTransformer transformer = new PurchasesBillsTransformer();
            List<PurchasesBills> list = purchasesBillsDAO.findByTotalPurchases(from, to);

            if (list != null) {
                l = new ArrayList<>();
                for (PurchasesBills d : list) {

                    l.add(transformer.transformEntityToBean(d));
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return l;
    }
//-------------------------------------------------------------------------------

    @Override
    public int incom(Date from, Date to) {

        List<SalesBillsBean> findByDateSales = findByDateSales(from, to);
        if (findByDateSales != null && findByDateSales.size() > 0) {
            int total = 0;
            for (SalesBillsBean findByDateSale : findByDateSales) {
                total += findByDateSale.getTotalnet();
            }
            return total;

        } else {
            return 0;
        }
    }

    @Override
    public int payment(Date from, Date to) {
        List<PurchasesBillsBean> findByDateSales = findByDatePurchases(from, to);
        if (findByDateSales != null && findByDateSales.size() > 0) {
            int total = 0;
            for (PurchasesBillsBean findByDateSale : findByDateSales) {
                total += findByDateSale.getTotal();
            }
            return total;

        } else {
            return 0;
        }

    }

    @Override
    public int profits(Date from, Date to) {
        List<SalesBillsBean> findByDateSales = findByDateSales(from, to);
        if (findByDateSales != null && findByDateSales.size() > 0) {
            int total = 0;
            for (SalesBillsBean findByDateSale : findByDateSales) {
                total += findByDateSale.getTotalprofits();
            }
            return total;

        } else {
            return 0;
        }
    }

}
