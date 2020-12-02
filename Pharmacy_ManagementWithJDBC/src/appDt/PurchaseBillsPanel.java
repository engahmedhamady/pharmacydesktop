/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.PurchasesBillsBean;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

/**
 *
 * @author ahmed
 */
public class PurchaseBillsPanel extends JPanel implements ActionListener, ItemListener, CaretListener, ChangeListener {
StoreGetWay getWay = new StoreGetWay();
 String text;
    int totalfromNumber, totaltoNumber, netfromNumber, nettoNumber, profitfromNumber, profittoNumber;
    int x = 0;
    int selectedrow;
    Date fromdate, todate;
    String[] columnNames = {"name", "date", "type", "unit_price", "quantity", "total", "company"};
    String[] items = {"------", "ALL", "BILL-CODE", "DATE", "TOTAL"};
    String[] header = {"BILL-CODE", "TOTAL", "DATE"};
    JTable exampleJTable, exampleJTable2;
    JScrollPane sp, sp2;
    JTextField search_bill_code;
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    public static DefaultTableModel defTableModel2 = new DefaultTableModel();
    JButton search, clear;
    JPanel eastsearchPanel, westsearchPanel, searchPanel, namePanel, quantityPanel, typePanel, ALLPanel;
    JComboBox cb;
    JDialog view_dialog;
    JSpinner totaltoSpiner, totalfromSpiner, nettoSpiner, netfromSpiner, profittoSpiner, profitfromSpiner;
    JDateChooser frompicker, topicker;
    JTextFieldDateEditor editor1, editor2;

    // constructor
    public PurchaseBillsPanel() throws SQLException {
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
        defTableModel.setColumnIdentifiers(header);// // set columns names
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
                    try {
                        viewdialog();
                    } catch (SQLException ex) {
                        Logger.getLogger(PurchaseBillsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });
        exampleJTable2 = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        exampleJTable2 = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        // set jtable as defaultmodel
        exampleJTable2.setModel(defTableModel2);
        // add table to scorllpane
        sp2 = new JScrollPane(exampleJTable2);
        sp2.getViewport().setBackground(Color.pink);
        exampleJTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // set number columns
        defTableModel2.setColumnCount(7);
        exampleJTable2.getTableHeader().setPreferredSize(new Dimension(sp2.getWidth(), 50));// set size header table
        exampleJTable2.getTableHeader().setBackground(Color.PINK);// set background header table
        exampleJTable2.setBackground(Color.yellow); // set background table 
        exampleJTable2.setRowHeight(30);// set row hieght table
        exampleJTable2.getTableHeader().setEnabled(false); // set header enable to prevent  move columns

        // set columns names
        defTableModel2.setColumnIdentifiers(columnNames);
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
        // define combobox
        cb = new JComboBox(items);

        cb.setPreferredSize(new Dimension(75, 50));
        cb.addItemListener(this);
        cb.setBackground(Color.yellow);
        // organize component in group layout   
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addGap(100)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(clear)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(search)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(cb)).addGap(70));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE)
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

    //------------------------------------------------------edit dialog-method-------------------------------------------------------------------
// action performed for search button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {

            if (x == 1) {
                defTableModel.setNumRows(0);

                List<PurchasesBillsBean> l = getWay.listAllPurchases();
                if (l != null && l.size() > 0) {
                    int i = 0;
                    for (i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getBillCode(),
                            l.get(i).getTotal(), l.get(i).getDateBill()});

                    }

                }

            }
            if (x == 2) {
                defTableModel.setNumRows(0);

                List<PurchasesBillsBean> l = getWay.findByCodePurchases(Integer.parseInt(text));

                if (l != null && l.size() > 0) {
                    int i = 0;
                    for (i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getBillCode(),
                            l.get(i).getTotal(), l.get(i).getDateBill()});

                    }
                }
            }

            //---------------------------------------------------------------------------select by total------------------------------------------------------------------------
            if (x == 3) {
                defTableModel.setNumRows(0);

                List<PurchasesBillsBean> l = getWay.findByTotalPurchases(totalfromNumber, totaltoNumber);

                if (l != null && l.size() > 0) {
                    int i = 0;
                    for (i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getBillCode(),
                            l.get(i).getTotal(), l.get(i).getDateBill()});

                    }
                }

            }
            //----------------------------------------------------- select by date ------------------------------------------------------------------------------
            if (x == 4) {
                try {
                    defTableModel.setNumRows(0);
                    fromdate = new SimpleDateFormat("yyyy-mm-dd").parse((((JTextField) frompicker.getDateEditor().getUiComponent()).getText()));
                    todate = new SimpleDateFormat("yyyy-mm-dd").parse((((JTextField) topicker.getDateEditor().getUiComponent()).getText()));

                    List<PurchasesBillsBean> l = getWay.findByDatePurchases(fromdate, todate);
                    if (l != null && l.size() > 0) {
                        int i = 0;
                        for (i = 0; i < l.size(); i++) {
                            defTableModel.addRow(new Object[]{l.get(i).getBillCode(),
                                l.get(i).getTotal(), l.get(i).getDateBill()});

                        }
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "try again");
                }
            }

        }
        if (e.getSource() == clear) {
            defTableModel.setRowCount(0);
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

            if (cb.getSelectedItem().equals("BILL-CODE")) {
                billCodeSelectedMethod();
                x = 2;
            }
            if (cb.getSelectedItem().equals("TOTAL")) {
                totalSelectedMethod();
                x = 3;
            }
            if (cb.getSelectedItem().equals("DATE")) {
                dateSelectedMethod();
                x = 4;
            }

        }

    }

    private JPanel billCodeSelectedMethod() {
        search_bill_code = new JTextField();
        search_bill_code.setPreferredSize(new Dimension(200, 75));
        search_bill_code.addCaretListener(this);

        search_bill_code.setEnabled(true);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        namePanel.add(search_bill_code);
        TitledBorder border1 = new TitledBorder("BILL-CODE");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel totalSelectedMethod() {
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        JLabel from = new JLabel("FROM");
        JLabel TO = new JLabel("TO");
        SpinnerModel model = new SpinnerNumberModel(50, 0, 1000000000, 10);
        SpinnerModel models = new SpinnerNumberModel(50, 0, 1000000000, 10);
        totalfromSpiner = new JSpinner(model);
        totalfromSpiner.addChangeListener(this);
        totalfromSpiner.setPreferredSize(new Dimension(150, 50));
        totaltoSpiner = new JSpinner(models);
        totaltoSpiner.addChangeListener(this);
        totaltoSpiner.setPreferredSize(new Dimension(150, 50));
        namePanel.add(from);
        namePanel.add(totalfromSpiner);
        namePanel.add(TO);
        namePanel.add(totaltoSpiner);
        TitledBorder border1 = new TitledBorder("BILL TOTAL ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westsearchPanel.setBorder(border1);
        westsearchPanel.removeAll();
        westsearchPanel.repaint();
        westsearchPanel.revalidate();
        westsearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel dateSelectedMethod() {
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
        TitledBorder border1 = new TitledBorder("BILL DATE ");
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

    public JDialog viewdialog() throws SQLException {
        view_dialog = new JDialog();
        view_dialog.setPreferredSize(new Dimension(600, 500));
        view_dialog.setLayout(new BorderLayout());
        view_dialog.setPreferredSize(new Dimension(400, 400));

        JPanel south = new JPanel();
        south.setBackground(Color.pink);
        view_dialog.setLayout(new BorderLayout());
        view_dialog.add(sp2, BorderLayout.CENTER);
        view_dialog.add(south, BorderLayout.SOUTH);
        view_dialog.pack();
        view_dialog.setVisible(true);
        defTableModel2.setRowCount(0);
        int bill_code_selected = (int) exampleJTable.getValueAt(selectedrow, 0);

        List<PurchasesBillsBean> l = getWay.findByCodePurchases(bill_code_selected);

        defTableModel2.addRow(new Object[]{l.get(0).getDrugName(), l.get(0).getDateBill(),
            l.get(0).getDrugType(), l.get(0).getPurchasePrice(), l.get(0).getQuantityDrug(),
            l.get(0).getTotal(), l.get(0).getCompany()});
        return view_dialog;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == search_bill_code) {
            text = search_bill_code.getText().trim();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == totalfromSpiner) {
            totalfromNumber = (int) totalfromSpiner.getValue();

        }
        if (e.getSource() == totaltoSpiner) {
            totaltoNumber = (int) totaltoSpiner.getValue();

        }

    }

}
