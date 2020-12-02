
package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.DeliveryBillsBean;
import com.store.common.beans.DrugsBean;
import com.store.common.beans.SalesBillsBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
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
public class NewSalesPanel extends JPanel implements ActionListener, CaretListener, ChangeListener {

    int totalnet = 0;//  totalnet bill
    public static int totalprofits = 0;  // total profit bill
    int code;
    int numberDrug = 0;
    int unitnumber = 1;  // unit number drud
    int selectedrow;
    int editDiscount;
    String text;   //  name of drug
    private static final String[] header = {"Name", "UnitPrice", "UnitNumber", "Total", "discount ", "Net"};//name of columns
    public static JTable exampleJTable = new JTable();     // table bill
    public static TableModel tabModel = exampleJTable.getModel();
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    private JScrollPane scrollPane = new JScrollPane(exampleJTable);
    private JScrollBar vScroll = scrollPane.getVerticalScrollBar();
    // private int row;
    //  private boolean isAutoScroll;
    JButton newBill, AddDrug, Save, Delivery, clear, cancel, edcancel, ededit, eddelete, addcancel, addok, editok, editcancel;
    JPanel south, north, center;
    JLabel bill_code, unitlabel, namelabel;
    public static JTextField bill_code_field, addnamefield;
    JDialog Edit_Delete_dialog, Drug_Dialog, Edit_Dialog;
    public static JSpinner spiner, editspiner;
    StoreGetWay getWay = new StoreGetWay();
    List<DrugsBean> drugsBeans = new ArrayList<>();
    // constructor  

    public NewSalesPanel() {
        super(new BorderLayout());  // set super panel as border layout
        south = new JPanel(new GridLayout(1, 6));
        south.setPreferredSize(new Dimension(100, 100));
        newBill = new JButton("NewBill");
        AddDrug = new JButton("AddDrug");
        Save = new JButton("Save");
        Delivery = new JButton("Delivery");
        clear = new JButton("clear");
        cancel = new JButton("Cancel");
        south.add(newBill);
        south.add(AddDrug);
        south.add(Save);
        south.add(Delivery);
        south.add(clear);
        south.add(cancel);
        newBill.addActionListener(this);
        AddDrug.addActionListener(this);
        Save.addActionListener(this);
        Delivery.addActionListener(this);
        clear.addActionListener(this);
        cancel.addActionListener(this);
        newSalesButtonControl(true, false, false, false, false, false);
        //----------------------------------------------------------------------------------------------------------------------------------------------------------------           
        exampleJTable = new JTable() // set table cells as uneditable
        {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        exampleJTable.setModel(defTableModel);  // set jtable as defaultmodel
        scrollPane = new JScrollPane(exampleJTable);  // add table to scorllpane
        scrollPane.getViewport().setBackground(Color.pink);
        exampleJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        defTableModel.setColumnCount(7);// set number columns
        defTableModel.setColumnIdentifiers(header);// // set columns names
        exampleJTable.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 75));// set size header table
        exampleJTable.getTableHeader().setBackground(Color.PINK);// set background header table
        exampleJTable.setBackground(Color.yellow); // set background table 
        exampleJTable.setRowHeight(50);// set row hieght table
        exampleJTable.getTableHeader().setEnabled(false); // set header enable to prevent  move columns
        numberDrug = defTableModel.getRowCount();
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
        //---------------------------------------------------------north panel---------------------------------------------------------------------------------
        north = new JPanel();
        north.setBackground(Color.cyan);
        bill_code = new JLabel("BILL_CODE");
        bill_code_field = new JTextField();
        bill_code_field.setEditable(false);
        bill_code_field.setBackground(Color.WHITE);
        bill_code_field.addCaretListener(this);
        bill_code_field.setPreferredSize(new Dimension(150, 50));
        bill_code_field.addCaretListener(this);
        north.add(bill_code);
        north.add(bill_code_field);
        //------------------------------------------------------------------------------------------------------------------------------------------------------
        add(north, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
    }// end constructor 
    //  drugdialogmethod to panel add drug to table

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
        SpinnerModel dmodel = new SpinnerNumberModel(1, 0, 1000, 1);
        spiner = new JSpinner(dmodel);
        spiner.addChangeListener(this);
        spiner.setPreferredSize(new Dimension(150, 50));
        unitlabel = new JLabel("UNIT NUMBER");
        unitlabel.setPreferredSize(new Dimension(100, 75));
        quantitypanel.add(unitlabel);
        quantitypanel.add(spiner);
        //---------------------------------------------------------------------------------------         
        JPanel buttonpanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 0));
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
        Drug_Dialog.setPreferredSize(new Dimension(300, 300));
        Drug_Dialog.setLayout(new GridLayout(3, 1));
        Drug_Dialog.setPreferredSize(new Dimension(400, 300));
        Drug_Dialog.add(namepanel);
        Drug_Dialog.add(quantitypanel);
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
    }// end method
    // method editdeletdialog to dialog edit or delete drug in table

    public void editdeletedialogmethod() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        north.setBackground(Color.gray);
        ededit = new JButton("edit");
        ededit.setPreferredSize(new Dimension(75, 50));
        ededit.addActionListener(this);
        ededit.setBackground(Color.green);
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING, 40, 100));
        center.setBackground(Color.gray);
        eddelete = new JButton("delete");
        eddelete.setPreferredSize(new Dimension(75, 50));
        eddelete.setBackground(Color.green);
        edcancel = new JButton("cancel");
        edcancel.setPreferredSize(new Dimension(75, 50));
        edcancel.setBackground(Color.green);
        edcancel.addActionListener(this);
        eddelete.addActionListener(this);
        center.add(ededit);
        center.add(eddelete);
        center.add(edcancel);
        //edit_delet-dialog
        Edit_Delete_dialog = new JDialog();
        Edit_Delete_dialog.setLayout(new BorderLayout());
        Edit_Delete_dialog.setPreferredSize(new Dimension(400, 300));
        Edit_Delete_dialog.add(north, BorderLayout.NORTH);
        Edit_Delete_dialog.add(center, BorderLayout.CENTER);
        Edit_Delete_dialog.setBackground(Color.gray);
        Edit_Delete_dialog.pack();
        Edit_Delete_dialog.setVisible(true);
        Edit_Delete_dialog.addWindowListener(
                new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Edit_Delete_dialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
    }// end method
    //------------------------------------------------------edit dialog-method-------------------------------------------------------------------

    public JDialog EditDialog() {
        JPanel dcenter = new JPanel();
        dcenter.setBackground(Color.gray);
        JLabel unitnumber = new JLabel("UNIT NUMBER");
        unitnumber.setPreferredSize(new Dimension(100, 75));
        SpinnerModel dmodeledit = new SpinnerNumberModel(1, 1, 1000, 1);
        editspiner = new JSpinner(dmodeledit);
        editspiner.addChangeListener(this);
        editspiner.setPreferredSize(new Dimension(150, 50));
        editspiner.setValue(defTableModel.getValueAt(selectedrow, 2));
        editDiscount = (int) defTableModel.getValueAt(selectedrow, 4) / (int) defTableModel.getValueAt(selectedrow, 2);
        dcenter.add(unitnumber);
        dcenter.add(editspiner);
        JPanel dsouth = new JPanel();
        dsouth.setBackground(Color.gray);
        editok = new JButton("OK");
        editok.setBackground(Color.green);
        editok.setPreferredSize(new Dimension(100, 75));
        editcancel = new JButton("cancel");
        editcancel.setBackground(Color.green);
        editcancel.setPreferredSize(new Dimension(100, 75));
        dsouth.add(editok, BorderLayout.WEST);
        dsouth.add(editcancel, BorderLayout.EAST);
        editok.addActionListener(this);
        editcancel.addActionListener(this);
        JPanel dall = new JPanel(new BorderLayout());
        dall.setBackground(Color.gray);
        dall.add(dcenter, BorderLayout.CENTER);
        dall.add(dsouth, BorderLayout.SOUTH);
        Edit_Dialog = new JDialog();
        Edit_Dialog.setBackground(Color.gray);
        Edit_Dialog.setBackground(Color.gray);
        Edit_Dialog.setPreferredSize(new Dimension(300, 300));
        Edit_Dialog.setLayout(new BorderLayout(20, 50));
        Edit_Dialog.setPreferredSize(new Dimension(400, 300));
        Edit_Dialog.add(dall, BorderLayout.CENTER);
        Edit_Dialog.pack();
        editspiner.addChangeListener(this);
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
// actionperformed method

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newBill) {

            code = getWay.createBill();
            bill_code_field.setText(Integer.toString(code));
            newSalesButtonControl(false, true, false, false, false, true);
        }
        if (e.getSource() == AddDrug) {
            drugDialog();
            numberDrug = defTableModel.getRowCount();
            HomePage.frame.setEnabled(false);
        }
        if (e.getSource() == Save) {
            java.util.Date date = new java.util.Date(new java.util.Date().getTime());
            int count = defTableModel.getRowCount();
            int totalProfit = 0;
            int totalNet = 0;
            int billCode = Integer.parseInt(bill_code_field.getText());
            SalesBillsBean salesBillsBean = new SalesBillsBean();
            salesBillsBean.setBillcode(billCode);
            salesBillsBean.setBilldate(date);
            if (drugsBeans.size() > 0) {
                salesBillsBean.setDrug1(drugsBeans.get(0).getName());
                salesBillsBean.setUnitprice1(drugsBeans.get(0).getSellingPrice());
                salesBillsBean.setQuantity1((int) defTableModel.getValueAt(0, 2));
                salesBillsBean.setTotal1((int) defTableModel.getValueAt(0, 3));
                salesBillsBean.setDiscount1((int) defTableModel.getValueAt(0, 4));
                salesBillsBean.setNet1((int) defTableModel.getValueAt(0, 5));
                salesBillsBean.setProfit1((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
                totalNet += (int) defTableModel.getValueAt(0, 5);
                totalProfit += ((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
            }
            if (drugsBeans.size() > 1) {
                salesBillsBean.setDrug2(drugsBeans.get(1).getName());
                salesBillsBean.setUnitprice2(drugsBeans.get(1).getSellingPrice());
                salesBillsBean.setQuantity2((int) defTableModel.getValueAt(1, 2));
                salesBillsBean.setTotal2((int) defTableModel.getValueAt(1, 3));
                salesBillsBean.setDiscount2((int) defTableModel.getValueAt(1, 4));
                salesBillsBean.setNet2((int) defTableModel.getValueAt(1, 5));
                salesBillsBean.setProfit2((int) defTableModel.getValueAt(1, 2) * drugsBeans.get(1).getProfit());

                totalNet += (int) defTableModel.getValueAt(1, 5);
                totalProfit += ((int) defTableModel.getValueAt(1, 2) * drugsBeans.get(1).getProfit());
            }
            if (drugsBeans.size() > 2) {
                salesBillsBean.setDrug3(drugsBeans.get(2).getName());
                salesBillsBean.setUnitprice3(drugsBeans.get(2).getSellingPrice());
                salesBillsBean.setQuantity3((int) defTableModel.getValueAt(2, 2));
                salesBillsBean.setTotal3((int) defTableModel.getValueAt(2, 3));
                salesBillsBean.setDiscount3((int) defTableModel.getValueAt(2, 4));
                salesBillsBean.setNet3((int) defTableModel.getValueAt(2, 5));
                salesBillsBean.setProfit3((int) defTableModel.getValueAt(2, 2) * drugsBeans.get(2).getProfit());

                totalNet += (int) defTableModel.getValueAt(2, 5);
                totalProfit += ((int) defTableModel.getValueAt(2, 2) * drugsBeans.get(2).getProfit());
            }
            if (drugsBeans.size() > 3) {
                salesBillsBean.setDrug4(drugsBeans.get(3).getName());
                salesBillsBean.setUnitprice4(drugsBeans.get(3).getSellingPrice());
                salesBillsBean.setQuantity4((int) defTableModel.getValueAt(3, 2));
                salesBillsBean.setTotal4((int) defTableModel.getValueAt(3, 3));
                salesBillsBean.setDiscount4((int) defTableModel.getValueAt(3, 4));
                salesBillsBean.setNet4((int) defTableModel.getValueAt(3, 5));
                salesBillsBean.setProfit4((int) defTableModel.getValueAt(3, 2) * drugsBeans.get(3).getProfit());

                totalNet += (int) defTableModel.getValueAt(3, 5);
                totalProfit += ((int) defTableModel.getValueAt(3, 2) * drugsBeans.get(3).getProfit());
            }
            if (drugsBeans.size() > 4) {
                salesBillsBean.setDrug5(drugsBeans.get(4).getName());
                salesBillsBean.setUnitprice5(drugsBeans.get(4).getSellingPrice());
                salesBillsBean.setQuantity5((int) defTableModel.getValueAt(4, 2));
                salesBillsBean.setTotal5((int) defTableModel.getValueAt(4, 3));
                salesBillsBean.setDiscount5((int) defTableModel.getValueAt(4, 4));
                salesBillsBean.setNet5((int) defTableModel.getValueAt(4, 5));
                salesBillsBean.setProfit5((int) defTableModel.getValueAt(4, 2) * drugsBeans.get(4).getProfit());
                totalNet += (int) defTableModel.getValueAt(4, 5);
                totalProfit += ((int) defTableModel.getValueAt(4, 2) * drugsBeans.get(4).getProfit());
            }
            salesBillsBean.setTotalnet(totalNet);
            salesBillsBean.setTotalprofits(totalProfit);
            getWay.saveSaleBill(salesBillsBean);
            bill_code_field.setText("");
            defTableModel.setRowCount(0);
            drugsBeans.clear();
            newSalesButtonControl(true, false, false, false, false, false);
        }
        if (e.getSource() == Delivery) {
            int billCode = Integer.parseInt(bill_code_field.getText());
            DeliveryBillsBean salesBillsBean = new DeliveryBillsBean();
            java.util.Date date = new java.util.Date(new java.util.Date().getTime());
            int count = defTableModel.getRowCount();
            int totalProfit = 0;
            int totalNet = 0;

            salesBillsBean.setBillcode(billCode);
            salesBillsBean.setBilldate(date);
           
            if (drugsBeans.size() > 0) {
                salesBillsBean.setDrug1(drugsBeans.get(0).getName());
                salesBillsBean.setUnitprice1(drugsBeans.get(0).getSellingPrice());
                salesBillsBean.setQuantity1((int) defTableModel.getValueAt(0, 2));
                salesBillsBean.setTotal1((int) defTableModel.getValueAt(0, 3));
                salesBillsBean.setDiscount1((int) defTableModel.getValueAt(0, 4));
                salesBillsBean.setNet1((int) defTableModel.getValueAt(0, 5));
                 salesBillsBean.setProfit1((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
             
                totalNet += (int) defTableModel.getValueAt(0, 5);
                totalProfit += ((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
            }
            if (drugsBeans.size() > 1) {
                salesBillsBean.setDrug2(drugsBeans.get(1).getName());
                salesBillsBean.setUnitprice2(drugsBeans.get(1).getSellingPrice());
                salesBillsBean.setQuantity2((int) defTableModel.getValueAt(1, 2));
                salesBillsBean.setTotal2((int) defTableModel.getValueAt(1, 3));
                salesBillsBean.setDiscount2((int) defTableModel.getValueAt(1, 4));
                salesBillsBean.setNet2((int) defTableModel.getValueAt(1, 5));
                 salesBillsBean.setProfit2((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
             
                totalNet += (int) defTableModel.getValueAt(1, 5);
                totalProfit += ((int) defTableModel.getValueAt(1, 2) * drugsBeans.get(1).getProfit());
            }
            if (drugsBeans.size() > 2) {
                salesBillsBean.setDrug3(drugsBeans.get(2).getName());
                salesBillsBean.setUnitprice3(drugsBeans.get(2).getSellingPrice());
                salesBillsBean.setQuantity3((int) defTableModel.getValueAt(2, 2));
                salesBillsBean.setTotal3((int) defTableModel.getValueAt(2, 3));
                salesBillsBean.setDiscount3((int) defTableModel.getValueAt(2, 4));
                salesBillsBean.setNet3((int) defTableModel.getValueAt(2, 5));
                 salesBillsBean.setProfit3((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
             
                totalNet += (int) defTableModel.getValueAt(2, 5);
                totalProfit += ((int) defTableModel.getValueAt(2, 2) * drugsBeans.get(2).getProfit());
            }
            if (drugsBeans.size() > 3) {
                salesBillsBean.setDrug4(drugsBeans.get(3).getName());
                salesBillsBean.setUnitprice4(drugsBeans.get(3).getSellingPrice());
                salesBillsBean.setQuantity4((int) defTableModel.getValueAt(3, 2));
                salesBillsBean.setTotal4((int) defTableModel.getValueAt(3, 3));
                salesBillsBean.setDiscount4((int) defTableModel.getValueAt(3, 4));
                salesBillsBean.setNet4((int) defTableModel.getValueAt(3, 5));
                 salesBillsBean.setProfit4((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
             
                totalNet += (int) defTableModel.getValueAt(3, 5);
                totalProfit += ((int) defTableModel.getValueAt(3, 2) * drugsBeans.get(3).getProfit());
            }
            if (drugsBeans.size() > 4) {
                salesBillsBean.setDrug5(drugsBeans.get(4).getName());
                salesBillsBean.setUnitprice5(drugsBeans.get(4).getSellingPrice());
                salesBillsBean.setQuantity5((int) defTableModel.getValueAt(4, 2));
                salesBillsBean.setTotal5((int) defTableModel.getValueAt(4, 3));
                salesBillsBean.setDiscount5((int) defTableModel.getValueAt(4, 4));
                salesBillsBean.setNet5((int) defTableModel.getValueAt(4, 5));
                 salesBillsBean.setProfit5((int) defTableModel.getValueAt(0, 2) * drugsBeans.get(0).getProfit());
             
                totalNet += (int) defTableModel.getValueAt(4, 5);
                totalProfit += ((int) defTableModel.getValueAt(4, 2) * drugsBeans.get(4).getProfit());
            }

            salesBillsBean.setTotalnet(totalNet);
            salesBillsBean.setTotalprofits(totalProfit);
            DeliveryBillsBean deliveryBill = getWay.deliveryBill(salesBillsBean);
            if (deliveryBill!=null)
            {JOptionPane.showMessageDialog(null, "bill saved in delivery");}
            else{
               JOptionPane.showMessageDialog(null, "failed operation");
            }
            drugsBeans.clear();

            bill_code_field.setText("");
            defTableModel.setRowCount(0);
            newSalesButtonControl(true, false, false, false, false, false);
        }

        if (e.getSource() == clear) {
            defTableModel.setRowCount(0);
            newSalesButtonControl(false, true, false, false, false, true);
        }
        if (e.getSource() == cancel) {
            bill_code_field.setText("");
            defTableModel.setRowCount(0);
            newSalesButtonControl(true, false, false, false, false, false);
        }

        if (e.getSource() == addok) {
            boolean exit = false;
            text = addnamefield.getText().trim();
            unitnumber = (int) spiner.getValue();
            if (!text.equals("") && unitnumber != 0) {
               
                List<DrugsBean> l = getWay.findDrug(text);
                if (l != null) {
                    drugsBeans.add(l.get(0));
                    defTableModel.addRow(new Object[]{l.get(0).getName(), l.get(0).getSellingPrice(), unitnumber,
                        (l.get(0).getSellingPrice() * unitnumber), (l.get(0).getDiscount() * unitnumber),
                        ((l.get(0).getSellingPrice() * unitnumber) - (l.get(0).getDiscount() * unitnumber))});

                    addnamefield.setText("");
                    spiner.setValue(1);
                    //   LoginPanel.logined = false; 
                    text = null;
                    unitnumber = 0;
                    exit = true;
                    HomePage.frame.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "not found");
                }
            }

            numberDrug = defTableModel.getRowCount();
            if (numberDrug > 0 && numberDrug < 5) {
                newSalesButtonControl(false, true, true, true, true, true);
            }
            if (numberDrug >= 5) {
                newSalesButtonControl(false, false, true, true, true, true);
            }
            if (exit) {
                Drug_Dialog.dispose();
                exit = false;
            } else if (text.equals("") && unitnumber == 0) {
                JOptionPane.showMessageDialog(null, " ENTER DRUG NAME");
            } else if (text.equals("")) {
                JOptionPane.showMessageDialog(null, " ENTER DRUG NAME");
            } else if (unitnumber == 0) {
                JOptionPane.showMessageDialog(null, " ENTER DRUG QUANTITY");
            }
        }

        if (e.getSource() == addcancel) {
            Drug_Dialog.dispose();
            HomePage.frame
                    .setEnabled(true);
        }
        if (e.getSource() == ededit) {
            Edit_Delete_dialog.dispose();
            EditDialog();
        }
        if (e.getSource() == eddelete) {
            int billCode = Integer.parseInt(bill_code_field.getText());
            DeliveryBillsBean bean = new DeliveryBillsBean(billCode);

            getWay.deletedeliveryBill(bean);
            defTableModel.removeRow(selectedrow);
            Edit_Delete_dialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == edcancel) {
            Edit_Delete_dialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == editok) {
            int x = (int) editspiner.getValue();
            defTableModel.setValueAt(x, selectedrow, 2);
            defTableModel.setValueAt( ((int)defTableModel.getValueAt( selectedrow, 1)*x), selectedrow, 3);
            defTableModel.setValueAt((x*editDiscount), selectedrow, 4);
            defTableModel.setValueAt((((int)defTableModel.getValueAt( selectedrow, 1)*x)-(x*editDiscount)), selectedrow, 5);
            //  String name = (String) defTableModel.getValueAt(row, 0);
            Edit_Dialog.dispose();
            HomePage.frame.setEnabled(true);
        }
        if (e.getSource() == editcancel) {
            Edit_Dialog.dispose();
            HomePage.frame.setEnabled(true);
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == addnamefield) {
            text = null;
            //  text = addnamefield.getText().trim();
            //  unitnumber = (int) spiner.getValue();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {

        if (e.getSource() == spiner) {

            //  unitnumber = (int) spiner.getValue();
        }
    }

    public void newSalesButtonControl(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f) {
        newBill.setEnabled(a);
        AddDrug.setEnabled(b);
        Save.setEnabled(c);
        Delivery.setEnabled(d);
        clear.setEnabled(e);
        cancel.setEnabled(f);
        if (a) {
            newBill.setBackground(Color.green);
        } else {
            newBill.setBackground(Color.pink);
        };
        if (b) {
            AddDrug.setBackground(Color.green);
        } else {
            AddDrug.setBackground(Color.pink);
        };
        if (c) {
            Save.setBackground(Color.green);
        } else {
            Save.setBackground(Color.pink);
        };
        if (d) {
            Delivery.setBackground(Color.green);
        } else {
            Delivery.setBackground(Color.pink);
        };
        if (e) {
            clear.setBackground(Color.green);
        } else {
            clear.setBackground(Color.pink);
        };
        if (f) {
            cancel.setBackground(Color.green);
        } else {
            cancel.setBackground(Color.pink);
        };

    }

//    void setSalesBills(int i, String name, int d,  int net, int p, int q, int tt, int un) {
//
//        if (i == 0) {
//            salesBills.setDrug1(name);
//            salesBills.setDiscount1(d);
//            
//            salesBills.setNet1(net);
//            salesBills.setProfit1(p);
//            salesBills.setQuantity1(q);
//            salesBills.setTotal1(tt);
//            salesBills.setUnitprice1(un);
//        }
//        if (i == 1) {
//            salesBills.setDrug2(name);
//            salesBills.setDiscount2(d);;
//            
//            salesBills.setNet2(net);
//            salesBills.setProfit2(p);
//            salesBills.setQuantity2(q);
//            salesBills.setTotal2(tt);
//            salesBills.setUnitprice2(un);
//        }
//        if (i == 2) {
//            salesBills.setDrug3(name);
//            salesBills.setDiscount3(d);;
//         
//            salesBills.setNet3(net);
//            salesBills.setProfit3(p);
//            salesBills.setQuantity3(q);
//            salesBills.setTotal3(tt);
//            salesBills.setUnitprice3(un);
//        }
//        if (i == 3) {
//            salesBills.setDrug4(name);
//            salesBills.setDiscount4(d);;
//          
//            salesBills.setNet4(net);
//            salesBills.setProfit4(p);
//            salesBills.setQuantity4(q);
//            salesBills.setTotal4(tt);
//            salesBills.setUnitprice4(un);
//        }
//        if (i == 4) {
//            salesBills.setDrug5(name);
//            salesBills.setDiscount5(d);;
//          
//            salesBills.setNet5(net);
//            salesBills.setProfit5(p);
//            salesBills.setQuantity5(q);
//            salesBills.setTotal5(tt);
//            salesBills.setUnitprice5(un);
//        }
//
//    }
//    void setDliveryBills(int i, String name, int d,  int net, int p, int q, int tt, int un) {
//
//        if (i == 0) {
//            db.setDrug1(name);
//            db.setDiscount1(d);;
//         
//            db.setNet1(net);
//            db.setProfit1(p);
//            db.setQuantity1(q);
//            db.setTotal1(tt);
//            db.setUnitprice1(un);
//        }
//        if (i == 1) {
//            db.setDrug2(name);
//            db.setDiscount2(d);;
//         
//            db.setNet2(net);
//            db.setProfit2(p);
//            db.setQuantity2(q);
//            db.setTotal2(tt);
//            db.setUnitprice2(un);
//        }
//        if (i == 2) {
//            db.setDrug3(name);
//            db.setDiscount3(d);;
//            
//            db.setNet3(net);
//            db.setProfit3(p);
//            db.setQuantity3(q);
//            db.setTotal3(tt);
//            db.setUnitprice3(un);
//        }
//        if (i == 3) {
//            db.setDrug4(name);
//            db.setDiscount4(d);;
//          
//            db.setNet4(net);
//            db.setProfit4(p);
//            db.setQuantity4(q);
//            db.setTotal4(tt);
//            db.setUnitprice4(un);
//        }
//        if (i == 4) {
//            db.setDrug5(name);
//            db.setDiscount5(d);;
//            
//            db.setNet5(net);
//            db.setProfit5(p);
//            db.setQuantity5(q);
//            db.setTotal5(tt);
//            db.setUnitprice5(un);
//        }
//
//    }
}
