package com.store.dal.repos;

import com.store.dal.entities.Customer;
import com.store.dal.manager.DBConnectionManager;
import com.store.dal.myGenerics.repos.commonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CustomerDAO implements commonDAO<Customer> {

    @Override
    public Customer add(Customer customer) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("insert   into  customer  (name , email , phone  )  VALUES (?,?,?) ;");
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPhone());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0) {
                return customer;
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
    public Customer update(Customer customer) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionManager.getConnection();

            stmt = conn.prepareStatement("update  CUSTOMER set  email= ? ,phone =?    where  name  = ? ");

            stmt.setString(1, customer.getEmail());
            stmt.setString(2, customer.getPhone());
            stmt.setString(3, customer.getName());

            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate > 0) {
                return customer;
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
    public void remove(Object name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
      Customer c = (Customer) name;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("delete from customer where ID =?");
            stmt.setInt(1, c.getID());
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
    public Customer findById(Object ID) {
           Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
       Customer customer =null;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from customer where id=?");
            stmt.setInt(1, (int)ID);
            rs = stmt.executeQuery();
           
            while (rs.next()) {

                customer = new Customer(rs.getInt("ID"),rs.getString("Name"), rs.getString("Email"), rs.getString("Phone"));
               
            }
        } catch (Exception ex) {

          return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
        return customer;
    }

    @Override
    public List<Customer> findList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Customer> l = null; // Fadyaaaaaaaaa

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from customer where 1");
            rs = stmt.executeQuery();
            l = new ArrayList<>();
            while (rs.next()) {
                l.add(new Customer(rs.getInt("ID"), rs.getString("Name"), rs.getString("Email"), rs.getString("Phone")));
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

    public void patchRemove(List<Customer> customers) {
        if (customers == null) {

            return;
        }
        for (Customer customer : customers) {
            remove(customer.getName());
        }

    }

    public List<Customer> findByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Customer> l = null;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from customer where Name =?");
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            l = new ArrayList<>();
            while (rs.next()) {

                l.add(new Customer(rs.getInt("ID"),rs.getString("Name"), rs.getString("Email"), rs.getString("Phone")));

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
