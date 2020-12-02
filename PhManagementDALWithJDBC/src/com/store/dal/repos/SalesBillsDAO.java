package com.store.dal.repos;

import com.store.dal.entities.DeliveryBills;
import com.store.dal.entities.SalesBills;
import com.store.dal.manager.DBConnectionManager;

import com.store.dal.myGenerics.repos.commonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesBillsDAO implements commonDAO<SalesBills> {

    @Override
    public SalesBills add(SalesBills deliveryBills) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
     
        java.sql.Date sqldate = new java.sql.Date(new java.util.Date().getTime());
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("insert into sales_bills  (Bill_Code ,Bill_Date ,Drug1 ,unit_price1 ,Quantity1 ,total1 ,discount1 ,net1 ,profit1 ,Drug2,unit_price2,Quantity2 ,total2 ,discount2 ,net2 ,profit2 ,Drug3	, unit_price3 ,	Quantity3 ,total3 ,discount3 , net3 , profit3 ,	Drug4 ,	unit_price4 ,Quantity4 ,total4,	discount4,net4,profit4	,Drug5 ,unit_price5 ,Quantity5,	total5,	discount5,net5,	profit5,Totalnet,totalProfits	)  "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            stmt.setInt(1, deliveryBills.getBillCode());
            stmt.setDate(2, sqldate);
            stmt.setString(3, deliveryBills.getDrug1());
            System.out.println("date");
            stmt.setInt(4, deliveryBills.getUnitPrice1());
            stmt.setInt(5, deliveryBills.getQuantity1());
            stmt.setInt(6, deliveryBills.getTotal1());
            stmt.setInt(7, deliveryBills.getDiscount1());
            stmt.setInt(8, deliveryBills.getNet1());
            stmt.setInt(9, deliveryBills.getProfit1());
            stmt.setString(10, deliveryBills.getDrug2());
            stmt.setInt(11, deliveryBills.getUnitPrice2());
            stmt.setInt(12, deliveryBills.getQuantity2());
            stmt.setInt(13, deliveryBills.getTotal2());
            stmt.setInt(14, deliveryBills.getDiscount2());
            stmt.setInt(15, deliveryBills.getNet2());
            stmt.setInt(16, deliveryBills.getProfit2());
            stmt.setString(17, deliveryBills.getDrug3());
            stmt.setInt(18, deliveryBills.getUnitPrice3());
            stmt.setInt(19, deliveryBills.getQuantity3());
            stmt.setInt(20, deliveryBills.getTotal3());
            stmt.setInt(21, deliveryBills.getDiscount3());
            stmt.setInt(22, deliveryBills.getNet3());
            stmt.setInt(23, deliveryBills.getProfit3());
            stmt.setString(24, deliveryBills.getDrug4());
            stmt.setInt(25, deliveryBills.getUnitPrice4());
            stmt.setInt(26, deliveryBills.getQuantity4());
            stmt.setInt(27, deliveryBills.getTotal4());
            stmt.setInt(28, deliveryBills.getDiscount4());
            stmt.setInt(29, deliveryBills.getNet4());
            stmt.setInt(30, deliveryBills.getProfit4());
            stmt.setString(31, deliveryBills.getDrug5());
            stmt.setInt(32, deliveryBills.getUnitPrice5());
            stmt.setInt(33, deliveryBills.getQuantity5());
            stmt.setInt(34, deliveryBills.getTotal5());
            stmt.setInt(35, deliveryBills.getDiscount5());
            stmt.setInt(36, deliveryBills.getNet5());
            stmt.setInt(37, deliveryBills.getProfit5());
            stmt.setInt(38, deliveryBills.getTotalnet());
            stmt.setInt(39, deliveryBills.getTotalProfits());
            int executeUpdate = stmt.executeUpdate();
            System.out.println(executeUpdate);
            if (executeUpdate > 0) {
                return deliveryBills;
            } else {
                System.out.println("c");
                return null;
            }
        } catch (Exception ex) {
            System.out.println("d");
            ex.printStackTrace();
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }

    @Override
    public SalesBills update(SalesBills deliveryBills) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("UPDATE `sales_bills` SET `Drug1`=?,`unit_price1`=?,`Quantity1`=?,`total1`=?,`discount1`=?,`net1`=?,`profit1`=?,`Drug2`=?,"
                    + "`unit_price2`=?,`Quantity2`=?,`total2`=?,`discount2`=?,`net2`=?,`profit2`=?,`Drug3`=?,`unit_price3`=?,`Quantity3`=?,`total3`=?,"
                    + "`discount3`=?,`net3`=?,`profit3`=?,`Drug4`=?,`unit_price4`=?,`Quantity4`=?,`total4`=?,`discount4`=?,`net4`=?,`profit4`=?,"
                    + "`Drug5`=?,`unit_price5`=?,`Quantity5`=?,`total5`=?,`discount5`=?,`net5`=?,`profit5`=?,`Totalnet`=?,`totalProfits`=? WHERE `Bill_Code`=?");

            stmt.setString(1, deliveryBills.getDrug1());
            stmt.setInt(2, deliveryBills.getUnitPrice1());
            stmt.setInt(3, deliveryBills.getQuantity1());
            stmt.setInt(4, deliveryBills.getTotal1());
            stmt.setInt(5, deliveryBills.getDiscount1());
            stmt.setInt(6, deliveryBills.getNet1());
            stmt.setInt(7, deliveryBills.getProfit1());
            stmt.setString(8, deliveryBills.getDrug2());
            stmt.setInt(9, deliveryBills.getUnitPrice2());
            stmt.setInt(10, deliveryBills.getQuantity2());
            stmt.setInt(11, deliveryBills.getTotal2());
            stmt.setInt(12, deliveryBills.getDiscount2());
            stmt.setInt(13, deliveryBills.getNet2());
            stmt.setInt(14, deliveryBills.getProfit2());
            stmt.setString(15, deliveryBills.getDrug3());
            stmt.setInt(16, deliveryBills.getUnitPrice3());
            stmt.setInt(17, deliveryBills.getQuantity3());
            stmt.setInt(18, deliveryBills.getTotal3());
            stmt.setInt(19, deliveryBills.getDiscount3());
            stmt.setInt(20, deliveryBills.getNet3());
            stmt.setInt(21, deliveryBills.getProfit3());
            stmt.setString(22, deliveryBills.getDrug4());
            stmt.setInt(23, deliveryBills.getUnitPrice4());
            stmt.setInt(24, deliveryBills.getQuantity4());
            stmt.setInt(25, deliveryBills.getTotal4());
            stmt.setInt(26, deliveryBills.getDiscount4());
            stmt.setInt(27, deliveryBills.getNet4());
            stmt.setInt(28, deliveryBills.getProfit4());
            stmt.setString(29, deliveryBills.getDrug5());
            stmt.setInt(30, deliveryBills.getUnitPrice5());
            stmt.setInt(31, deliveryBills.getQuantity5());
            stmt.setInt(32, deliveryBills.getTotal5());
            stmt.setInt(33, deliveryBills.getDiscount5());
            stmt.setInt(34, deliveryBills.getNet5());
            stmt.setInt(35, deliveryBills.getProfit5());
            stmt.setInt(36, deliveryBills.getTotalnet());
            stmt.setInt(37, deliveryBills.getTotalProfits());
            stmt.setInt(38, deliveryBills.getBillCode());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0) {
                return deliveryBills;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public void remove(Object billCode) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SalesBills sb = (SalesBills) billCode;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("DELETE  FROM `sales_bills` WHERE bill_code = ?   ");
            stmt.setInt(1, sb.getBillCode());
            int executeUpdate = stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public SalesBills findById(Object billCode) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SalesBills bills = null;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM `sales_bills` WHERE bill_code = ? ");
            stmt.setInt(1, (int) billCode);
            rs = stmt.executeQuery();
            if (rs.next()) {
                bills = new SalesBills();
                bills.setBillCode(rs.getInt("Bill_Code"));
                bills.setBillDate(rs.getDate("Bill_Date"));
                bills.setDrug1(rs.getString("Drug1"));
                bills.setUnitPrice1(rs.getInt("unit_price1"));
                bills.setQuantity1(rs.getInt("Quantity1"));
                bills.setTotal1(rs.getInt("total1"));
                bills.setDiscount1(rs.getInt("discount1"));
                bills.setNet1(rs.getInt("net1"));
                bills.setProfit1(rs.getInt("profit1"));
                bills.setDrug2(rs.getString("Drug2"));
                bills.setUnitPrice2(rs.getInt("unit_price2"));
                bills.setQuantity2(rs.getInt("Quantity2"));
                bills.setTotal2(rs.getInt("total2"));
                bills.setDiscount2(rs.getInt("discount2"));
                bills.setNet2(rs.getInt("net2"));
                bills.setProfit2(rs.getInt("profit2"));
                bills.setDrug3(rs.getString("Drug3"));
                bills.setUnitPrice3(rs.getInt("unit_price3"));
                bills.setQuantity3(rs.getInt("Quantity3"));
                bills.setTotal3(rs.getInt("total3"));
                bills.setDiscount3(rs.getInt("discount3"));
                bills.setNet3(rs.getInt("net3"));
                bills.setProfit3(rs.getInt("profit3"));
                bills.setDrug4(rs.getString("Drug4"));
                bills.setUnitPrice4(rs.getInt("unit_price4"));
                bills.setQuantity4(rs.getInt("Quantity4"));
                bills.setTotal4(rs.getInt("total4"));
                bills.setDiscount4(rs.getInt("discount4"));
                bills.setNet4(rs.getInt("net4"));
                bills.setProfit4(rs.getInt("profit4"));
                bills.setDrug5(rs.getString("Drug5"));
                bills.setUnitPrice5(rs.getInt("unit_price5"));
                bills.setQuantity5(rs.getInt("Quantity5"));
                bills.setTotal5(rs.getInt("total5"));
                bills.setDiscount5(rs.getInt("discount5"));
                bills.setNet5(rs.getInt("net5"));
                bills.setProfit5(rs.getInt("profit5"));
                bills.setTotalProfits(rs.getInt("totalProfits"));
                bills.setTotalnet(rs.getInt("Totalnet"));
                return bills;
            } else {
                return null;
            }
        } catch (Exception ex) {
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public List<SalesBills> findList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SalesBills bills = null;
        List<SalesBills> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM `sales_bills` WHERE  1 ");

            rs = stmt.executeQuery();
            while (rs.next()) {
                bills = new SalesBills();
                bills.setBillCode(rs.getInt("Bill_Code"));
                bills.setBillDate(rs.getDate("Bill_Date"));
                bills.setDrug1(rs.getString("Drug1"));
                bills.setUnitPrice1(rs.getInt("unit_price1"));
                bills.setQuantity1(rs.getInt("Quantity1"));
                bills.setTotal1(rs.getInt("total1"));
                bills.setDiscount1(rs.getInt("discount1"));
                bills.setNet1(rs.getInt("net1"));
                bills.setProfit1(rs.getInt("profit1"));
                bills.setDrug2(rs.getString("Drug2"));
                bills.setUnitPrice2(rs.getInt("unit_price2"));
                bills.setQuantity2(rs.getInt("Quantity2"));
                bills.setTotal2(rs.getInt("total2"));
                bills.setDiscount2(rs.getInt("discount2"));
                bills.setNet2(rs.getInt("net2"));
                bills.setProfit2(rs.getInt("profit2"));
                bills.setDrug3(rs.getString("Drug3"));
                bills.setUnitPrice3(rs.getInt("unit_price3"));
                bills.setQuantity3(rs.getInt("Quantity3"));
                bills.setTotal3(rs.getInt("total3"));
                bills.setDiscount3(rs.getInt("discount3"));
                bills.setNet3(rs.getInt("net3"));
                bills.setProfit3(rs.getInt("profit3"));
                bills.setDrug4(rs.getString("Drug4"));
                bills.setUnitPrice4(rs.getInt("unit_price4"));
                bills.setQuantity4(rs.getInt("Quantity4"));
                bills.setTotal4(rs.getInt("total4"));
                bills.setDiscount4(rs.getInt("discount4"));
                bills.setNet4(rs.getInt("net4"));
                bills.setProfit4(rs.getInt("profit4"));
                bills.setDrug5(rs.getString("Drug5"));
                bills.setUnitPrice5(rs.getInt("unit_price5"));
                bills.setQuantity5(rs.getInt("Quantity5"));
                bills.setTotal5(rs.getInt("total5"));
                bills.setDiscount5(rs.getInt("discount5"));
                bills.setNet5(rs.getInt("net5"));
                bills.setProfit5(rs.getInt("profit5"));
                bills.setTotalProfits(rs.getInt("totalProfits"));
                bills.setTotalnet(rs.getInt("Totalnet"));
                l.add(bills);

            }

        } catch (Exception ex) {
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
        return l;
    }

    public void patchRemove(List<SalesBills> salesBillses) {
        if (salesBillses == null) {

            return;
        }
        for (SalesBills salesBills : salesBillses) {
            remove(salesBills.getBillCode());
        }

    }

    public List<SalesBills> findByTotal(int from, int to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SalesBills bills = null;
        List<SalesBills> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM `sales_bills` WHERE Totalnet BETWEEN ? AND ? ");
            stmt.setInt(1, from);
            stmt.setInt(2, to);
            rs = stmt.executeQuery();
            while (rs.next()) {
                bills = new SalesBills();
                bills.setBillCode(rs.getInt("Bill_Code"));
                bills.setBillDate(rs.getDate("Bill_Date"));
                bills.setDrug1(rs.getString("Drug1"));
                bills.setUnitPrice1(rs.getInt("unit_price1"));
                bills.setQuantity1(rs.getInt("Quantity1"));
                bills.setTotal1(rs.getInt("total1"));
                bills.setDiscount1(rs.getInt("discount1"));
                bills.setNet1(rs.getInt("net1"));
                bills.setProfit1(rs.getInt("profit1"));
                bills.setDrug2(rs.getString("Drug2"));
                bills.setUnitPrice2(rs.getInt("unit_price2"));
                bills.setQuantity2(rs.getInt("Quantity2"));
                bills.setTotal2(rs.getInt("total2"));
                bills.setDiscount2(rs.getInt("discount2"));
                bills.setNet2(rs.getInt("net2"));
                bills.setProfit2(rs.getInt("profit2"));
                bills.setDrug3(rs.getString("Drug3"));
                bills.setUnitPrice3(rs.getInt("unit_price3"));
                bills.setQuantity3(rs.getInt("Quantity3"));
                bills.setTotal3(rs.getInt("total3"));
                bills.setDiscount3(rs.getInt("discount3"));
                bills.setNet3(rs.getInt("net3"));
                bills.setProfit3(rs.getInt("profit3"));
                bills.setDrug4(rs.getString("Drug4"));
                bills.setUnitPrice4(rs.getInt("unit_price4"));
                bills.setQuantity4(rs.getInt("Quantity4"));
                bills.setTotal4(rs.getInt("total4"));
                bills.setDiscount4(rs.getInt("discount4"));
                bills.setNet4(rs.getInt("net4"));
                bills.setProfit4(rs.getInt("profit4"));
                bills.setDrug5(rs.getString("Drug5"));
                bills.setUnitPrice5(rs.getInt("unit_price5"));
                bills.setQuantity5(rs.getInt("Quantity5"));
                bills.setTotal5(rs.getInt("total5"));
                bills.setDiscount5(rs.getInt("discount5"));
                bills.setNet5(rs.getInt("net5"));
                bills.setProfit5(rs.getInt("profit5"));
                bills.setTotalProfits(rs.getInt("totalProfits"));
                bills.setTotalnet(rs.getInt("Totalnet"));
                l.add(bills);
            }
        } catch (Exception ex) {
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
        return l;
    }

    public List<SalesBills> findByDate(Date from, Date to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SalesBills bills = null;
        List<SalesBills> l = new ArrayList<>();
        java.sql.Date fromSqldate = new java.sql.Date(from.getTime());
        java.sql.Date toSqldate = new java.sql.Date(to.getTime());
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM `sales_bills` WHERE Bill_Date >= "+fromSqldate +"<="+toSqldate );
          
            rs = stmt.executeQuery();
            while (rs.next()) {
                bills = new SalesBills();
                bills.setBillCode(rs.getInt("Bill_Code"));
                bills.setBillDate(rs.getDate("Bill_Date"));
                bills.setDrug1(rs.getString("Drug1"));
                bills.setUnitPrice1(rs.getInt("unit_price1"));
                bills.setQuantity1(rs.getInt("Quantity1"));
                bills.setTotal1(rs.getInt("total1"));
                bills.setDiscount1(rs.getInt("discount1"));
                bills.setNet1(rs.getInt("net1"));
                bills.setProfit1(rs.getInt("profit1"));
                bills.setDrug2(rs.getString("Drug2"));
                bills.setUnitPrice2(rs.getInt("unit_price2"));
                bills.setQuantity2(rs.getInt("Quantity2"));
                bills.setTotal2(rs.getInt("total2"));
                bills.setDiscount2(rs.getInt("discount2"));
                bills.setNet2(rs.getInt("net2"));
                bills.setProfit2(rs.getInt("profit2"));
                bills.setDrug3(rs.getString("Drug3"));
                bills.setUnitPrice3(rs.getInt("unit_price3"));
                bills.setQuantity3(rs.getInt("Quantity3"));
                bills.setTotal3(rs.getInt("total3"));
                bills.setDiscount3(rs.getInt("discount3"));
                bills.setNet3(rs.getInt("net3"));
                bills.setProfit3(rs.getInt("profit3"));
                bills.setDrug4(rs.getString("Drug4"));
                bills.setUnitPrice4(rs.getInt("unit_price4"));
                bills.setQuantity4(rs.getInt("Quantity4"));
                bills.setTotal4(rs.getInt("total4"));
                bills.setDiscount4(rs.getInt("discount4"));
                bills.setNet4(rs.getInt("net4"));
                bills.setProfit4(rs.getInt("profit4"));
                bills.setDrug5(rs.getString("Drug5"));
                bills.setUnitPrice5(rs.getInt("unit_price5"));
                bills.setQuantity5(rs.getInt("Quantity5"));
                bills.setTotal5(rs.getInt("total5"));
                bills.setDiscount5(rs.getInt("discount5"));
                bills.setNet5(rs.getInt("net5"));
                bills.setProfit5(rs.getInt("profit5"));
                bills.setTotalProfits(rs.getInt("totalProfits"));
                bills.setTotalnet(rs.getInt("Totalnet"));
                l.add(bills);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
        return l;
    }

    public List<SalesBills> findByProfit(int from, int to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        SalesBills bills = null;
        List<SalesBills> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM `sales_bills` WHERE totalProfits BETWEEN ? AND ? ");
            stmt.setInt(1, from);
            stmt.setInt(2, to);
            rs = stmt.executeQuery();
            while (rs.next()) {
                bills = new SalesBills();
                bills.setBillCode(rs.getInt("Bill_Code"));
                bills.setBillDate(rs.getDate("Bill_Date"));
                bills.setDrug1(rs.getString("Drug1"));
                bills.setUnitPrice1(rs.getInt("unit_price1"));
                bills.setQuantity1(rs.getInt("Quantity1"));
                bills.setTotal1(rs.getInt("total1"));
                bills.setDiscount1(rs.getInt("discount1"));
                bills.setNet1(rs.getInt("net1"));
                bills.setProfit1(rs.getInt("profit1"));
                bills.setDrug2(rs.getString("Drug2"));
                bills.setUnitPrice2(rs.getInt("unit_price2"));
                bills.setQuantity2(rs.getInt("Quantity2"));
                bills.setTotal2(rs.getInt("total2"));
                bills.setDiscount2(rs.getInt("discount2"));
                bills.setNet2(rs.getInt("net2"));
                bills.setProfit2(rs.getInt("profit2"));
                bills.setDrug3(rs.getString("Drug3"));
                bills.setUnitPrice3(rs.getInt("unit_price3"));
                bills.setQuantity3(rs.getInt("Quantity3"));
                bills.setTotal3(rs.getInt("total3"));
                bills.setDiscount3(rs.getInt("discount3"));
                bills.setNet3(rs.getInt("net3"));
                bills.setProfit3(rs.getInt("profit3"));
                bills.setDrug4(rs.getString("Drug4"));
                bills.setUnitPrice4(rs.getInt("unit_price4"));
                bills.setQuantity4(rs.getInt("Quantity4"));
                bills.setTotal4(rs.getInt("total4"));
                bills.setDiscount4(rs.getInt("discount4"));
                bills.setNet4(rs.getInt("net4"));
                bills.setProfit4(rs.getInt("profit4"));
                bills.setDrug5(rs.getString("Drug5"));
                bills.setUnitPrice5(rs.getInt("unit_price5"));
                bills.setQuantity5(rs.getInt("Quantity5"));
                bills.setTotal5(rs.getInt("total5"));
                bills.setDiscount5(rs.getInt("discount5"));
                bills.setNet5(rs.getInt("net5"));
                bills.setProfit5(rs.getInt("profit5"));
                bills.setTotalProfits(rs.getInt("totalProfits"));
                bills.setTotalnet(rs.getInt("Totalnet"));
                l.add(bills);
            }
        } catch (Exception ex) {
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
        return l;
    }

}
