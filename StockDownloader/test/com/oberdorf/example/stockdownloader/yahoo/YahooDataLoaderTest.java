package com.oberdorf.example.stockdownloader.yahoo;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.oberdorf.example.stockdownloader.DataLoaderException;
import com.oberdorf.example.stockdownloader.Stock;

public class YahooDataLoaderTest {
	@Test
	public void test() throws DataLoaderException {
		YahooStreamFetcher streamFetcher = new MockStreamFetcher();
		
		YahooDataLoader ydl = new YahooDataLoader(streamFetcher);
		
		List<Stock> stocks = ydl.loadStocks();
		assertNotNull(stocks);
		assertEquals(stocks.size(), 2);
		Map<String, Stock> stockMap = new HashMap<String, Stock>();
		for (Stock stock : stocks) {
			assertNotNull(stock);
			assertNotNull(stock.getRic());
			stockMap.put(stock.getRic(), stock);
		}
		assertTrue(stockMap.size()==2);
		assertTrue(stockMap.get("AAPL").getName().equals("Apple"));
		assertTrue(stockMap.get("GOOG").getName().equals("Google"));
	}
}

class MockStreamFetcher extends YahooStreamFetcher {
	private static String industriesStream = "<test><industry id=\"123\"></industry><industry id=\"321\"></industry></test>";
	private static String industry123 = "<test><company symbol=\"AAPL\" name=\"Apple\"></company></test>";
	private static String industry321 = "<test><company symbol=\"GOOG\" name=\"Google\"></company></test>";
	
	@Override
	InputStream getIndustriesStream() throws DataLoaderException {
		return new ByteArrayInputStream(industriesStream.getBytes());
	}

	@Override
	InputStream getIndustryStream(long id) throws DataLoaderException {
		if (id == 123) {
			return new ByteArrayInputStream(industry123.getBytes());
		} else if (id == 321) {
			return new ByteArrayInputStream(industry321.getBytes());
		} else {
			 throw new DataLoaderException("Unknown industry " + id, null);
		}
	}
	
}