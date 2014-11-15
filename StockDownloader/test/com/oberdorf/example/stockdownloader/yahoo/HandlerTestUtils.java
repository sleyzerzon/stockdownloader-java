package com.oberdorf.example.stockdownloader.yahoo;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

public final class HandlerTestUtils {
	static final Attributes getAttributes(String symbol, String name) {
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("", "", "symbol", "CDATA", symbol);
		attributes.addAttribute("", "", "name", "CDATA", name);
		return attributes;
	}
}
