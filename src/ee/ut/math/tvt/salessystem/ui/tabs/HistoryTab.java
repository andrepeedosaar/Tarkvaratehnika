package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {

	private SalesSystemModel model;

	public HistoryTab(SalesSystemModel model){
	    this.model = model;
	  }

	public Component draw() {
		JPanel panel = new JPanel();
		
		//Create new JTable
		JTable table = new JTable(model.getSalesHistoryModel());
		
		//Add table listener. Shows order details if specific row is selected
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            showOrder();
	        }
	    });

	    JTableHeader header = table.getTableHeader();
	    header.setReorderingAllowed(false);

	    JScrollPane scrollPane = new JScrollPane(table);

	    GridBagConstraints gc = new GridBagConstraints();
	    GridBagLayout gb = new GridBagLayout();
	    gc.fill = GridBagConstraints.BOTH;
	    gc.weightx = 1.0;
	    gc.weighty = 1.0;
	    
	    panel.setLayout(gb);
	    panel.add(scrollPane, gc);
		
		return panel;
	}
	private void showOrder(){
		
	}
}