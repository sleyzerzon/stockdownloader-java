package com.oberdorf.example.stockdownloader;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

/** Unit test for the DailyData class.
 * 
 * This mainly checks all the accessors to see that they
 * work as expected.  We also check hashcode and equals
 * to validate - hashcode should only consider date and stock,
 * which are composite key in practice.  Equals checks all the
 * data to test full value equality.
 * 
 * @author oly
 *
 */
public class DailyDataTest {
	@Test
	public void testAccessors() {
		DailyData dd = new DailyData();
		Date date = new Date();
		
		double open = 143;
		double high = 154;
		double low = 121;
		double close = 133;
		double adjustedClose = 266;
		long volume = 512000;
		
		dd = createDailyData("TEST", date, open, high, low, close, adjustedClose, volume);
		
		assertEquals(dd.getStock(), "TEST");
		assertEquals(dd.getOpen(), open, 0.0);
		assertEquals(dd.getHigh(), high, 0.0);
		assertEquals(dd.getLow(), low, 0.0);
		assertEquals(dd.getClose(), close, 0.0);
		assertEquals(dd.getAdjustedClose(), adjustedClose, 0.0);
		assertEquals(dd.getVolume(), volume, 0.0);
	}
	
	@Test
	public void testHashCode() {
		Date date = new Date();
		Date date2 = (Date)date.clone();
		date2.setTime(date2.getTime() - 100000);
		
		double open = 143;
		double high = 154;
		double low = 121;
		double close = 133;
		double adjustedClose = 266;
		long volume = 512000;
		
		DailyData dd = new DailyData();
		DailyData ddsame = new DailyData();
		DailyData dddiff = new DailyData();
		
		dd = createDailyData("TEST", date, open, high, low, close, adjustedClose, volume);
		
		// test matching, and also with all non-hashed properties modified
		ddsame  = createDailyData("TEST", date, open, high, low, close, adjustedClose, volume);
		assertEquals(dd.hashCode(), ddsame.hashCode());
		ddsame  = createDailyData("TEST", date, open+1, high+1, low+1, close+1, adjustedClose+1, volume+200000);
		assertEquals(dd.hashCode(), ddsame.hashCode());
		
		// dddiff we will change the symbol and date, which are expected inputs for hashCode()
		// in theory we could get unlucky, like 1 in a billion chance.  So we do not use random
		// values for symbol/date, but fixed values that do generate a different hash code.
		dddiff = createDailyData("TEST2", date, open, high, low, close, adjustedClose, volume);
		assertNotEquals(dd.hashCode(), dddiff.hashCode());
		dddiff = createDailyData("TEST", date2, open, high, low, close, adjustedClose, volume);
		assertNotEquals(dd.hashCode(), dddiff.hashCode());
	}
	
	@Test
	public void testEquals() {
		Date date = new Date();
		Date date2 = (Date)date.clone();
		date2.setTime(date2.getTime() - 100000);
		
		double open = 143;
		double high = 154;
		double low = 121;
		double close = 133;
		double adjustedClose = 266;
		long volume = 512000;
		
		DailyData dd = new DailyData();
		DailyData ddsame = new DailyData();
		DailyData dddiff = new DailyData();
		
		dd = createDailyData("TEST", date, open, high, low, close, adjustedClose, volume);
		
		// test matching, and also with all non-hashed properties modified
		ddsame  = createDailyData("TEST", date, open, high, low, close, adjustedClose, volume);
		assertTrue(dd.equals(ddsame));
		// modify slowly and check for differences
		dddiff = createDailyData("TEST2", date, open, high, low, close, adjustedClose, volume);
		assertFalse(dd.equals(dddiff));
		dddiff = createDailyData("TEST", date2, open, high, low, close, adjustedClose, volume);
		assertFalse(dd.equals(dddiff));
		dddiff = createDailyData("TEST", date, open+1, high, low, close, adjustedClose, volume);
		assertFalse(dd.equals(dddiff));
		dddiff = createDailyData("TEST", date, open, high+1, low, close, adjustedClose, volume);
		assertFalse(dd.equals(dddiff));
		dddiff = createDailyData("TEST", date, open, high, low+1, close, adjustedClose, volume);
		assertFalse(dd.equals(dddiff));
		dddiff = createDailyData("TEST", date, open, high, low, close+1, adjustedClose, volume);
		assertFalse(dd.equals(dddiff));
		dddiff = createDailyData("TEST", date, open, high, low, close, adjustedClose+1, volume);
		assertFalse(dd.equals(dddiff));
		dddiff = createDailyData("TEST", date, open, high, low, close, adjustedClose, volume+500000);
		assertFalse(dd.equals(dddiff));
		
		ddsame  = createDailyData("TEST", date, open+1, high+1, low+1, close+1, adjustedClose+1, volume+200000);
		assertEquals(dd.hashCode(), ddsame.hashCode());
		
		// dddiff we will change the symbol and date, which are expected inputs for hashCode()
		// in theory we could get unlucky, like 1 in a billion chance.  So we do not use random
		// values for symbol/date, but fixed values that do generate a different hash code.
		dddiff = createDailyData("TEST2", date, open, high, low, close, adjustedClose, volume);
		assertNotEquals(dd.hashCode(), dddiff.hashCode());
		dddiff = createDailyData("TEST", date2, open, high, low, close, adjustedClose, volume);
		assertNotEquals(dd.hashCode(), dddiff.hashCode());
	}
	
	/** utility factory method to generate a daily data */
	private DailyData createDailyData(String stock, Date date, double open, double high, double low, 
			double close, double adjustedClose, long volume) {
		DailyData dd = new DailyData();
		
		dd.setStock(stock);
		dd.setDate(date);
		dd.setOpen(open);
		dd.setHigh(high);
		dd.setLow(low);
		dd.setClose(close);
		dd.setAdjustedClose(adjustedClose);
		dd.setVolume(volume);
		
		return dd;
	}
}
