/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.dal.repos;

import com.store.dal.entities.BillCodeStore;
import com.store.dal.manager.DBConnectionManager;
import com.store.dal.myGenerics.repos.commonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillCodeStoreDAO implements commonDAO<BillCodeStore> {

    @Override

    public BillCodeStore add(BillCodeStore billCodeStore) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("insert   into bill_code_store  (code )  VALUES (?);");
            stmt.setInt(1, billCodeStore.getCode());

            stmt.executeUpdate();
            return billCodeStore;
        } catch (Exception ex) {

            return null;
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }

    @Override
    public BillCodeStore update(BillCodeStore billCodeStore) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("update  bill_code_store  set  code= ?   where  code  = ? ");
            stmt.setInt(1, billCodeStore.getCode());
            stmt.setInt(2, billCodeStore.getCode());
            int executeUpdate = stmt.executeUpdate();
            if (executeUpdate >= 0) {
                return billCodeStore;
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
    public void remove(Object num) {
               Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
   BillCodeStore store =     (BillCodeStore) num;
     int n=store.getCode();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("DELETE  FROM bill_code_store  WHERE code = ?   ");
            stmt.setInt(1, n);
                   int executeUpdate = stmt.executeUpdate();
                   System.out.println(executeUpdate);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }

    }

    @Override
    public BillCodeStore findById(Object num) {
          Connection conn = null;
        PreparedStatement stmt = null;
        BillCodeStore codeStore =null;
        ResultSet rs = null;
      
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from bill_code_store where code =?");
            stmt.setInt(0, (int)num);
            rs = stmt.executeQuery();
            while (rs.next()) {
                codeStore = new  BillCodeStore();
               codeStore.setCode(rs.getInt("code"));
                    
            }
            return codeStore;
        } catch (Exception ex) {
           return null;

        } finally {
            DBConnectionManager.closeResultSet(rs);
            DBConnectionManager.closeStatement(stmt);
            DBConnectionManager.closeConnection(conn);
        }
    }

    @Override
    public List<BillCodeStore> findList() {
          Connection conn = null;
        PreparedStatement stmt = null;
        BillCodeStore codeStore =null;
        ResultSet rs = null;
       List<BillCodeStore> l = new ArrayList();
        try {
            conn = DBConnectionManager.getConnection();
            stmt = conn.prepareStatement("select * from bill_code_store where 1");
           
         
            rs = stmt.executeQuery();
            while (rs.next()) {
                codeStore = new  BillCodeStore();
               codeStore.setCode(rs.getInt("code"));
                l.add(codeStore);
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

    public void patchRemove(List<BillCodeStore> billCodeStores) {

    }
}
