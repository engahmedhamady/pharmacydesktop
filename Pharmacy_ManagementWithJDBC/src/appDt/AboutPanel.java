/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**
 *
 * @author ahmed
 */
public class AboutPanel  implements ActionListener
{
      JDialog about ;
      JButton ok ;
      JLabel text ;
      

      public AboutPanel()
              
      {
            HomePage.frame.setEnabled(false);
            ok = new JButton("ok");
            ok.setPreferredSize(new Dimension(100, 100));
            text =new JLabel();
            text.setText("");
           about = new JDialog();
           about.setPreferredSize(new Dimension(300, 300));
           about.setBackground(Color.LIGHT_GRAY);
               // set search panel as grouplayout
             GroupLayout groupLayout = new GroupLayout( about);   
             groupLayout.setAutoCreateGaps(true);  
             groupLayout.setAutoCreateContainerGaps(true);  
             about.setLayout(groupLayout);
               // organize component in group layout   
             groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(200)
             .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(text)).addGap(50)
             .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(ok)).addGap(70) );  
  
             groupLayout.setVerticalGroup(groupLayout.createSequentialGroup() 
            .addGroup(groupLayout.createParallelGroup(BASELINE)
            .addGap(100).addComponent(text)
            .addGap(100).addComponent(ok))); 
             about.addWindowListener(
                     new WindowAdapter() 
                     {

                                @Override
                                public void windowClosing(WindowEvent e)
                                {
                                          about.dispose();
                                         HomePage.frame.setEnabled(true);
                                }});
            about.setVisible(true);
            
            
      }

      @Override
      public void actionPerformed(ActionEvent e) {
            if (e.getSource()==ok)
            {
                     HomePage.frame.setVisible(true);
            
            
            }
            }
      
      
}
