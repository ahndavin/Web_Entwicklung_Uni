package festivalmanager.festival;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Festival {
	private @Id @GeneratedValue long id;
	private final int maxVisitors;
	private int currentVisitors;

	private boolean sellingTickets;

	private final List<String> plan = new ArrayList<>();

	private String location;
	private String date;

	public Festival(String location, String date, int maxVisitors, boolean sellingTickets) {
		this.location = location;
		this.date = date;

		this.maxVisitors = maxVisitors;

		this.sellingTickets = sellingTickets;
	}

	public String getLocation() {
		return location;
	}

	public String getDate() {
		return date;
	}

	public int getMaxVisitors() {
		return maxVisitors;
	}

	public int getVisitorCount() {
		return currentVisitors;
	}

	public Iterable<String> getPlan() {
		return plan;
	}

	public void editPlan(int index, String newValue) {
		if(plan.size() >= index) {
			plan.set(index, newValue);
		}
	}
}
