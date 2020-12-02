package com.store.dal.repos;

import com.store.dal.entities.PurchasesBills;
import com.store.dal.manager.DBConnectionManager;
import com.store.dal.myGenerics.repos.commonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchasesBillsDAO implements commonDAO<PurchasesBills> {

    @Override
    public PurchasesBills add(PurchasesBills pb) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
             java.sql.Date sqldate = new java.sql.Date(new java.util.Date().getTime());
             conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("INSERT INTO `purchases_bills`(`Billcode`, `Barcode`, `Name`, `Type`, `Purchasing_Price`, `Selling_price`, `Discount`, `Quantity`, `total`, `Company`, `DateBill`) VALUES (?,?,?,?,?,?,?,?,?,?,?);");
            stmt.setInt(1, pb.getBillcode());
            stmt.setString(2, pb.getBarcode());
            stmt.setString(3, pb.getName());
            stmt.setString(4, pb.getType());
            stmt.setInt(5, pb.getPurchasingPrice());
            stmt.setInt(6, pb.getSellingPrice());
            stmt.setInt(7, pb.getDiscount());
            stmt.setInt(8, pb.getQuantity());
            stmt.setInt(9, pb.getTotal());
            stmt.setString(10, pb.getCompany());
            stmt.setDate(11, sqldate);
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0) {
                return pb;
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
    public PurchasesBills update(PurchasesBills pb) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            // java.sql.Date sqldate = new java.sql.Date(pb.getExpiry().getTime());
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("UPDATE `purchases_bills` SET `Barcode`=?,`Name`=? ,`Type`=?,`Purchasing_Price`=?,`Selling_price`=?,`Discount`=?,`Quantity`=?,`total`=?,`Company`=?,`DateBill`=? WHERE `Billcode`=?");

            stmt.setString(1, pb.getBarcode());
            stmt.setString(2, pb.getName());
            stmt.setString(3, pb.getType());
            stmt.setInt(4, pb.getPurchasingPrice());
            stmt.setInt(5, pb.getSellingPrice());
            stmt.setInt(6, pb.getDiscount());
            stmt.setInt(7, pb.getQuantity());
            stmt.setInt(8, pb.getTotal());
            stmt.setString(9, pb.getCompany());
            stmt.setDate(10, (java.sql.Date) pb.getDateBill());
            stmt.setInt(11, pb.getBillcode());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0) {
                return pb;
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
        PurchasesBills bills = (PurchasesBills) billCode;
        try {
            // java.sql.Date sqldate = new java.sql.Date(pb.getExpiry().getTime());
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("DELETE FROM `purchases_bills` WHERE Billcode =?");

            stmt.setInt(1, bills.getBillcode());
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
    public PurchasesBills findById(Object billCode) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PurchasesBills bills = null;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from  purchases_bills  where Billcode =? ");
            stmt.setInt(1, (int) billCode);
            rs = stmt.executeQuery();
            if (rs.next()) {
                bills = new PurchasesBills();
                bills.setBarcode(rs.getString("Barcode"));
                bills.setBillcode(rs.getInt("BillCode"));
                bills.setCompany(rs.getString("company"));
                bills.setDateBill(rs.getDate("DateBill"));
                bills.setDiscount(rs.getInt("Discount"));
                bills.setName(rs.getString("name"));
                bills.setPurchasingPrice(rs.getInt("Purchasing_Price"));
                bills.setQuantity(rs.getInt("quantity"));
                bills.setSellingPrice(rs.getInt("Selling_price"));
                bills.setTotal(rs.getInt("total"));
                bills.setType(rs.getString("type"));
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
    public List<PurchasesBills> findList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PurchasesBills bills = null;
        List<PurchasesBills> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from  purchases_bills  where 1 ");

            rs = stmt.executeQuery();
            while (rs.next()) {
                bills = new PurchasesBills();
                bills.setBarcode(rs.getString("Barcode"));
                bills.setBillcode(rs.getInt("BillCode"));
                bills.setCompany(rs.getString("company"));
                bills.setDateBill(rs.getDate("DateBill"));
                bills.setDiscount(rs.getInt("Discount"));
                bills.setName(rs.getString("name"));
                bills.setPurchasingPrice(rs.getInt("Purchasing_Price"));
                bills.setQuantity(rs.getInt("quantity"));
                bills.setSellingPrice(rs.getInt("Selling_price"));
                bills.setTotal(rs.getInt("total"));
                bills.setType(rs.getString("type"));
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

    public void patchRemove(List<PurchasesBills> purchasesBillses) {
        if (purchasesBillses == null) {

            return;
        }
        for (PurchasesBills purchasesBills : purchasesBillses) {
            remove(purchasesBills.getBillcode());
        }

    }

    public List<PurchasesBills> findByTotalPurchases(int from, int to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PurchasesBills bills = null;
        List<PurchasesBills> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from  purchases_bills  where total between ? and ?");
             stmt.setInt(1, from);
              stmt.setInt(2, to);
            rs = stmt.executeQuery();
            while (rs.next()) {
                bills = new PurchasesBills();
                bills.setBarcode(rs.getString("Barcode"));
                bills.setBillcode(rs.getInt("BillCode"));
                bills.setCompany(rs.getString("company"));
                bills.setDateBill(rs.getDate("DateBill"));
                bills.setDiscount(rs.getInt("Discount"));
                bills.setName(rs.getString("name"));
                bills.setPurchasingPrice(rs.getInt("Purchasing_Price"));
                bills.setQuantity(rs.getInt("quantity"));
                bills.setSellingPrice(rs.getInt("Selling_price"));
                bills.setTotal(rs.getInt("total"));
                bills.setType(rs.getString("type"));
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

    public List<PurchasesBills> findByDate(Date from, Date to) 
            {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PurchasesBills bills = null;
        java.sql.Date fromSqldate = new java.sql.Date(from.getTime());
        java.sql.Date toSqldate = new java.sql.Date(to.getTime());
       
        List<PurchasesBills> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from  purchases_bills  WHERE DateBill >= "+fromSqldate +"<="+toSqldate);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                bills = new PurchasesBills();
                bills.setBarcode(rs.getString("Barcode"));
                bills.setBillcode(rs.getInt("BillCode"));
                bills.setCompany(rs.getString("company"));
                bills.setDateBill(rs.getDate("DateBill"));
                bills.setDiscount(rs.getInt("Discount"));
                bills.setName(rs.getString("name"));
                bills.setPurchasingPrice(rs.getInt("Purchasing_Price"));
                bills.setQuantity(rs.getInt("quantity"));
                bills.setSellingPrice(rs.getInt("Selling_price"));
                bills.setTotal(rs.getInt("total"));
                bills.setType(rs.getString("type"));
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
}
