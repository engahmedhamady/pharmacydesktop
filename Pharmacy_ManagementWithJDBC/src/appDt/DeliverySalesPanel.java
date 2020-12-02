/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.DeliveryBillsBean;
import com.store.common.beans.SalesBillsBean;
import com.store.dal.entities.DeliveryBills;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ahmed
 */
public class DeliverySalesPanel extends JPanel implements ActionListener, CaretListener, ItemListener, ChangeListener {
  StoreGetWay getWay = new StoreGetWay();
 int rowSelected1, rowSelected2, totalNet = 0;
    int totalProfits = 0;
    int numberDrug1, numberDrug2 = 0;
    int unitNumber = 1;
    int x;
    private int row;
    int billCodeSelected = 0;
    String text;
    private static final String[] header = {"BILL_CODE"};
    String[] items = {"------", "ALL", "BILL_CODE"};
    String[] columnNames = {"Name", "Expiry", "UnitPrice", "UnitNumber", "Total", "discount ", "Net"};
    JTable exampleJTable = new JTable();
    JTable exampleJTable2 = new JTable();
    TableModel tabModel = exampleJTable.getModel();
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    public static DefaultTableModel defTableModel2 = new DefaultTableModel();
    private JScrollPane scrollPane = new JScrollPane(exampleJTable);
    private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
    private JScrollPane scrollPane2 = new JScrollPane(exampleJTable2);
    private JScrollBar vScroll2 = scrollPane2.getVerticalScrollBar();
    JButton search, clear;
    JButton save, delete, edit, cancel;
    JButton editCancel, editAddDrug, editOk;
    JButton addDrugOk, addDrugCancel;
    JButton viewDelete, viewEdit, viewCancel;
    JButton viewEditOk, viewEditCancel;
    JPanel south, north, center;
    JLabel bill_code, unitLabel, nameLabel, unitLabelEdit;
    JTextField billCodeField, addNameField;
    public static JDialog saveEditDeleteDialog, editViewDialog, drugDialog, viewDialog, viewDialogEditDialog;
    JSpinner spiner, sniperEdit;
    JComboBox cb;
    DeliveryBillsBean db;

    public DeliverySalesPanel() {
        // set super panel as border layout
        super(new BorderLayout());
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------           
        exampleJTable = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        exampleJTable.setModel(defTableModel);  // set jtable as defaultmodel
        scrollPane = new JScrollPane(exampleJTable);  // add table to scorllpane
        scrollPane.getViewport().setBackground(Color.pink);
        exampleJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defTableModel.setColumnCount(1);// set number columns
        defTableModel.setColumnIdentifiers(header);// // set columns names
        exampleJTable.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 75));// set size header table
        exampleJTable.getTableHeader().setBackground(Color.PINK);// set background header table
        exampleJTable.setBackground(Color.yellow); // set background table 
        exampleJTable.setRowHeight(50);// set row hieght table
        exampleJTable.getTableHeader().setEnabled(false); // set header enable to prevent  move columns
        scrollPane.setSize(new Dimension(200, 500));
        // add mouselistener to table
        exampleJTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    rowSelected1 = exampleJTable.getSelectedRow();
                    billCodeSelected = (int) defTableModel.getValueAt(rowSelected1, 0);
                    saveEditDeletDaialog();
                    HomePage.frame.setEnabled(false);

                }
            }
        });

        exampleJTable2 = new JTable() {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        // set jtable as defaultmodel
        exampleJTable2.setModel(defTableModel2);
        // add table to scorllpane
        scrollPane2 = new JScrollPane(exampleJTable2);
        scrollPane2.getViewport().setBackground(Color.pink);
        exampleJTable2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // set number columns
        defTableModel2.setColumnCount(7);
        exampleJTable2.getTableHeader().setPreferredSize(new Dimension(scrollPane2.getWidth(), 50));// set size header table
        exampleJTable2.getTableHeader().setBackground(Color.PINK);// set background header table
        exampleJTable2.setBackground(Color.yellow); // set background table 
        exampleJTable2.setRowHeight(30);// set row hieght table
        exampleJTable2.getTableHeader().setEnabled(false); // set header enable to prevent  move columns

        // set columns names
        defTableModel2.setColumnIdentifiers(columnNames);
        numberDrug2 = defTableModel2.getRowCount();
        // add mouselistener to table
        exampleJTable2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    rowSelected2 = exampleJTable2.getSelectedRow();
                    viewDialogmethod();
                    editViewDialog.setEnabled(false);
                }
            }
        });

        //-------------------------------------------------------------------------------------------------------NORTH PANEL -----------------------------------
        north = new JPanel();
        north.setBackground(Color.cyan);
        billCodeField = new JTextField();
        billCodeField.setPreferredSize(new Dimension(200, 75));
        search = new JButton("search");
        search.setBackground(Color.green);
        search.setPreferredSize(new Dimension(150, 75));
        clear = new JButton("clear");
        clear.setBackground(Color.green);
        clear.setPreferredSize(new Dimension(150, 75));
        // define combobox
        cb = new JComboBox();
        cb.setBackground(Color.YELLOW);
        cb.setPreferredSize(new Dimension(100, 75));
        //MenuItem all = 
        cb.addItem(items[0]);
        cb.addItem(items[1]);
        cb.addItem(items[2]);
        search.setEnabled(false);
        clear.setEnabled(false);
        billCodeField.setEnabled(false);
        cb.addItemListener(this);
        search.addActionListener(this);
        billCodeField.addCaretListener(this);
        clear.addActionListener(this);
        north.add(clear);
        north.add(search);
        north.add(billCodeField);
        north.add(cb);
        //------------------------------------------------------------------------------------------------------------------------------------------------------
        add(north, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

    }
    //---------------------------------------------------------------------------saveeditdelete method---------------------------

    public JDialog saveEditDeletDaialog() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        north.setBackground(Color.gray);
        edit = new JButton("edit");
        edit.setPreferredSize(new Dimension(75, 50));
        edit.addActionListener(this);
        edit.setBackground(Color.green);
        save = new JButton("Save");
        save.setPreferredSize(new Dimension(75, 50));
        save.addActionListener(this);
        save.setBackground(Color.green);
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
        center.add(save);
        center.add(edit);
        center.add(delete);
        center.add(cancel);
        //--------------------------------------------------------------save_edit_delet-dialog----------------------------------------------------------------
        saveEditDeleteDialog = new JDialog();
        saveEditDeleteDialog.setLayout(new BorderLayout());
        saveEditDeleteDialog.setPreferredSize(new Dimension(500, 300));
        saveEditDeleteDialog.add(north, BorderLayout.NORTH);
        saveEditDeleteDialog.add(center, BorderLayout.CENTER);
        saveEditDeleteDialog.setBackground(Color.gray);
        saveEditDeleteDialog.pack();
        saveEditDeleteDialog.setVisible(true);
        saveEditDeleteDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                saveEditDeleteDialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });

        return saveEditDeleteDialog;
    }
    //-----------------------------------------------------edit_View_Dialog method ---------------------------------------------------------------

    public JDialog editViewDialog() throws SQLException {
        editViewDialog = new JDialog();
        editViewDialog.setPreferredSize(new Dimension(600, 500));
        editViewDialog.setLayout(new BorderLayout());
        editViewDialog.setPreferredSize(new Dimension(400, 400));
        editAddDrug = new JButton("add drug");
        editAddDrug.setBackground(Color.green);
        editAddDrug.setPreferredSize(new Dimension(110, 50));
        editOk = new JButton("OK");
        editOk.setBackground(Color.green);
        editOk.setPreferredSize(new Dimension(75, 50));
        editCancel = new JButton("Cancel");
        editCancel.setBackground(Color.green);
        editCancel.setPreferredSize(new Dimension(75, 50));
        JPanel south = new JPanel();
        south.setBackground(Color.pink);
        south.add(editAddDrug);
        south.add(editOk);
        south.add(editCancel);
        editAddDrug.addActionListener(this);
        editCancel.addActionListener(this);
        editOk.addActionListener(this);
        editViewDialog.setLayout(new BorderLayout());
        editViewDialog.add(scrollPane2, BorderLayout.CENTER);
        editViewDialog.add(south, BorderLayout.SOUTH);
        editViewDialog.pack();
        editViewDialog.setVisible(true);
        defTableModel2.setRowCount(0);
        System.out.println(billCodeSelected);
        List<DeliveryBillsBean> l = getWay.listByCodeDelivery(billCodeSelected);

        if (l != null && l.size() != 0) {
            System.out.println(billCodeSelected);
            if (l.get(0).getDrug1() != null) {
                defTableModel2.addRow(new Object[]{l.get(0).getDrug1(), l.get(0).getUnitprice1(),
                    l.get(0).getQuantity1(), l.get(0).getTotal1(), l.get(0).getDiscount1(), l.get(0).getNet1()});

            }
            if (l.get(0).getDrug2() != null) {
                defTableModel2.addRow(new Object[]{l.get(0).getDrug2(), l.get(0).getUnitprice2(),
                    l.get(0).getQuantity2(), l.get(0).getTotal2(), l.get(0).getDiscount2(), l.get(0).getNet2()});
            }
            if (l.get(0).getDrug3() != null) {
                defTableModel2.addRow(new Object[]{l.get(0).getDrug3(), l.get(0).getUnitprice3(),
                    l.get(0).getQuantity3(), l.get(0).getTotal3(), l.get(0).getDiscount3(), l.get(0).getNet3()});
            }
            if (l.get(0).getDrug4() != null) {
                defTableModel2.addRow(new Object[]{l.get(0).getDrug4(), l.get(0).getUnitprice4(),
                    l.get(0).getQuantity4(), l.get(0).getTotal4(), l.get(0).getDiscount4(), l.get(0).getNet4()});
            }
            if (l.get(0).getDrug5() != null) {
                defTableModel2.addRow(new Object[]{l.get(0).getDrug5(), l.get(0).getUnitprice5(),
                    l.get(0).getQuantity5(), l.get(0).getTotal5(), l.get(0).getDiscount5(), l.get(0).getNet5()});
            }
        } else {
            JOptionPane.showMessageDialog(null, "TRY AGAIN");
        }
        editViewDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                editViewDialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        return editViewDialog;
    }

    //  drugdialogmethod to panel add drug to table
    public JDialog drugDialog() {
        JPanel dNorth = new JPanel();
        dNorth.setBackground(Color.yellow);
        nameLabel = new JLabel("DRUG NAME ");
        nameLabel.setPreferredSize(new Dimension(100, 75));
        addNameField = new JTextField();
        addNameField.setPreferredSize(new Dimension(150, 75));
        addNameField.addCaretListener(this);
        dNorth.add(nameLabel);
        dNorth.add(addNameField);
        //----------------------------------------------------------------------------------           
        JPanel dCenter = new JPanel();
        dCenter.setBackground(Color.yellow);
        SpinnerModel dModel = new SpinnerNumberModel(1, 0, 1000, 1);
        spiner = new JSpinner(dModel);
        spiner.addChangeListener(this);
        spiner.setPreferredSize(new Dimension(150, 50));
        unitLabel = new JLabel("UNIT NUMBER");
        unitLabel.setPreferredSize(new Dimension(100, 75));
        dCenter.add(unitLabel);
        dCenter.add(spiner);
        //---------------------------------------------------------------------------------------         
        JPanel dSouth = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
        dSouth.setBackground(Color.yellow);
        addDrugOk = new JButton("OK");
        addDrugOk.setPreferredSize(new Dimension(100, 75));
        addDrugOk.setBackground(Color.green);
        addDrugCancel = new JButton("cancel");
        addDrugCancel.setPreferredSize(new Dimension(100, 75));
        addDrugCancel.setBackground(Color.green);
        addDrugOk.addActionListener(this);
        addDrugCancel.addActionListener(this);
        dSouth.add(addDrugOk, BorderLayout.WEST);
        dSouth.add(addDrugCancel, BorderLayout.EAST);
        //-----------------------------------------------------------------------------------------------------------     
        JPanel dall = new JPanel(new BorderLayout());
        dall.add(dNorth, BorderLayout.NORTH);
        dall.add(dCenter, BorderLayout.CENTER);
        dall.add(dSouth, BorderLayout.SOUTH);
        //---------------------------------------------------------------------------------------------------------------
        drugDialog = new JDialog();
        drugDialog.setPreferredSize(new Dimension(300, 300));
        drugDialog.setLayout(new BorderLayout(20, 50));
        drugDialog.setPreferredSize(new Dimension(400, 300));
        drugDialog.setLayout(new BorderLayout());
        drugDialog.add(dall, BorderLayout.CENTER);
        drugDialog.pack();
        drugDialog.setVisible(true);
        editViewDialog.setEnabled(false);
        drugDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                drugDialog.dispose();
                editViewDialog.setEnabled(true);
            }
        });
        return drugDialog;
    } // end method

    //--------------------------------------------------------- viewDialogmethod method ------------------------------------------------------
    public JDialog viewDialogmethod() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        north.setBackground(Color.gray);
        viewEdit = new JButton("edit");
        viewEdit.setPreferredSize(new Dimension(75, 50));
        viewEdit.addActionListener(this);
        viewEdit.setBackground(Color.green);

        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING, 40, 100));
        center.setBackground(Color.gray);
        viewDelete = new JButton("delete");
        viewDelete.setPreferredSize(new Dimension(75, 50));
        viewDelete.setBackground(Color.green);
        viewCancel = new JButton("cancel");
        viewCancel.setPreferredSize(new Dimension(75, 50));
        viewCancel.setBackground(Color.green);
        viewCancel.addActionListener(this);
        viewDelete.addActionListener(this);

        center.add(viewEdit);
        center.add(viewDelete);
        center.add(viewCancel);
        //--------------------------------------------------------------save_edit_delet-dialog----------------------------------------------------------------
        viewDialog = new JDialog();
        viewDialog.setLayout(new BorderLayout());
        viewDialog.setPreferredSize(new Dimension(400, 300));
        viewDialog.add(north, BorderLayout.NORTH);
        viewDialog.add(center, BorderLayout.CENTER);
        viewDialog.setBackground(Color.gray);
        viewDialog.pack();
        viewDialog.setVisible(true);
        return viewDialog;

    }
    //-----------------------------------------------------------------------------------------------------------------------------------------

    public JDialog viewDialogEditDialog() {

        viewDialogEditDialog = new JDialog();
        viewDialogEditDialog.setBackground(Color.yellow);
        viewDialogEditDialog.setPreferredSize(new Dimension(300, 300));

        JPanel dCenter = new JPanel();
        dCenter.setBackground(Color.yellow);
        JPanel dSouth = new JPanel();
        dSouth.setBackground(Color.yellow);
        JPanel dAll = new JPanel(new BorderLayout());
        dAll.setBackground(Color.yellow);
        viewDialogEditDialog.setLayout(new BorderLayout(20, 50));
        viewDialogEditDialog.setPreferredSize(new Dimension(400, 300));
        unitLabelEdit = new JLabel("UNIT NUMBER");
        unitLabelEdit.setPreferredSize(new Dimension(100, 75));
        viewEditOk = new JButton("OK");
        viewEditOk.setPreferredSize(new Dimension(100, 75));
        viewEditCancel = new JButton("cancel");
        viewEditCancel.setPreferredSize(new Dimension(100, 75));
        SpinnerModel dModelEdit = new SpinnerNumberModel((int) defTableModel2.getValueAt(rowSelected2, 3), 0, 1000, 1);
        sniperEdit = new JSpinner(dModelEdit);
        sniperEdit.addChangeListener(this);
        sniperEdit.setPreferredSize(new Dimension(150, 50));
        dCenter.add(unitLabelEdit);
        dCenter.add(sniperEdit);
        dSouth.add(viewEditOk, BorderLayout.WEST);
        dSouth.add(viewEditCancel, BorderLayout.EAST);
        viewDialogEditDialog.setLayout(new BorderLayout());

        dAll.add(dCenter, BorderLayout.CENTER);
        dAll.add(dSouth, BorderLayout.SOUTH);
        viewDialogEditDialog.add(dAll, BorderLayout.CENTER);
        viewEditOk.addActionListener(this);
        viewEditCancel.addActionListener(this);
        viewDialogEditDialog.pack();
        sniperEdit.addChangeListener(this);
        viewDialogEditDialog.setVisible(true);
        viewDialogEditDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                viewDialogEditDialog.dispose();
                editViewDialog.setEnabled(true);
            }
        });
        return viewDialogEditDialog;

    }
    //---------------------------------------------------action performed method-----------------------------------------------------

    @Override
    public void actionPerformed(ActionEvent e) {
        //------------------------------------------ search button------------------------------------------------------------------      
        if (e.getSource() == search) {

            if (x == 1) {
                defTableModel.setNumRows(0);
                List<DeliveryBillsBean> l = getWay.listAllDelivery();
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getBillcode()});

                    }
                }
            }
            if (x == 2) {

                defTableModel.setNumRows(0);
                List<DeliveryBillsBean> l = getWay.listByCodeDelivery(Integer.parseInt(text));
                if (l != null) {
                    defTableModel.addRow(new Object[]{l.get(0).getBillcode()});
                }

            }

        }

        //-----------------------------------------------------------clear button-------------------------------------------------------------------
        if (e.getSource() == clear) {

            DeliverySalesPanel.defTableModel.setRowCount(0);
            billCodeField.setText("");

        }
        //-----------------------------------------------save button in  save_edit_delete_dialog---------------------------------------------

        if (e.getSource() == save) {
            int billCodes = (int) DeliverySalesPanel.defTableModel.getValueAt(rowSelected1, 0);
            DeliveryBillsBean bean = new DeliveryBillsBean(billCodes);
            SalesBillsBean saveDeliveryBill = getWay.saveDeliveryBill(bean);
            if (saveDeliveryBill != null) {
                JOptionPane.showMessageDialog(null, "SAVED SUCCESSFULLY");
                defTableModel.removeRow(rowSelected1);
            } else {
                JOptionPane.showMessageDialog(null, "SAVED Failed");
            }

            saveEditDeleteDialog.dispose();
            billCodeField.setText("");
            HomePage.frame.setEnabled(true);

        }
        //---------------------------------------edit button in save_edit_delete_dialog----------------------------------------------

        if (e.getSource() == edit) {
            saveEditDeleteDialog.dispose();
            try {
                editViewDialog();
            } catch (SQLException ex) {
                Logger.getLogger(DeliverySalesPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            int x = defTableModel2.getRowCount();
            if (x < 5) {
                editAddDrug.setEnabled(true);
            } else {
                editAddDrug.setEnabled(false);
            }
        }
        //---------------------------------------delete button in save_edit_delete_dialog----------------------------------------------

        if (e.getSource() == delete) {
            int billCodes = (int) defTableModel.getValueAt(rowSelected1, 0);
            DeliveryBillsBean bean = new DeliveryBillsBean(billCodes);

            //     java.sql.Date sqldate = new java.sql.Date (new java.util.Date().getTime());
            DeliveryBillsBean deliveryBill = getWay.deletedeliveryBill(bean);
            if (deliveryBill != null) {

                JOptionPane.showMessageDialog(null, "DELETE SUCCESSFULLY");
                defTableModel.removeRow(rowSelected1);

            } else {
                JOptionPane.showMessageDialog(null, "FAILED DELETE TRY AGAIN");
            }
            saveEditDeleteDialog.dispose();
            HomePage.frame.setEnabled(true);

        }
        //---------------------------------------cancelbutton in save_edit_delete_dialog----------------------------------------------
        if (e.getSource() == cancel) {
            saveEditDeleteDialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        //---------------------------------------------------  editviewok button in edit_view_dialog-------------------------------
        if (e.getSource() == editOk) {
            int billCodes = (int) billCodeSelected;
            row = 0;
            int rowNumber = defTableModel2.getRowCount();

            String drug = (String) defTableModel2.getValueAt(row, 0);
            Date expiry = (Date) defTableModel2.getValueAt(row, 1);
            // int     profit = l.get(0).getProfit() * (int) defTableModel2.getValueAt(row, 3);
            // totalProfits += profit;

            int unitPrice = (int) defTableModel2.getValueAt(row, 2);
            int unitNumber = (int) defTableModel2.getValueAt(row, 3);
            int total = (int) defTableModel2.getValueAt(row, 4);
            int discount = (int) defTableModel2.getValueAt(row, 5);
            int net = (int) defTableModel2.getValueAt(row, 6);
            totalNet += net;
            totalNet = 0;
            totalProfits = 0;
            JOptionPane.showMessageDialog(null, "EDIT SUCCESSFULLY");
            editViewDialog.dispose();
            HomePage.frame.setEnabled(true);
            billCodeField.setText("");

        }

        //---------------------------------------------------  editviewcancel button in edit_view_dialog-------------------------------
        if (e.getSource() == editCancel) {
            editViewDialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        //---------------------------------------------------  editviewadddrug button in edit_view_dialog-------------------------------

        if (e.getSource() == editAddDrug) {
            drugDialog();
            numberDrug2 = defTableModel2.getRowCount();
            editViewDialog.setEnabled(false);
        }

        //----------------------------------------adddrugok button drug_gialog------------------------------------------------------------------------------
        if (e.getSource() == addDrugOk) {
            boolean exit = false;
            unitNumber = 0;
            text = addNameField.getText().trim();
            unitNumber = (int) spiner.getValue();

            if (text != null && text != "" && unitNumber > 0) {
                //defTableModel2.setRowCount(0);
                drugDialog.dispose();
                editViewDialog.setEnabled(true);

            } else {
                addNameField.setText("");
                spiner.setValue(1);
                text = null;
                unitNumber = 0;
                JOptionPane.showMessageDialog(null, "TRY AGAIN ");
            }

            numberDrug2 = defTableModel2.getRowCount();

            if (numberDrug2 > 0 && numberDrug2 < 5) {
                editAddDrug.setEnabled(true);
            }
            if (numberDrug2 >= 5) {
                editAddDrug.setEnabled(false);
            }
        }
        //-----------------------------------------------------addcancel in drug_dialog -------------------------------------------------------
        if (e.getSource() == addDrugCancel) {
            drugDialog.dispose();
            editViewDialog.setEnabled(true);
        }

        //----------------------------------------------------------------------------------------------------------------------------------------------------
        if (e.getSource() == viewDelete) {
            defTableModel2.removeRow(rowSelected2);
            viewDialog.dispose();
            editViewDialog.setEnabled(true);
            if (DeliverySalesPanel.defTableModel2.getRowCount() < 5) {
                editAddDrug.setEnabled(true);
            } else {
                editAddDrug.setEnabled(false);
            }
        }
        //------------------------------------------------------------------------------------------------------------------
        if (e.getSource() == viewCancel) {
            viewDialog.dispose();
            editViewDialog.setEnabled(true);
        }
        //------------------------------------------------------------------------------------------------------------------------------
        if (e.getSource() == viewEdit) {
            viewDialogEditDialog();
            viewDialog.dispose();

        }

        //----------------------------------------------------------------------------------------------------------------
        if (e.getSource() == viewEditOk) {

            int cell = (int) sniperEdit.getValue();
            String Drug = (String) DeliverySalesPanel.defTableModel2.getValueAt(rowSelected2, 0);
            Date expiry = (Date) DeliverySalesPanel.defTableModel2.getValueAt(rowSelected2, 1);
            if (cell > 0) {

                int total = cell * (int) defTableModel2.getValueAt(rowSelected2, 2);
                defTableModel2.setValueAt(cell, rowSelected2, 3);
                defTableModel2.setValueAt(total, rowSelected2, 4);
                // defTableModel2.setValueAt(discount, rowSelected2, 5);
                // defTableModel2.setValueAt((total - discount), rowSelected2, 6);
                viewDialogEditDialog.dispose();
                editViewDialog.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(null, "PLEASE TRY AGAIN ");
            }

        }
        //-----------------------------------------------------------------------------------------------------------------------------------------
        if (e.getSource() == viewEditCancel) {
            viewDialogEditDialog.dispose();
            editViewDialog.setEnabled(true);
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == billCodeField) {
            text = null;
            text = billCodeField.getText().trim();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cb) {

            if (cb.getSelectedItem().equals("ALL")) {

                x = 1;
                billCodeField.setText("");
                search.setEnabled(true);
                clear.setEnabled(true);
                billCodeField.setEnabled(false);
            }
            if (cb.getSelectedItem().equals("BILL_CODE")) {

                x = 2;
                search.setEnabled(true);
                clear.setEnabled(true);
                billCodeField.setEnabled(true);
            }
            if (cb.getSelectedItem().equals("------")) {

                x = 3;
                search.setEnabled(false);
                clear.setEnabled(true);
                billCodeField.setEnabled(false);
            }

        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
    }

    void setDliveryBills(int i, String name, int d, int net, int p, int q, int tt, int un) {

        if (i == 0) {
            System.out.println(i);
            System.out.println(name);
            db.setDrug1(name);
            System.out.println(name);
            db.setDiscount1(d);

            db.setNet1(net);
            db.setProfit1(p);
            db.setQuantity1(q);
            db.setTotal1(tt);
            db.setUnitprice1(un);
        }
        if (i == 1) {
            System.out.println(i);
            System.out.println(name);
            db.setDrug2(name);
            db.setDiscount2(d);;

            db.setNet2(net);
            db.setProfit2(p);
            db.setQuantity2(q);
            db.setTotal2(tt);
            db.setUnitprice2(un);
        }
        if (i == 2) {
            System.out.println(i);
            System.out.println(name);
            db.setDrug3(name);
            db.setDiscount3(d);;

            db.setNet3(net);
            db.setProfit3(p);
            db.setQuantity3(q);
            db.setTotal3(tt);
            db.setUnitprice3(un);
        }
        if (i == 3) {
            System.out.println(i);
            System.out.println(name);
            db.setDrug4(name);
            db.setDiscount4(d);;

            db.setNet4(net);
            db.setProfit4(p);
            db.setQuantity4(q);
            db.setTotal4(tt);
            db.setUnitprice4(un);
        }
        if (i == 4) {
            System.out.println(i);
            System.out.println(name);
            db.setDrug5(name);
            db.setDiscount5(d);;

            db.setNet5(net);
            db.setProfit5(p);
            db.setQuantity5(q);
            db.setTotal5(tt);
            db.setUnitprice5(un);
        }
    }

}
