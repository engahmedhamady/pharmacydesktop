package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.AdminBean;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

public class Setting extends JPanel implements CaretListener, ActionListener {

    StoreGetWay getWay = new StoreGetWay();
  JButton addaccount, deleteacount, changepassword, viewacounts, addok, deleteok, changeok;
    JPanel north, center, addpanel, deletepanel, changepanel, viewpanel;
    JLabel addPasswordLabel, addUsernameLabel, deletePasswordLabel, deleteUsernameLabel,
            changePasswordLabel, changeUsernameLabel, newchangePasswordLabel;
    public static JTextField addUsernameAreas, deleteUsernameAreas, changeUsernameAreas;
    public static JPasswordField addPasswordArea, deletePasswordArea, changePasswordArea, newchangePasswordArea;
    String[] columnNames = {"UserName"};
    JTable exampleJTable;
    JScrollPane sp;
    DefaultTableModel defTableModel = new DefaultTableModel();
    public static String password, newpassword, username;

    public Setting() {
        super(new BorderLayout());
        north = new JPanel();
        north.setPreferredSize(new Dimension(800, 100));
        north.setLayout(new GridLayout(1, 4));
        addaccount = new JButton("ADD NEW ACCOUNT");
        addaccount.setBackground(Color.blue);
        deleteacount = new JButton("DELETE ACCOUNT");
        deleteacount.setBackground(Color.blue);
        changepassword = new JButton("CHANGE PASSWORD");
        changepassword.setBackground(Color.blue);
        viewacounts = new JButton("VIEW ACCOUNTS");
        viewacounts.setBackground(Color.blue);
        addaccount.addActionListener(this);
        deleteacount.addActionListener(this);
        changepassword.addActionListener(this);
        viewacounts.addActionListener(this);
        north.add(addaccount);
        north.add(deleteacount);
        north.add(changepassword);
        north.add(viewacounts);
        center = new JPanel(new CardLayout());
        center.setBackground(Color.cyan);
        center.setBackground(Color.cyan);
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        setVisible(true);
    }
    //--------------------------------------------------------------------------------------------------------------------------------     

    public JPanel addacountmethod() {
        addpanel = new JPanel();
        addok = new JButton("ADD");
        addok.setBackground(Color.green);
        addok.setPreferredSize(new Dimension(50, 50));
        addPasswordLabel = new JLabel("PassWord");
        addok.addActionListener(this);
        addPasswordLabel.setPreferredSize(new Dimension(100, 100));
        addUsernameLabel = new JLabel("UserName");
        addUsernameLabel.setBackground(Color.GREEN);
        addUsernameLabel.setSize(new Dimension(100, 50));
        addUsernameLabel.setBackground(Color.lightGray);
        addUsernameAreas = new JTextField();
        addUsernameAreas.addCaretListener(this);
        addUsernameAreas.setMaximumSize(new Dimension(300, 50));
        addUsernameAreas.setPreferredSize(new Dimension(200, 50));
        addUsernameAreas.setMinimumSize(new Dimension(200, 50));
        addPasswordArea = new JPasswordField();
        addPasswordArea.addCaretListener(this);
        addPasswordArea.setMaximumSize(new Dimension(300, 50));
        addPasswordArea.setPreferredSize(new Dimension(200, 50));
        addPasswordArea.setMinimumSize(new Dimension(200, 50));
        addpanel.setBackground(Color.white);
        GroupLayout groupLayout = new GroupLayout(addpanel);
        addpanel.setBackground(Color.CYAN);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        addpanel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(350)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(100).addComponent(addUsernameLabel).addGap(20).addComponent(addPasswordLabel))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(addUsernameAreas).addComponent(addPasswordArea).addComponent(addok)));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGap(200)
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100).addComponent(addUsernameLabel)
                        .addGap(50).addComponent(addUsernameAreas))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(addPasswordLabel).addComponent(addPasswordArea))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(addok)));
        addpanel.setPreferredSize(new Dimension(600, 600));
        addpanel.setVisible(true);
        return addpanel;
    }  //end method

    public JPanel deleteacountmethod() {
        deletepanel = new JPanel();
        deleteok = new JButton("DELETE");
        deleteok.setBackground(Color.green);
        deleteok.setPreferredSize(new Dimension(50, 50));
        deleteok.addActionListener(this);
        deletePasswordLabel = new JLabel("PassWord");
        deletePasswordLabel = new JLabel("PassWord");
        deletePasswordLabel.setPreferredSize(new Dimension(100, 100));
        deleteUsernameLabel = new JLabel("UserName");
        deleteUsernameLabel.setBackground(Color.GREEN);
        deleteUsernameLabel.setSize(new Dimension(100, 50));
        deleteUsernameAreas = new JTextField();
        deleteUsernameAreas.addCaretListener(this);
        deleteUsernameAreas.setMaximumSize(new Dimension(300, 50));
        deleteUsernameAreas.setPreferredSize(new Dimension(200, 50));
        deleteUsernameAreas.setMinimumSize(new Dimension(200, 50));
        deletePasswordArea = new JPasswordField();
        deletePasswordArea.addCaretListener(this);
        deletePasswordArea.setMaximumSize(new Dimension(300, 50));
        deletePasswordArea.setPreferredSize(new Dimension(200, 50));
        deletePasswordArea.setMinimumSize(new Dimension(200, 50));
        deletepanel.setBackground(Color.white);
        GroupLayout groupLayout = new GroupLayout(deletepanel);
        deletepanel.setBackground(Color.CYAN);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        deletepanel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(350)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(100).addComponent(deleteUsernameLabel).addGap(20).addComponent(deletePasswordLabel))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(deleteUsernameAreas).addComponent(deletePasswordArea).addComponent(deleteok)));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGap(200)
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100).addComponent(deleteUsernameLabel)
                        .addGap(50).addComponent(deleteUsernameAreas))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(deletePasswordLabel).addComponent(deletePasswordArea))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(deleteok)));
        deletepanel.setPreferredSize(new Dimension(600, 600));
        deletepanel.setVisible(true);
        return deletepanel;
    }// end method 

    public JPanel changepasswordacountmethod() {
        changepanel = new JPanel();
        changeok = new JButton("CHANGE");
        changeok.setBackground(Color.green);
        changeok.setPreferredSize(new Dimension(50, 50));
        changeok.addActionListener(this);
        changePasswordLabel = new JLabel("PassWord");
        changePasswordLabel.setPreferredSize(new Dimension(100, 100));
        newchangePasswordLabel = new JLabel("New PassWord");
        newchangePasswordLabel.setPreferredSize(new Dimension(100, 100));
        changeUsernameLabel = new JLabel("UserName");
        changeUsernameLabel.setBackground(Color.GREEN);
        changeUsernameLabel.setSize(new Dimension(100, 50));
        changeUsernameLabel.setBackground(Color.lightGray);
        changeUsernameAreas = new JTextField();
        changeUsernameAreas.addCaretListener(this);
        changeUsernameAreas.setMaximumSize(new Dimension(300, 50));
        changeUsernameAreas.setPreferredSize(new Dimension(200, 50));
        changeUsernameAreas.setMinimumSize(new Dimension(200, 50));
        changePasswordArea = new JPasswordField();
        changePasswordArea.addCaretListener(this);
        changePasswordArea.setMaximumSize(new Dimension(300, 50));
        changePasswordArea.setPreferredSize(new Dimension(200, 50));
        changePasswordArea.setMinimumSize(new Dimension(200, 50));
        newchangePasswordArea = new JPasswordField();
        newchangePasswordArea.addCaretListener(this);
        newchangePasswordArea.setMaximumSize(new Dimension(300, 50));
        newchangePasswordArea.setPreferredSize(new Dimension(200, 50));
        newchangePasswordArea.setMinimumSize(new Dimension(200, 50));
        changepanel.setBackground(Color.white);
        GroupLayout groupLayout = new GroupLayout(changepanel);
        changepanel.setBackground(Color.cyan);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        changepanel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(350)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(100).addComponent(changeUsernameLabel).addGap(20).addComponent(changePasswordLabel)
                        .addGap(20).addComponent(newchangePasswordLabel))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(changeUsernameAreas).addComponent(changePasswordArea).addComponent(newchangePasswordArea)
                        .addComponent(changeok)));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGap(150)
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100).addComponent(changeUsernameLabel)
                        .addGap(50).addComponent(changeUsernameAreas))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(changePasswordLabel).addComponent(changePasswordArea))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(newchangePasswordLabel).addComponent(newchangePasswordArea))
                .addGroup(groupLayout.createParallelGroup(BASELINE).addGap(100)
                        .addComponent(changeok)));
        changepanel.setPreferredSize(new Dimension(600, 600));
        changepanel.setVisible(true);
        return changepanel;
    }// end method

    public JPanel viewacountmethod() {
        exampleJTable = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        // set jtable as defaultmodel
        exampleJTable.setModel(defTableModel);
        // add table to scorllpane
        sp = new JScrollPane(exampleJTable);
        defTableModel.setRowCount(0);
        // set columns names
        exampleJTable.setBackground(Color.cyan);
        sp.getViewport().setBackground(Color.cyan);
        defTableModel.setColumnIdentifiers(columnNames);
        exampleJTable.getTableHeader().setPreferredSize(new Dimension(sp.getWidth(), 75));// set size header table
        exampleJTable.getTableHeader().setBackground(Color.PINK);// set background header table
        exampleJTable.setBackground(Color.yellow); // set background table 
        exampleJTable.setRowHeight(50);// set row hieght table
        exampleJTable.getTableHeader().setEnabled(false);
        viewpanel = new JPanel(new BorderLayout());
        viewpanel.setBackground(Color.white);
        viewpanel.setBackground(Color.CYAN);
        viewpanel.setPreferredSize(new Dimension(600, 600));
        viewpanel.add(sp, BorderLayout.CENTER);
        viewpanel.setVisible(true);
        List<AdminBean> l = getWay.listAll();

        for (int i = 0; i < l.size(); i++) {
            defTableModel.addRow(new Object[]{l.get(i).getName()});

        }

        return viewpanel;
    }// end method

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == addUsernameAreas) {
        }
        if (e.getSource() == addPasswordArea) {
        }
        if (e.getSource() == deleteUsernameAreas) {
        }
        if (e.getSource() == deletePasswordArea) {
        }
        if (e.getSource() == changeUsernameAreas) {
        }
        if (e.getSource() == changePasswordArea) {
        }
        if (e.getSource() == newchangePasswordArea) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addaccount) {
            center.removeAll();
            center.repaint();
            center.revalidate();
            center.add(addacountmethod());
            settingFrameButtonControl(true, false, false, false);
        }
        if (e.getSource() == deleteacount) {
            center.removeAll();
            center.repaint();
            center.revalidate();
            center.add(deleteacountmethod());
            settingFrameButtonControl(false, true, false, false);
        }
        if (e.getSource() == changepassword) {
            center.removeAll();
            center.repaint();
            center.revalidate();
            center.add(changepasswordacountmethod());
            settingFrameButtonControl(false, false, true, false);
        }
        if (e.getSource() == viewacounts) {
            center.removeAll();
            center.repaint();
            center.revalidate();
            center.add(viewacountmethod());
            settingFrameButtonControl(false, false, false, true);
        }
        if (e.getSource() == addok) {
            username = addUsernameAreas.getText().trim();
            password = String.valueOf(addPasswordArea.getPassword());
            if (!username.equals(null) && !password.equals(null) && !username.equals("") && !password.equals("")) {

                AdminBean admin = new AdminBean();
                admin.setName(username);
                admin.setPassword(password);
              
                if (getWay.login(admin)) {
                    JOptionPane.showMessageDialog(null, " sorry invalid username please choose another name");

                    addUsernameAreas.setText("");
                    addPasswordArea.setText("");
                    username = null;
                    password = null;
                } else {
                     getWay.addAccount(admin);
                    addUsernameAreas.setText("");
                    addPasswordArea.setText("");
                    username = null;
                    password = null;
                    JOptionPane.showMessageDialog(null, "your  account is created .....");
                }

            } else if (password.equals("") && username.equals("")) {
                username = null;
                password = null;
                JOptionPane.showMessageDialog(null, " enter username and password");
            } else if (username.equals("")) {
                addPasswordArea.setText("");
                username = null;
                password = null;
                JOptionPane.showMessageDialog(null, " enter username ");

            } else if (password.equals("")) {

                username = null;
                password = null;
                JOptionPane.showMessageDialog(null, " enter password");
            }

        }

        if (e.getSource() == deleteok) {
            username = deleteUsernameAreas.getText().trim();
            password = String.valueOf(deletePasswordArea.getPassword());
            if (!username.equals(null) && !password.equals(null) && !username.equals("") && !password.equals("")) {

                AdminBean d = new AdminBean();
                d.setName(username);
                d.setPassword(password);
                if (getWay.login(d)) {
                    getWay.deleteAccount(d);
                    deleteUsernameAreas.setText("");
                    deletePasswordArea.setText("");

                    JOptionPane.showMessageDialog(null, "your  account " + username + " is deleted.....");
                    username = null;
                    password = null;
                } else {
                    deleteUsernameAreas.setText("");
                    deletePasswordArea.setText("");

                    JOptionPane.showMessageDialog(null, "FAILED OPERATION TRY AGAIN");
                }

            } else if (password.equals("") && username.equals("")) {
                username = null;
                password = null;
                JOptionPane.showMessageDialog(null, " enter  username and password");
            } else if (username.equals("")) {
                deletePasswordArea.setText("");
                username = null;
                password = null;
                JOptionPane.showMessageDialog(null, " enter username ");
            } else if (password.equals("")) {
                username = null;
                password = null;
                JOptionPane.showMessageDialog(null, " enter password");
            }

        }
        if (e.getSource() == changeok) {

            username = changeUsernameAreas.getText().trim();
            password = String.valueOf(changePasswordArea.getPassword());
            newpassword = String.valueOf(newchangePasswordArea.getPassword());
            if (!username.equals(null) && !password.equals(null) && !newpassword.equals(null) && !username.equals("") && !password.equals("") && !newpassword.equals("")) {
                AdminBean d = new AdminBean();
                d.setName(username);
                d.setPassword(password);
                if (getWay.login(d)) {
                    d.setPassword(newpassword);
                    getWay.updatePassword(d);
                    changeUsernameAreas.setText("");
                    changePasswordArea.setText("");
                    newchangePasswordArea.setText("");

                    username = null;
                    password = null;
                    newpassword = null;
                    JOptionPane.showMessageDialog(null, "PASSWORD UPDATED SUCCESSFULLY");
                } else {
                    changeUsernameAreas.setText("");
                    changePasswordArea.setText("");
                    newchangePasswordArea.setText("");
                    username = null;
                    password = null;
                    newpassword = null;
                    JOptionPane.showMessageDialog(null, "FAILED OPERATION TRY AGAIN");
                }

            } else if (password.equals("") && username.equals("") && newpassword.equals("")) {
                username = null;
                password = null;
                newpassword = null;
                JOptionPane.showMessageDialog(null, " PLEASE TRY AGAIN");
            } else if (username.equals("")) {
                changePasswordArea.setText("");
                newchangePasswordArea.setText("");
                username = null;
                password = null;
                newpassword = null;
                JOptionPane.showMessageDialog(null, " enter username ");
            } else if (password.equals("")) {
                newchangePasswordArea.setText("");
                username = null;
                password = null;
                newpassword = null;
                JOptionPane.showMessageDialog(null, " enter current  password");
            } else if (newpassword.equals("")) {

                username = null;
                password = null;
                newpassword = null;
                JOptionPane.showMessageDialog(null, " enter new  password");
            }
        }
    }

    public void settingFrameButtonControl(boolean a, boolean b, boolean c, boolean d) {
        if (a) {
            addaccount.setEnabled(!a);
            deleteacount.setEnabled(a);
            changepassword.setEnabled(a);
            viewacounts.setEnabled(a);
            addaccount.setBackground(Color.green);
            deleteacount.setBackground(Color.blue);
            changepassword.setBackground(Color.blue);
            viewacounts.setBackground(Color.blue);
        }
        if (b) {
            addaccount.setEnabled(b);
            deleteacount.setEnabled(!b);
            changepassword.setEnabled(b);
            viewacounts.setEnabled(b);
            addaccount.setBackground(Color.blue);
            deleteacount.setBackground(Color.green);
            changepassword.setBackground(Color.blue);
            viewacounts.setBackground(Color.blue);
        }
        if (c) {
            addaccount.setEnabled(c);
            deleteacount.setEnabled(c);
            changepassword.setEnabled(!c);
            viewacounts.setEnabled(c);
            addaccount.setBackground(Color.blue);
            deleteacount.setBackground(Color.blue);
            changepassword.setBackground(Color.green);
            viewacounts.setBackground(Color.blue);
        }
        if (d) {
            addaccount.setEnabled(d);
            deleteacount.setEnabled(d);
            changepassword.setEnabled(d);
            viewacounts.setEnabled(!d);
            addaccount.setBackground(Color.blue);
            deleteacount.setBackground(Color.blue);
            changepassword.setBackground(Color.blue);
            viewacounts.setBackground(Color.green);
        }
    }
}
