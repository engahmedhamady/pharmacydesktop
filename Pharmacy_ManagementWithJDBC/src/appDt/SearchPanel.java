/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;


import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.DrugsBean;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
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


/**
 *
 * @author ahmed
 */
public class SearchPanel extends JPanel implements ActionListener, ItemListener, CaretListener, ChangeListener {
 StoreGetWay getWay = new StoreGetWay();
    String text;
    String stodate, sfromdate, type;
    int fromNumber, toNumber;
    int x = 0;
    Date todate, fromdate;
    private JTable table;
    String[] columnNames = {"Name", "TYPE", "Price", "Discount", "Quantity", "Company"};
    String[] items = {"------", "ALL", "Name", "TYPE", "Price"};
    String[] typesItems = {"------", "capsule", "ointment", "injection", "pill"};
    JTable exampleJTable;
    JScrollPane sp;
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    JButton search, edit, delete, cancel;
    JTextField searchfield;
    JPanel eastsearchPanel, westsearchPanel, searchPanel, namePanel, quantityPanel, typePanel, ALLPanel;
    JComboBox cb, typeCB;
    JDialog Edit_Delete_dialog;
    JSpinner toSpiner, fromSpiner;
    JDateChooser fromdatechooser, todatechooser;
    JTextFieldDateEditor fromeditor, toeditor;
    // constructor

    public SearchPanel() throws SQLException {
        //-----------------------------------------------------------------------------------------------------------------------------------------------------------------
        // set super panel as border layout
        super(new BorderLayout());
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
        exampleJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // set number columns
        defTableModel.setColumnCount(7);
        // set columns names
        defTableModel.setColumnIdentifiers(columnNames);
        // add mouselistener to table
        exampleJTable.setModel(defTableModel);  // set jtable as defaultmodel
        sp = new JScrollPane(exampleJTable);  // add table to scorllpane
        sp.getViewport().setBackground(Color.pink);
        exampleJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defTableModel.setColumnCount(7);// set number columns
        defTableModel.setColumnIdentifiers(columnNames);// // set columns names
        exampleJTable.getTableHeader().setPreferredSize(new Dimension(sp.getWidth(), 75));// set size header table
        exampleJTable.getTableHeader().setBackground(Color.PINK);// set background header table
        exampleJTable.setBackground(Color.yellow); // set background table 
        exampleJTable.setRowHeight(50);// set row hieght table
        exampleJTable.getTableHeader().setEnabled(false); // set header enable to prevent  move columns

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
        search.setPreferredSize(new Dimension(100, 100));
        search.addActionListener(this);
        search.setBackground(Color.GREEN);
        // define combobox
        cb = new JComboBox();
        cb.setBackground(Color.yellow);
        //MenuItem all = 
        cb.addItem(items[0]);
        cb.addItem(items[1]);
        cb.addItem(items[2]);
        cb.addItem(items[3]);
        cb.addItem(items[4]);
       

        cb.setPreferredSize(new Dimension(75, 50));
        cb.addItemListener(this);

        // organize component in group layout   
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(200)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(search)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(cb)).addGap(70));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE)
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

// action performed for search button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {

            //---------------------------------------------------------------------- select all---------------------------------------------------------------------------- 
            if (x == 1) {
                defTableModel.setNumRows(0);

                List<DrugsBean> l = getWay.listAllSearch();
                for (int i = 0; i < l.size(); i++) {
                    defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                        l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                        l.get(i).getCompany()});
                   
                }
            }
            //----------------------------------------------------------------select by name----------------------------------------------------------------------------
            if (x == 2) {
                defTableModel.setNumRows(0);
                 List<DrugsBean> l = getWay.findByNameSearch(text);
                for (int i = 0; i < l.size(); i++) {
                    defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                        l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                        l.get(i).getCompany()});
                    i++;
                }
            }

            //---------------------------------------------------------------------------select by  capsule type------------------------------------------------------------------------
            if (x == 3) {
                type = "capsule";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByTypeSearch(type);
                for (int i = 0; i < l.size(); i++) {
                    defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                        l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                       l.get(i).getCompany()});
                    i++;
                }

            }
            //----------------------------------------------------- select by ointment type ------------------------------------------------------------------------------
            if (x == 4) {
                type = "ointment";
                defTableModel.setNumRows(0);
               List<DrugsBean> l = getWay.findByTypeSearch(type);

                for (int i = 0; i < l.size(); i++) {
                    defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                        l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                         l.get(i).getCompany()});
                    i++;
                }
            }

            //--------------------------------------------------------------- select by injection type ---------------------------------------------------------------------
            if (x == 5) {
                type = "injection";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByTypeSearch(type);
 for (int i = 0; i < l.size(); i++) {
                    defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                        l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                         l.get(i).getCompany()});
                    i++;
                }

            }

        }
        // ==================================== selection by pill type ===============================
        if (x == 6) {
            type = "pill";
            defTableModel.setNumRows(0);
           List<DrugsBean> l = getWay.findByTypeSearch(type);
 for (int i = 0; i < l.size(); i++) {
                defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                    l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                     l.get(i).getCompany()});
                i++;
            }

        }

        //==================================== select by price ============================================
        if (x == 8) {

            defTableModel.setNumRows(0);
            List<DrugsBean> l = getWay.findByPriceSearch(fromNumber, toNumber);
            if (l != null) {
                for (int i = 0; i < l.size(); i++) {
                    defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                        l.get(i).getSellingPrice(), l.get(i).getDiscount(), l.get(i).getQuantity(),
                         l.get(i).getCompany()});

                }
            }
        }
        //=
        
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

            if (cb.getSelectedItem().equals("Price")) {
                PriceSelectedMethod();
                x = 8;
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
        // searchfield.setEditable(false);
        searchfield.setEnabled(true);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        // namePanel.setPreferredSize(new Dimension(800, 100));
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

    private JPanel ExpirySelectedMethod() {
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        JLabel from = new JLabel("FROM");
        JLabel TO = new JLabel("TO");
        fromdatechooser = new JDateChooser();
        fromdatechooser.setBackground(Color.yellow);
        fromdatechooser.setDateFormatString("yyyy-MM-dd");
        fromdatechooser.setSize(new Dimension(150, 40));
        fromeditor = (JTextFieldDateEditor) fromdatechooser.getDateEditor();
        fromeditor.addCaretListener(this);
        fromeditor.setText("0000-00-00");
        fromeditor.setEditable(false);
        fromeditor.setPreferredSize(new Dimension(150, 40));
        todatechooser = new JDateChooser();
        todatechooser.setBackground(Color.yellow);
        todatechooser.setDateFormatString("yyyy-MM-dd");
        todatechooser.setSize(new Dimension(150, 40));

        toeditor = (JTextFieldDateEditor) todatechooser.getDateEditor();
        toeditor.addCaretListener(this);
        toeditor.setText("0000-00-00");
        toeditor.setEditable(false);
        toeditor.setPreferredSize(new Dimension(150, 40));
        namePanel.add(from);
        namePanel.add(fromdatechooser);
        namePanel.add(TO);
        namePanel.add(todatechooser);
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

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == searchfield) {
            text = searchfield.getText();
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
