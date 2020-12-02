package com.store.dal.repos;

import com.store.dal.entities.Drugs;
import com.store.dal.manager.DBConnectionManager;
import com.store.dal.myGenerics.repos.commonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DrugsDAO implements commonDAO<Drugs> {

    @Override
    public Drugs add(Drugs d) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("insert into drugs ( barcode ,Name , Type , purchasing_Price , selling_price , discount ,Quantity , profit  , Company  )  VALUES (?,?,?,?,?,?,?,?,?);");
            stmt.setString(1, d.getBarcode());
            stmt.setString(2, d.getName());
            stmt.setString(3, d.getType());
            stmt.setInt(4, d.getPurchasingPrice());
            stmt.setInt(5, d.getSellingPrice());
            stmt.setInt(6, d.getDiscount());
            stmt.setInt(7, d.getQuantity());
            stmt.setInt(8, d.getProfit());
            stmt.setString(9, d.getCompany());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0) {
                return d;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;

        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public Drugs update(Drugs d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int totalpayment = 0;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("update  drugs  set  quantity = ? ,  selling_price=?,profit=? , discount =? where  name  = ? ");
            stmt.setInt(1, d.getQuantity());
            stmt.setInt(2, d.getSellingPrice());
            stmt.setInt(3, d.getProfit());
              stmt.setInt(4, d.getDiscount());
            stmt.setString(5, d.getName());

            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0) {
                return d;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
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
        Drugs d = (Drugs) billCode;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("delete from Drugs where name = ?");
            stmt.setString(1, d.getName());
            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
    }

    public List<Drugs> findList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drugs> l = new ArrayList<>();

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs");

            rs = stmt.executeQuery();
            while (rs.next()) {
                Drugs drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                l.add(drugs);
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

    public List<Drugs> findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drugs> l = new ArrayList<>();

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs where name=?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Drugs drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                l.add(drugs);
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

    public List<Drugs> findByType(String type) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drugs> l = new ArrayList<>();

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs where type =?");
            stmt.setString(1, type);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Drugs drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                l.add(drugs);
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

    public List<Drugs> findByCompanye(String company) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drugs> l = new ArrayList<>();

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs where company=?");
            stmt.setString(1, company);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Drugs drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                l.add(drugs);
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

    public List<Drugs> findByPrice(int from, int to) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drugs> l = new ArrayList<>();

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs where Selling_price between ? and ?");
            stmt.setInt(1, from);
            stmt.setInt(2, to);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Drugs drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                l.add(drugs);
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

    public List<Drugs> findByQuantity(int from, int to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drugs> l = new ArrayList<>();

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs where Quantity between ? and ?");
            stmt.setInt(1, from);
            stmt.setInt(2, to);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Drugs drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                l.add(drugs);
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

    @Override
    public Drugs findById(Object id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Drugs drugs = null;
        Drugs d= (Drugs) id;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs where Name =?");
            stmt.setString(1,  d.getName());
            rs = stmt.executeQuery();
            if (rs.next()) {
                drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                return drugs;
            } else {
                return null;
            }
        } catch (Exception ex) {
                 ex.printStackTrace();
            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }

    public List<Drugs> findByDiscount(int from, int to) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Drugs> l = new ArrayList<>();

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from Drugs where Discount between ? and ?");
            stmt.setInt(1, from);
            stmt.setInt(2, to);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Drugs drugs = new Drugs();
                drugs.setBarcode(rs.getString("barcode"));
                drugs.setCompany(rs.getString("Company"));
                drugs.setDiscount(rs.getInt("Discount"));
                drugs.setName(rs.getString("Name"));
                drugs.setProfit(rs.getInt("profit"));
                drugs.setPurchasingPrice(rs.getInt("purchasing_Price"));
                drugs.setQuantity(rs.getInt("Quantity"));
                drugs.setSellingPrice(rs.getInt("Selling_Price"));
                drugs.setType(rs.getString("Type"));

                l.add(drugs);
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
