package appDt;
import com.store.bll.delegate.StoreGetWay;
import com.store.common.beans.DrugsBean;
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
public class DiscountPanel extends JPanel implements ActionListener, ItemListener, CaretListener, ChangeListener {

    String text, type;
    int fromNumber, toNumber, dFromNumber, dToNumber, editNumber, row;
    int x = 0;
    int selectedRow;
    private JTable table;
    String[] columnNames = {"Name", "TYPE", "Price", "Discount"};
    String[] items = {"------", "ALL", "Name", "TYPE", "Price", "discount"};
    String[] typesItems = {"------", "capsule", "ointment", "injection", "pill"};
    JTable exampleJTable;
    JScrollPane sp;
    public static DefaultTableModel defTableModel = new DefaultTableModel();
    JButton search, clear, edit, cancel, editOk, editCancel;
    JTextField searchField;
    JPanel eastSearchPanel, westSearchPanel, searchPanel, namePanel, quantityPanel, typePanel, allPanel;
    JComboBox cb, typeCB;
    JDialog editDeleteDialog, addDrugDialog, editDialog;
    JSpinner toSpiner, fromSpiner, dToSpiner, dFromSpiner, editSpiner;
    JLabel unitNumber;
     StoreGetWay getWay = new StoreGetWay();
 // constructor

    public DiscountPanel() throws SQLException {
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
        defTableModel.setColumnCount(5);// set number columns
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
                    editDeleteDialogMethod();
                    HomePage.frame.setEnabled(false);
                }
            }
        });

        //--------------------------------------------------search panel---------------------------------------------------------------------------------------------------
        eastSearchPanel = new JPanel();
        eastSearchPanel.setBackground(Color.cyan);
        eastSearchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
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
        cb.setBackground(Color.yellow);
        //MenuItem all = 
        cb.addItem(items[0]);
        cb.addItem(items[1]);
        cb.addItem(items[2]);
        cb.addItem(items[3]);
        cb.addItem(items[4]);
        cb.addItem(items[5]);
        cb.setPreferredSize(new Dimension(100, 50));
        cb.addItemListener(this);
        eastSearchPanel.add(clear);
        eastSearchPanel.add(search);
        eastSearchPanel.add(cb);
        westSearchPanel = new JPanel(new CardLayout());
        westSearchPanel.setBackground(Color.cyan);
        // define serch textfield
        searchPanel = new JPanel(new GridLayout(1, 2));
        searchPanel.add(westSearchPanel);
        searchPanel.add(eastSearchPanel);
        //--------------------------------------------------------------add panel to super panel------------------------------------------------
        add(searchPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
    }
    //==============================edit deletedialog method=============================================

    public void editDeleteDialogMethod() {
        JPanel north = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        north.setBackground(Color.gray);
        edit = new JButton("edit");
        edit.setPreferredSize(new Dimension(75, 50));
        edit.addActionListener(this);
        edit.setBackground(Color.green);
        JPanel center = new JPanel(new FlowLayout(FlowLayout.LEADING, 80, 100));
        center.setBackground(Color.gray);

        cancel = new JButton("cancel");
        cancel.setPreferredSize(new Dimension(75, 50));
        cancel.setBackground(Color.green);
        cancel.addActionListener(this);

        center.add(edit);

        center.add(cancel);
        //--------------------------------------------------------------edit_delet-dialog----------------------------------------------------------------
        editDeleteDialog = new JDialog();
        editDeleteDialog.setLayout(new BorderLayout());
        editDeleteDialog.setPreferredSize(new Dimension(400, 300));
        editDeleteDialog.add(north, BorderLayout.NORTH);
        editDeleteDialog.add(center, BorderLayout.CENTER);
        editDeleteDialog.setBackground(Color.gray);
        editDeleteDialog.pack();
        editDeleteDialog.setResizable(false);
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

    public JDialog editDialog() {

        JPanel discountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
        discountPanel.setBackground(Color.gray);
        JLabel unitNumber = new JLabel("DISCOUNT");
        unitNumber.setPreferredSize(new Dimension(100, 75));
        SpinnerModel dModelEdit = new SpinnerNumberModel(0, 0, 1000, 1);
        editSpiner = new JSpinner(dModelEdit);
        editSpiner.setValue(defTableModel.getValueAt(selectedRow, 3));
        editSpiner.addChangeListener(this);
        editSpiner.setPreferredSize(new Dimension(150, 50));
        discountPanel.add(unitNumber);
        discountPanel.add(editSpiner);
        //------------------------------------------------------------------------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
        buttonPanel.setBackground(Color.gray);
        editOk = new JButton("OK");
        editOk.setBackground(Color.green);
        editOk.setPreferredSize(new Dimension(100, 75));
        editCancel = new JButton("cancel");
        editCancel.setBackground(Color.green);
        editCancel.setPreferredSize(new Dimension(100, 75));
        buttonPanel.add(editOk, BorderLayout.WEST);
        buttonPanel.add(editCancel, BorderLayout.EAST);
        editOk.addActionListener(this);
        editCancel.addActionListener(this);
        editDialog = new JDialog();
        editDialog.setBackground(Color.gray);
        editDialog.setPreferredSize(new Dimension(300, 330));
        editDialog.setLayout(new GridLayout(2, 1));
        editDialog.setPreferredSize(new Dimension(400, 300));
        editDialog.add(discountPanel);
        editDialog.add(buttonPanel);
        editDialog.pack();
        editSpiner.addChangeListener(this);
        editDialog.setVisible(true);
        editDialog.addWindowListener(
                new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                editDialog.dispose();
                HomePage.frame.setEnabled(true);
            }
        });
        return editDialog;

    }
    //---------------------------------------------------------------------------------------------------------------------------------------------

// action performed for search button
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == search) {

            //---------------------------------------------------------------------- select all---------------------------------------------------------------------------- 
            if (x == 1) {
                defTableModel.setNumRows(0);

                List<DrugsBean> l = getWay.listAllDiscount();
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }
            //----------------------------------------------------------------select by name----------------------------------------------------------------------------
            if (x == 2) {
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByNameDiscount(text);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }

            //---------------------------------------------------------------------------select by  capsule type------------------------------------------------------------------------
            if (x == 3) {
                type = "capsule";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByTypeDiscount(type);

                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }
            //----------------------------------------------------- select by ointment type ------------------------------------------------------------------------------
            if (x == 4) {
                type = "ointment";

                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByTypeDiscount(type);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }
            //--------------------------------------------------------------- select by injection type ---------------------------------------------------------------------
            if (x == 5) {
                type = "injection";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByTypeDiscount(type);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }
            // ==================================== selection by pill type ===============================
            if (x == 6) {
                type = "pill";
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByTypeDiscount(type);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }
            // ==================================== select by discount ======================================
            if (x == 7) {
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByDiscount(dFromNumber, dToNumber);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }
            //==================================== select by price ============================================
            if (x == 8) {
                defTableModel.setNumRows(0);
                List<DrugsBean> l = getWay.findByPriceDiscount(fromNumber, toNumber);
                if (l != null) {
                    for (int i = 0; i < l.size(); i++) {
                        defTableModel.addRow(new Object[]{l.get(i).getName(), l.get(i).getType(),
                            l.get(i).getSellingPrice(), l.get(i).getDiscount()});

                    }

                }

            }
        }
        //----------------------------------------------- edit button-------------------------------------------------------------------------
        if (e.getSource() == edit) {
            editDeleteDialog.dispose();
            editDialog();

        }
        if (e.getSource() == cancel) {
            editDeleteDialog.dispose();
            HomePage.frame.setEnabled(true);

        }
        if (e.getSource() == editOk) {

            int x = (int) editSpiner.getValue();
            defTableModel.setValueAt(x, row, 3);
            String name = (String) defTableModel.getValueAt(row, 0);

//            DrugsBean bean = new DrugsBean();
//            bean.setName(name);
//            bean.setDiscount(x);
            DrugsBean updateDiscount = getWay.updateDiscount(name, x);
            System.out.println(updateDiscount.getDiscount());
            HomePage.frame.setEnabled(true);

            editDialog.dispose();

        }
        if (e.getSource() == editCancel) {
            editDialog.dispose();
            HomePage.frame.setEnabled(true);
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
            if (cb.getSelectedItem().equals("TYPE")) {
                typeSelectedMethod();
            }
            if (cb.getSelectedItem().equals("discount")) {
                discountSelectedMethod();
                x = 7;
            }
            if (cb.getSelectedItem().equals("Price")) {
                priceSelectedMethod();
                x = 8;
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
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 75));
        searchField.addCaretListener(this);
        // searchfield.setEditable(false);
        searchField.setEnabled(true);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        // namePanel.setPreferredSize(new Dimension(800, 100));
        namePanel.add(searchField);
        TitledBorder border1 = new TitledBorder("DRUG NAME");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westSearchPanel.setBorder(border1);
        westSearchPanel.removeAll();
        westSearchPanel.repaint();
        westSearchPanel.revalidate();
        westSearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel discountSelectedMethod() {
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        JLabel dFrom = new JLabel("FROM");
        JLabel dTo = new JLabel("TO");
        SpinnerModel dModel = new SpinnerNumberModel(0, 0, 1000, 10);
        SpinnerModel dModels = new SpinnerNumberModel(0, 0, 1000, 10);
        dFromSpiner = new JSpinner(dModel);
        dFromSpiner.addChangeListener(this);
        dFromSpiner.setPreferredSize(new Dimension(150, 50));
        dToSpiner = new JSpinner(dModels);
        dToSpiner.addChangeListener(this);
        dToSpiner.setPreferredSize(new Dimension(150, 50));
        namePanel.add(dFrom);
        namePanel.add(dFromSpiner);
        namePanel.add(dTo);
        namePanel.add(dToSpiner);
        TitledBorder border1 = new TitledBorder("DISCOUNT DRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        westSearchPanel.setBorder(border1);
        westSearchPanel.removeAll();
        westSearchPanel.repaint();
        westSearchPanel.revalidate();
        westSearchPanel.add(namePanel);
        return namePanel;
    }

    private JPanel typeSelectedMethod() {
        typeCB = new JComboBox(typesItems);
        typeCB.addItemListener(this);
        namePanel = new JPanel();
        namePanel.setBackground(Color.cyan);
        namePanel.add(typeCB);
        TitledBorder border1 = new TitledBorder("TYPE DRUG ");
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
        JLabel label = new JLabel("ALL DRUGS SELECTED");
        allPanel.add(label);
        TitledBorder border1 = new TitledBorder("ALL DRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        allPanel.setBackground(Color.cyan);

        westSearchPanel.setBorder(border1);
        westSearchPanel.removeAll();
        westSearchPanel.repaint();
        westSearchPanel.revalidate();
        westSearchPanel.add(allPanel);
        return namePanel;
    }

    private JPanel priceSelectedMethod() {
        namePanel = new JPanel();

        JLabel from = new JLabel("FROM");
        JLabel To = new JLabel("TO");
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
        namePanel.add(To);
        namePanel.add(toSpiner);
        TitledBorder border1 = new TitledBorder("UNIT PRICEDRUG ");
        border1.setTitleJustification(TitledBorder.LEFT);
        border1.setTitlePosition(TitledBorder.TOP);
        namePanel.setBackground(Color.cyan);
        westSearchPanel.setBackground(Color.cyan);
        westSearchPanel.setBorder(border1);
        westSearchPanel.removeAll();
        westSearchPanel.repaint();
        westSearchPanel.revalidate();
        westSearchPanel.add(namePanel);

        return namePanel;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (e.getSource() == searchField) {
            text = null;
            text = searchField.getText();
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
        if (e.getSource() == dFromSpiner) {

            dFromNumber = (int) dFromSpiner.getValue();

        }
        if (e.getSource() == dToSpiner) {
            dToNumber = (int) dToSpiner.getValue();

        }
        if (e.getSource() == editSpiner) {
            editNumber = (int) editSpiner.getValue();

        }
    }

}
