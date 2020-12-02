package appDt;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ReportsPanel extends JPanel implements ActionListener {

    JPanel buttonPanel, selectedPanel, SalesPanel;
    JButton AnalysisButton, salesBillButton, purchaseButton;
    JScrollPane sp;

    public ReportsPanel() {
        setLayout(new BorderLayout());
        buttonsPanelGui();
        salesBillButton.addActionListener(this);
        purchaseButton.addActionListener(this);
        AnalysisButton.addActionListener(this);

    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------

    private void buttonsPanelGui() {

        buttonPanel = new JPanel(new GridLayout(1, 4));
        sp = new JScrollPane(buttonPanel);
        buttonPanel.setPreferredSize(new Dimension(1000, 100));
        //buttonPanel.setMaximumSize(new Dimension(2000, 200));
        salesBillButton = new JButton("SALES-BILLS");
        salesBillButton.setBackground(Color.blue);
        purchaseButton = new JButton("PURCHASE-BILLS");
        purchaseButton.setBackground(Color.blue);
        AnalysisButton = new JButton("ANALYSIS");
        AnalysisButton.setBackground(Color.blue);
        buttonPanel.add(salesBillButton);
        buttonPanel.add(purchaseButton);
        buttonPanel.add(AnalysisButton);

        add(buttonPanel, BorderLayout.NORTH);
        selectedPanel = new JPanel(new CardLayout());
        selectedPanel.setPreferredSize(new Dimension(200, 800));

        selectedPanel.setBackground(Color.cyan);
        add(selectedPanel, BorderLayout.CENTER);
    }
    //----------------------------------------------------------------------------------------------------------------------------------------------------

//---------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == salesBillButton) {
            selectedPanel.removeAll();
            selectedPanel.repaint();
            selectedPanel.revalidate();
            reportsFrameButtonControl(true, false, false);

            try {
                selectedPanel.add(new SalesBillsPanel());
            } catch (SQLException ex) {
                Logger.getLogger(ReportsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == purchaseButton) {
            selectedPanel.removeAll();
            selectedPanel.repaint();
            selectedPanel.revalidate();
            reportsFrameButtonControl(false, true, false);
            try {
                selectedPanel.add(new PurchaseBillsPanel());
            } catch (SQLException ex) {
                Logger.getLogger(ReportsPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == AnalysisButton) {
            selectedPanel.removeAll();
            selectedPanel.repaint();
            selectedPanel.revalidate();
            selectedPanel.add(new AnalysisPanel());
            reportsFrameButtonControl(false, false, true);
        }
    }

    public void reportsFrameButtonControl(boolean a, boolean b, boolean c) {
        if (a) {
            salesBillButton.setEnabled(!a);
            purchaseButton.setEnabled(a);
            AnalysisButton.setEnabled(a);
            salesBillButton.setBackground(Color.green);
            purchaseButton.setBackground(Color.blue);
            AnalysisButton.setBackground(Color.blue);
        }
        if (b) {
            salesBillButton.setEnabled(b);
            purchaseButton.setEnabled(!b);
            AnalysisButton.setEnabled(b);
            salesBillButton.setBackground(Color.blue);
            purchaseButton.setBackground(Color.green);
            AnalysisButton.setBackground(Color.blue);

        }
        if (c) {
            salesBillButton.setEnabled(c);
            purchaseButton.setEnabled(c);
            AnalysisButton.setEnabled(!c);

            salesBillButton.setBackground(Color.blue);
            purchaseButton.setBackground(Color.blue);
            AnalysisButton.setBackground(Color.green);

        }

    }

}
