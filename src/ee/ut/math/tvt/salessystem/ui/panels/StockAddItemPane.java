package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockAddItemPane extends JPanel{
	
	private SalesSystemModel model;
	
	private JLabel id;
	private JLabel name;
	private JLabel price;
	private JLabel quantity;
	
	private JTextField add_id;
	private JTextField add_name;
	private JTextField add_price;
	private JTextField add_quantity;
	
	private JButton accept;
	private JButton clear;;
	
	
	private static final Logger log = Logger.getLogger(StockAddItemPane.class);
	
	public StockAddItemPane(SalesSystemModel model) {
		this.model = model;
		
		initComp();
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = getConstraints();
		
		//Id
		add(id, gc);
		
		gc.gridx = 1;
		add(add_id,gc);
		
		//Name
		gc.gridx = 0;
		gc.gridy = 1;
		add(name, gc);

		gc.gridx = 1;
		add(add_name, gc);
		
		//Price
		gc.gridx = 0;
		gc.gridy = 2;
		add(price, gc);
		
		gc.gridx = 1;
		add(add_price, gc);
		
		//Quantity
		gc.gridx = 0;
		gc.gridy = 3;
		add(quantity, gc);
		
		gc.gridx = 1;
		add(add_quantity, gc);
		
		//Buttons
		gc.ipady = 10;
		gc.gridx = 0;
		gc.gridy = 4;
		add(accept, gc);
		
		gc.gridx = 1;
		add(clear, gc);
	}
	
	//Initalize components
	private void initComp() {
		id = new JLabel("Product ID:");
		name = new JLabel("Product name:");
		price = new JLabel("Product price:");
		quantity = new JLabel("Product quantity:");
		
		add_id = new JTextField();
		add_name = new JTextField();
		add_price = new JTextField();
		add_quantity = new JTextField();
		
		accept = new JButton("Accept");
		clear = new JButton("Clear fields");
		
		accept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acceptEventHandler();
			}
		});
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearEventHandler();
			}
		});
		
	}
	
	//Eventhandlers
	private void acceptEventHandler() {
		
		try{
			int text_id = Integer.parseInt(add_id.getText());
			String text_name = add_id.getText();
			double text_price = Double.parseDouble(add_id.getText());
			int text_quantity = Integer.parseInt(add_id.getText());
			
			try{
				StockTableModel stm = new StockTableModel();
				StockItem si = new StockItem(Long.parseLong(add_id.getText()), 
											 add_name.getText(), 
											 Double.parseDouble(add_price.getText()), 
											 Integer.parseInt(add_quantity.getText()));
				stm.addItem(si);
				
				log.info("Item added to stock");
				Window win = SwingUtilities.getWindowAncestor(accept);
		        win.setVisible(false);
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private void clearEventHandler() {
		add_id.setText("");
		add_name.setText("");
		add_price.setText("");
		add_quantity.setText("");
		log.info("Fields cleared");
		
	}
	
	// Get layout constraints
		private GridBagConstraints getConstraints() {
			GridBagConstraints gc = new GridBagConstraints();
			gc.fill = GridBagConstraints.HORIZONTAL;
			gc.anchor = GridBagConstraints.CENTER;
			gc.weightx = 0.2;
			gc.weighty = 1.0;
			gc.fill = GridBagConstraints.BOTH;
			return gc;
		}
}
