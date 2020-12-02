/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author ahmed
 */
public class PurchasesPanel extends JPanel implements ActionListener{
      
      JPanel buttonPanel , selectedPanel, SalesPanel,billPanel, customerPanel,lostPanel ;
      JButton foundedButton ,lostButton , billButtons,customerButton;
       JScrollPane sp;
      public PurchasesPanel()  
      {
            setLayout(new BorderLayout());
             buttonsPanelGui();
             selectedPanelGui();
           
            
           
            foundedButton.addActionListener(this);
           lostButton.addActionListener(this);
           customerButton.addActionListener(this);
           billButtons.addActionListener(this);
      }
      //----------------------------------------------------------------------------------------------------------------------------------------------------
      private void buttonsPanelGui ()
      {
      
            buttonPanel  = new JPanel(new GridLayout(1, 4));
            sp = new JScrollPane(buttonPanel);
            buttonPanel.setPreferredSize(new Dimension(1000, 100));
            //buttonPanel.setMaximumSize(new Dimension(2000, 200));
            foundedButton = new JButton("FOUNDED");
            foundedButton.setBackground(Color.blue);
           lostButton  = new JButton("REQUESTED");
           lostButton.setBackground(Color.blue);
            customerButton = new JButton("CUSTOMERS");
             customerButton.setBackground(Color.blue);
            billButtons = new JButton("NEW BILL");
            billButtons.setBackground(Color.blue);
            buttonPanel.add(billButtons );
            buttonPanel.add(foundedButton );
            buttonPanel.add(lostButton );
            buttonPanel.add(customerButton);
            add(buttonPanel,BorderLayout.NORTH);
      }
      //----------------------------------------------------------------------------------------------------------------------------------------------------
      private void selectedPanelGui ()
      {
      
          selectedPanel = new  JPanel(new CardLayout());
            selectedPanel.setPreferredSize(new  Dimension(200, 800));
            
            selectedPanel.setBackground(Color.cyan);
            add(selectedPanel,BorderLayout.CENTER);
      
      }
      //-------------------------------------------------------------------------------------------------------------------------------------------------------
      private  void  deliverySalesCardGui (){}
      //-------------------------------------------------------------------------------------------------------------------------------------------------------
       private  void  newsalesCardGui (){
        SalesPanel.add(new NewSalesPanel());
            
      
      
      }
      
//---------------------------------------------------------------------------------------------------------------------------------------------------------------
      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource()==foundedButton){
                 selectedPanel.removeAll();
                 selectedPanel.repaint();
                 selectedPanel.revalidate();
                  purchaseFrameButtonControl(true, false, false, false);
                  try {
                        selectedPanel.add( new FoundedPurchasesPanel());
                  } catch (SQLException ex) {
                        Logger.getLogger(PurchasesPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
                   
            }
             if (e.getSource()==lostButton){
                selectedPanel.removeAll();
                 selectedPanel.repaint();
                 selectedPanel.revalidate();
                   purchaseFrameButtonControl(false, true, false, false);
                  try {
                        selectedPanel.add( new RequestedPurchasesPanel());
                  } catch (SQLException ex) {
                        Logger.getLogger(PurchasesPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
            }
              if (e.getSource()==billButtons){
                  try {
                        selectedPanel.removeAll();
                        selectedPanel.repaint();
                        selectedPanel.revalidate();
                        selectedPanel.add( new NewPurchasePanel());
                          purchaseFrameButtonControl(false, false, false, true);
                  } 
                  catch (ParseException ex)
                  {
                        JOptionPane.showMessageDialog(null, "bill not saved");            
                  }
                
            }
               if (e.getSource()==customerButton){
                  try {
                        selectedPanel.removeAll();
                        selectedPanel.repaint();
                        selectedPanel.revalidate();
                        selectedPanel.add( new CustomerPanel());
                        purchaseFrameButtonControl(false, false, true, false);
                  } catch (SQLException ex) {
                        Logger.getLogger(PurchasesPanel.class.getName()).log(Level.SEVERE, null, ex);
                  }
                
            }
           
            
      }
        public void  purchaseFrameButtonControl (boolean a , boolean b,boolean c,boolean d)
      {
               if (a)
               {
                     foundedButton.setEnabled(!a);
                     lostButton.setEnabled(a);
                      customerButton.setEnabled(a);
                     billButtons.setEnabled(a);
                     billButtons.setBackground(Color.blue);
                     customerButton.setBackground(Color.blue);
                    lostButton.setBackground(Color.blue);
                     foundedButton.setBackground(Color.green);
               }
               if(b)
               {
                      foundedButton.setEnabled(b);
                     lostButton.setEnabled(!b);
                      customerButton.setEnabled(b);
                     billButtons.setEnabled(b);
                     billButtons.setBackground(Color.blue);
                     customerButton.setBackground(Color.blue);
                    lostButton.setBackground(Color.green);
                     foundedButton.setBackground(Color.blue);
               }
                if(c)
               {
                      foundedButton.setEnabled(c);
                     lostButton.setEnabled(c);
                      customerButton.setEnabled(!c);
                     billButtons.setEnabled(c);
                     billButtons.setBackground(Color.blue);
                     customerButton.setBackground(Color.green);
                    lostButton.setBackground(Color.blue);
                     foundedButton.setBackground(Color.blue);
               }
                if(d)
               {
                      foundedButton.setEnabled(d);
                     lostButton.setEnabled(d);
                      customerButton.setEnabled(d);
                     billButtons.setEnabled(!d);
                     billButtons.setBackground(Color.green);
                     customerButton.setBackground(Color.blue);
                    lostButton.setBackground(Color.blue);
                     foundedButton.setBackground(Color.blue);
               }
          
      }
      
      
      
}
