package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

public class SoldHistoryItem implements Cloneable {

	private String date;
	private String time;
	private double total;
	private List<SoldItem> soldItems;

	public SoldHistoryItem(String date, String time, List<SoldItem> soldItems) {
		this.date = date;
		this.time = time;
		this.soldItems = soldItems;
		total = calcTotal();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getTotal() {
		return total;
	}

	public double calcTotal() {
		double a = 0;
		for (SoldItem el : soldItems) {
			a += el.getSum();
		}
		return a;
	}

}
