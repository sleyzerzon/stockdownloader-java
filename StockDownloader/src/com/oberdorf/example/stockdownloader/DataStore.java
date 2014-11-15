package com.oberdorf.example.stockdownloader;

import java.util.List;

/** A DataStore is used to save and retrieve stock information and daily
 * data efficiently.
 * 
 * Because querying various webservices would be extremely slow, we want
 * to preload our data with a DataLoader, then store it locally.  The DataStore
 * is used to do this, and various implementations are possible.  This could
 * be a MySQL database, SQLite or even flat files or serialized Java objects.
 * 
 * @author oly
 */
public interface DataStore {
	public void saveStock(Stock s);
	public void saveDailyData(List<DailyData> dailyData);
}
