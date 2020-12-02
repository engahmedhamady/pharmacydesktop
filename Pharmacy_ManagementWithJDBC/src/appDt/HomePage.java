 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;

import com.store.bll.delegate.StoreGetWay;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author ahmed
 */
public class HomePage  implements ActionListener{
      
    static   JButton sales, discount , login , purchases,about,logout,reports, setting , search , exit;
      static JPanel higherPanel ,lowerPanel; 

      LoginPanel loginPanel = new LoginPanel();
      public static JFrame frame ;
     
      
      public HomePage ()
      {
            frame = new JFrame();
        frame. setPreferredSize(new Dimension(1000, 1000));
      //___________________________________________________________higherpanel_________________________________________________________________
         higherPanel = new JPanel(new GridLayout(10,1,0,0));
         sales = new RoundButton("Sales");
         purchases = new RoundButton("Purchases");
         search = new RoundButton("Search");
         discount = new RoundButton("Discount");
              
         reports = new RoundButton("Reports");
         setting = new RoundButton("Settings");
         logout = new RoundButton("LOGOUT");
          login = new RoundButton("LOGIN");
          exit = new RoundButton("EXIT");
          about = new RoundButton("ABOUT");
         higherPanel.add(sales); 
         higherPanel.add(purchases);
         higherPanel.add(search);
         higherPanel.add(discount);
        
         higherPanel.add(reports);
         higherPanel.add(setting);
         higherPanel.add(exit);
         higherPanel.add(about);
         higherPanel.add(logout);
           higherPanel.add(login);
           
         higherPanel.setBackground(Color.CYAN);
         higherPanel.setPreferredSize(new Dimension(100, 0));
   //______________________________________________lower panel______________________________________________________________________
           lowerPanel = new JPanel(new CardLayout( 20,20));
           lowerPanel.setPreferredSize(new  Dimension(500, 500));
           lowerPanel.setBackground(Color.CYAN);
           lowerPanel.add(new LoginPanel());
//______________________________________________________main frame  ____________________________________________     
         frame.setLayout(new BorderLayout());
         frame.add(higherPanel,BorderLayout.WEST);
         frame.add(lowerPanel,BorderLayout.CENTER);
         frame.setBackground(Color.GREEN);
  //-------------------------------------------------------------------------add actionlistener-----------------------------------------------------------
        discount.addActionListener(this);
      
        logout.addActionListener(this);
        login.addActionListener(this);
        purchases.addActionListener(this);
        reports.addActionListener(this);
        sales.addActionListener(this);
        search.addActionListener(this);
        setting.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);
        doLogin();
         frame.setSize(new Dimension(1400, 700));
           
          
           frame.setVisible(true);
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           
      }
//--------------------------------------------------action performed method-----------------------------------------------------------------------------------
      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource()==sales) {
                 lowerPanel.removeAll();
                 lowerPanel.repaint();
                 lowerPanel.revalidate();
                 lowerPanel.add( new SalesFrame());
                 homePageButtonControl(false, true, true, true, true, true, true, true, true);
            }
            if (e.getSource()==purchases) {
                 lowerPanel.removeAll();
                 lowerPanel.repaint();
                 lowerPanel.revalidate();
                 lowerPanel.add( new PurchasesPanel());
                   homePageButtonControl(true, false, true, true, true, true, true, true, true);
            }
            if (e.getSource()==search) {
                lowerPanel.removeAll();
                lowerPanel.repaint();
                lowerPanel.revalidate();
                  try {   
                        lowerPanel.add( new SearchPanel());
                  } catch (SQLException ex) {
                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    homePageButtonControl(true, true, false, true, true, true, true, true, true);
            }
            if (e.getSource()==discount) {
                   lowerPanel.removeAll();
                   lowerPanel.repaint();
                   lowerPanel.revalidate();
                  try {
                        lowerPanel.add( new DiscountPanel());
                  } catch (SQLException ex) {
                        Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    homePageButtonControl(true, true, true, false, true, true, true, true, true);
            }

              if (e.getSource()==reports) {
                   lowerPanel.removeAll();
                   lowerPanel.repaint();
                   lowerPanel.revalidate();
                   lowerPanel.add( new ReportsPanel());
                     homePageButtonControl(true, true, true, true, true, false, true, true, true);
            }
            if (e.getSource()==setting) {
                   lowerPanel.removeAll();
                   lowerPanel.repaint();
                   lowerPanel.revalidate();
                   lowerPanel.add( new Setting());
                   homePageButtonControl(true, true, true, true, true, true, false, true, true);
            }
            if (e.getSource()==exit) {
                  lowerPanel.removeAll();
                   lowerPanel.repaint();
                   lowerPanel.revalidate(); 
                     homePageButtonControl(true, true, true, true, true, true, true, false, true);
            }
              if (e.getSource()==logout) {
                   loginPanel.logined = false;
                   lowerPanel.removeAll();
                   lowerPanel.repaint();
                   lowerPanel.revalidate();
                   lowerPanel.add( new LoginPanel());
                   doLogin();
            }
            if (e.getSource()==login) {
                  
                   loginPanel.testLogIn();
                   
            }
             if (e.getSource()== about) {
                   homePageButtonControl(true, true, true, true, false, true, true, true, true);
                 //   new AboutPanel();
            
                  
                 
                   
            }
          
           
      }
       public   static void doLogin( )
       {
       login.setEnabled(!LoginPanel.logined); 
       sales.setEnabled(LoginPanel.logined); 
       discount.setEnabled(LoginPanel.logined);
       purchases.setEnabled(LoginPanel.logined);
       
       logout.setEnabled(LoginPanel.logined); 
       reports.setEnabled(LoginPanel.logined); 
       setting.setEnabled(LoginPanel.logined);  
       search.setEnabled(LoginPanel.logined);
       exit.setEnabled(LoginPanel.logined);
       about.setEnabled(LoginPanel.logined);
       }
       public   static void homePageButtonControl(boolean a,boolean b,boolean c,boolean d,boolean e,boolean f,boolean g,boolean h,boolean i)
       {
             sales.setEnabled(a); 
            purchases.setEnabled(b);
             search.setEnabled(c);
           discount.setEnabled(d);
              about.setEnabled(e); 
            reports.setEnabled(f); 
          setting.setEnabled(g);  
          exit.setEnabled(h);
          logout.setEnabled(i); 
          about.setEnabled(e); 
      
       
       }
    
      
}
