
package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.LostDrugsBean;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
public class RequestedPurchasesPanel extends JPanel implements ActionListener, ItemListener, CaretListener, ChangeListener {

 StoreGetWay getWay = new StoreGetWay();
   String text, textadd, type;
    public static String names;
    public static int quantities;
    int fromNumber, toNumber, unitnumber;
    int x = 0;
    int selectedrow, cbitem;
    public static int addquantity, addprice;
    private JTable table;
    String[] columnNames = {"Name", "TYPE", "Quantity"};
    String[] items = {"------", "ALL", "Name", "TYPE"};
    String[] typesItems = {"------", "capsule", "ointment", "injection", "pill"};
    JTable exampleJTable;
    JScrollPane sp;
    JLabel namelabel, unitlabel, typelabel;
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    JButton search, add, clear, edit, delete, cancel, editok, editcancel, addok, addcancel;
    JTextField searchfield, addnamefield;
    JPanel eastsearchPanel, westsearchPanel, searchPanel, namePanel, quantityPanel, typePanel, ALLPanel;
    JComboBox cb, typeCB, addcb;
    public static JDialog Edit_Delete_dialog, Add_Drug_Dialog, Edit_Dialog, Drug_Dialog;
    JSpinner toSpiner, fromSpiner, editspiner, editspiner1, editspiner2, spiner;
    //  JXDatePicker frompicker , topicker;
    // constructor

    public RequestedPurchasesPanel() throws SQLException {
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
        defTableModel.setColumnCount(3);// set number columns
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
                    selectedrow = exampleJTable.getSelectedRow();// selected row
                    HomePage.frame.setEnabled(false);
                    editdeletedialogmethod();

                }
            }
        });

        //--------------------------------------------------search panel---------------------------------------------------------------------------------------------------
        eastsearchPanel = new JPanel();
        eastsearchPanel.setBackground(Color.cyan);
        // set search panel as grouplayout
        GroupLayout groupLayout = new GroupLayout(eastsearchPanel);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        eastsearchPanel.setLayout(groupLayout);
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
        westsearchPanel = new JPanel(new CardLayout());
        westsearchPanel.setBackground(Color.cyan);
        // define serch textfield
        searchPanel = new JPanel(new GridLayout(1, 2));
        searchPanel.add(westsearchPanel);
        searchPanel.add(eastsearchPanel);
        //--------------------------------------------------------------add panel to super panel------------------------------------------------
        add(searchPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        cb.addItemListener(this);

        //===========================================================================
    }

    public void editdeletedialogmethod() {
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
        Edit_Delete_dialog = new JDialog();
        Edit_Delete_dialog.setLayout(new BorderLayout());
        Edit_Delete_dialog.setPreferredSize(new Dimension(400, 300));
        Edit_Delete_dialog.add(north, BorderLayout.NORTH);
        Edit_Delete_dialog.add(center, BorderLayout.CENTER);
        Edit_Delete_dialog.setBackground(Color.gray);
        Edit_Delete_dialog.pack();
        Edit_Delete_dialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Edit_Delete_dialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        Edit_Delete_dialog.setVisible(true);
    }// end method    
    //------------------------------------------------------edit dialog-method-------------------------------------------------------------------

    public JDialog EditDialog() {
        JPanel quantitypanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quantitypanel.setBackground(Color.gray);
        JLabel unitnumber = new JLabel("QUANTITY");
        unitnumber.setPreferredSize(new Dimension(100, 75));
        unitnumber.setPreferredSize(new Dimension(100, 75));
        SpinnerModel dmodeledit = new SpinnerNumberModel(0, 0, 1000, 1);
        editspiner = new JSpinner(dmodeledit);
        editspiner.addChangeListener(this);
        editspiner.setPreferredSize(new Dimension(150, 50));
        editspiner.setValue(defTableModel.getValueAt(selectedrow, 2));
        quantitypanel.add(unitnumber);
        quantitypanel.add(editspiner);
        //-----------------------------------------------------------------------
        JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        buttonpanel.setBackground(Color.gray);
        editok = new JButton("OK");
        editok.setBackground(Color.green);
        editok.setPreferredSize(new Dimension(100, 75));
        editcancel = new JButton("cancel");
        editcancel.setBackground(Color.green);
        editcancel.setPreferredSize(new Dimension(100, 75));
        buttonpanel.add(editok, BorderLayout.WEST);
        buttonpanel.add(editcancel, BorderLayout.EAST);
        editok.addActionListener(this);
        editcancel.addActionListener(this);
        //---------------------------------------------------------------------------------

        Edit_Dialog = new JDialog();
        Edit_Dialog.setLayout(new GridLayout(2, 1));
        Edit_Dialog.setBackground(Color.gray);
        Edit_Dialog.setBackground(Color.gray);
        Edit_Dialog.setPreferredSize(new Dimension(400, 300));
        Edit_Dialog.add(quantitypanel);
        Edit_Dialog.add(buttonpanel);
        Edit_Dialog.pack();
        Edit_Dialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Edit_Dialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        Edit_Dialog.setVisible(true);
        return Edit_Dialog;
    }// end method

    public JDialog drugDialog() {
        JPanel namepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        namepanel.setBackground(Color.yellow);
        namelabel = new JLabel("DRUG NAME ");
        namelabel.setPreferredSize(new Dimension(100, 75));
        addnamefield = new JTextField();
        addnamefield.setPreferredSize(new Dimension(150, 75));
        addnamefield.addCaretListener(this);
        namepanel.add(namelabel);
        namepanel.add(addnamefield);
        //----------------------------------------------------------------------------------           
        JPanel quantitypanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        quantitypanel.setBackground(Color.yellow);
        SpinnerModel dmodel = new SpinnerNumberModel(0, 0, 10000, 1);
        spiner = new JSpinner(dmodel);
        spiner.addChangeListener(this);
        spiner.setPreferredSize(new Dimension(150, 50));
        unitlabel = new JLabel("UNIT NUMBER");
        unitlabel.setPreferredSize(new Dimension(100, 75));
        quantitypanel.add(unitlabel);
        quantitypanel.add(spiner);
        //---------------------------------------------------------------------------------------  
        JPanel typepanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        typepanel.setBackground(Color.yellow);
        typelabel = new JLabel("UNIT TYPE");
        typelabel.setPreferredSize(new Dimension(100, 75));
        addcb = new JComboBox(typesItems);
        addcb.setPreferredSize(new Dimension(150, 50));
        addcb.addItemListener(this);
        addcb.setBackground(Color.yellow);
        typepanel.add(typelabel);
        typepanel.add(addcb);
        //------------------------------------------------------------------------------------------------------     
        JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 60, 0));
        buttonpanel.setBackground(Color.yellow);
        addok = new JButton("OK");
        addok.setPreferredSize(new Dimension(100, 75));
        addok.setBackground(Color.green);
        addcancel = new JButton("cancel");
        addcancel.setPreferredSize(new Dimension(100, 75));
        addcancel.setBackground(Color.green);
        addok.addActionListener(this);
        addcancel.addActionListener(this);
        buttonpanel.add(addok);
        buttonpanel.add(addcancel);
        //---------------------------------------------------------------------------------------------------------------
        Drug_Dialog = new JDialog();
        Drug_Dialog.setLayout(new GridLayout(4, 1));
        Drug_Dialog.setPreferredSize(new Dimension(500, 500));

        Drug_Dialog.add(namepanel);
        Drug_Dialog.add(quantitypanel);
        Drug_Dialog.add(typepanel);
        Drug_Dialog.add(buttonpanel);
        Drug_Dialog.pack();
        Drug_Dialog.setVisible(true);
        Drug_Dialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                Drug_Dialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        return Drug_Dialog;
    } // end method
// action performed for search button

    @Override
    public void actionPerformed(ActionEvent e) {
         if (e.getSource() == clear) {
            defTableModel.setRowCount(0);
        }
        if (e.getSource() == search) {

            //---------------------------------------------------------------------- select all---------------------------------------------------------------------------- 
            if (x == 1) {
                defTableModel.setNumRows(0);
                List<LostDrugsBean> l = getWay.listAllRequested();
                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getDrugname(), l.get(i).getDrugtype(), l.get(i).getQuantitydrug()});

                    }
                }
            }
            //----------------------------------------------------------------select by name----------------------------------------------------------------------------
            if (x == 2) {
                defTableModel.setNumRows(0);
                List<LostDrugsBean> l = getWay.listByNameRequested(searchfield.getText().trim());
                System.out.println(l);
                System.out.print(searchfield.getText().trim());
                if (l != null) {
                    if (l.size() > 0) {
                        for (int i = 0; i < l.size(); i++) {
                            defTableModel.addRow(new Object[]{l.get(i).getDrugname(), l.get(i).getDrugtype(), l.get(i).getQuantitydrug()});
                        }
                    }
                }
            }

            //---------------------------------------------------------------------------select by  capsule type------------------------------------------------------------------------
            if (x == 3) {
                type = "capsule";
                defTableModel.setNumRows(0);
                List<LostDrugsBean> l = getWay.listByTypeRequested(type);
                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getDrugname(), l.get(i).getDrugtype(), l.get(i).getQuantitydrug()});

                    }
                }

            }
            //----------------------------------------------------- select by ointment type ------------------------------------------------------------------------------
            if (x == 4) {

                type = "ointment";
                defTableModel.setNumRows(0);
                List<LostDrugsBean> l = getWay.listByTypeRequested(type);
                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getDrugname(), l.get(i).getDrugtype(), l.get(i).getQuantitydrug()});

                    }
                }

            }
            //--------------------------------------------------------------- select by injection type ---------------------------------------------------------------------
            if (x == 5) {
                type = "injection";
                defTableModel.setNumRows(0);
                List<LostDrugsBean> l = getWay.listByTypeRequested(type);
                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getDrugname(), l.get(i).getDrugtype(), l.get(i).getQuantitydrug()});

                    }
                }

            }
            // ==================================== selection by pill type ===============================
            if (x == 6) {
                type = "pill";
                defTableModel.setNumRows(0);
                List<LostDrugsBean> l = getWay.listByTypeRequested(type);
                if (l.size() > 0) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getDrugname(), l.get(i).getDrugtype(), l.get(i).getQuantitydrug()});

                    }
                }

            }
        }

        if (e.getSource() == add) {
            HomePage.frame.setEnabled(false);
            drugDialog();

        }
        if (e.getSource() == edit) {
            Edit_Delete_dialog.dispose();
            EditDialog();

        }
        if (e.getSource() == delete) {
            LostDrugsBean bean = new LostDrugsBean();
            String drug = (String) defTableModel.getValueAt(selectedrow, 0);
            String type= (String) defTableModel.getValueAt(selectedrow, 1);
            
            bean.setDrugname(drug);
            bean.setDrugtype(type);
            LostDrugsBean deleteLostDrug = getWay.deleteLostDrug(bean);
             Edit_Delete_dialog.dispose();
            if (deleteLostDrug == null) {
                 JOptionPane.showMessageDialog(null, "TRY AGAIN");
               
            } else {
                 defTableModel.removeRow(selectedrow);
            JOptionPane.showMessageDialog(null, " DRUG DELETED SUCCESSFULLY");

            }
       
            HomePage.frame.setEnabled(true);

        }

        if (e.getSource() == cancel) {
            Edit_Delete_dialog.dispose();
            HomePage.frame.setEnabled(true);

        }
        if (e.getSource() == editok) {

            int x = (int) editspiner.getValue();

            defTableModel.setValueAt(x, selectedrow, 2);
            String name = (String) defTableModel.getValueAt(selectedrow, 0);
            LostDrugsBean bean = new LostDrugsBean(name, "", x);
            LostDrugsBean updateDrugRequested = getWay.updateDrugRequested(bean);
            if (updateDrugRequested != null) {
                JOptionPane.showMessageDialog(null, "edit successfully");
            } else {
                JOptionPane.showMessageDialog(null, "TRY AGAIN");
            }
            Edit_Dialog.dispose();
              HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == editcancel) {
            Edit_Dialog.dispose();
            HomePage.frame.setEnabled(true);

        }
        if (e.getSource() == addcancel) {
            Drug_Dialog.dispose();
            HomePage.frame.setEnabled(true);

        }
        if (e.getSource() == addok) {
            boolean exit = false;
            textadd = addnamefield.getText().trim();
            addquantity = (int) spiner.getValue();
            int selecteditem = addcb.getSelectedIndex();
            if (!textadd.equals("") && !(textadd == null) && cbitem > 0 && addquantity != 0 && !addcb.getSelectedObjects().equals("------")) {
                LostDrugsBean bean = new LostDrugsBean();
                bean.setDrugname(textadd);
                bean.setDrugtype((String) addcb.getSelectedItem());
                bean.setQuantitydrug(addquantity);
                LostDrugsBean addRequested = getWay.addRequested(bean);
                if (addRequested != null) {
                    JOptionPane.showMessageDialog(null, " DRUG ADDED ");
                } else {
                    JOptionPane.showMessageDialog(null, " failed operation ");
                }

                addquantity = 0;
                quantities = 0;
                names = null;
                textadd = null;
                exit = true;
                addnamefield.setText("");
                spiner.setValue(0);
                addcb.setSelectedIndex(0);
                HomePage.frame.setEnabled(true);
            } else if (addnamefield.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, " ENTER DRUG NAME");
            } else if (addquantity == 0) {
                JOptionPane.showMessageDialog(null, " ENTER DRUG QUANTITY");
            } else if (!addcb.getSelectedObjects().equals("------")) {
                JOptionPane.showMessageDialog(null, " SELECT DRUG TYPE");
            }
            if (exit) {
                Drug_Dialog.dispose();
                exit = false;
            }
        }
    }

//========================================= end action performed method ==========================   
    @Override
    public void itemStateChanged(ItemEvent ex) {
        if (ex.getSource() == cb) {

            if (cb.getSelectedItem().equals("ALL")) {
                ALLSelectedMethod();
                x = 1;
            }
            if (cb.getSelectedItem().equals("Name")) {
                nameSelectedMethod();
                x = 2;
            }
            if (cb.getSelectedItem().equals("TYPE")) {
                TypeSelectedMethod();
            }

        }
        if (ex.getSource() == typeCB) {

            if (typeCB.getSelectedItem().equals("capsule")) {

                x = 3;
            }
            if (typeCB.getSelectedItem().equals("ointment")) {

                x = 4;
            }
            if (typeCB.getSelectedItem().equals("injection")) {
                x = 5;
            }
            if (typeCB.getSelectedItem().equals("pill")) {

                x = 6;
            }
        }
        if (ex.getSource() == addcb) {
            if (addcb.getSelectedItem() == "------") {
                cbitem = 0;
                type = "------";
            }
            if (addcb.getSelectedItem() == "capsule") {
                cbitem = 1;
                type = "capsule";
            }
            if (addcb.getSelectedItem() == "ointment") {

                cbitem = 2;
                type = "ointment";
            }
            if (addcb.getSelectedItem() == "injection") {

                cbitem = 3;
                type = "injection";
            }
            if (addcb.getSelectedItem() == "pill") {
                cbitem = 4;
                type = "pill";
            }
        }
    }

    private JPanel nameSelectedMethod() {
        searchfield = new JTextField();
        searchfield.setPreferredSize(new Dimension(200, 75));
        searchfield.addCaretListener(this);

        searchfield.setEnabled(true);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        namePanel.add(searchfield);
        TitledBorder border1 = new TitledBorder("DRUG NAME");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel TypeSelectedMethod() {
        typeCB = new JComboBox(typesItems);
        typeCB.setBackground(Color.yellow);
        typeCB.addItemListener(this);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        namePanel.add(typeCB);
        TitledBorder border1 = new TitledBorder("TYPE DRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel ALLSelectedMethod() {
        ALLPanel = new JPanel();
        ALLPanel.setBackground(Color.cyan);
        JLabel label = new JLabel("ALL DRUGS SELECTED");
        ALLPanel.add(label);
        TitledBorder border1 = new TitledBorder("ALL DRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(ALLPanel);
        return namePanel;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == searchfield) {
            text = searchfield.getText().trim();
        }
        if (e.getSource() == addnamefield) {
            textadd = addnamefield.getText().trim();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == fromSpiner) {
            fromNumber = (int) fromSpiner.getValue();
        }
        if (e.getSource() == toSpiner) {
            toNumber = (int) toSpiner.getValue();
        }
        if (e.getSource() == spiner) {
            addquantity = (int) spiner.getValue();
        }
    }
}
