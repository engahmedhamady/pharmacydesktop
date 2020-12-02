package com.store.dal.repos;

import com.store.dal.entities.LostDrugs;
import com.store.dal.manager.DBConnectionManager;
import com.store.dal.myGenerics.repos.commonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LostDrugsDAO implements commonDAO<LostDrugs> {

    @Override
    public LostDrugs add(LostDrugs ld) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
                   conn= DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("insert into  lost_drugs  (Drug_Name , Drug_Type , Quantity_Drug  )  VALUES (?,?,?) ;");

            stmt.setString(1, ld.getDrugName());
            stmt.setString(2, ld.getDrugType());
            stmt.setInt(3, ld.getQuantityDrug());
            if (stmt.executeUpdate() > 0) {
                return ld;
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
    public LostDrugs update(LostDrugs ld) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
               conn= DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("update  lost_drugs set  Quantity_Drug	= ?    where  Drug_Name = ? ");
            stmt.setInt(1, ld.getQuantityDrug());
            stmt.setString(2, ld.getDrugName());
            if (stmt.executeUpdate() > 0) {
                return ld;
            } else {
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }

    @Override
    public void remove(Object name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        LostDrugs drugs = (LostDrugs) name ;
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("delete from lost_drugs where Drug_Name  =?");
            stmt.setString(1, drugs.getDrugName());
             stmt.executeUpdate();
        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public LostDrugs findById(Object name) {
      
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            LostDrugs l = null;
            try {
            
                conn = DBConnectionManager.getConnection();
              
                stmt = conn.prepareStatement("select * from lost_drugs where Drug_Name =?");
                stmt.setString(1, (String) name);
                
                rs = stmt.executeQuery();
               
               if (rs.next()) {
                   
                    l=new LostDrugs(rs.getString("Drug_name"),
                            rs.getString("Drug_type"), rs.getInt("quantity_drug"));
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

    @Override
    public List<LostDrugs> findList() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LostDrugs> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from lost_drugs ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                l.add(new LostDrugs(rs.getString("Drug_name"),
                        rs.getString("Drug_type"), rs.getInt("quantity_drug")));
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

    public void patchRemove(List<LostDrugs> lostDrugses) {
        if (lostDrugses == null) {

            return;
        }
        for (LostDrugs lostDrugs : lostDrugses) {
            remove(lostDrugs.getDrugName());
        }

    }

    public List<LostDrugs> findByName(String name) {
        {

            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            List<LostDrugs> l = null;
            try {
            
                conn = DBConnectionManager.getConnection();
                l = new ArrayList<>();
                stmt = conn.prepareStatement("select * from lost_drugs where Drug_Name =?");
                stmt.setString(1, name);
                
                rs = stmt.executeQuery();
               
                while (rs.next()) {
                   
                    l.add(new LostDrugs(rs.getString("Drug_name"),
                            rs.getString("Drug_type"), rs.getInt("quantity_drug")));
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

    public List<LostDrugs> findByType(String type) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LostDrugs> l = new ArrayList<>();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from lost_drugs where Drug_Type=?");
            stmt.setString(1, type);
            rs = stmt.executeQuery();
            while (rs.next()) {
                l.add(new LostDrugs(rs.getString("Drug_name"),
                        rs.getString("Drug_type"), rs.getInt("quantity_drug")));
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
