package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesHistoryModel;

public class SalesHistoryModelTest {
	
	StockItem item, item2;
	SoldItem si1, si2;
	
	SalesHistoryModel shm;
	
	List<SoldItem> soldItems;
	
	SoldHistoryItem shi;
	
	@Before
	public void setUp() {
		item = new StockItem(1L, "Leib", 2.0, 10);
		item2 = new StockItem(2L, "Keefir", "Hapu", 10.0, 5);
		
		si1 = new SoldItem(item, 1);
		si2 = new SoldItem(item2, 2);
	}
	
	@Test
	public void testgetRowWithZeroHistoryItems() {
		shi = new SoldHistoryItem(getCurrentDate(), getCurrentTime(), soldItems);
		SalesHistoryModel shm = new SalesHistoryModel();
		shm.addItem(shi);
		assertEquals(shm.getRow(0), 2);
	}
	
	@Test
	public void testgetRowWithOneHistoryItems() {
		soldItems.add(si1);
		shi = new SoldHistoryItem(getCurrentDate(), getCurrentTime(), soldItems);
		SalesHistoryModel shm = new SalesHistoryModel();
		shm.addItem(shi);
		assertEquals(shm.getRow(0), 2);
	}
	
	@Test
	public void testgetRowWithTwoHistoryItems() {
		soldItems.add(si1);
		soldItems.add(si2);
		shi = new SoldHistoryItem(getCurrentDate(), getCurrentTime(), soldItems);
		SalesHistoryModel shm = new SalesHistoryModel();
		shm.addItem(shi);
		assertEquals(shm.getRow(0), 2);
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

