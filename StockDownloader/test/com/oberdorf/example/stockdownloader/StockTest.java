package com.oberdorf.example.stockdownloader;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockTest {

	@Test
	public void testRic() {
		Stock s = new Stock("AAPL", "Apple");
		assertEquals(s.getRic(), "AAPL");
		assertEquals(s.getName(), "Apple");
		s = new Stock("AAPL", null);
		assertEquals(s.getName(), "AAPL"); // default to RIC
		s = new Stock("AAPL", "");
		assertEquals(s.getName(), "AAPL"); // default to RIC
	}

	@Test
	public void testEquals() {
		Stock s1 = new Stock("AAPL", "Apple");
		Stock s2 = new Stock("AAPL", "Apple");
		Stock s3 = new Stock("GOOG", "Google");
		Stock s4 = new Stock("AAPL", "DifferentName");
		
		assertEquals(s1,s2);
		assertNotEquals(s1,s3);
		assertNotEquals(s1, s4);
	}
}
