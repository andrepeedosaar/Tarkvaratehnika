package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.awt.Window;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.SoldHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

public class ConfirmOrderPane extends JPanel {

	private static final long serialVersionUID = -8013931014111078167L;
	private JLabel orderSumLabelTxt, orderSumLabelVal, payAmountLabel,
			changeLabelTxt, changeLabelVal;
	private JTextField payAmountField;
	private JButton cancelButton, acceptButton;

	private static final Logger log = Logger.getLogger(ConfirmOrderPane.class);

	// System model model
	SalesSystemModel model;

	public ConfirmOrderPane(SalesSystemModel model) {

		// Initialize model/success
		this.model = model;

		// Initialize components
		initComp();

		setLayout(new GridBagLayout());
		GridBagConstraints gc = getConstraints();

		// Add components to the dialog
		// Add sum of order txt
		add(orderSumLabelTxt, gc);

		// Add sum of order val
		gc.gridx = 1;
		add(orderSumLabelVal, gc);

		// Add payment amount label
		gc.gridx = 0;
		gc.gridy = 1;
		add(payAmountLabel, gc);

		// Add payment amount field
		gc.gridx = 1;
		add(payAmountField, gc);

		// Add change amount txt
		gc.gridy = 2;
		gc.gridx = 0;
		add(changeLabelTxt, gc);

		// Add change amount val
		gc.gridx = 1;
		add(changeLabelVal, gc);

		// Add accept button
		gc.gridx = 0;
		gc.gridy = 3;
		add(acceptButton, gc);

		// Add cancel button
		gc.gridx = 1;
		add(cancelButton, gc);

	}

	private void initComp() {
		// Get total sum
		int sum = 0;
		for (SoldItem el : model.getCurrentPurchaseTableModel().getTableRows())
			sum += el.getSum();

		// Init labels
		orderSumLabelTxt = new JLabel("Total order sum: ");
		payAmountLabel = new JLabel("Payment amount:");
		changeLabelTxt = new JLabel("Change amount: ");

		orderSumLabelVal = new JLabel(String.valueOf(sum));
		changeLabelVal = new JLabel();

		// Init field
		payAmountField = new JTextField();

		// Init buttons
		cancelButton = new JButton("Cancel");
		acceptButton = new JButton("Accept");

		// Add listeners

		// Cancel button listener
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelSaleEventHandler();
			}
		});
		// Accept button listener
		acceptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptSaleEventHandler();
			}
		});

		payAmountField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
			}
			public void focusLost(FocusEvent e) {
				calcChange();
			}
		});
	}

	protected void cancelSaleEventHandler() {
		log.info("Sale cancelled!");
		// Close frame
		Window win = SwingUtilities.getWindowAncestor(cancelButton);
		win.setVisible(false);
	}

	protected void acceptSaleEventHandler() {
		try {
			String tmp = changeLabelVal.getText();

			if (Double.parseDouble(tmp) >= 0) {
				
				//Finalize sale - update history + db
				finalizeSale();
				
				// Close frame
				Window win = SwingUtilities.getWindowAncestor(acceptButton);
				win.setVisible(false);

			} else
				throw new NumberFormatException();
		} catch (NumberFormatException e) {
			changeLabelVal.setText("Invalid input");
		}
	}

	private void finalizeSale() {
		
		List<SoldItem> soldItems = model.getCurrentPurchaseTableModel().getTableRows();
		
		SoldHistoryItem  historyItem = new SoldHistoryItem(getCurrentDate(), getCurrentTime(), soldItems);
		
		StockItem dbItem;
		
		//Update database
		Session session = HibernateUtil.currentSession();
		session.getTransaction().begin();
		
		//Add history item
		session.persist(historyItem);
		
		for(SoldItem el : soldItems){
			//Add solditems to db
			session.persist(el);
			//Change stock quantities
			dbItem = (StockItem)session.get(StockItem.class, el.getStockItem().getId());
			dbItem.setQuantity(dbItem.getQuantity()-el.getQuantity());
		}
		session.getTransaction().commit();
		
		//Update local info
		// If sale is successful add it to history
		model.getSalesHistoryModel().addItem(historyItem);
		
		log.info("Sale complete");
		
	}

	private void calcChange() throws NumberFormatException{
		double change = -1;
		try{
			change = Double.parseDouble(payAmountField.getText())
					- Double.parseDouble(orderSumLabelVal.getText());
			if (change < 0)
				throw new NumberFormatException();
			else
				changeLabelVal.setText(String.format("%.2f", change).replace(
						",", "."));
		}catch (NumberFormatException e) {
			changeLabelVal.setText("Invalid input");
		}
	}

	// Methods for retrieving current date and time
	private String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date).toString();
	}

	private String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date).toString();
	}

	// Get layout constraints
	private GridBagConstraints getConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.2;
		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;

		return gc;
	}

}
