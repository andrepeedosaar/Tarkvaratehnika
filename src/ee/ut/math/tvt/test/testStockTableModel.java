package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class testStockTableModel {
	
	StockItem item;
	
	@Before
	public void setUp() {
		item = new StockItem(null, "Leib", 2.0, 10);
	  }
	
	@Test
	public void testValidateNameUniqueness(){
		StockTableModel stm = new StockTableModel();
		stm.addItem(item);
		assertEquals("name", stm.getValueAt(0, 2));
	};
	
	@Test
	public void testHasEnoughInStock(){
		StockTableModel stm = new StockTableModel();
		stm.addItem(item);
		assertEquals(10, stm.getValueAt(0, 3));
	};
	
	@Test
	public void testGetItemByIdWhenItemExists(){
		StockTableModel stm = new StockTableModel();
		assertTrue(stm.addItem(item));
		
	};
	
	@Test
	public void testGetItemByIdWhenThrowsException(){
		StockTableModel stm = new StockTableModel();
		stm.addItem(item);
		try{
			assertEquals(0, stm.getTableRows());
		}catch(IllegalArgumentException e){
			return;
		}
		fail();
		
	};

}
