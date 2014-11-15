package com.oberdorf.example.stockdownloader.yahoo;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.oberdorf.example.stockdownloader.Stock;


/** XML Handler for company information from Yahoo.
 * 
 * This handler is intended to be reused for each document with companies
 * in it.  It will aggregate all the equivalent Stock records and can then
 * return them as a full list.
 * 
 * @author Oliver Oberdorf <oly@oberdorf.org>
 */
final class CompanyHandler extends DefaultHandler {
	private List<Stock> companies;
	
	CompanyHandler() {
		companies = new ArrayList<Stock>();
	}
	
	@Override
	public void startElement(String uri, String localname, String qName,
			Attributes attrs) throws SAXException {
		super.startElement(uri, localname, qName, attrs);
		if (qName.equalsIgnoreCase("company")) {
			String symbol = attrs.getValue("symbol");
			String name = attrs.getValue("name");
			companies.add(new Stock(symbol, name));
		}
	}
	
	List<Stock> getCompanies() {
		return companies;
	}
}
