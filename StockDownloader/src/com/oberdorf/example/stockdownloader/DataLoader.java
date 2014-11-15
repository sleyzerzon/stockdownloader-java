package com.oberdorf.example.stockdownloader;

import java.util.Date;
import java.util.List;

public interface DataLoader {
	public List<Stock> loadStocks() throws DataLoaderException;
	public List<DailyData> loadDailyData(Stock stock, Date fromDate, Date toDate) throws DataLoaderException;
}
