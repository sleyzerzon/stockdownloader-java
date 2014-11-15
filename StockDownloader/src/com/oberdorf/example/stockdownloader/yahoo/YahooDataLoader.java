package com.oberdorf.example.stockdownloader.yahoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.oberdorf.example.stockdownloader.DailyData;
import com.oberdorf.example.stockdownloader.DataLoader;
import com.oberdorf.example.stockdownloader.DataLoaderException;
import com.oberdorf.example.stockdownloader.Stock;

public final class YahooDataLoader implements DataLoader {
	private YahooStreamFetcher streamFetcher;
	
	public YahooDataLoader() {
		streamFetcher = new YahooStreamFetcher();
	}
	
	/** ONLY for JUnit testing.
	 * 
	 * For normal use, use the no-arg constructor which will give you
	 * a default Yahoo data fetcher.
	 * 
	 * @param streamFetcher
	 */
	public YahooDataLoader(YahooStreamFetcher streamFetcher) {
		this.streamFetcher = streamFetcher;
	}
	
	@Override
	public List<Stock> loadStocks() throws DataLoaderException {
		try {
	        SAXParserFactory factory = SAXParserFactory.newInstance();
	        SAXParser parser = factory.newSAXParser();
	        
	        IndustryHandler industryHandler = new IndustryHandler();
	        parser.parse(streamFetcher.getIndustriesStream(), industryHandler);
	
	        // same handler used for all industry searches, will aggregate all companies together into one list
	        CompanyHandler companyHandler = new CompanyHandler();
	        for (long id : industryHandler.getIds()) {
	        	parser = factory.newSAXParser();
	        	parser.parse(streamFetcher.getIndustryStream(id), companyHandler);
	        }
	        return companyHandler.getCompanies();
		} catch (SAXException saxe) {
			throw new DataLoaderException("XML Error reading company information from Yahoo", saxe);
		} catch (ParserConfigurationException pce) {
			throw new DataLoaderException("Parser Configuration error reading stocks from Yahoo, very unusual", pce);
		} catch (IOException ioe) {
			throw new DataLoaderException("Error reading company information from Yahoo web api", ioe);
		}
	}
	
	@Override
	public List<DailyData> loadDailyData(Stock stock, Date fromDate, Date toDate) {
		return null;
	}
}
