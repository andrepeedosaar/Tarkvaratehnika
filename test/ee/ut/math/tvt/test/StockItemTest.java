package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	
	StockItem item;

	@Before
	public void setUp() {
		item = new StockItem(1L, "Leib", "Viljatoode",2.0, 10);
	  }
	
	@Test
	public void testClone(){
		//How to test it?
		
		// Object item2 = item.clone();
		// ei tööta assertEquals(item2, item);
		
		
		
		assertNotSame(item, item.clone());
//		assertEquals(item.toString(), item.clone().toString());
	};
	
	@Test
	public void testGetColumn(){
		//Get column 2 (which is price)
		assertEquals(2.0, item.getColumn(2));
	};
	
}
