package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;

public class ConfirmOrderPane extends JPanel{
	
	private JLabel orderSumLabelTxt, orderSumLabelVal, payAmountLabel, changeLabelTxt, changeLabelVal;
	private JTextField payAmountField;
	private JButton cancelButton, acceptButton;
	
	public ConfirmOrderPane(){
		initComp();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = getConstraints();
		
		//Add components to the dialog
		//Add sum of order txt
		add(orderSumLabelTxt,gc);
		
		//Add sum of order val
		gc.gridx=1;
		add(orderSumLabelVal,gc);
		
		//Add payment amount label
		gc.gridx=0;
		gc.gridy=1;
		add(payAmountLabel,gc);
		
		//Add payment amount field
		gc.gridx = 1;
		add(payAmountField, gc);
		
		//Add change amount txt
		gc.gridy=2;
		gc.gridx=0;
		add(changeLabelTxt, gc);
		
		//Add change amount val
		gc.gridx=1;
		add(changeLabelVal, gc);
		
		//Add accept button
		gc.gridx=0;
		gc.gridy=3;
		add(acceptButton, gc);
		
		//Add cancel button
		gc.gridx=1;
		add(cancelButton, gc);
		
		
	}
	private void initComp(){
		//Init labels
		orderSumLabelTxt = new JLabel("Total order sum: ");
		payAmountLabel = new JLabel("Payment amount:");
		changeLabelTxt = new JLabel("Change amount: ");
		
		orderSumLabelVal = new JLabel("SOME VALUE");
		changeLabelVal = new JLabel("SOME VALUE");
		
		//Init field
		payAmountField = new JTextField();
		
		//Init buttons
		cancelButton = new JButton("Cancel");
		acceptButton = new JButton("Accept");
	}
	
	private GridBagConstraints getConstraints(){
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.anchor = GridBagConstraints.CENTER;
        gc.weightx = 0.2;
        gc.weighty = 1.0;
        gc.fill = GridBagConstraints.BOTH;
		
		return gc;
	}
	
}
