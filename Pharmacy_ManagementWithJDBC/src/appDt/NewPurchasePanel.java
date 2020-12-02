
package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.PurchasesBillsBean;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 *
 * @author ahmed
 */
public class NewPurchasePanel extends JPanel implements ActionListener, CaretListener, ChangeListener, ItemListener {

    StoreGetWay getWay = new StoreGetWay();
 String name, barcode, company;
    String type = "------";
    int code, purchase, sell, discount, quantity, cbItam;
    JButton newBill, save, clear, cancel;
    Date date;
    JPanel south, north, center;
    JLabel billCode, barCodeLabel, nameLabel, typeLabel, purchasePriceLable, sellPriceLabel, discountLabel, expiryLabel, companyLabel, quantityLabel;
    JTextField billCodeField, barCodeField, nameField, companyField;
    JCalendar availFromDate;
    JSpinner purchaseSpiner, sellSpiner, quantitySpiner, discountSpiner;
    JComboBox typeCb;
    JDateChooser expiry;
    JTextFieldDateEditor editor;
    String[] items = {"------", "capsule", "ointment", "injection", "pill"};
    // constructor   

    public NewPurchasePanel() throws ParseException {
        super(new BorderLayout());  // set super panel as border layout
        south = new JPanel(new GridLayout(1, 6));
        south.setPreferredSize(new Dimension(100, 100));
        newBill = new JButton("NewBill");
        save = new JButton("Save");
        clear = new JButton("clear");
        cancel = new JButton("Cancel");
        south.add(newBill);
        south.add(save);
        south.add(clear);
        south.add(cancel);
        newBill.addActionListener(this);
        save.addActionListener(this);
        clear.addActionListener(this);
        cancel.addActionListener(this);
        newSalesButtonControl(true, false, false, false);
        //---------------------------------------------------------north panel---------------------------------------------------------------------------------
        north = new JPanel();
        north.setBackground(Color.cyan);
        billCode = new JLabel("BILL_CODE");
        billCodeField = new JTextField();
        billCodeField.setEditable(false);
        billCodeField.setBackground(Color.WHITE);
        billCodeField.addCaretListener(this);
        billCodeField.setPreferredSize(new Dimension(150, 50));
        billCodeField.addCaretListener(this);
        north.add(billCode);
        north.add(billCodeField);
        //------------------------------------------------------------------------------------------------------------------------------------------------------
        center = new JPanel();
        center.setBackground(Color.cyan);
        TitledBorder border1 = new TitledBorder("NEW DRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        center.setBorder(border1);
        barCodeLabel = new JLabel("BARCODE");
        barCodeLabel.setPreferredSize(new Dimension(75, 50));
        nameLabel = new JLabel("NAME");
        nameLabel.setPreferredSize(new Dimension(75, 50));
        typeLabel = new JLabel("TYPE");
        typeLabel.setPreferredSize(new Dimension(75, 50));
        purchasePriceLable = new JLabel("PURCH_PRICE");
        purchasePriceLable.setPreferredSize(new Dimension(75, 50));
        sellPriceLabel = new JLabel("SELL_PRICE");
        sellPriceLabel.setPreferredSize(new Dimension(75, 50));
        discountLabel = new JLabel("DISCOUNT");
        discountLabel.setPreferredSize(new Dimension(75, 50));
        quantityLabel = new JLabel("QUANTITY");
        quantityLabel.setPreferredSize(new Dimension(75, 50));
        //   expiryLabel = new JLabel("EXPIRY");
        // expiryLabel.setPreferredSize(new Dimension(75, 50));
        companyLabel = new JLabel("COMPANY");
        companyLabel.setPreferredSize(new Dimension(75, 50));
        barCodeField = new JTextField();
        barCodeField.setPreferredSize(new Dimension(150, 50));
        nameField = new JTextField();
        nameField.setPreferredSize(new Dimension(150, 50));
        nameField.addCaretListener(this);
        companyField = new JTextField();
        companyField.setPreferredSize(new Dimension(150, 50));
        companyField.addCaretListener(this);
        barCodeField = new JTextField();
        barCodeField.addCaretListener(this);
        barCodeField.setPreferredSize(new Dimension(150, 50));
        expiry = new JDateChooser();
        expiry.setDateFormatString("yyyy-MM-dd");
        expiry.setSize(new Dimension(150, 40));
        editor = (JTextFieldDateEditor) expiry.getDateEditor();
        editor.addCaretListener(this);
        editor.setText("0000-00-00");
        editor.setEditable(false);
        editor.setPreferredSize(new Dimension(150, 40));
        typeCb = new JComboBox();
        //MenuItem all = 
        typeCb.addItem(items[0]);
        typeCb.addItem(items[1]);
        typeCb.addItem(items[2]);
        typeCb.addItem(items[3]);
        typeCb.addItem(items[4]);
        typeCb.setPreferredSize(new Dimension(150, 50));
        typeCb.addItemListener(this);
        SpinnerModel purchaseModel = new SpinnerNumberModel(0, 0, 10000, 1);
        SpinnerModel sellModel = new SpinnerNumberModel(0, 0, 10000, 1);
        SpinnerModel discountModel = new SpinnerNumberModel(0, 0, 10000, 1);
        SpinnerModel quantityModel = new SpinnerNumberModel(0, 0, 10000, 1);
        purchaseSpiner = new JSpinner(purchaseModel);
        purchaseSpiner.addChangeListener(this);
        purchaseSpiner.setPreferredSize(new Dimension(150, 50));
        sellSpiner = new JSpinner(sellModel);
        sellSpiner.addChangeListener(this);
        sellSpiner.setPreferredSize(new Dimension(150, 50));
        discountSpiner = new JSpinner(discountModel);
        discountSpiner.addChangeListener(this);
        discountSpiner.setPreferredSize(new Dimension(150, 50));
        quantitySpiner = new JSpinner(quantityModel);
        quantitySpiner.addChangeListener(this);
        quantitySpiner.setPreferredSize(new Dimension(150, 50));
        // set search panel as grouplayout
        GroupLayout groupLayout = new GroupLayout(center);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        center.setLayout(groupLayout);
        // organize component in group layout   
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(barCodeLabel).addGap(50).addComponent(purchasePriceLable).addGap(50).addComponent(quantityLabel)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(barCodeField).addGap(50).addComponent(purchaseSpiner).addGap(50).addComponent(quantitySpiner)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(nameLabel).addGap(50).addComponent(sellPriceLabel))
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(nameField).addGap(50).addComponent(sellSpiner))
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(typeLabel).addGap(50).addComponent(discountLabel).addGap(50).addComponent(companyLabel)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(typeCb).addGap(50).addComponent(discountSpiner).addGap(50).addComponent(companyField)));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGap(30)
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addGap(100).addComponent(barCodeLabel).addGap(100).addComponent(barCodeField).addGap(100).addComponent(nameLabel).addGap(100).addComponent(nameField).addGap(100).addComponent(typeLabel).addGap(100).addComponent(typeCb))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addGap(100).addComponent(purchasePriceLable).addGap(100).addComponent(purchaseSpiner).addGap(100).addComponent(sellPriceLabel).addGap(100).addComponent(sellSpiner).addGap(100).addComponent(discountLabel).addGap(100).addComponent(discountSpiner))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addGap(100).addComponent(quantityLabel).addGap(100).addComponent(quantitySpiner).addGap(100).addComponent(companyLabel).addGap(100).addComponent(companyField))
        );
        add(north, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(south, BorderLayout.SOUTH);
        enableFieldControl(false);
    }// end constructor 

// actionperformed method
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newBill) {
            enableFieldControl(true);
            code = getWay.createBill();
            billCodeField.setText(Integer.toString(code));
            newSalesButtonControl(false, true, true, true);

        }
        if (e.getSource() == save) {

            try {

                // java.util.Date utilDate = new SimpleDateFormat("yyyy-mm-dd").parse((((JTextField) expiry.getDateEditor().getUiComponent()).getText()));
                if (name != null && name != "" && barcode != null && barcode != "" && cbItam > 0 && purchase > 0 && sell > 0 && discount >= 0 && quantity > 0 && company != null && company != "") {
                    int total = purchase * quantity;
                    int billCodes = (int) Integer.valueOf(billCodeField.getText());
                    java.util.Date date = new java.util.Date(new java.util.Date().getTime());
                    PurchasesBillsBean bean = new PurchasesBillsBean();
                    
                    bean.setBarCode(barcode);
                    bean.setBillCode(billCodes);
                    bean.setCompany(company);
                    bean.setDateBill(date);
                    bean.setDiscount(discount);
                    bean.setDrugName(name);
                    bean.setDrugType(type);
                    bean.setPurchasePrice(purchase);
                    bean.setQuantityDrug(quantity);
                    bean.setTotal(total);
                    bean.setSellPrice(sell);

                    PurchasesBillsBean saveBill = getWay.saveBill(bean);
                    System.out.println(saveBill);
                    if (saveBill != null) {
                        
                        JOptionPane.showMessageDialog(null, "bill saved successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "bill not saved");
                    }
                    clearControl();
                    newSalesButtonControl(true, false, false, false);
                    billCodeField.setText("");
                    enableFieldControl(false);

                    name = null;
                    barcode = null;
                    company = null;
                    cbItam = 0;
                    purchase = 0;
                    sell = 0;
                    discount = 0;
                    quantity = 0;
                } else {
                    JOptionPane.showMessageDialog(null, "bill not saved");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "bill not saved");
            }

        }
        if (e.getSource() == clear) {
            clearControl();
            newSalesButtonControl(false, true, true, true);
        }
        if (e.getSource() == cancel) {
            billCodeField.setText("");
            clearControl();
            enableFieldControl(false);
            newSalesButtonControl(true, false, false, false);
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == nameField) {
            name = nameField.getText();
        }
        if (e.getSource() == barCodeField) {
            barcode = barCodeField.getText();
        }
        if (e.getSource() == companyField) {
            company = companyField.getText();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == discountSpiner) {
            discount = (int) discountSpiner.getValue();
        }
        if (e.getSource() == quantitySpiner) {
            quantity = (int) quantitySpiner.getValue();
        }
        if (e.getSource() == sellSpiner) {
            sell = (int) sellSpiner.getValue();
        }
        if (e.getSource() == purchaseSpiner) {
            purchase = (int) purchaseSpiner.getValue();
        }
    }

    public void newSalesButtonControl(boolean a, boolean b, boolean c, boolean d) {
        newBill.setEnabled(a);
        save.setEnabled(b);
        clear.setEnabled(c);
        cancel.setEnabled(d);
        if (a) {
            newBill.setBackground(Color.green);
        } else {
            newBill.setBackground(Color.pink);
        };
        if (b) {
            save.setBackground(Color.green);
        } else {
            save.setBackground(Color.pink);
        };
        if (c) {
            clear.setBackground(Color.green);
        } else {
            clear.setBackground(Color.pink);
        };
        if (d) {
            cancel.setBackground(Color.green);
        } else {
            cancel.setBackground(Color.pink);
        };
    }

    public void clearControl() {
        nameField.setText("");
        companyField.setText("");
        barCodeField.setText("");
        sellSpiner.setValue(0);
        purchaseSpiner.setValue(0);
        discountSpiner.setValue(0);
        quantitySpiner.setValue(0);
        typeCb.setSelectedItem("------");

    }

    public void enableFieldControl(boolean a) {
        nameField.setEnabled(a);
        companyField.setEnabled(a);
        barCodeField.setEnabled(a);
        sellSpiner.setEnabled(a);
        purchaseSpiner.setEnabled(a);
        discountSpiner.setEnabled(a);
        quantitySpiner.setEnabled(a);
        typeCb.setEnabled(a);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == typeCb) {
            if (typeCb.getSelectedItem() == "------") {
                cbItam = 0;
                type = "------";
            }
            if (typeCb.getSelectedItem() == "capsule") {
                cbItam = 1;
                type = "capsule";
            }
            if (typeCb.getSelectedItem() == "ointment") {

                cbItam = 2;
                type = "ointment";
            }
            if (typeCb.getSelectedItem() == "injection") {

                cbItam = 3;
                type = "injection";
            }
            if (typeCb.getSelectedItem() == "pill") {
                cbItam = 4;
                type = "pill";
            }
        }
    }

}
