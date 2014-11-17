package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	StockItem item;
	
	@Before
	public void setUp() {
		item = new StockItem(null, "Leib", 2.0, 10);
	  }
	
	@Test
	public void testGetSum(){
		SoldItem s = new SoldItem(item, 1);
		
		assertEquals(s.getSum(), 2.0, 0.0001);
	};
	
	@Test
	public void testGetSumWithZeroQuantity(){
		SoldItem s = new SoldItem(item, 0);
		assertEquals(s.getSum(), 0.0, 0.0001);
	};

}
