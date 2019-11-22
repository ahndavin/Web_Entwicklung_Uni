package festivalmanager.festival;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class FestivalForm {
	@NotEmpty
	private String name;

	@NotEmpty
	private String location;

	@NotEmpty
	private String startDate;

	private String endDate;

	@Min(0)
	private int maxVisitors;

	private boolean sellingTickets;

	public FestivalForm() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		if(endDate.equals("")) {
			endDate = null;
		}

		this.endDate = endDate;
	}

	public int getMaxVisitors() {
		return maxVisitors;
	}

	public void setMaxVisitors(int maxVisitors) {
		this.maxVisitors = maxVisitors;
	}

	public boolean isSellingTickets() {
		return sellingTickets;
	}

	public void setSellingTickets(boolean sellingTickets) {
		this.sellingTickets = sellingTickets;
	}

	public Festival toFestival() {
		return new Festival(name, location, startDate, endDate, maxVisitors, sellingTickets);
	}
}
