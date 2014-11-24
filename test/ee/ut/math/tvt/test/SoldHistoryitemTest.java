package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldHistoryitemTest {
	
	StockItem item, item2;
	SoldItem si1, si2;
	
	SoldHistoryItem shi;
	
	List<SoldItem> soldItems = new ArrayList();
	
	@Before
	public void setUp() {
		item = new StockItem(1L, "Leib", 2.0, 10);
		item2 = new StockItem(2L, "Sai", 3.0, 10);
		
		si1 = new SoldItem(item, 1);
		si2 = new SoldItem(item2, 2);
	  }
	
	@Test
	public void testcalcTotalWithZeroSoldItems(){
		SoldHistoryItem shi = new SoldHistoryItem(getCurrentDate(), getCurrentTime(), soldItems);
		assertEquals(shi.calcTotal(), 0, 0.0001);
	}
	
	@Test
	public void testcalcTotalWithOneSoldItems(){
		soldItems.add(si1);
		SoldHistoryItem shi = new SoldHistoryItem(getCurrentDate(), getCurrentTime(), soldItems);
		assertEquals(shi.calcTotal(), 2.0, 0.0001);
	}

	@Test
	public void testcalcTotalWithMoreThanOneSoldItem(){
		soldItems.add(si1);
		soldItems.add(si2);
		SoldHistoryItem shi = new SoldHistoryItem(getCurrentDate(), getCurrentTime(), soldItems);
		assertEquals(shi.calcTotal(), 8.0, 0.0001);
	}
	
	private String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(date).toString();
	}

	private String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date).toString();
	}
	
}
