package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.log4j.Logger;

import java.awt.Window;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class ConfirmOrderPane extends JPanel {

	private static final long serialVersionUID = -8013931014111078167L;
	private JLabel orderSumLabelTxt, orderSumLabelVal, payAmountLabel,
			changeLabelTxt, changeLabelVal;
	private JTextField payAmountField;
	private JButton cancelButton, acceptButton;
	
	private static final Logger log = Logger.getLogger(ConfirmOrderPane.class);

	// Warehouse model
	private SalesSystemModel model;

	public ConfirmOrderPane(SalesSystemModel model) {

		// Initialize model
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
		//Get total sum
		int sum = 0;
		for (SoldItem el : model.getCurrentPurchaseTableModel().getTableRows())
			sum+=el.getSum();
		
		
		// Init labels
		orderSumLabelTxt = new JLabel("Total order sum: ");
		payAmountLabel = new JLabel("Payment amount:");
		changeLabelTxt = new JLabel("Change amount: ");
		
		orderSumLabelVal =new JLabel(String.valueOf(sum));
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
	}

	protected void cancelSaleEventHandler() {
		log.info("Sale cancelled!");
		//Close frame
		Window win = SwingUtilities.getWindowAncestor(cancelButton);
        win.setVisible(false);
	}

	protected void acceptSaleEventHandler() {
		log.info("Sale complete");
		//Close frame
		Window win = SwingUtilities.getWindowAncestor(acceptButton);
        win.setVisible(false);
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
