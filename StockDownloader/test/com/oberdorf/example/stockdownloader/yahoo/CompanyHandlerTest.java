package com.oberdorf.example.stockdownloader.yahoo;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.oberdorf.example.stockdownloader.Stock;

public class CompanyHandlerTest {
	@Test
	public void test() throws SAXException {
		CompanyHandler handler = new CompanyHandler();

		handler.startElement("", "", "company", getAttributes("AAPL", "Apple"));
		handler.startElement("", "", "company", getAttributes("GOOG", "Google"));
		
		List<Stock> stocks = handler.getCompanies();
		Map<String, Stock> stocksMap = new HashMap<String, Stock>();
		for (Stock stock : stocks) {
			stocksMap.put(stock.getRic(), stock);
		}
		assertTrue(stocksMap.size()==2);
		assertEquals(stocksMap.get("AAPL").getName(), "Apple");
		assertEquals(stocksMap.get("GOOG").getName(), "Google");
	}
	
	private Attributes getAttributes(String symbol, String name) {
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "", "symbol", "CDATA", symbol);
		attributes.addAttribute("", "", "name", "CDATA", name);
		return attributes;
	}
}
