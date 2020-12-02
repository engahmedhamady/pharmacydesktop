/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;

import appDt.DeliverySalesPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author ahmed
 */
public class SalesFrame extends JPanel implements ActionListener{
      
      JPanel buttonPanel , selectedPanel ;
      JButton  newSales , deliverySales;
       JScrollPane sp; Font font ;
      public SalesFrame()  
      {
                setLayout(new BorderLayout());
                buttonsPanelGui();
                selectedPanelGui();
               deliverySales.addActionListener(this);
               newSales.addActionListener(this);
      }
      private void buttonsPanelGui ()
      {
           
                buttonPanel  = new JPanel(new GridLayout(1, 3));
                sp = new JScrollPane(buttonPanel);
                buttonPanel.setPreferredSize(new Dimension(1000, 100));
               newSales  = new JButton("NEW SALES");
               newSales.setFont(font);
               newSales.setForeground(Color.red);
               newSales.setBackground(Color.blue);
               deliverySales = new JButton("DELIVERY LIST");
               deliverySales.setBackground(Color.blue);
               deliverySales.setForeground(Color.red);
               deliverySales.setFont(font);
               buttonPanel.add(newSales );
               buttonPanel.add(deliverySales );
              add(buttonPanel,BorderLayout.NORTH);
      }
      private void selectedPanelGui ()
      {
              selectedPanel = new  JPanel(new CardLayout());
              selectedPanel.setPreferredSize(new  Dimension(200, 800));
              selectedPanel.setBackground(Color.CYAN);
              add(selectedPanel,BorderLayout.CENTER);
      }
      @Override
      public void actionPerformed(ActionEvent e) 
      {
               if (e.getSource()==newSales)
               {
                        selectedPanel.removeAll();
                        selectedPanel.repaint();
                       selectedPanel.revalidate();
                       selectedPanel.add( new NewSalesPanel());
                       salesFrameButtonControl(false, true);
              }
              if (e.getSource()==deliverySales)
              {    
                      selectedPanel.removeAll();
                      selectedPanel.repaint();
                      selectedPanel.revalidate();
                      selectedPanel.add( new DeliverySalesPanel());
                      salesFrameButtonControl(true, false);
             }
    
    } 
      public void  salesFrameButtonControl (boolean a , boolean b)
      {
               if (a)
               {
                        newSales.setEnabled(a);
                        deliverySales.setEnabled(b);
                        newSales.setBackground(Color.blue);
                        deliverySales.setBackground(Color.green);
               }
               if(b)
               {
                       newSales.setEnabled(a);
                       deliverySales.setEnabled(b);
                       newSales.setBackground(Color.GREEN);
                       deliverySales.setBackground(Color.blue);
               }
   }     
}