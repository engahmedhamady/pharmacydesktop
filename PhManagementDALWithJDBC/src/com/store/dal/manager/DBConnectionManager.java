/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.dal.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmed
 */
public class DBConnectionManager {
      
    //   private static final String IP = "localhost";
    //private static final String PORT = "1521";
    //private static final String DB_NAME = "XE";
   // private static final String USERNAME = "store_mn";
    // private static final String Password = "store_mn";
    
    
       public static Connection getConnection() throws Exception
       {  
           System.out.println("111");
               Class.forName("com.mysql.cj.jdbc.Driver");   
               System.out.println("111");
           //  Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + IP + ":" + PORT + ":" + DB_NAME, USERNAME, Password);
               Connection   conn = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/pharmacy_management?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
                return conn; 
       } 
       
        public static void closeConnection(Connection conn) 
        {
                if (conn == null)
                {
                      return;
                }
                try 
                {
                     conn.close();
                } 
                catch (SQLException ex)
                {
                     ex.printStackTrace();
                }
         }
       public  static  void closeStatement (PreparedStatement stmt)
       {
                 if (stmt==null)
                 {
                        return;
                 }
                 try
                 {
                       stmt.close();
                 } catch (SQLException ex)
                 {
                         ex.printStackTrace();
                 }
       
       }
       public static void closeResultSet(ResultSet rs) 
       {
               if (rs == null)
               {
                      return;
               }
               try
               {
                     rs.close();
               } 
               catch (SQLException ex) 
               {
                     ex.printStackTrace();
               }
       }
}
