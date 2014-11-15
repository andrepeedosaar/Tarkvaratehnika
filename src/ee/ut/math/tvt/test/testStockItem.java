package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class testStockItem {
	
	StockItem item;

	@Before
	public void setUp() {
		item = new StockItem(null, "Leib", 2.0, 10);
	  }
	
	@Test
	public void testClone(){
		assertEquals(item, item.clone());
	};
	
	@Test
	public void testGetColumn(){
		assertEquals(item, item.getColumn(0));
	};
	
}
