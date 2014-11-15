package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;

public class testPurchaseItemPanel {
	
	private StockItem item;
	
	@Before
	public void setUp() {
		item = new StockItem(null, "Leib", "Must", 5.0, 5);
	  }

	@Test
	public void testAddSoldItem(){
		PurchaseItemPanel p = new PurchaseItemPanel(null);
		p.addItemEventHandler();
	};
	
	@Test
	public void testGetSumWithNoItems(){
		assertEquals(item.getQuantity(), equals(0));
	};
	
	@Test
	public void testGetSumWithOneItem(){
		assertEquals(item.getQuantity(), equals(1));
	};
	
	@Test
	public void testGetSumWithMultipleItems(){
		assertEquals(item.getQuantity(), equals(2));
	};

}
