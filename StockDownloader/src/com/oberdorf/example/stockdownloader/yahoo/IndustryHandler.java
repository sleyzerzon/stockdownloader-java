package com.oberdorf.example.stockdownloader.yahoo;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/** XML  Handler for the Yahoo Industry data, basically a list of industry ids */
final class IndustryHandler extends DefaultHandler {
	private List<Long> ids;
	
	IndustryHandler() {
		ids = new ArrayList<Long>();
	}
	
	@Override
	public void startElement(String uri, String localname, String qName,
			Attributes attrs) throws SAXException {
		super.startElement(uri, localname, qName, attrs);
		if (qName.equalsIgnoreCase("industry")) {
			String idstr = attrs.getValue("id");
			try {
				long id = Long.parseLong(idstr);
				ids.add(id);
			} catch (Throwable t) {
				// error parsing id, let's ignore
			}
		}
	}
	
	List<Long> getIds() {
		return ids;
	}
}
