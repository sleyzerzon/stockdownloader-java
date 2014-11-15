package com.oberdorf.example.apps;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.oberdorf.example.stockdownloader.DataLoaderException;
import com.oberdorf.example.stockdownloader.Stock;
import com.oberdorf.example.stockdownloader.yahoo.YahooDataLoader;

public class CompanyLister {
	public static void main(String[] args) throws DataLoaderException, IOException {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		Writer writer = null;
		// Yes, I am aware of log4j.  This is a console app; keeping this example simple.
		System.out.println("Loading stocks from Yahoo - this can take a long time to complete...");
		if (args.length>0) {
			System.out.println("Writing list of stocks to file: " + args[0]);
			fos = new FileOutputStream(args[0]);
			bos = new BufferedOutputStream(fos);
			writer = new OutputStreamWriter(bos);
		}
		YahooDataLoader ydl = new YahooDataLoader();
		List<Stock> stocks = ydl.loadStocks();
		Collections.sort(stocks, new Comparator<Stock>() {
			@Override
			public int compare(Stock a, Stock b) {
				return a.getRic().compareTo(b.getRic());
			}
		});
		for (Stock stock : stocks) {
			if (writer != null) {
				writer.write(String.format("%s,\"%s\"%s", stock.getRic(), stock.getName(), System.lineSeparator()));
			} else {
				System.out.println(String.format("%s: %s", stock.getRic(), stock.getName()));
			}
		}
		if (writer != null) {
			try {
				writer.flush();
				writer.close();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		if (bos != null) {
			try {
				bos.flush();
				bos.close();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.flush();
				fos.close();
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		System.out.println("done.");
	}
}
