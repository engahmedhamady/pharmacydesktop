/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.AdminBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


/**
 *
 * @author ahmed
 */
public class LoginPanel extends JPanel implements CaretListener {

    public static boolean logined = false;
    static String word, password;
    JPanel mainPanel, centerPanel;
 
    JLabel passWordLabel, userNameLabel, backGroundWest, backGroundEast;
    static JTextField userNameArea;
    static JPasswordField passWordArea;
    ImageIcon image1, image2;
    HomePage hp ;
   StoreGetWay getWay = new StoreGetWay();
    public LoginPanel() {
        image1 = new ImageIcon("1.png");
        image2 = new ImageIcon("2.png");
        backGroundWest = new JLabel("", image1, JLabel.LEFT);
        backGroundEast = new JLabel("", image2, JLabel.RIGHT);
        passWordLabel = new JLabel("PassWord");

        passWordLabel.setPreferredSize(new Dimension(100, 100));
        userNameLabel = new JLabel("UserName");
        userNameLabel.setBackground(Color.GREEN);

        userNameLabel.setSize(new Dimension(100, 50));
        userNameLabel.setBackground(Color.lightGray);
        userNameArea = new JTextField();
        userNameArea.addCaretListener(this);
        userNameArea.setMaximumSize(new Dimension(300, 50));
        userNameArea.setPreferredSize(new Dimension(200, 50));
        userNameArea.setMinimumSize(new Dimension(200, 50));
        passWordArea = new JPasswordField();
        passWordArea.addCaretListener(this);
        passWordArea.setMaximumSize(new Dimension(300, 50));
        passWordArea.setPreferredSize(new Dimension(200, 50));
        passWordArea.setMinimumSize(new Dimension(200, 50));
        centerPanel = new JPanel();
        centerPanel.setBackground(Color.white);
        GroupLayout groupLayout = new GroupLayout(centerPanel);
        centerPanel.setBackground(Color.CYAN);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        centerPanel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(90)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(100).addComponent(userNameLabel).addGap(20).addComponent(passWordLabel))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(userNameArea).addComponent(passWordArea)));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGap(250)
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100).addComponent(userNameLabel)
                        .addGap(50).addComponent(userNameArea))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(passWordLabel).addComponent(passWordArea)));

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(1000, 1000));
        add(backGroundWest, BorderLayout.WEST);
        add(backGroundEast, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);

    }

    public  void testLogIn() {
        word = userNameArea.getText().trim();
        password = String.valueOf(passWordArea.getPassword());
        AdminBean adminBean =  new AdminBean();
        adminBean.setName(word);
        adminBean.setPassword(password);
        if (word != null && password != null && !word.equals("") && !password.equals("")) {
          
            if (getWay.login(adminBean)) {
                HomePage.lowerPanel.removeAll();
                HomePage.lowerPanel.repaint();
                HomePage.lowerPanel.revalidate();
                logined = true;
                HomePage.doLogin();
                word = null;
                password = null;
            } else {
                userNameArea.setText("");
                passWordArea.setText("");
                LoginPanel.logined = false;
                JOptionPane.showMessageDialog(null, " enter correct username and password");

                word = null;
                password = null;
            }

        } else if (password.equals("") && word.equals("")) {

            word = null;
            password = null;
            JOptionPane.showMessageDialog(null, " enter username and password");

        } else if (word.equals("")) {

            passWordArea.setText("");
            word = null;
            password = null;
            JOptionPane.showMessageDialog(null, " enter username ");

        } else if (password.equals("")) {

            word = null;
            password = null;
            JOptionPane.showMessageDialog(null, " enter password");

        }

    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == userNameArea) {
            // word=null;
            // word = UsernameArea.getText().trim();
        }
        if (e.getSource() == passWordArea) {   // password= null;
            // password = String.valueOf(PasswordArea.getPassword());
        }
    }

}
