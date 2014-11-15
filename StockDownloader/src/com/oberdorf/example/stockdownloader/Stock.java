package com.oberdorf.example.stockdownloader;

import java.io.Serializable;

/** General information about a stock.
 * 
 * This holds time-insensitive information about a stock.  For now,
 * it only holds the RIC code, but it could hold other ids, current P/E
 * and other stats.  We will use it instead of just a RIC throughout the
 * code base so everything can benefit from future additions without much
 * refactoring.
 * 
 * @author Oliver Oberdorf <oly@oberdorf.org>
 *
 */
public final class Stock implements Serializable {
	private static final long serialVersionUID = 7657523698316306493L;

	private String ric;
	private String name;
	
	public Stock() {
		this.ric = "";
		this.name = "";
	}
	
	public Stock(String ric, String name) {
		if (ric == null) {
			throw new IllegalArgumentException("Unexpected null RIC code for Stock");
		}
		this.ric = ric;
		this.name = name;
		if (name == null || name.isEmpty()) {
			this.name = ric;
		}
	}
	
	public String getRic() {
		return ric;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ric == null) ? 0 : ric.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (ric == null) {
			if (other.ric != null)
				return false;
		} else if (!ric.equals(other.ric)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)){
			return false;
		}
		return true;
	}
}
