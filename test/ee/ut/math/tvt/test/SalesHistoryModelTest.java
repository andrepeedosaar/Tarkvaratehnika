package ee.ut.math.tvt.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldHistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesHistoryModel;

public class SalesHistoryModelTest {
	
	SalesHistoryModel shm;
	
	List<SoldItem> soldItems = new ArrayList<SoldItem>();
	
	SoldHistoryItem shi;
	
	@Test
	public void testAddHistoryItem() {
		SalesHistoryModel shm = new SalesHistoryModel();
		shi = new SoldHistoryItem("Date1", "Time1", soldItems);
		try{
			shm.addItem(shi);
		}catch(Exception e){
			fail();
		}
	}
	
	@Test
	public void testGetRowWithZeroHistoryItems() {
		SalesHistoryModel shm = new SalesHistoryModel();
		try{
			assertEquals(shm.getRow(0), "");
		}catch(IndexOutOfBoundsException e){
			return;
		}
		fail();
	}
	
	@Test
	public void tesGetRowWithOneHistoryItem() {
		shi = new SoldHistoryItem("Date1", "Time1", soldItems);
		SalesHistoryModel shm = new SalesHistoryModel();
		shm.addItem(shi);
		assertEquals(shm.getRow(0), shi);
	}
		
}

