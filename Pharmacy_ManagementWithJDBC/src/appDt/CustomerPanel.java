
package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.CustomerBean;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmed
 */
public class CustomerPanel extends JPanel implements ActionListener, ItemListener, CaretListener {

    StoreGetWay getWay = new StoreGetWay();
    String text;
    String addName, addPhone, addEmail, editPhone, editEmail;
    int x = 0;
    int selectedRow;
    private JTable table;
    String[] columnNames = {"ID","NAME", "EMAIL", "PHONE NUMBER"};
    String[] items = {"------", "ALL", "Name"};

    JTable exampleJTable;
    JScrollPane sp;
    JLabel addNameLabel, addPhoneLabel, addEmailLabel, editPhoneLabel, editEmailLabel;
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    JButton search, add, clear, edit, delete, cancel, addOk, addCancel, editOk, editCancel;
    JTextField searchField, addNameField, addPhoneField, addEmailField, editPhoneField, editEmailField;
    JPanel eastSearchPanel, westSearchPanel, searchPanel, namePanel, quantityPanel, typePanel, allPanel;
    JComboBox cb;
    public static JDialog editDeleteDialog, addCustomerDialog, editDialog;

    // constructor
    public CustomerPanel() throws SQLException {
        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
        // set super panel as border layout
        super(new BorderLayout());
        exampleJTable = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        exampleJTable.setModel(defTableModel);  // set jtable as defaultmodel
        sp = new JScrollPane(exampleJTable);  // add table to scorllpane
        sp.getViewport().setBackground(Color.pink);
        exampleJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defTableModel.setColumnCount(4);// set number columns
        defTableModel.setColumnIdentifiers(columnNames);// // set columns names
        exampleJTable.getTableHeader().setPreferredSize(new Dimension(sp.getWidth(), 75));// set size header table
        exampleJTable.getTableHeader().setBackground(Color.PINK);// set background header table
        exampleJTable.setBackground(Color.yellow); // set background table 
        exampleJTable.setRowHeight(50);// set row hieght table
        exampleJTable.getTableHeader().setEnabled(false); // set header enable to prevent  move columns

        // add mouselistener to table
        exampleJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    selectedRow = exampleJTable.getSelectedRow();// selected row
                    HomePage.frame.setEnabled(false);
                    editDeleteDialogMethod();
                }
            }
        });

        //--------------------------------------------------search panel---------------------------------------------------------------------------------------------------
        eastSearchPanel = new JPanel();
        eastSearchPanel.setBackground(Color.cyan);
        // set search panel as grouplayout
        GroupLayout groupLayout = new GroupLayout(eastSearchPanel);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        eastSearchPanel.setLayout(groupLayout);
        // define search button   
        search = new JButton("search");
        search.setBackground(Color.green);
        search.setPreferredSize(new Dimension(100, 100));
        search.addActionListener(this);
        clear = new JButton("clear");
        clear.setBackground(Color.green);
        clear.setPreferredSize(new Dimension(100, 100));
        clear.addActionListener(this);
        // define add button   
        add = new JButton("ADD ");
        add.setBackground(Color.green);
        add.setPreferredSize(new Dimension(100, 100));
        add.addActionListener(this);
        // define combobox
        cb = new JComboBox(items);

        cb.setPreferredSize(new Dimension(75, 50));
        cb.addItemListener(this);
        cb.setBackground(Color.yellow);
        // organize component in group layout   
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(100)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(add)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(clear)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(search)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(cb)).addGap(70));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addGap(100).addComponent(add)
                        .addGap(100).addComponent(clear)
                        .addGap(100).addComponent(search)
                        .addGap(100).addComponent(cb)));
        westSearchPanel = new JPanel(new CardLayout());
        westSearchPanel.setBackground(Color.cyan);
        // define serch textfield
        searchPanel = new JPanel(new GridLayout(1, 2));
        searchPanel.add(westSearchPanel);
        searchPanel.add(eastSearchPanel);
        //--------------------------------------------------------------add panel to super panel------------------------------------------------
        add(searchPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        cb.addItemListener(this);

        //===========================================================================
    }

    public void editDeleteDialogMethod() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        north.setBackground(Color.gray);
        edit = new JButton("edit");
        edit.setPreferredSize(new Dimension(75, 50));
        edit.addActionListener(this);
        edit.setBackground(Color.green);
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING, 40, 100));
        center.setBackground(Color.gray);
        delete = new JButton("delete");
        delete.setPreferredSize(new Dimension(75, 50));
        delete.setBackground(Color.green);
        cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(75, 50));
        cancel.setBackground(Color.green);
        cancel.addActionListener(this);
        delete.addActionListener(this);
        center.add(edit);
        center.add(delete);
        center.add(cancel);
        //--------------------------------------------------------------edit_delet-dialog----------------------------------------------------------------
        editDeleteDialog = new JDialog();
        editDeleteDialog.setLayout(new BorderLayout());
        editDeleteDialog.setPreferredSize(new Dimension(400, 300));
        editDeleteDialog.add(north, BorderLayout.NORTH);
        editDeleteDialog.add(center, BorderLayout.CENTER);
        editDeleteDialog.setBackground(Color.gray);
        editDeleteDialog.pack();
        editDeleteDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                editDeleteDialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        editDeleteDialog.setVisible(true);
    }// end method    
    //------------------------------------------------------edit dialog-method-------------------------------------------------------------------

    public JDialog editDialog() {
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        emailPanel.setBackground(Color.gray);
        editEmailLabel = new JLabel("E-MAIL");
        editEmailLabel.setPreferredSize(new Dimension(100, 75));
        editEmailField = new JTextField();
        editEmailField.setPreferredSize(new Dimension(150, 75));
        emailPanel.add(editEmailLabel);
        emailPanel.add(editEmailField);
        //-------------------------------------------------------
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        phonePanel.setBackground(Color.gray);
        editPhoneLabel = new JLabel("PHONE");
        editPhoneLabel.setPreferredSize(new Dimension(100, 75));
        editPhoneField = new JTextField();
        editPhoneField.setPreferredSize(new Dimension(150, 75));
        phonePanel.add(editPhoneLabel);
        phonePanel.add(editPhoneField);
        //--------------------------------------------------------------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 40));
        buttonPanel.setBackground(Color.gray);
        editOk = new JButton("OK");
        editOk.setBackground(Color.green);
        editOk.setPreferredSize(new Dimension(100, 75));
        editCancel = new JButton("cancel");
        editCancel.setBackground(Color.green);
        editCancel.setPreferredSize(new Dimension(100, 75));
        buttonPanel.add(editOk);
        buttonPanel.add(editCancel);
        editOk.addActionListener(this);
        editCancel.addActionListener(this);
        //---------------------------------------------------------------------------
        editDialog = new JDialog();
        editDialog.setLayout(new GridLayout(3, 1));
        editDialog.setBackground(Color.gray);
        editDialog.setPreferredSize(new Dimension(400, 400));
        editDialog.add(emailPanel);
        editDialog.add(phonePanel);
        editDialog.add(buttonPanel);
        editDialog.pack();
        editDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                editDialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        editDialog.setVisible(true);
        return editDialog;
    }// end method

    public JDialog addCustomer() {
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        namePanel.setBackground(Color.yellow);
        addNameLabel = new JLabel(" NAME ");
        addNameLabel.setPreferredSize(new Dimension(100, 75));
        addNameField = new JTextField();
        addNameField.setPreferredSize(new Dimension(400, 75));
        addNameField.addCaretListener(this);
        namePanel.add(addNameLabel);
        namePanel.add(addNameField);
        //----------------------------------------------------------------------------------           
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        phonePanel.setBackground(Color.yellow);
        addPhoneLabel = new JLabel("PHONE ");
        addPhoneLabel.setPreferredSize(new Dimension(100, 75));
        addPhoneField = new JTextField();
        addPhoneField.setPreferredSize(new Dimension(400, 75));
        addPhoneField.addCaretListener(this);
        phonePanel.add(addPhoneLabel);
        phonePanel.add(addPhoneField);
        //--------------------------------------------------------------------------------------------
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        emailPanel.setBackground(Color.yellow);
        addEmailLabel = new JLabel("EMAIL ");
        addEmailLabel.setPreferredSize(new Dimension(100, 75));
        addEmailField = new JTextField();
        addEmailField.setPreferredSize(new Dimension(400, 75));
        addEmailField.addCaretListener(this);

        emailPanel.add(addEmailLabel);
        emailPanel.add(addEmailField);
        //---------------------------------------------------------------------------------------         
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 30));
        buttonPanel.setBackground(Color.yellow);
        addOk = new JButton("OK");
        addOk.setPreferredSize(new Dimension(100, 75));
        addOk.setBackground(Color.green);
        addCancel = new JButton("cancel");
        addCancel.setPreferredSize(new Dimension(100, 75));
        addCancel.setBackground(Color.green);
        addOk.addActionListener(this);
        addCancel.addActionListener(this);
        buttonPanel.add(addOk);
        buttonPanel.add(addCancel);
        //---------------------------------------------------------------------------------------------------------------
        addCustomerDialog = new JDialog();
        addCustomerDialog.setLayout(new GridLayout(4, 1));
        addCustomerDialog.setPreferredSize(new Dimension(700, 500));
        addCustomerDialog.add(namePanel);
        addCustomerDialog.add(phonePanel);
        addCustomerDialog.add(emailPanel);
        addCustomerDialog.add(buttonPanel);
        addCustomerDialog.pack();
        addCustomerDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                addCustomerDialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        addCustomerDialog.pack();
        addCustomerDialog.setVisible(true);
        return addCustomerDialog;
    } // end method
// action performed for search button

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {

            //---------------------------------------------------------------------- select all---------------------------------------------------------------------------- 
            if (x == 1) {
                defTableModel.setNumRows(0);
                List<CustomerBean> l = getWay.listAllCustomer();

                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getID(),l.get(i).getName(), l.get(i).getEmail(),
                            l.get(i).getPhone()});

                    }
                }

            }
            //----------------------------------------------------------------select by name----------------------------------------------------------------------------
            if (x == 2) {
                text = searchField.getText().trim();
                defTableModel.setNumRows(0);
                List<CustomerBean> l = getWay.listByNameCustomer(text);
                for (int i = 0; i < l.size(); i++) {
                    defTableModel.addRow(new Object[]{l.get(i).getID(),l.get(i).getName(), l.get(i).getEmail(),
                            l.get(i).getPhone()});

                }
            }
        }

        if (e.getSource() == clear) {
            defTableModel.setRowCount(0);
        }
        if (e.getSource() == add) {

            HomePage.frame.setEnabled(false);
            addCustomer();

        }

        if (e.getSource() == edit) {

            editDeleteDialog.dispose();
            editDialog();
            editEmailField.setText((String) defTableModel.getValueAt(selectedRow, 1));
            editPhoneField.setText((String) defTableModel.getValueAt(selectedRow, 2));

        }
        if (e.getSource() == delete) {
             int id = (int) defTableModel.getValueAt(selectedRow,0);
            String name = (String) defTableModel.getValueAt(selectedRow, 1);
             String phone = (String) defTableModel.getValueAt(selectedRow, 3);
           String email = (String) defTableModel.getValueAt(selectedRow, 2);
           
            CustomerBean bean = new CustomerBean();
            bean.setID(id);
            bean.setName(name);
            bean.setEmail(email);
            bean.setPhone(phone);
            getWay.deleteCustomer(bean);
            defTableModel.removeRow(selectedRow);
            editDeleteDialog.dispose();
            HomePage.frame.setEnabled(true);

        }
        if (e.getSource() == cancel) {
            editDeleteDialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == editOk) {
            editEmail = editEmailField.getText().trim();
            editPhone = editPhoneField.getText().trim();
            defTableModel.setValueAt(editEmail, selectedRow, 2);
            defTableModel.setValueAt(editPhone, selectedRow, 3);
            String name = (String) defTableModel.getValueAt(selectedRow, 1);
            int id = (int) defTableModel.getValueAt(selectedRow, 0);
            CustomerBean bean = new CustomerBean();
            bean.setID(id);
            bean.setName(name);
            bean.setPhone(editPhone);
            bean.setEmail(editEmail);
            getWay.updateCustomer(bean);
            editDialog.dispose();
            HomePage.frame.setEnabled(true);
            editEmail = null;
            editPhone = null;

        }
        if (e.getSource() == editCancel) {
            editDialog.dispose();
            HomePage.frame.setEnabled(true);

        }
        if (e.getSource() == addCancel) {
            addCustomerDialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == addOk) {
            boolean exit = false;
            addName = addNameField.getText().trim();
            addEmail = addEmailField.getText().trim();
            addPhone = addPhoneField.getText().trim();
            if (!addName.equals("") && !(addName == null)) {
                CustomerBean bean = new CustomerBean();
                bean.setName(addName);
                bean.setEmail(addEmail);
                bean.setPhone(addPhone);
                CustomerBean addCustomer = getWay.addCustomer(bean);
                exit = true;
                HomePage.frame.setEnabled(true);
                addCustomerDialog.dispose();
                if (addCustomer!=null) {
                    JOptionPane.showMessageDialog(null, "customer added");
                } else {
                    JOptionPane.showMessageDialog(null, "failed");
               
                }

            } else if (addName.equals("")) {
                JOptionPane.showMessageDialog(null, " ENTER CUSTOMER NAME");
            }

            if (exit) {
                addCustomerDialog.dispose();
                exit = false;
            }
        }
    }

    //========================================= end action performed method ==========================   
    @Override
    public void itemStateChanged(ItemEvent ex) {
        if (ex.getSource() == cb) {

            if (cb.getSelectedItem().equals("ALL")) {
                allSelectedMethod();
                x = 1;
            }
            if (cb.getSelectedItem().equals("Name")) {
                nameSelectedMethod();
                x = 2;
            }

        }

    }

    private JPanel nameSelectedMethod() {
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 75));
        searchField.addCaretListener(this);

        searchField.setEnabled(true);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        namePanel.add(searchField);
        TitledBorder border1 = new TitledBorder("CUSTOMER NAME");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westSearchPanel.setBorder(border1);
        westSearchPanel.removeAll();
        westSearchPanel.repaint();
        westSearchPanel.revalidate();
        westSearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel allSelectedMethod() {
        allPanel = new JPanel();
        allPanel.setBackground(Color.cyan);
        JLabel label = new JLabel("ALL CUSTOMERS SELECTED");
        allPanel.add(label);
        TitledBorder border1 = new TitledBorder("ALL CUSTOMERS ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westSearchPanel.setBorder(border1);
        westSearchPanel.removeAll();
        westSearchPanel.repaint();
        westSearchPanel.revalidate();
        westSearchPanel.add(allPanel);
        return namePanel;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == searchField) {
            text = searchField.getText().trim();
        }
        if (e.getSource() == addNameField) {
            addName = addNameField.getText().trim();
        }
        if (e.getSource() == addEmailField) {
            addEmail = addEmailField.getText().trim();
        }
        if (e.getSource() == addPhoneField) {
            addPhone = addPhoneField.getText().trim();
        }
        if (e.getSource() == editEmailField) {
            editEmail = editEmailField.getText().trim();
        }
        if (e.getSource() == editPhoneField) {
            editPhone = editPhoneField.getText().trim();
        }

    }

}
