package com.oberdorf.example.stockdownloader.yahoo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import com.oberdorf.example.stockdownloader.DataLoaderException;


/** This utility class can be overriden to aid in unit testing.
 * 
 * Provides two streams via Yahoo URLs.  One is a list of all industries in XML.  The
 * other takes a single industry ID and returns XML with all contained companies.  We
 * used to get all the companies in one shot, but this appears to no longer work.
 */
class YahooStreamFetcher {
	InputStream getIndustriesStream() throws DataLoaderException {
		try {
	        URL industries = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20industry.id%20from%20yahoo.finance.sectors&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
	        return industries.openStream();
		} catch (MalformedURLException mfe) {
			throw new DataLoaderException("Bad URL in YahooStreamFetcher fetching industries, should not happen!", mfe);
		} catch (IOException ioe) {
			throw new DataLoaderException("Unable to load industries from Yahoo", ioe);
		}
	}
	
	InputStream getIndustryStream(long id) throws DataLoaderException {
		try {
	        URL companies = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.industry%20where%20id=" + id + "&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
	        return companies.openStream();
		} catch (MalformedURLException mfe) {
			throw new DataLoaderException("Bad URL in YahooStreamFetcher fetching companies, should not happen!", mfe);
		} catch (IOException ioe) {
			throw new DataLoaderException("Unable to load companies from Yahoo", ioe);
		}
	}
}
