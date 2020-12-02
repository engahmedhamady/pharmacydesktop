package appDt;

import com.store.bll.delegate.StoreGetWay;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.TRAILING;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AnalysisPanel extends JPanel implements ActionListener {

      StoreGetWay getWay = new StoreGetWay();
    JTextField paymentField, incomeField, profitField;
    JLabel paymentLabel, incomeLabel, profitLabel, fromDateLabel, toDateLabel;
    JButton ok, clear;
    JDateChooser fromDateChooser, toDateChooser;
    JTextFieldDateEditor editor1, editor2;
    Date fromDate, toDate;
    GroupLayout groupLayout = new GroupLayout(this);

    public AnalysisPanel() {
        super.setLayout(groupLayout);
        setBackground(Color.cyan);
        paymentLabel = new JLabel("PAYMENT");
        paymentLabel.setPreferredSize(new Dimension(75, 50));
        incomeLabel = new JLabel("INCOME");
        incomeLabel.setPreferredSize(new Dimension(75, 50));
        profitLabel = new JLabel("PROFIT");
        paymentLabel.setPreferredSize(new Dimension(75, 50));
        fromDateLabel = new JLabel("FROM");
        fromDateLabel.setPreferredSize(new Dimension(75, 50));
        toDateLabel = new JLabel("TO");
        toDateLabel.setPreferredSize(new Dimension(75, 50));
        paymentField = new JTextField();
        paymentField.setPreferredSize(new Dimension(100, 50));
        incomeField = new JTextField();
        incomeField.setPreferredSize(new Dimension(100, 50));
        profitField = new JTextField();
        profitField.setPreferredSize(new Dimension(100, 50));
        ok = new JButton("OK");
        ok.addActionListener(this);
        ok.setPreferredSize(new Dimension(100, 75));
        clear = new JButton("CLEAR");
        clear.addActionListener(this);
        clear.setPreferredSize(new Dimension(100, 75));
        fromDateChooser = new JDateChooser();
        fromDateChooser.setDateFormatString("yyyy-MM-dd");
        fromDateChooser.setSize(new Dimension(75, 50));
        toDateChooser = new JDateChooser();
        toDateChooser.setDateFormatString("yyyy-MM-dd");
        toDateChooser.setSize(new Dimension(100, 50));
        editor1 = (JTextFieldDateEditor) fromDateChooser.getDateEditor();
        editor1.setText("0000-00-00");
        editor1.setPreferredSize(new Dimension(150, 40));
        editor1.setEditable(false);
        editor2 = (JTextFieldDateEditor) toDateChooser.getDateEditor();
        editor2.setText("0000-00-00");
        editor2.setPreferredSize(new Dimension(150, 40));
        editor2.setEditable(false);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
        // organize component in group layout   
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(fromDateLabel).addGap(50).addComponent(paymentLabel).addGap(50).addComponent(profitLabel)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(fromDateChooser).addGap(50).addComponent(paymentField).addGap(50).addComponent(profitField)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(toDateLabel).addGap(50).addComponent(incomeLabel).addGap(50).addComponent(ok)).addGap(50)
                .addGroup(groupLayout.createParallelGroup(TRAILING).addComponent(toDateChooser).addGap(100).addComponent(incomeField).addGap(100).addComponent(clear)).addGap(50));

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup().addGap(30)
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addGap(100).addComponent(fromDateLabel).addGap(100).addComponent(fromDateChooser).addGap(300).addComponent(toDateLabel).addGap(100).addComponent(toDateChooser).addGap(100))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addGap(100).addComponent(paymentLabel).addGap(100).addComponent(paymentField).addGap(100).addComponent(incomeLabel).addGap(100).addComponent(incomeField).addGap(100))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addGap(100).addComponent(profitLabel).addGap(100).addComponent(profitField).addGap(100).addComponent(ok).addGap(100).addComponent(clear).addGap(100)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clear) {
            paymentField.setText("");
            incomeField.setText("");
            profitField.setText("");
            editor1.setText("0000-00-00");
            editor2.setText("0000-00-00");
        }
        if (e.getSource() == ok) {

            try {
                int totalProfit = 0;
                int totalPayment = 0;
                int totalIncome = 0;
                fromDate = new SimpleDateFormat("yyyy-mm-dd").parse((((JTextField) fromDateChooser.getDateEditor().getUiComponent()).getText()));
                System.out.println(fromDate);
                toDate = new SimpleDateFormat("yyyy-mm-dd").parse((((JTextField) toDateChooser.getDateEditor().getUiComponent()).getText()));
                totalPayment = getWay.payment(fromDate, toDate);
                totalProfit = getWay.profits(fromDate, toDate);
                totalIncome = getWay.incom(fromDate, toDate);
                paymentField.setText(String.valueOf(totalPayment));
                incomeField.setText(String.valueOf(totalIncome));
                profitField.setText(String.valueOf(totalProfit));
            } catch (ParseException ex) {
                Logger.getLogger(AnalysisPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
