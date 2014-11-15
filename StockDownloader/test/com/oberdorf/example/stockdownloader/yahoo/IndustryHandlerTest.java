package com.oberdorf.example.stockdownloader.yahoo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class IndustryHandlerTest {

	@Test
	public void test() throws SAXException {
		IndustryHandler industryHandler = new IndustryHandler();
		
		Attributes attr1 = getAttributes(123);
		Attributes attr2 = getAttributes(321);
		
		industryHandler.startElement("", "", "industry", attr1);
		industryHandler.startElement("", "", "industry", attr2);
		
		List<Long> industries = industryHandler.getIds();
		
		assertNotNull(industries);
		assertTrue(industries.size() == 2);
		assertTrue(industries.contains(new Long(123)));
		assertTrue(industries.contains(new Long(321)));
		// yes, with size==2 and the two distinct values, this next line is a bit pedantic...
		assertFalse(industries.contains(new Long(0)));
	}
	
	private Attributes getAttributes(long id) {
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "", "id", "CDATA", ""+id);
		return attributes;
	}
}
