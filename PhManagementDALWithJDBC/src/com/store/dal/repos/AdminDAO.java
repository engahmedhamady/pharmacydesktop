
package com.store.dal.repos;

import com.store.dal.entities.Admin;
import com.store.dal.manager.DBConnectionManager;

import com.store.dal.myGenerics.repos.commonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class AdminDAO implements commonDAO<Admin> {
// insert 

    @Override
    public Admin add(Admin d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("insert   into admin  (name ,password )  VALUES (?,?);");
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getPassword());
            stmt.executeUpdate();
            return d;
        } catch (Exception ex) {

            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }

    @Override
    public Admin update(Admin d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("UPDATE    admin  SET  password = ?   WHERE  name = ?");

            stmt.setString(1, d.getPassword());
            stmt.setString(2, d.getName());
            stmt.executeUpdate();
            return d;
        } catch (Exception ex) {
            return null;

        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }
// delete

    @Override
    public void remove(Object name) {
        Admin admin = (Admin) name;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("DELETE  FROM `admin` WHERE name = ?   ");
            stmt.setString(1, admin.getName());

            stmt.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }
// find by name

    @Override
    public Admin findById(Object name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        Admin admin = null;
        ResultSet rs = null;
        List l = new ArrayList();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from admin where name =?");
            stmt.setString(1, (String)name);
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                admin.setName(rs.getString("Name"));
                    admin.setName(rs.getString("Password"));
            }
            return admin;
        } catch (Exception ex) {
           return null;

        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

     
    }

    public Admin find(Admin admin) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Admin ad =null;
        try {
           
            conn = DBConnectionManager.getConnection();
            
            stmt = conn.prepareStatement("select * from admin where Name=? and Password=?");
            stmt.setString(1, admin.getName());
             stmt.setString(2, admin.getPassword());
            rs = stmt.executeQuery();
            while (rs.next()) {
              ad= new Admin();
              ad.setName(rs.getString("Name"));
                ad.setPassword(rs.getString("Password"));
            }
            return ad;
        } catch (Exception ex) {
           return null;

        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

      
    }

    @Override
    public List<Admin> findList() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List l = new ArrayList();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select name from admin where 1");
            rs = stmt.executeQuery();
            while (rs.next()) {

                l.add(new Admin(rs.getString("Name")));
            }
             return l;
        } catch (Exception ex) {
           return null;

        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

        
    }

    public void patchRemove(List<Admin> admins) {

    }

}
