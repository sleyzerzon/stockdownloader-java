package com.oberdorf.example.stockdownloader;

import java.util.Date;

public class DailyData {
	private String stock;
	private Date   date;
	private double open;
	private double high;
	private double low;
	private double close;
	private double adjustedClose;
	private long volume;
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public double getAdjustedClose() {
		return adjustedClose;
	}
	public void setAdjustedClose(double adjustedClose) {
		this.adjustedClose = adjustedClose;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((stock == null) ? 0 : stock.hashCode());
		return result;
	}
	
	/** Check for equality.
	 * 
	 * This aggressively checks all fields for exact equality.  It does
	 * not allow any tolerance for float precision so it is really checking
	 * if the data are completely indistinguishable.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DailyData other = (DailyData) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (stock == null) {
			if (other.stock != null)
				return false;
		} else if (!(stock.equals(other.stock)
				&& open == other.open
				&& high == other.high
				&& low == other.low
				&& close == other.close
				&& adjustedClose == other.adjustedClose
				&& volume == other.volume)) {
			return false;
		}
		return true;
	}	
}
