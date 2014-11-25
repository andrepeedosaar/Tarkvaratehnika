package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {

	StockItem item;

	@Before
	public void setUp() {
		item = new StockItem(1L, "Leib", "Viljatoode", 2.0, 10);
	}

	@Test
	public void testClone() {

		StockItem item2 = (StockItem) item.clone();
		
		//Item 2 is an independant object not a reference
		assertFalse(item == item2);
		//Yet the content is the same
		assertEquals(item.toString(), item2.toString());
	};

	@Test
	public void testGetColumn() {
		// Get column 2 (which is price)
		assertEquals(2.0, item.getColumn(2));
	};

}
