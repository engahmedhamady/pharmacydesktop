
package appDt;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
import static appDt.SearchPanel.defTableModel;
import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.DrugsBean;

/**
 *
 * @author ahmed
 */
public class FoundedPurchasesPanel extends JPanel implements ActionListener, ItemListener, CaretListener, ChangeListener {

      StoreGetWay getWay = new StoreGetWay();
 String text, type;
    int fromNumber, toNumber;
    int x = 0;
    int selectedrow;
    Date fromdate, todate;
    private JTable table;
    String[] columnNames = {"Name", "TYPE", "Price", "Discount", "Quantity", "Company", "BarCode"};
    String[] items = {"------", "ALL", "Name", "TYPE", "Quantity", "Price", "Company"};
    String[] typesItems = {"------", "capsule", "ointment", "injection", "pill"};
    JTable exampleJTable;
    JScrollPane sp;
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    JButton search, clear, edit, delete, cancel, editok, editcancel;
    JTextField searchfield;
    JPanel eastsearchPanel, westsearchPanel, searchPanel, namePanel, quantityPanel, typePanel, ALLPanel;
    JComboBox cb, typeCB;
    public static JDialog Edit_Delete_dialog, Add_Drug_Dialog, Edit_Dialog;
    JSpinner toSpiner, fromSpiner, editspiner, editspiner1;
    JDateChooser frompicker, topicker;
    JTextFieldDateEditor editor1, editor2;
    // constructor

    public FoundedPurchasesPanel() throws SQLException {
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
        defTableModel.setColumnCount(8);// set number columns
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
                    editdeletedialogmethod();
                    HomePage.frame.setEnabled(false);
                }
            }
        });

        //--------------------------------------------------search panel---------------------------------------------------------------------------------------------------
        eastsearchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
        eastsearchPanel.setBackground(Color.cyan);

        // define search button   
        search = new JButton("search");
        search.setBackground(Color.green);
        search.setPreferredSize(new Dimension(75, 50));
        search.addActionListener(this);
        clear = new JButton("clear");
        clear.setBackground(Color.green);
        clear.setPreferredSize(new Dimension(75, 50));
        clear.addActionListener(this);
        // define combobox
        cb = new JComboBox();
        //MenuItem all = 
        cb.addItem(items[0]);
        cb.addItem(items[1]);
        cb.addItem(items[2]);
        cb.addItem(items[3]);
        cb.addItem(items[4]);
        cb.addItem(items[5]);
        cb.addItem(items[6]);

        cb.setPreferredSize(new Dimension(75, 50));
        cb.addItemListener(this);
        cb.setBackground(Color.yellow);
        eastsearchPanel.add(clear);
        eastsearchPanel.add(search);
        eastsearchPanel.add(cb);
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
        delete.addActionListener(this);
        cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(75, 50));
        cancel.setBackground(Color.green);
        cancel.addActionListener(this);
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
        SpinnerModel dmodeledit1 = new SpinnerNumberModel(0, 0, 1000, 1);
        editspiner1 = new JSpinner(dmodeledit1);
        editspiner1.setValue(defTableModel.getValueAt(selectedrow, 4));
        editspiner1 = new JSpinner(dmodeledit1);
        editspiner1.addChangeListener(this);
        editspiner1.setPreferredSize(new Dimension(150, 50));
        quantitypanel.add(unitnumber);
        quantitypanel.add(editspiner1);
        JPanel pricepanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 70, 0));
        pricepanel.setBackground(Color.gray);
        JLabel unitnPRICE = new JLabel("PRICE");
        unitnumber.setPreferredSize(new Dimension(100, 75));
        SpinnerModel dmodeledit = new SpinnerNumberModel(0, 0, 1000, 1);
        editspiner = new JSpinner(dmodeledit);
        editspiner.addChangeListener(this);
        editspiner.setPreferredSize(new Dimension(150, 50));
        editspiner.setValue(defTableModel.getValueAt(selectedrow, 2));
        pricepanel.add(unitnPRICE, FlowLayout.LEFT);
        pricepanel.add(editspiner);
        JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 55, 0));
        buttonpanel.setBackground(Color.gray);
        editok = new JButton("OK");
        editok.setBackground(Color.green);
        editok.setPreferredSize(new Dimension(100, 75));
        editcancel = new JButton("cancel");
        editcancel.setBackground(Color.green);
        editcancel.setPreferredSize(new Dimension(100, 75));
        editok.addActionListener(this);
        editcancel.addActionListener(this);
        buttonpanel.add(editok);
        buttonpanel.add(editcancel);
        Edit_Dialog = new JDialog();
        Edit_Dialog.setPreferredSize(new Dimension(380, 400));
        Edit_Dialog.setLayout(new GridLayout(3, 1));
        Edit_Dialog.add(quantitypanel);
        Edit_Dialog.add(pricepanel);
        Edit_Dialog.add(buttonpanel);
        Edit_Dialog.pack();
        Edit_Dialog.setVisible(true);
        Edit_Dialog.addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Edit_Dialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        return Edit_Dialog;
    }// end method

// action performed for search button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {

            //---------------------------------------------------------------------- select all---------------------------------------------------------------------------- 
            if (x == 1) {
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listAllFounded();

                 if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }

            }
            //----------------------------------------------------------------select by name----------------------------------------------------------------------------
            if (x == 2) {

                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByNamefounded(text);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }

            }

            //---------------------------------------------------------------------------select by  capsule type------------------------------------------------------------------------
            if (x == 3) {
                type = "capsule";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByTypefounded(type);

                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }
            }
            //----------------------------------------------------- select by ointment type ------------------------------------------------------------------------------
            if (x == 4) {
                type = "ointment";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByTypefounded(type);
                 if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }
            }
            //--------------------------------------------------------------- select by injection type ---------------------------------------------------------------------
            if (x == 5) {
                type = "injection";

                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByTypefounded(type);
                 if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }
            }
            // ==================================== selection by pill type ===============================
            if (x == 6) {
                type = "pill";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByTypefounded(type);
                 if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }
            }
            // ==================================== select by quantity ======================================
            if (x == 7) {

                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByQuantityfounded(fromNumber, toNumber);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }

            }
            //==================================== select by price ============================================
            if (x == 8) {
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByPricefounded(fromNumber, toNumber);
                 if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }

            }
            //============================================select by company ==================================
            if (x == 9) {

                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.listByCompanyfounded(text);
                 if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                            l.get(i).getCompany(), l.get(i).getBarcode()});

                    }
                }

            }

        }
        if (e.getSource() == clear) {
            defTableModel.setRowCount(0);

        }
        if (e.getSource() == edit) {
            Edit_Delete_dialog.dispose();
            EditDialog();

        }
        if (e.getSource() == delete) {

            String drug = (String) defTableModel.getValueAt(selectedrow, 0);
            String type = (String) defTableModel.getValueAt(selectedrow, 1);
            int discount = (int) defTableModel.getValueAt(selectedrow, 3);
            int price = (int) defTableModel.getValueAt(selectedrow, 2);
            int quantity = (int) defTableModel.getValueAt(selectedrow, 4);

            String company = (String) defTableModel.getValueAt(selectedrow, 5);
            String barCode = (String) defTableModel.getValueAt(selectedrow, 6);

            DrugsBean bean = new DrugsBean();
            bean.setName(drug);
            bean.setCompany(company);
            bean.setType(type);
            bean.setBarcode(barCode);

            getWay.deleteDrug(bean);
            defTableModel.removeRow(selectedrow);
            Edit_Delete_dialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == cancel) {
            Edit_Delete_dialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == editok) {

            defTableModel.setValueAt((int) editspiner.getValue(), selectedrow, 2);
            defTableModel.setValueAt((int) editspiner1.getValue(), selectedrow, 4);
            String name = (String) defTableModel.getValueAt(selectedrow, 0);
            String type = (String) defTableModel.getValueAt(selectedrow, 1);
            int discount = (int) defTableModel.getValueAt(selectedrow, 3);
            String company = (String) defTableModel.getValueAt(selectedrow, 5);

            String barCode = (String) defTableModel.getValueAt(selectedrow, 6);

            int q = (int) editspiner1.getValue();
            int p = (int) editspiner.getValue();
            DrugsBean d = new DrugsBean();

            d.setName(name);
            d.setQuantity(q);
            d.setType(type);
            d.setDiscount(discount);
            d.setSellingPrice(p);
            d.setBarcode(barCode);
            d.setCompany(company);
            getWay.updateDrug(d);
            Edit_Dialog.dispose();
            HomePage.frame.setEnabled(true);

        }

        if (e.getSource() == editcancel) {
            Edit_Dialog.dispose();
            HomePage.frame.setEnabled(true);
        }
    }
    // end action performed method 

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
            if (cb.getSelectedItem().equals("Quantity")) {
                QuantitySelectedMethod();
                x = 7;
            }
            if (cb.getSelectedItem().equals("Price")) {
                PriceSelectedMethod();
                x = 8;
            }
            if (cb.getSelectedItem().equals("Company")) {
                CompanySelectedMethod();
                x = 9;
            }
            if (cb.getSelectedItem().equals("Expiry")) {
                ExpirySelectedMethod();
                x = 10;
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

    private JPanel QuantitySelectedMethod() {
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        JLabel from = new JLabel("FROM");
        JLabel TO = new JLabel("TO");
        SpinnerModel model = new SpinnerNumberModel(50, 1, 1000, 10);
        SpinnerModel models = new SpinnerNumberModel(50, 1, 1000, 10);
        fromSpiner = new JSpinner(model);
        fromSpiner.addChangeListener(this);
        fromSpiner.setPreferredSize(new Dimension(150, 50));
        toSpiner = new JSpinner(models);
        toSpiner.addChangeListener(this);
        toSpiner.setPreferredSize(new Dimension(150, 50));
        namePanel.add(from);
        namePanel.add(fromSpiner);
        namePanel.add(TO);
        namePanel.add(toSpiner);
        TitledBorder border1 = new TitledBorder("QUANTITY DRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel ExpirySelectedMethod() {
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        JLabel from = new JLabel("FROM");
        JLabel TO = new JLabel("TO");
        frompicker = new JDateChooser();
        frompicker.setBackground(Color.yellow);
        frompicker.setDateFormatString("yyyy-MM-dd");
        frompicker.setSize(new Dimension(150, 40));
        editor1 = (JTextFieldDateEditor) frompicker.getDateEditor();
        editor1.addCaretListener(this);
        editor1.setText("0000-00-00");
        editor1.setEditable(false);
        editor1.setPreferredSize(new Dimension(150, 40));
        topicker = new JDateChooser();
        topicker.setBackground(Color.yellow);
        topicker.setDateFormatString("yyyy-MM-dd");
        topicker.setSize(new Dimension(150, 40));
        editor2 = (JTextFieldDateEditor) topicker.getDateEditor();
        editor2.addCaretListener(this);
        editor2.setText("0000-00-00");
        editor2.setEditable(false);
        editor2.setPreferredSize(new Dimension(150, 40));
        namePanel.add(from);
        namePanel.add(frompicker);
        namePanel.add(TO);
        namePanel.add(topicker);
        TitledBorder border1 = new TitledBorder("EXPIRY DRUG ");
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

    private JPanel PriceSelectedMethod() {
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        JLabel from = new JLabel("FROM");
        JLabel TO = new JLabel("TO");
        SpinnerModel model = new SpinnerNumberModel(50, 1, 1000, 10);
        SpinnerModel models = new SpinnerNumberModel(50, 1, 1000, 10);
        fromSpiner = new JSpinner(model);
        fromSpiner.addChangeListener(this);
        fromSpiner.setPreferredSize(new Dimension(150, 50));
        toSpiner = new JSpinner(models);
        toSpiner.addChangeListener(this);
        toSpiner.setPreferredSize(new Dimension(150, 50));
        namePanel.add(from);
        namePanel.add(fromSpiner);
        namePanel.add(TO);
        namePanel.add(toSpiner);
        TitledBorder border1 = new TitledBorder("UNIT PRICEDRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel CompanySelectedMethod() {
        searchfield = new JTextField();
        searchfield.setPreferredSize(new Dimension(200, 75));
        searchfield.addCaretListener(this);
        // searchfield.setEditable(false);
        searchfield.setEnabled(true);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        // namePanel.setPreferredSize(new Dimension(800, 100));
        namePanel.add(searchfield);
        TitledBorder border1 = new TitledBorder("COMPANY NAME");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(namePanel);
        return namePanel;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == searchfield) {
            text = searchfield.getText().trim();
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
    }

}
